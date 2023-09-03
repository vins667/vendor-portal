import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IFormsDownload } from 'app/shared/model/forms-download.model';

type EntityResponseType = HttpResponse<IFormsDownload>;
type EntityArrayResponseType = HttpResponse<IFormsDownload[]>;

@Injectable({ providedIn: 'root' })
export class FormsDownloadService {
  public resourceUrl = SERVER_API_URL + 'api/forms-downloads';

  constructor(protected http: HttpClient) {}

  create(formsDownload: IFormsDownload): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(formsDownload);
    return this.http
      .post<IFormsDownload>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(formsDownload: IFormsDownload): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(formsDownload);
    return this.http
      .put<IFormsDownload>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IFormsDownload>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IFormsDownload[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  download(id: number): any {
    return this.http.get(`${this.resourceUrl}-download/${id}`, { headers: new HttpHeaders({}), responseType: 'blob' });
  }

  protected convertDateFromClient(formsDownload: IFormsDownload): IFormsDownload {
    const copy: IFormsDownload = Object.assign({}, formsDownload, {
      createdDate: formsDownload.createdDate != null && formsDownload.createdDate.isValid() ? formsDownload.createdDate.toJSON() : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.createdDate = res.body.createdDate != null ? moment(res.body.createdDate) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((formsDownload: IFormsDownload) => {
        formsDownload.createdDate = formsDownload.createdDate != null ? moment(formsDownload.createdDate) : null;
      });
    }
    return res;
  }
}
