import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment-dev';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class VerifyService {

  private apiUrl = `${environment.apiUrl}/auth/checkUnauthorized`;

  constructor(private http: HttpClient) {}
  
  checkUserService(): Observable<string>{
    return this.http.get(this.apiUrl, { responseType: 'text' })
  }
}
