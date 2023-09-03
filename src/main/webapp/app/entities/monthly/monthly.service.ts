import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ISalaryBean } from 'app/shared/model/salary-bean.model';
import { SERVER_API_URL } from 'app/app.constants';
import { Observable } from 'rxjs';
import { createRequestOption } from 'app/shared/util/request-util';
import { IMonthly } from 'app/shared/model/monthly.model';
import { IMessage } from 'app/shared/model/message.model';

type EntityArrayResponseType = HttpResponse<ISalaryBean[]>;

@Injectable({ providedIn: 'root' })
export class MonthlyService {
  public resourceUrl = SERVER_API_URL + 'api/monthly-salary';

  constructor(protected http: HttpClient) {}

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ISalaryBean[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  find(monthYear?: string): Observable<HttpResponse<IMonthly>> {
    return this.http.get<IMonthly>(`${this.resourceUrl}/${monthYear}`, { observe: 'response' });
  }

  downloadPdf(monthYear?: string): Observable<Blob> {
    return this.http.get(`${this.resourceUrl + '-report'}/${monthYear}`, { headers: new HttpHeaders({}), responseType: 'blob' });
  }

  postMail(monthYear?: string): Observable<HttpResponse<IMessage>> {
    return this.http.get<IMessage>(`${this.resourceUrl + '-report-mail'}/${monthYear}`, { observe: 'response' });
  }
}
