import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';
import { SERVER_API_URL } from 'app/app.constants';
import { IEmployeeView } from 'app/shared/model/employee-view.model';
import { IValidateOtp } from 'app/shared/model/validate-otp.model';
import { IUser } from 'app/core/user/user.model';

type EntityResponseType = HttpResponse<IEmployeeView>;
type EntityArrayResponseType = HttpResponse<IEmployeeView[]>;

@Injectable({ providedIn: 'root' })
export class PasswordResetInitService {
  public resourceUrl = SERVER_API_URL + 'api/employee-views';
  public resourceUrlReset = SERVER_API_URL + 'api/reset-user-password';

  constructor(private http: HttpClient) {}

  save(mail: string): Observable<any> {
    return this.http.post(SERVER_API_URL + 'api/account/reset-password/init', mail);
  }

  findByLogin(id: string): Observable<EntityResponseType> {
    return this.http
      .get<IEmployeeView>(`${this.resourceUrl + '-login'}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  reset(otpValidate: IValidateOtp): Observable<HttpResponse<IUser>> {
    return this.http.post<IUser>(this.resourceUrlReset, otpValidate, { observe: 'response' });
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.doj = res.body.doj != null ? moment(res.body.doj) : null;
      res.body.dob = res.body.dob != null ? moment(res.body.dob) : null;
    }
    return res;
  }
}
