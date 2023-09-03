import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';

@Injectable({ providedIn: 'root' })
export class Form16Service {
  public resourceUrl = SERVER_API_URL + 'api/tds-form16-download';

  constructor(protected http: HttpClient) {}

  download(finYear: number): any {
    return this.http.get(`${this.resourceUrl}/${finYear}`, { headers: new HttpHeaders({}), responseType: 'blob' });
  }
}
