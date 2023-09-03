import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ISuggestion } from 'app/shared/model/suggestion.model';

type EntityResponseType = HttpResponse<ISuggestion>;
type EntityArrayResponseType = HttpResponse<ISuggestion[]>;

@Injectable({ providedIn: 'root' })
export class SuggestionService {
  public resourceUrl = SERVER_API_URL + 'api/suggestions';

  constructor(protected http: HttpClient) {}

  create(suggestion: ISuggestion): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(suggestion);
    return this.http
      .post<ISuggestion>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(suggestion: ISuggestion): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(suggestion);
    return this.http
      .put<ISuggestion>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ISuggestion>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ISuggestion[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(suggestion: ISuggestion): ISuggestion {
    const copy: ISuggestion = Object.assign({}, suggestion, {
      createdDate: suggestion.createdDate != null && suggestion.createdDate.isValid() ? suggestion.createdDate.toJSON() : null
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
      res.body.forEach((suggestion: ISuggestion) => {
        suggestion.createdDate = suggestion.createdDate != null ? moment(suggestion.createdDate) : null;
      });
    }
    return res;
  }
}
