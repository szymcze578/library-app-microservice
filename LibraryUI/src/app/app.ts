import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Verify } from './components/verify/verify';
import { RegisterForm } from './components/forms/register-form/register-form';
import { TheHeader } from './components/the-header/the-header';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, RegisterForm, TheHeader],
  template: `
  <app-the-header>
      <app-register-form/>
    <router-outlet />
  `,
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('LibraryUI');
}
