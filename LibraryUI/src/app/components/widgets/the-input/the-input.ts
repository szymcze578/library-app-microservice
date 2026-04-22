import {
  Component,
  Input,
  forwardRef,
  signal,
  computed,
  OnDestroy,
} from '@angular/core';
import {
  ControlValueAccessor,
  NG_VALUE_ACCESSOR,
  NG_VALIDATORS,
  Validator,
  AbstractControl,
  ValidationErrors,
  ReactiveFormsModule,
} from '@angular/forms';

export type InputType    = 'text' | 'email' | 'password' | 'tel' | 'url' | 'search';
export type InputVariant = 'default' | 'filled' | 'ghost';
export type InputSize    = 'sm' | 'md' | 'lg';

// ─── Tailwind class maps ───────────────────────────────────────────────────────

const SIZE_HEIGHT:  Record<InputSize, string> = { sm: 'h-8',  md: 'h-10', lg: 'h-12' };
const SIZE_TEXT:    Record<InputSize, string> = { sm: 'text-xs', md: 'text-sm', lg: 'text-base' };
const SIZE_PADDING: Record<InputSize, string> = { sm: 'px-2.5', md: 'px-3', lg: 'px-4' };

const STRENGTH_BAR: Record<string, string> = {
  Weak:   'bg-rose-400',
  Fair:   'bg-amber-400',
  Good:   'bg-sky-400',
  Strong: 'bg-emerald-500',
};
const STRENGTH_TEXT: Record<string, string> = {
  Weak:   'text-rose-500',
  Fair:   'text-amber-500',
  Good:   'text-sky-500',
  Strong: 'text-emerald-600',
};

