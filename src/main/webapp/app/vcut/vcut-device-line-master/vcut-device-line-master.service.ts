import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IVcutDeviceLineMaster } from 'app/shared/model/vcut-device-line-master.model';
import { IVcutUserDeviceMaster } from 'app/shared/model/vcut-user-device-master.model';
import { IYarnCountMaster } from 'app/shared/model/yarn-count-master.model';
import { IEmployeeSearch } from 'app/shared/model/employee-search.model';
type EntityResponseType = HttpResponse<IVcutDeviceLineMaster>;
type EntityArrayResponseType = HttpResponse<IVcutDeviceLineMaster[]>;

@Injectable({ providedIn: 'root' })
export class VcutDeviceLineMasterService {
  public resourceUrl = SERVER_API_URL + 'api/vcut-device-line-masters';
  public resourceUrlDetails = SERVER_API_URL + 'api/vcut-user-device-masters';

  constructor(protected http: HttpClient) {}

  create(vcutDeviceLineMaster: IVcutDeviceLineMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(vcutDeviceLineMaster);
    return this.http
      .post<IVcutDeviceLineMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(vcutDeviceLineMaster: IVcutDeviceLineMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(vcutDeviceLineMaster);
    return this.http
      .put<IVcutDeviceLineMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IVcutDeviceLineMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IVcutDeviceLineMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }
  querySearchEmployee(employeeSearch?: IEmployeeSearch): Observable<EntityArrayResponseType> {
    return this.http
      .post<IVcutUserDeviceMaster[]>(this.resourceUrl + '-search-employees', employeeSearch, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }
  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
  deleteDetail(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrlDetails}/${id}`, { observe: 'response' });
  }
  protected convertDateFromClient(vcutDeviceLineMaster: IVcutDeviceLineMaster): IVcutDeviceLineMaster {
    const copy: IVcutDeviceLineMaster = Object.assign({}, vcutDeviceLineMaster, {
      createdDate:
        vcutDeviceLineMaster.createdDate != null && vcutDeviceLineMaster.createdDate.isValid()
          ? vcutDeviceLineMaster.createdDate.toJSON()
          : null
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
      res.body.forEach((vcutDeviceLineMaster: IVcutDeviceLineMaster) => {
        vcutDeviceLineMaster.createdDate = vcutDeviceLineMaster.createdDate != null ? moment(vcutDeviceLineMaster.createdDate) : null;
      });
    }
    return res;
  }
}
