import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { INewsDetails } from 'app/shared/model/news-details.model';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';

type EntityResponseType = HttpResponse<INewsDetails>;
type EntityArrayResponseType = HttpResponse<INewsDetails[]>;

@Injectable({ providedIn: 'root' })
export class NewsDetailsService {
  public resourceUrl = SERVER_API_URL + 'api/news-details';

  constructor(protected http: HttpClient) {}

  create(newsDetails: INewsDetails): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(newsDetails);
    return this.http
      .post<INewsDetails>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(newsDetails: INewsDetails): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(newsDetails);
    return this.http
      .put<INewsDetails>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<INewsDetails>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<INewsDetails[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  shortClose(id: number): Observable<EntityResponseType> {
    return this.http.get<INewsDetails>(`${this.resourceUrl + '-closed'}/${id}`, { observe: 'response' });
  }

  approve(id: number): Observable<EntityResponseType> {
    return this.http
      .get<INewsDetails>(`${this.resourceUrl}-approve/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  createUpload(file: File[], id: number): Observable<EntityResponseType> {
    const files: Array<File> = file;
    const formData: FormData = new FormData();
    for (let i = 0; i < files.length; i++) {
      formData.append('file', files[i]);
    }
    formData.append('id', id + '');
    return this.http
      .post<INewsDetails>(this.resourceUrl + '-attaches', formData, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  download(id: number): any {
    return this.http.get(`${this.resourceUrl}-attaches-download/${id}`, { headers: new HttpHeaders({}), responseType: 'blob' });
  }

  dashboard(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<INewsDetails[]>(this.resourceUrl + '-dashboard', { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  protected convertDateFromClient(newsDetails: INewsDetails): INewsDetails {
    const copy: INewsDetails = Object.assign({}, newsDetails, {
      endDate: newsDetails.endDate != null && newsDetails.endDate.isValid() ? newsDetails.endDate.format(DATE_FORMAT) : null,
      createdDate: newsDetails.createdDate != null && newsDetails.createdDate.isValid() ? newsDetails.createdDate.toJSON() : null,
      approvedDate: newsDetails.approvedDate != null && newsDetails.approvedDate.isValid() ? newsDetails.approvedDate.toJSON() : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.endDate = res.body.endDate != null ? moment(res.body.endDate) : null;
      res.body.createdDate = res.body.createdDate != null ? moment(res.body.createdDate) : null;
      res.body.approvedDate = res.body.approvedDate != null ? moment(res.body.approvedDate) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((newsDetails: INewsDetails) => {
        newsDetails.endDate = newsDetails.endDate != null ? moment(newsDetails.endDate) : null;
        newsDetails.createdDate = newsDetails.createdDate != null ? moment(newsDetails.createdDate) : null;
        newsDetails.approvedDate = newsDetails.approvedDate != null ? moment(newsDetails.approvedDate) : null;
      });
    }
    return res;
  }
}