@Component({
  selector: 'app-the-input',
  standalone: true,
  imports: [ReactiveFormsModule],
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: forwardRef(() => TheInputComponent),
      multi: true,
    },
    {
      provide: NG_VALIDATORS,
      useExisting: forwardRef(() => TheInputComponent),
      multi: true,
    },
  ],
  template: `
    <!-- Wrapper -->
    <div class="flex flex-col gap-1.5 w-full">

      <!-- Label -->
      @if (label) {
        <label
          [for]="inputId"
          class="text-sm font-medium transition-colors leading-tight"
          [class]="labelClasses()"
        >
          {{ label }}
          @if (required) {
            <span class="text-rose-500 ml-0.5">*</span>
          }
        </label>
      }

      <!-- Input container -->
      <div
        class="relative flex items-center w-full overflow-hidden transition-all duration-150"
        [class]="containerClasses()"
      >
        <!-- Prefix icon -->
        @if (prefixIcon) {
          <span
            class="flex-shrink-0 flex items-center pl-3 text-zinc-400 [&_svg]:w-4 [&_svg]:h-4"
            [innerHTML]="prefixIcon"
          ></span>
        }

        <!-- Native input -->
        <input
          [id]="inputId"
          class="flex-1 h-full bg-transparent border-none outline-none placeholder-zinc-400 disabled:cursor-not-allowed disabled:text-zinc-400"
          [class]="inputFieldClasses()"
          [type]="currentType()"
          [placeholder]="placeholder"
          [disabled]="disabled()"
          [readonly]="readonly"
          [autocomplete]="autocomplete"
          [attr.maxlength]="maxLength || null"
          [attr.minlength]="minLength || null"
          [attr.aria-label]="ariaLabel || label"
          [attr.aria-invalid]="hasError()"
          [attr.aria-describedby]="hasError() ? errorId : hintId"
          [value]="value()"
          (input)="onInput($event)"
          (blur)="onBlur()"
          (focus)="onFocus()"
          (keydown.enter)="onEnterKey()"
        />

        <!-- Character count -->
        @if (maxLength && showCharCount) {
          <span
            class="flex-shrink-0 text-xs pr-3 transition-colors"
            [class]="charCountWarn() ? 'text-amber-500' : 'text-zinc-400'"
          >
            {{ value().length }}/{{ maxLength }}
          </span>
        }

        <!-- Password toggle -->
        @if (type === 'password') {
          <button
            type="button"
            class="flex-shrink-0 flex items-center justify-center w-9 h-full text-zinc-400
                   hover:text-zinc-600 focus-visible:outline-none focus-visible:ring-2
                   focus-visible:ring-indigo-400 focus-visible:ring-offset-1
                   transition-colors rounded-sm"
            (click)="togglePasswordVisibility()"
            [attr.aria-label]="showPassword() ? 'Hide password' : 'Show password'"
            tabindex="-1"
          >
            @if (!showPassword()) {
              <!-- Eye open -->
              <svg class="w-4 h-4" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"
                   fill="none" stroke="currentColor" stroke-width="2"
                   stroke-linecap="round" stroke-linejoin="round">
                <path d="M1 12s4-8 11-8 11 8 11 8-4 8-11 8-11-8-11-8z"/>
                <circle cx="12" cy="12" r="3"/>
              </svg>
            } @else {
              <!-- Eye closed -->
              <svg class="w-4 h-4" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"
                   fill="none" stroke="currentColor" stroke-width="2"
                   stroke-linecap="round" stroke-linejoin="round">
                <path d="M17.94 17.94A10.07 10.07 0 0 1 12 20c-7 0-11-8-11-8a18.45 18.45 0 0 1 5.06-5.94M9.9 4.24A9.12 9.12 0 0 1 12 4c7 0 11 8 11 8a18.5 18.5 0 0 1-2.16 3.19m-6.72-1.07a3 3 0 1 1-4.24-4.24"/>
                <line x1="1" y1="1" x2="23" y2="23"/>
              </svg>
            }
          </button>
        }

        <!-- Suffix icon (non-password) -->
        @if (suffixIcon && type !== 'password') {
          <span
            class="flex-shrink-0 flex items-center pr-3 text-zinc-400 [&_svg]:w-4 [&_svg]:h-4"
            [innerHTML]="suffixIcon"
          ></span>
        }

        <!-- Clear button -->
        @if (clearable && value() && !disabled()) {
          <button
            type="button"
            class="flex-shrink-0 flex items-center justify-center w-9 h-full text-zinc-400
                   hover:text-rose-500 transition-colors animate-[fadeIn_.14s_ease]
                   focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-indigo-400"
            (click)="clearValue()"
            aria-label="Clear input"
            tabindex="-1"
          >
            <svg class="w-3.5 h-3.5" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"
                 fill="none" stroke="currentColor" stroke-width="2.5" stroke-linecap="round">
              <line x1="18" y1="6" x2="6" y2="18"/>
              <line x1="6" y1="6" x2="18" y2="18"/>
            </svg>
          </button>
        }

        <!-- Animated focus bottom line (default & filled variants only) -->
        @if (variant !== 'ghost') {
          <span
            class="absolute bottom-0 left-0 h-0.5 transition-all duration-300 rounded-b"
            [class]="focusLineClasses()"
          ></span>
        }
      </div>

      <!-- Password strength meter -->
      @if (type === 'password' && showStrength && value().length > 0) {
        <div class="flex items-center gap-2 animate-[slideDown_.16s_ease]">
          <div class="flex gap-1 flex-1">
            @for (i of [0, 1, 2, 3]; track i) {
              <span
                class="flex-1 h-1 rounded-full transition-all duration-300"
                [class]="passwordStrength() > i ? STRENGTH_BAR[strengthLabel()] : 'bg-zinc-200'"
              ></span>
            }
          </div>
          <span
            class="text-xs font-medium w-12 text-right flex-shrink-0 transition-colors"
            [class]="STRENGTH_TEXT[strengthLabel()]"
          >
            {{ strengthLabel() }}
          </span>
        </div>
      }

      <!-- Hint text -->
      @if (hint && !hasError()) {
        <p [id]="hintId" class="text-xs text-zinc-400 px-0.5">{{ hint }}</p>
      }

      <!-- Error messages -->
      @if (hasError()) {
        <div [id]="errorId" role="alert" aria-live="polite"
             class="flex flex-col gap-0.5 animate-[slideDown_.12s_ease]">
          @for (err of computedErrors(); track err) {
            <p class="flex items-center gap-1.5 text-xs text-rose-600 m-0">
              <svg class="w-3 h-3 flex-shrink-0" xmlns="http://www.w3.org/2000/svg"
                   viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"
                   stroke-linecap="round" stroke-linejoin="round">
                <circle cx="12" cy="12" r="10"/>
                <line x1="12" y1="8" x2="12" y2="12"/>
                <line x1="12" y1="16" x2="12.01" y2="16"/>
              </svg>
              {{ err }}
            </p>
          }
        </div>
      }

    </div>
  `,
  styles: [`
    @keyframes fadeIn   { from { opacity: 0; transform: scale(.7) } to { opacity: 1; transform: scale(1) } }
    @keyframes slideDown { from { opacity: 0; transform: translateY(-4px) } to { opacity: 1; transform: translateY(0) } }
    /* Remove browser autofill background */
    input:-webkit-autofill { -webkit-box-shadow: 0 0 0 1000px white inset; }
  `],
})
export class TheInputComponent implements ControlValueAccessor, Validator, OnDestroy {

