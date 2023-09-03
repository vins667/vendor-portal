import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SERVER_API_URL } from 'app/app.constants';
import { IMessageBean } from 'app/shared/model/message-bean.model';

@Injectable({ providedIn: 'root' })
export class PasswordService {
  constructor(private http: HttpClient) {}

  save(newPassword: string, currentPassword: string): Observable<HttpResponse<IMessageBean>> {
    const password = { currentPassword: `${currentPassword}`, newPassword: `${newPassword}` };
    return this.http.post<IMessageBean>(SERVER_API_URL + 'api/account/change-password', password, { observe: 'response' });
  }
}
