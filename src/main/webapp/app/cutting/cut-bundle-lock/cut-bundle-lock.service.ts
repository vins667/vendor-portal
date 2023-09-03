import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { ICutBundleLock } from 'app/shared/model/cut-bundle-lock.model';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICutBundleLockId } from 'app/shared/model/cut-bundle-lock-id.model';

type EntityArrayResponseType = HttpResponse<ICutBundleLock[]>;

@Injectable({ providedIn: 'root' })
export class CutBundleLockService {
  public resourceUrl = SERVER_API_URL + 'api/cut-bundle-locks';

  constructor(protected http: HttpClient) {}

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ICutBundleLock[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: ICutBundleLockId): Observable<HttpResponse<any>> {
    return this.http.post<any>(`${this.resourceUrl + '-delete'}`, id, { observe: 'response' });
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((cutBundleLock: ICutBundleLock) => {
        cutBundleLock.lockedDate = cutBundleLock.lockedDate != null ? moment(cutBundleLock.lockedDate) : null;
      });
    }
    return res;
  }
}