  // ─── Public API ───────────────────────────────────────────────────────────
  @Input() label        = '';
  @Input() placeholder  = '';
  @Input() type: InputType    = 'text';
  @Input() variant: InputVariant = 'default';
  @Input() size: InputSize    = 'md';
  @Input() hint         = '';
  @Input() prefixIcon   = '';
  @Input() suffixIcon   = '';
  @Input() autocomplete = 'off';
  @Input() readonly     = false;
  @Input() clearable    = false;
  @Input() showCharCount = false;
  @Input() showStrength  = true;
  @Input() required      = false;
  @Input() maxLength: number | null = null;
  @Input() minLength: number | null = null;
  @Input() ariaLabel    = '';
  @Input() customErrors: Record<string, string> = {};

  // ─── Expose maps to template ──────────────────────────────────────────────
  readonly STRENGTH_BAR  = STRENGTH_BAR;
  readonly STRENGTH_TEXT = STRENGTH_TEXT;

  // ─── Internal signals ──────────────────────────────────────────────────────
  readonly value       = signal('');
  readonly isFocused   = signal(false);
  readonly isTouched   = signal(false);
  readonly showPassword = signal(false);
  readonly disabled    = signal(false);

  private control: AbstractControl | null = null;
  private static counter = 0;

  readonly inputId = `the-input-${++TheInputComponent.counter}`;
  readonly errorId = `${this.inputId}-error`;
  readonly hintId  = `${this.inputId}-hint`;

  // ─── CVA ──────────────────────────────────────────────────────────────────
  private onChange: (v: string) => void = () => {};
  private onTouched: () => void = () => {};

  ngOnDestroy(): void {}

  writeValue(val: string): void           { this.value.set(val ?? ''); }
  registerOnChange(fn: (v: string) => void): void { this.onChange = fn; }
  registerOnTouched(fn: () => void): void  { this.onTouched = fn; }
  setDisabledState(d: boolean): void       { this.disabled.set(d); }

  // ─── Validator ────────────────────────────────────────────────────────────
  validate(control: AbstractControl): ValidationErrors | null {
    this.control = control;
    return null;
  }

  // ─── Events ───────────────────────────────────────────────────────────────
  onInput(e: Event): void {
    const v = (e.target as HTMLInputElement).value;
    this.value.set(v);
    this.onChange(v);
  }
  onBlur():  void { this.isFocused.set(false); this.isTouched.set(true); this.onTouched(); }
  onFocus(): void { this.isFocused.set(true); }
  onEnterKey(): void {}
  togglePasswordVisibility(): void { this.showPassword.update(v => !v); }
  clearValue(): void { this.value.set(''); this.onChange(''); }

  // ─── Computed class strings ───────────────────────────────────────────────

  readonly currentType = computed(() =>
    this.type === 'password' && this.showPassword() ? 'text' : this.type
  );

  readonly hasError = computed(() => this.isTouched() && !!this.control?.errors);

