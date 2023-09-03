import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SERVER_API_URL } from 'app/app.constants';
import { IConveyanceMaster } from 'app/shared/model/conveyance-master.model';
import { ConveyanceSearchMaster } from 'app/shared/model/conveyance-serach-master.model';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

type EntityResponseType = HttpResponse<IConveyanceMaster>;
type EntityArrayResponseType = HttpResponse<IConveyanceMaster[]>;

@Injectable({ providedIn: 'root' })
export class ConveyanceMasterHrService {
  public resourceUrl = SERVER_API_URL + 'api/conveyance-masters';
  constructor(protected http: HttpClient) {}

  update(conveyanceMasterHr: IConveyanceMaster): Observable<EntityResponseType> {
    return this.http.put<IConveyanceMaster>(this.resourceUrl + '-update-hr', conveyanceMasterHr, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IConveyanceMaster>(`${this.resourceUrl}-aprv/${id}`, { observe: 'response' });
  }

  query(req?: ConveyanceSearchMaster): Observable<EntityArrayResponseType> {
    return this.http
      .post<IConveyanceMaster[]>(this.resourceUrl + '-hr', req, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  download(id: number): any {
    return this.http.get(`${this.resourceUrl}-download/${id}`, { headers: new HttpHeaders({}), responseType: 'blob' });
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.createdDate = res.body.createdDate != null ? moment(res.body.createdDate) : null;
      res.body.lastUpdatedDate = res.body.lastUpdatedDate != null ? moment(res.body.lastUpdatedDate) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((conveyanceMaster: IConveyanceMaster) => {
        conveyanceMaster.createdDate = conveyanceMaster.createdDate != null ? moment(conveyanceMaster.createdDate) : null;
        conveyanceMaster.lastUpdatedDate = conveyanceMaster.lastUpdatedDate != null ? moment(conveyanceMaster.lastUpdatedDate) : null;
      });
    }
    return res;
  }
}
