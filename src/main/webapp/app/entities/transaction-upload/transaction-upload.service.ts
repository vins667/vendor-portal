import { ITransactionUpload } from 'app/entities/transaction-upload/transaction-upload.model';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { SERVER_API_URL } from 'app/app.constants';
import { Injectable } from '@angular/core';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { createRequestOption } from 'app/shared/util/request-util';
import { IVcutStylePlanUpload } from 'app/shared/model/vcut-style-plan-upload.model';
import { TransactionUploadSearch } from 'app/entities/transaction-upload/transaction-upload-search';
type EntityResponseType = HttpResponse<ITransactionUpload>;
type EntityArrayResponseType = HttpResponse<ITransactionUpload[]>;

@Injectable({ providedIn: 'root' })
export class TransactionUploadService {
  public resourceUrl = SERVER_API_URL + 'api/transaction-uploads';

  constructor(protected http: HttpClient) {}

  create(transactionUpload: ITransactionUpload): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(transactionUpload);
    return this.http
      .post<ITransactionUpload>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(transactionUpload: ITransactionUpload): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(transactionUpload);
    return this.http
      .put<ITransactionUpload>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ITransactionUpload>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ITransactionUpload[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(transactionUpload: ITransactionUpload): ITransactionUpload {
    const copy: IVcutStylePlanUpload = Object.assign({}, transactionUpload, {
      transactionPostedDate:
        transactionUpload.transactionPostedDate != null && transactionUpload.transactionPostedDate.isValid()
          ? transactionUpload.transactionPostedDate.format(DATE_FORMAT)
          : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.transactionPostedDate = res.body.transactionPostedDate != null ? moment(res.body.transactionPostedDate) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((transactionUpload: ITransactionUpload) => {
        transactionUpload.transactionPostedDate =
          transactionUpload.transactionPostedDate != null ? moment(transactionUpload.transactionPostedDate) : null;
      });
    }
    return res;
  }

  upload(file: File[]): Observable<EntityResponseType> {
    const files: Array<File> = file;
    const formData: FormData = new FormData();
    for (let i = 0; i < files.length; i++) {
      formData.append('file', files[i]);
    }
    return this.http
      .post<ITransactionUpload>(this.resourceUrl + '-excel', formData, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  queryCustom(req?: TransactionUploadSearch): Observable<EntityArrayResponseType> {
    return this.http
      .post<ITransactionUpload[]>(this.resourceUrl + '-qry', req, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }
}
