import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SERVER_API_URL } from 'app/app.constants';
import { ITravelApplicationMaster } from 'app/shared/model/travel-application-master.model';
import { ConveyanceSearchMaster } from 'app/shared/model/conveyance-serach-master.model';
import { map } from 'rxjs/operators';
import * as moment from 'moment';
import { ITravelMasterAttach } from 'app/shared/model/travel-master-attach.model';

type EntityResponseType = HttpResponse<ITravelApplicationMaster>;
type EntityArrayResponseType = HttpResponse<ITravelApplicationMaster[]>;

@Injectable({ providedIn: 'root' })
export class TravelApplicationMasterHrService {
  public resourceUrl = SERVER_API_URL + 'api/travel-application-masters';
  public resourceUrlAttach = SERVER_API_URL + 'api/travel-master-attaches';

  constructor(protected http: HttpClient) {}

  create(travelApplicationMasterHr: ITravelApplicationMaster): Observable<EntityResponseType> {
    return this.http.post<ITravelApplicationMaster>(this.resourceUrl, travelApplicationMasterHr, { observe: 'response' });
  }

  update(travelApplicationMasterHr: ITravelApplicationMaster): Observable<EntityResponseType> {
    return this.http.put<ITravelApplicationMaster>(this.resourceUrl + '-update-hr', travelApplicationMasterHr, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITravelApplicationMaster>(`${this.resourceUrl}-view/${id}`, { observe: 'response' });
  }

  query(req?: ConveyanceSearchMaster): Observable<EntityArrayResponseType> {
    return this.http
      .post<ITravelApplicationMaster[]>(this.resourceUrl + '-hr', req, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  findAttach(id: number): Observable<HttpResponse<ITravelMasterAttach[]>> {
    return this.http.get<ITravelMasterAttach[]>(`${this.resourceUrlAttach}/${id}`, { observe: 'response' });
  }

  createUpload(file: File, type: string, id: number): Observable<HttpResponse<ITravelMasterAttach[]>> {
    const formData: FormData = new FormData();
    formData.append('file', file);
    formData.append('attchType', type);
    formData.append('id', id + '');
    return this.http.post<ITravelMasterAttach[]>(this.resourceUrlAttach, formData, { observe: 'response' });
  }

  download(id: number): any {
    return this.http.get(`${this.resourceUrlAttach + '-download'}/${id}`, { headers: new HttpHeaders({}), responseType: 'blob' });
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
      res.body.forEach((travelApplicationMaster: ITravelApplicationMaster) => {
        travelApplicationMaster.createdDate =
          travelApplicationMaster.createdDate != null ? moment(travelApplicationMaster.createdDate) : null;
        travelApplicationMaster.lastUpdatedDate =
          travelApplicationMaster.lastUpdatedDate != null ? moment(travelApplicationMaster.lastUpdatedDate) : null;
      });
    }
    return res;
  }
}
