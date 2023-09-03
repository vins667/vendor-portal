import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { INewsDetailsBody } from 'app/shared/model/news-details-body.model';

type EntityResponseType = HttpResponse<INewsDetailsBody>;
type EntityArrayResponseType = HttpResponse<INewsDetailsBody[]>;

@Injectable({ providedIn: 'root' })
export class NewsDetailsBodyService {
  public resourceUrl = SERVER_API_URL + 'api/news-details-bodies';

  constructor(protected http: HttpClient) {}

  create(newsDetailsBody: INewsDetailsBody): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(newsDetailsBody);
    return this.http
      .post<INewsDetailsBody>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(newsDetailsBody: INewsDetailsBody): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(newsDetailsBody);
    return this.http
      .put<INewsDetailsBody>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<INewsDetailsBody>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<INewsDetailsBody[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(newsDetailsBody: INewsDetailsBody): INewsDetailsBody {
    const copy: INewsDetailsBody = Object.assign({}, newsDetailsBody, {
      createdDate:
        newsDetailsBody.createdDate != null && newsDetailsBody.createdDate.isValid() ? newsDetailsBody.createdDate.toJSON() : null
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
      res.body.forEach((newsDetailsBody: INewsDetailsBody) => {
        newsDetailsBody.createdDate = newsDetailsBody.createdDate != null ? moment(newsDetailsBody.createdDate) : null;
      });
    }
    return res;
  }
}
