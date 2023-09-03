import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';

@Injectable({ providedIn: 'root' })
export class BankReconciliationUploadService {
  public resourceUrl = SERVER_API_URL + 'api/bank-reconciliation-uploads';

  constructor(protected http: HttpClient) {}

  download(): any {
    return this.http.get(`${this.resourceUrl}-download`, { headers: new HttpHeaders({}), responseType: 'blob' });
  }

  upload(file: File[]): Observable<any> {
    const files: Array<File> = file;
    const formData: FormData = new FormData();
    for (let i = 0; i < files.length; i++) {
      formData.append('file', files[i]);
    }
    return this.http.post<any>(this.resourceUrl, formData, { observe: 'response' });
  }
}
