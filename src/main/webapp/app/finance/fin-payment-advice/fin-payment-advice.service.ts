import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { IViewDifindocumentpaymentadvice } from './view-difindocumentpaymentadvice.model';
import { IPaymentadviceSearch } from './paymentadvice-search.model';
import { MasterSearch } from 'app/shared/model/master-search.model';

type EntityResponseType = HttpResponse<IViewDifindocumentpaymentadvice>;
type EntityArrayResponseType = HttpResponse<IViewDifindocumentpaymentadvice[]>;

@Injectable({ providedIn: 'root' })
export class FinPaymentAdviceService {
  public resourceUrl = SERVER_API_URL + 'api/viewdifindocumentpaymentadvice';

  constructor(protected http: HttpClient) {}

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IViewDifindocumentpaymentadvice>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  queryFilter(search: IPaymentadviceSearch): Observable<EntityArrayResponseType> {
    return this.http
      .post<IViewDifindocumentpaymentadvice[]>(this.resourceUrl + '-filter', search, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  downloadFilter(search: IPaymentadviceSearch): any {
    return this.http.post(this.resourceUrl + '-report', search, { headers: new HttpHeaders({}), responseType: 'blob' });
  }

  downloadPDF(companycode: string, businessunitcode: string, finyearcode: string, code: string): Observable<Blob> {
    const search = new MasterSearch();
    search.reportType = 'PDF';
    search.companyCode = companycode;
    search.businessunitcode = businessunitcode;
    search.finyearcode = finyearcode;
    search.code = code;
    return this.http.post(this.resourceUrl + '-download', search, { responseType: 'blob' });
  }

  download(): any {
    return this.http.get(`${this.resourceUrl}-sample`, { headers: new HttpHeaders({}), responseType: 'blob' });
  }

  upload(file: File[]): Observable<any> {
    const files: Array<File> = file;
    const formData: FormData = new FormData();
    for (let i = 0; i < files.length; i++) {
      formData.append('file', files[i]);
    }
    return this.http.post<any>(this.resourceUrl + '-upload', formData, { observe: 'response' });
  }

  save(viewDifindocumentpaymentadvices: IViewDifindocumentpaymentadvice[]): Observable<EntityArrayResponseType> {
    return this.http.post<IViewDifindocumentpaymentadvice[]>(`${this.resourceUrl + '-save'}`, viewDifindocumentpaymentadvices, {
      observe: 'response'
    });
  }

  resend(viewDifindocumentpaymentadvices: IViewDifindocumentpaymentadvice[]): Observable<EntityArrayResponseType> {
    return this.http.post<IViewDifindocumentpaymentadvice[]>(`${this.resourceUrl + '-resend'}`, viewDifindocumentpaymentadvices, {
      observe: 'response'
    });
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.postingdate = res.body.postingdate ? moment(res.body.postingdate) : undefined;
      res.body.utrdate = res.body.utrdate ? moment(res.body.utrdate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((finPaymentAdvice: IViewDifindocumentpaymentadvice) => {
        finPaymentAdvice.postingdate = finPaymentAdvice.postingdate ? moment(finPaymentAdvice.postingdate) : undefined;
        finPaymentAdvice.utrdate = finPaymentAdvice.utrdate ? moment(finPaymentAdvice.utrdate) : undefined;
      });
    }
    return res;
  }
}