  readonly computedErrors = computed((): string[] => {
    if (!this.control?.errors) return [];
    return Object.entries(this.control.errors).map(([key, val]) =>
      this.customErrors[key] ?? this.defaultErrorMessage(key, val)
    );
  });

  readonly charCountWarn = computed(() =>
    this.maxLength ? this.value().length >= this.maxLength * 0.85 : false
  );

  readonly labelClasses = computed(() => {
    if (this.hasError())   return 'text-rose-600';
    if (this.isFocused())  return 'text-indigo-600';
    return 'text-zinc-600';
  });

  readonly containerClasses = computed(() => {
    const h   = SIZE_HEIGHT[this.size];
    const txt = SIZE_TEXT[this.size];

    // Base border/bg per variant
    let base = '';
    if (this.variant === 'default') {
      base = 'bg-white border-[1.5px] rounded-lg';
    } else if (this.variant === 'filled') {
      base = 'rounded-lg border-[1.5px]';
    } else {
      // ghost — bottom border only, no radius
      base = 'border-b-[1.5px] border-x-0 border-t-0 rounded-none bg-transparent';
    }

    // State colours
    let state = '';
    if (this.disabled()) {
      state = 'opacity-60 cursor-not-allowed bg-zinc-50 border-zinc-200';
    } else if (this.hasError()) {
      state = this.variant === 'filled'
        ? 'bg-rose-50 border-rose-500 shadow-[0_0_0_3px_rgba(244,63,94,.15)]'
        : 'border-rose-500 shadow-[0_0_0_3px_rgba(244,63,94,.15)]';
    } else if (this.isFocused()) {
      state = this.variant === 'filled'
        ? 'bg-white border-indigo-500 shadow-[0_0_0_3px_rgba(99,102,241,.15)]'
        : 'border-indigo-500 shadow-[0_0_0_3px_rgba(99,102,241,.15)]';
    } else {
      state = this.variant === 'filled'
        ? 'bg-zinc-100 border-transparent'
        : 'border-zinc-300';
    }

    return [h, txt, base, state].join(' ');
  });

  readonly inputFieldClasses = computed(() => {
    const px = this.prefixIcon ? 'pl-1.5' : SIZE_PADDING[this.size];
    return `${px} ${SIZE_TEXT[this.size]} text-zinc-900`;
  });

  readonly focusLineClasses = computed(() => {
    const color = this.hasError() ? 'bg-rose-500' : 'bg-indigo-500';
    const width = (this.isFocused() || this.hasError()) ? 'w-full' : 'w-0';
    return `${color} ${width}`;
  });

  // ─── Password strength ────────────────────────────────────────────────────
  readonly passwordStrength = computed(() => {
    const v = this.value();
    if (!v) return 0;
    let s = 0;
    if (v.length >= 8)           s++;
    if (/[A-Z]/.test(v))         s++;
    if (/[0-9]/.test(v))         s++;
    if (/[^A-Za-z0-9]/.test(v)) s++;
    return s;
  });

  readonly strengthLabel = computed(() => {
    const s = this.passwordStrength();
    if (s <= 1) return 'Weak';
    if (s === 2) return 'Fair';
    if (s === 3) return 'Good';
    return 'Strong';
  });

  // ─── Error messages ───────────────────────────────────────────────────────
  private defaultErrorMessage(key: string, value: unknown): string {
    const map: Record<string, (v: unknown) => string> = {
      required:         ()      => 'This field is required.',
      email:            ()      => 'Please enter a valid email address.',
      minlength:        (v: any) => `Minimum ${v.requiredLength} characters required.`,
      maxlength:        (v: any) => `Maximum ${v.requiredLength} characters allowed.`,
      min:              (v: any) => `Minimum value is ${v.min}.`,
      max:              (v: any) => `Maximum value is ${v.max}.`,
      pattern:          ()      => 'Invalid format.',
      passwordMismatch: ()      => 'Passwords do not match.',
    };
    return map[key]?.(value) ?? `Validation error: ${key}`;
  }
}