import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { SERVER_API_URL } from 'app/app.constants';
import { IEmployeeView } from 'app/shared/model/employee-view.model';
import { IValidateOtp } from 'app/shared/model/validate-otp.model';
import { IUser } from 'app/core/user/user.model';
import { map } from 'rxjs/operators';
import { ISmsRegistration } from 'app/shared/model/sms-registration.model';

type EntityResponseType = HttpResponse<IEmployeeView>;
type EntityArrayResponseType = HttpResponse<IEmployeeView[]>;
type EntityResponseTypeSms = HttpResponse<ISmsRegistration>;
type EntityArrayResponseTypeSms = HttpResponse<ISmsRegistration>;

@Injectable({ providedIn: 'root' })
export class Register {
  public resourceUrl = SERVER_API_URL + 'api/employee-views';
  public resourceUrlSms = SERVER_API_URL + 'api/sms-registrations';
  public resourceUrlOtp = SERVER_API_URL + 'api/otp-validate';
  constructor(private http: HttpClient) {}

  save(account: any): Observable<any> {
    return this.http.post(SERVER_API_URL + 'api/register', account);
  }

  findByCard(id: string): Observable<EntityResponseType> {
    return this.http
      .get<IEmployeeView>(`${this.resourceUrl + '-card'}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  findByLogin(id: string): Observable<EntityResponseType> {
    return this.http
      .get<IEmployeeView>(`${this.resourceUrl + '-login'}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  create(smsRegistration: ISmsRegistration): Observable<EntityResponseTypeSms> {
    const copy = this.convertDateFromClientSms(smsRegistration);
    return this.http
      .post<ISmsRegistration>(this.resourceUrlSms, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  otpValidate(otpValidate: IValidateOtp): Observable<HttpResponse<IUser>> {
    return this.http.post<IUser>(this.resourceUrlOtp, otpValidate, { observe: 'response' });
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.doj = res.body.doj != null ? moment(res.body.doj) : null;
      res.body.dob = res.body.dob != null ? moment(res.body.dob) : null;
    }
    return res;
  }

  protected convertDateFromClientSms(smsRegistration: ISmsRegistration): ISmsRegistration {
    const copy: ISmsRegistration = Object.assign({}, smsRegistration, {
      createdDate:
        smsRegistration.createdDate != null && smsRegistration.createdDate.isValid() ? smsRegistration.createdDate.toJSON() : null
    });
    return copy;
  }
}
