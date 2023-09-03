import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IEmailInvitation } from 'app/shared/model/email-invitation.model';

type EntityResponseType = HttpResponse<IEmailInvitation>;
type EntityArrayResponseType = HttpResponse<IEmailInvitation[]>;

@Injectable({ providedIn: 'root' })
export class EmailInvitationService {
  public resourceUrl = SERVER_API_URL + 'api/email-invitations';

  constructor(protected http: HttpClient) {}

  create(emailInvitation: IEmailInvitation): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(emailInvitation);
    return this.http
      .post<IEmailInvitation>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(emailInvitation: IEmailInvitation): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(emailInvitation);
    return this.http
      .put<IEmailInvitation>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IEmailInvitation>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  emailResend(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IEmailInvitation>(`${this.resourceUrl + '-resend'}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IEmailInvitation[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(emailInvitation: IEmailInvitation): IEmailInvitation {
    const copy: IEmailInvitation = Object.assign({}, emailInvitation, {
      createdDate:
        emailInvitation.createdDate != null && emailInvitation.createdDate.isValid() ? emailInvitation.createdDate.toJSON() : null
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
      res.body.forEach((emailInvitation: IEmailInvitation) => {
        emailInvitation.createdDate = emailInvitation.createdDate != null ? moment(emailInvitation.createdDate) : null;
      });
    }
    return res;
  }
}
