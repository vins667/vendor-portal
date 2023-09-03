import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { IMonthlyNewsData } from 'app/shared/model/monthly-news-data.model';
import { createRequestOption } from 'app/shared/util/request-util';

type EntityResponseType = HttpResponse<IMonthlyNewsData>;
type EntityArrayResponseType = HttpResponse<IMonthlyNewsData[]>;

@Injectable({ providedIn: 'root' })
export class MonthlyNewsDataService {
  public resourceUrl = SERVER_API_URL + 'api/monthly-news-data';

  constructor(protected http: HttpClient) {}

  create(file: File): Observable<EntityResponseType> {
    const formData: FormData = new FormData();
    formData.append('file', file);
    return this.http
      .post<IMonthlyNewsData>(this.resourceUrl, formData, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(monthlyNewsData: IMonthlyNewsData): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(monthlyNewsData);
    return this.http
      .put<IMonthlyNewsData>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  download(id: number): any {
    return this.http.get(`${this.resourceUrl}-download/${id}`, { headers: new HttpHeaders({}), responseType: 'blob' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IMonthlyNewsData>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IMonthlyNewsData[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(monthlyNewsData: IMonthlyNewsData): IMonthlyNewsData {
    const copy: IMonthlyNewsData = Object.assign({}, monthlyNewsData, {
      createdDate:
        monthlyNewsData.createdDate != null && monthlyNewsData.createdDate.isValid() ? monthlyNewsData.createdDate.toJSON() : null,
      closedDate: monthlyNewsData.closedDate != null && monthlyNewsData.closedDate.isValid() ? monthlyNewsData.closedDate.toJSON() : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.createdDate = res.body.createdDate != null ? moment(res.body.createdDate) : null;
      res.body.closedDate = res.body.closedDate != null ? moment(res.body.closedDate) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((monthlyNewsData: IMonthlyNewsData) => {
        monthlyNewsData.createdDate = monthlyNewsData.createdDate != null ? moment(monthlyNewsData.createdDate) : null;
        monthlyNewsData.closedDate = monthlyNewsData.closedDate != null ? moment(monthlyNewsData.closedDate) : null;
      });
    }
    return res;
  }
}
