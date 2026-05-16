import { Component } from '@angular/core';
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-register-form',
  imports: [ReactiveFormsModule, RouterLink],
  templateUrl: './register-form.html',
  styleUrl: './register-form.css',
})
export class RegisterForm {
  readonly heroImage = 'https://www.figma.com/api/mcp/asset/d2d10bef-3e26-493f-b0b0-f4cc6d1d3d4c';
  readonly googleIcon = 'https://www.figma.com/api/mcp/asset/f76ec32b-6e4f-4eb4-bc0e-fba94b7d3b53';
  readonly appleIcon = 'https://www.figma.com/api/mcp/asset/ab16f6a9-8b98-4f8f-9f12-5e5c4e79b88f';

  form: FormGroup;

  constructor(private fb: FormBuilder) {
    this.form = this.fb.group({
      name: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(8)]],
      agreeTerms: [false, Validators.requiredTrue],
    });
  }

  onSubmit() {
    if (this.form.valid) {
      console.log(this.form.value);
    }
  }
}
