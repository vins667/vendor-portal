import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { IPaymentRequestForm } from 'app/paymentrequest/payment-request-form/payment-request-form.model';
import { SERVER_API_URL } from 'app/app.constants';
import { Observable } from 'rxjs';
import { createRequestOption } from 'app/shared/util/request-util';
import { IMasterSearch } from 'app/shared/model/master-search.model';
import { map } from 'rxjs/operators';
import * as moment from 'moment';
import { IDateBean } from 'app/shared/model/date-bean.model';
import { IOutstandingBean } from 'app/shared/db2/model/outstanding-bean.model';
import { IPaymentRequestFormDetails } from './payment-request-form-details.model';
import { IChequeBean } from 'app/paymentrequest/payment-request-finance/cheque-bean.model';

type EntityResponseType = HttpResponse<IPaymentRequestForm>;
type EntityArrayResponseType = HttpResponse<IPaymentRequestForm[]>;

@Injectable({ providedIn: 'root' })
export class PaymentRequestFinanceService {
  public resourceUrl = SERVER_API_URL + 'api/payment-request-finance';
  public resourceUrlDetails = SERVER_API_URL + 'api/payment-request-form-details';
  public resourceUrlSupplier = SERVER_API_URL + 'api/vieworderpartners/';
  public resourceUrlPaymentmethod = SERVER_API_URL + 'api/paymentmethods/';
  public resourceUrlPurchaseorder = SERVER_API_URL + 'api/db2-purchaseorders/';
  public resourceUrlCurrentDate = SERVER_API_URL + 'api/direct-current-date';
  public resourceUrlOutstanding = SERVER_API_URL + 'api/fetch-supplier-outstanding';
  public resourceUrlCheque = SERVER_API_URL + 'api/fin-document-by-cheque-number';

  constructor(private http: HttpClient) {}

  create(paymentRequestForm: IPaymentRequestForm): Observable<EntityResponseType> {
    return this.http.post<IPaymentRequestForm>(this.resourceUrl, paymentRequestForm, { observe: 'response' });
  }

  createDetails(paymentRequestFormDetail: IPaymentRequestFormDetails): Observable<HttpResponse<IPaymentRequestFormDetails>> {
    return this.http.post<IPaymentRequestFormDetails>(this.resourceUrlDetails, paymentRequestFormDetail, { observe: 'response' });
  }

  update(paymentRequestForm: IPaymentRequestForm): Observable<EntityResponseType> {
    return this.http.put<IPaymentRequestForm>(this.resourceUrl, paymentRequestForm, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IPaymentRequestForm>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }
  currentDate(): Observable<HttpResponse<IDateBean>> {
    return this.http.get<IDateBean>(`${this.resourceUrlCurrentDate}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IPaymentRequestForm[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  fetchOutstanding(search?: IOutstandingBean): Observable<HttpResponse<IOutstandingBean>> {
    return this.http.post<IOutstandingBean>(this.resourceUrlOutstanding, search, { observe: 'response' });
  }

  queryFilter(search: IMasterSearch): Observable<EntityArrayResponseType> {
    return this.http
      .post<IPaymentRequestForm[]>(this.resourceUrl + '-filter', search, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  fetchFinDoc(search: IChequeBean): Observable<HttpResponse<IChequeBean>> {
    return this.http.post<IChequeBean>(this.resourceUrlCheque, search, { observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((paymentRequestForm: IPaymentRequestForm) => {
        paymentRequestForm.createdDate = paymentRequestForm.createdDate != null ? moment(paymentRequestForm.createdDate) : null;
      });
    }
    return res;
  }

  upload(file): Observable<EntityResponseType> {
    // const files: Array<File> = file;
    const formData: FormData = new FormData();
    // for (let i = 0; i < files.length; i++) {
    //  formData.append('file', files[i]);
    //  }
    //  const formData = new FormData();
    formData.append('file', file, file.name);
    return this.http
      .post<IPaymentRequestForm>(this.resourceUrl, formData, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }
  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.requestDate = res.body.requestDate != null ? moment(res.body.requestDate) : null;
      res.body.poDate = res.body.poDate != null ? moment(res.body.poDate) : null;
      res.body.piDate = res.body.piDate != null ? moment(res.body.piDate) : null;
      res.body.invoiceDate = res.body.invoiceDate != null ? moment(res.body.invoiceDate) : null;
      res.body.utrDate = res.body.utrDate != null ? moment(res.body.utrDate) : null;
      res.body.createdDate = res.body.createdDate != null ? moment(res.body.createdDate) : null;
    }
    return res;
  }
}
