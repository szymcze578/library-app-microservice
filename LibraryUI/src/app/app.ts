import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { TheHeader } from './components/the-header/the-header';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, TheHeader],
  template: `

    <router-outlet />
  `,
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('LibraryUI');
}
