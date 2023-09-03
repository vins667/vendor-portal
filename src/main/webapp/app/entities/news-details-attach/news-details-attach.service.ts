import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { INewsDetailsAttach } from 'app/shared/model/news-details-attach.model';

type EntityResponseType = HttpResponse<INewsDetailsAttach>;
type EntityArrayResponseType = HttpResponse<INewsDetailsAttach[]>;

@Injectable({ providedIn: 'root' })
export class NewsDetailsAttachService {
  public resourceUrl = SERVER_API_URL + 'api/news-details-attaches';

  constructor(protected http: HttpClient) {}

  create(newsDetailsAttach: INewsDetailsAttach): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(newsDetailsAttach);
    return this.http
      .post<INewsDetailsAttach>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(newsDetailsAttach: INewsDetailsAttach): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(newsDetailsAttach);
    return this.http
      .put<INewsDetailsAttach>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<INewsDetailsAttach>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<INewsDetailsAttach[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(newsDetailsAttach: INewsDetailsAttach): INewsDetailsAttach {
    const copy: INewsDetailsAttach = Object.assign({}, newsDetailsAttach, {
      createdDate:
        newsDetailsAttach.createdDate != null && newsDetailsAttach.createdDate.isValid() ? newsDetailsAttach.createdDate.toJSON() : null
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
      res.body.forEach((newsDetailsAttach: INewsDetailsAttach) => {
        newsDetailsAttach.createdDate = newsDetailsAttach.createdDate != null ? moment(newsDetailsAttach.createdDate) : null;
      });
    }
    return res;
  }
}
