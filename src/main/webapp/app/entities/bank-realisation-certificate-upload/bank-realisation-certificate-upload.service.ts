import { HttpClient, HttpResponse } from '@angular/common/http';
import { IBankRealisationCertificateUpload } from 'app/entities/bank-realisation-certificate-upload/bank-realisation-certificate-upload.model';
import { Injectable } from '@angular/core';
import { IVcutStylePlanUpload } from 'app/shared/model/vcut-style-plan-upload.model';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import * as moment from 'moment';
import { BankRealizationCertificateUploadSearch } from 'app/entities/bank-realisation-certificate-upload/bank-realization-certificate-upload-search.model';

type EntityResponseType = HttpResponse<IBankRealisationCertificateUpload>;
type EntityArrayResponseType = HttpResponse<IBankRealisationCertificateUpload[]>;
@Injectable({ providedIn: 'root' })
export class BankRealisationCertificateUploadService {
  public resourceUrl = SERVER_API_URL + 'api/bank-realisation-certificate-uploads';
  constructor(protected http: HttpClient) {}

  create(bankRealisationCertificateUpload: IBankRealisationCertificateUpload): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(bankRealisationCertificateUpload);
    return this.http
      .post<IBankRealisationCertificateUpload>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }
  update(bankRealisationCertificateUpload: IBankRealisationCertificateUpload): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(bankRealisationCertificateUpload);
    return this.http
      .put<IBankRealisationCertificateUpload>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IBankRealisationCertificateUpload>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }
  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IBankRealisationCertificateUpload[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }
  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
  protected convertDateFromClient(bankRealisationCertificateUpload: IBankRealisationCertificateUpload): IVcutStylePlanUpload {
    const copy: IVcutStylePlanUpload = Object.assign({}, bankRealisationCertificateUpload, {
      planDate:
        bankRealisationCertificateUpload.brcDate != null && bankRealisationCertificateUpload.brcDate.isValid()
          ? bankRealisationCertificateUpload.brcDate.format(DATE_FORMAT)
          : null,
      createdDate:
        bankRealisationCertificateUpload.realisationDate != null && bankRealisationCertificateUpload.realisationDate.isValid()
          ? bankRealisationCertificateUpload.realisationDate.toJSON()
          : null
    });
    return copy;
  }
  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.brcDate = res.body.brcDate != null ? moment(res.body.brcDate) : null;
      res.body.realisationDate = res.body.realisationDate != null ? moment(res.body.realisationDate) : null;
    }
    return res;
  }
  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((bankRealisationCertificateUpload: IBankRealisationCertificateUpload) => {
        bankRealisationCertificateUpload.brcDate =
          bankRealisationCertificateUpload.brcDate != null ? moment(bankRealisationCertificateUpload.brcDate) : null;
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
      .post<IVcutStylePlanUpload>(this.resourceUrl + '-excel', formData, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }
  queryCustom(req?: BankRealizationCertificateUploadSearch): Observable<EntityArrayResponseType> {
    return this.http
      .post<IBankRealisationCertificateUpload[]>(this.resourceUrl + '-qry', req, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }
}
