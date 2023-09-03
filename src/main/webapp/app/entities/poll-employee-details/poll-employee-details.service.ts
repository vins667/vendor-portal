import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IPollEmployeeDetails } from 'app/shared/model/poll-employee-details.model';
import { IMessage } from 'app/shared/model/message.model';

type EntityResponseType = HttpResponse<IPollEmployeeDetails>;
type EntityArrayResponseType = HttpResponse<IPollEmployeeDetails[]>;

@Injectable({ providedIn: 'root' })
export class PollEmployeeDetailsService {
  public resourceUrl = SERVER_API_URL + 'api/poll-employee-details';

  constructor(protected http: HttpClient) {}

  create(pollEmployeeDetails: IPollEmployeeDetails): Observable<HttpResponse<IMessage>> {
    const copy = this.convertDateFromClient(pollEmployeeDetails);
    return this.http.post<IMessage>(this.resourceUrl, copy, { observe: 'response' });
  }

  update(pollEmployeeDetails: IPollEmployeeDetails): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(pollEmployeeDetails);
    return this.http
      .put<IPollEmployeeDetails>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IPollEmployeeDetails>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IPollEmployeeDetails[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(pollEmployeeDetails: IPollEmployeeDetails): IPollEmployeeDetails {
    const copy: IPollEmployeeDetails = Object.assign({}, pollEmployeeDetails, {
      createdDate:
        pollEmployeeDetails.createdDate != null && pollEmployeeDetails.createdDate.isValid()
          ? pollEmployeeDetails.createdDate.toJSON()
          : null
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
      res.body.forEach((pollEmployeeDetails: IPollEmployeeDetails) => {
        pollEmployeeDetails.createdDate = pollEmployeeDetails.createdDate != null ? moment(pollEmployeeDetails.createdDate) : null;
      });
    }
    return res;
  }
}
