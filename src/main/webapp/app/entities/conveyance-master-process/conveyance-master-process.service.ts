import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SERVER_API_URL } from 'app/app.constants';
import { IConveyanceMaster } from 'app/shared/model/conveyance-master.model';
import { ConveyanceSearchMaster } from 'app/shared/model/conveyance-serach-master.model';
import { map } from 'rxjs/operators';
import * as moment from 'moment';
import { IMaster } from 'app/shared/model/master.modal';

type EntityResponseType = HttpResponse<IConveyanceMaster>;
type EntityArrayResponseType = HttpResponse<IConveyanceMaster[]>;

@Injectable({ providedIn: 'root' })
export class ConveyanceMasterProcessService {
  public resourceUrl = SERVER_API_URL + 'api/conveyance-masters';
  constructor(protected http: HttpClient) {}

  query(req?: ConveyanceSearchMaster): Observable<EntityArrayResponseType> {
    return this.http
      .post<IConveyanceMaster[]>(this.resourceUrl + '-process', req, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  fetchControls(): Observable<HttpResponse<IMaster[]>> {
    return this.http.get<IMaster[]>(this.resourceUrl + '-controls', { observe: 'response' });
  }

  fetchControlsByCont(controlNo?: string): Observable<EntityArrayResponseType> {
    return this.http.get<IConveyanceMaster[]>(this.resourceUrl + '-fetch-controls/' + controlNo, { observe: 'response' });
  }

  save(processIds: number[]): Observable<HttpResponse<IMaster>> {
    return this.http.post<IMaster>(this.resourceUrl + '-process-save', processIds, { observe: 'response' });
  }

  downloadPdf(id: string): Observable<Blob> {
    return this.http.get(`${this.resourceUrl + '-report/' + id}`, { headers: new HttpHeaders({}), responseType: 'blob' });
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
