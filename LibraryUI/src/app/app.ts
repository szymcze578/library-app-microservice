import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Verify } from './components/verify/verify';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet,Verify],
  template: `
    <h1>Hello, {{ title() }}</h1>
      <app-verify/>
    <router-outlet />
  `,
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('LibraryUI');
}
