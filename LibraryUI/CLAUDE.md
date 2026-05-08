# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Commands

```bash
npm start          # dev server at http://localhost:4200
npm run build      # production build → dist/
npm test           # run unit tests (Vitest)
npm run watch      # build in watch mode (development)
```

Angular CLI is also available directly via `npx ng <command>` (e.g., `npx ng generate component ...`).

## Architecture

Angular 21 standalone-component app with Tailwind CSS v4 (PostCSS-based, no config file — utility classes only).

**Backend**: Spring Boot microservice gateway at `http://localhost:8080/api/v1` (configured in `src/environments/environment-dev.ts`). There is only one environment file; no `environment.prod.ts` yet.

**Component structure** (`src/app/components/`):
- `forms/register-form/` — registration form (currently renders raw HTML inputs, not yet wired to `TheInputComponent`)
- `verify/` — dev/debug component that calls `GET /auth/checkUnauthorized` and displays the response
- `widgets/the-input/` — fully-featured reusable input component (see below)

**Services** (`src/app/services/`):
- `verifyService.ts` — `VerifyService` hits `/auth/checkUnauthorized`; add new HTTP services here following the same pattern

**Routing**: `app.routes.ts` is empty; the root component renders `<app-register-form>` directly. Router outlet is present but unused.

**`TheInputComponent`** (`widgets/the-input/`) is the primary form primitive. Key API:
- Inputs: `type` (`text|email|password|tel|url|search`), `variant` (`default|filled|ghost`), `size` (`sm|md|lg`), `label`, `placeholder`, `hint`, `prefixIcon`/`suffixIcon` (raw SVG HTML string), `clearable`, `showCharCount`, `showStrength`, `required`, `maxLength`, `minLength`, `customErrors`
- Implements `ControlValueAccessor` + `Validator` — use with Angular reactive forms via `formControlName`/`[formControl]`
- Password type auto-shows strength meter; toggle visibility button is built in
- All styling is Tailwind computed signals — no component-level CSS classes needed from outside

## Conventions

- All new components must be **standalone** (`standalone: true` is the default in Angular 21)
- Use Angular **signals** (`signal()`, `computed()`) for local state; avoid `BehaviorSubject` for component-level reactivity
- Inject services via constructor injection (not `inject()`) to stay consistent with existing code
- Tailwind classes are composed in `computed()` signals rather than template ternaries when logic is complex — follow the pattern in `TheInputComponent`
- Prettier is configured in `package.json`: 100-char print width, single quotes, angular HTML parser
