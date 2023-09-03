import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { ISmsRegistration } from 'app/shared/model/sms-registration.model';
import { IValidateOtp } from 'app/shared/model/validate-otp.model';
import { IUser } from 'app/core/user/user.model';
import { createRequestOption } from 'app/shared/util/request-util';

type EntityResponseType = HttpResponse<ISmsRegistration>;
type EntityArrayResponseType = HttpResponse<ISmsRegistration[]>;

@Injectable({ providedIn: 'root' })
export class SmsRegistrationService {
  public resourceUrl = SERVER_API_URL + 'api/sms-registrations';
  public resourceUrlOtp = SERVER_API_URL + 'api/otp-validate';
  public resourceUrlReset = SERVER_API_URL + 'api/reset-user-password';

  constructor(protected http: HttpClient) {}

  create(smsRegistration: ISmsRegistration): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(smsRegistration);
    return this.http
      .post<ISmsRegistration>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(smsRegistration: ISmsRegistration): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(smsRegistration);
    return this.http
      .put<ISmsRegistration>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ISmsRegistration>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ISmsRegistration[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  otpValidate(otpValidate: IValidateOtp): Observable<HttpResponse<IUser>> {
    return this.http.post<IUser>(this.resourceUrlOtp, otpValidate, { observe: 'response' });
  }

  reset(otpValidate: IValidateOtp): Observable<HttpResponse<IUser>> {
    return this.http.post<IUser>(this.resourceUrlReset, otpValidate, { observe: 'response' });
  }

  protected convertDateFromClient(smsRegistration: ISmsRegistration): ISmsRegistration {
    const copy: ISmsRegistration = Object.assign({}, smsRegistration, {
      createdDate:
        smsRegistration.createdDate != null && smsRegistration.createdDate.isValid() ? smsRegistration.createdDate.toJSON() : null
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
      res.body.forEach((smsRegistration: ISmsRegistration) => {
        smsRegistration.createdDate = smsRegistration.createdDate != null ? moment(smsRegistration.createdDate) : null;
      });
    }
    return res;
  }
}
