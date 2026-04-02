import { Component, signal } from '@angular/core';
import { VerifyService } from '../../services/verifyService';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-verify',
  imports: [CommonModule],
  templateUrl: './verify.html',
  styleUrl: './verify.css',
})
export class Verify {

  result = signal<string | null>(null);
  error = signal<string | null>(null);
  loading = signal(false);

  constructor(private verifyService: VerifyService) {}

  checkUser(): void {
    this.verifyService.checkUserService().subscribe({
      next: (response) => {
        this.loading.set(false);

        if (response && response.trim().length > 0) {
          this.result.set(response);
        } else {
          this.result.set('Empty response from server');
        }
      },
      error: () => {
        this.loading.set(false);
        this.error.set('Request failed');
      }
    });
  }
}
