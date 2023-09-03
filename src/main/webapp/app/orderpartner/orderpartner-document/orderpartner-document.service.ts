import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IOrderpartnerDocument, OrderpartnerDocument } from './orderpartner-document.model';
import { SERVER_API_URL } from 'app/app.constants';
import { IMasterSearch } from 'app/shared/model/master-search.model';

export type EntityResponseType = HttpResponse<IOrderpartnerDocument>;
export type EntityArrayResponseType = HttpResponse<IOrderpartnerDocument[]>;

@Injectable({ providedIn: 'root' })
export class OrderpartnerDocumentService {
  public resourceUrl = SERVER_API_URL + 'api/vieworderpartners';
  public resourceUrlDocument = SERVER_API_URL + 'api/orderpartner-uploads';

  create(orderpartnerDocument: IOrderpartnerDocument, documentType: string, file: File[]): Observable<EntityResponseType> {
    const formData: FormData = new FormData();
    const files: Array<File> = file;
    if (files !== undefined) {
      for (let i = 0; i < files.length; i++) {
        formData.append('file', files[i]);
      }
      formData.append('customersuppliertype', orderpartnerDocument.customersuppliertype);
      formData.append('customersuppliercode', orderpartnerDocument.customersuppliercode);
      formData.append('documentType', documentType);
    }
    return this.http.post<IOrderpartnerDocument>(this.resourceUrlDocument, formData, { observe: 'response' });
  }

  constructor(protected http: HttpClient) {}

  queryFilter(search: IMasterSearch): Observable<EntityArrayResponseType> {
    return this.http.post<IOrderpartnerDocument[]>(this.resourceUrl + '-filter', search, { observe: 'response' });
  }

  save(orderpartnerDocument: IOrderpartnerDocument): Observable<EntityResponseType> {
    return this.http.post<IOrderpartnerDocument>(`${this.resourceUrl + '-document-save'}`, orderpartnerDocument, {
      observe: 'response'
    });
  }

  find(customersuppliertype: string, customersuppliercode: string): Observable<EntityResponseType> {
    return this.http.get<IOrderpartnerDocument>(`${this.resourceUrl + '-document'}/${customersuppliertype}/${customersuppliercode}`, {
      observe: 'response'
    });
  }

  download(id: number): any {
    return this.http.get(`${this.resourceUrlDocument}-download/${id}`, { headers: new HttpHeaders({}), responseType: 'blob' });
  }

  downloadXlsx(customersuppliertype?: string): Observable<Blob> {
    return this.http.get(`${this.resourceUrl + '-exports'}/${customersuppliertype}`, {
      headers: new HttpHeaders({}),
      responseType: 'blob'
    });
  }

  upload(file: File[]): Observable<EntityResponseType> {
    const files: Array<File> = file;
    const formData: FormData = new FormData();
    for (let i = 0; i < files.length; i++) {
      formData.append('file', files[i]);
    }
    return this.http.post<OrderpartnerDocument>(this.resourceUrl + '-excel', formData, { observe: 'response' });
  }
}
