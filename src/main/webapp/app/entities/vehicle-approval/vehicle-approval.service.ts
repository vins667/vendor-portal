import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IVehicleMaster } from 'app/shared/model/vehicle-master.model';
import { ILeaveSearch } from 'app/shared/model/leave-search.model';
import { IMessage } from 'app/shared/model/message.model';

type EntityResponseType = HttpResponse<IVehicleMaster>;
type EntityArrayResponseType = HttpResponse<IVehicleMaster[]>;

@Injectable({ providedIn: 'root' })
export class VehicleApprovalService {
  public resourceUrl = SERVER_API_URL + 'api/vehicle-masters';

  constructor(protected http: HttpClient) {}

  create(vehicleMaster: IVehicleMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(vehicleMaster);
    return this.http
      .post<IVehicleMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(vehicleMasters: IVehicleMaster[]): Observable<HttpResponse<IMessage>> {
    const vehicleSaveMasters = [];
    vehicleMasters.forEach(vehicleMaster => {
      const copy = this.convertDateFromClient(vehicleMaster);
      vehicleSaveMasters.push(copy);
    });
    return this.http.put<IMessage>(this.resourceUrl, vehicleSaveMasters, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IVehicleMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IVehicleMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  queryHod(req?: ILeaveSearch): Observable<EntityArrayResponseType> {
    const copy = this.convertDateFromClientLeave(req);
    return this.http
      .post<IVehicleMaster[]>(`${this.resourceUrl + '-hod'}`, copy, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  queryTransport(req?: ILeaveSearch): Observable<EntityArrayResponseType> {
    const copy = this.convertDateFromClientLeave(req);
    return this.http
      .post<IVehicleMaster[]>(`${this.resourceUrl + '-transport'}`, copy, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  updateTransport(vehicleMaster: IVehicleMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(vehicleMaster);
    return this.http
      .put<IVehicleMaster>(`${this.resourceUrl + '-transport'}`, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  protected convertDateFromClient(vehicleMaster: IVehicleMaster): IVehicleMaster {
    const copy: IVehicleMaster = Object.assign({}, vehicleMaster, {
      vehicleDate: vehicleMaster.vehicleDate != null && vehicleMaster.vehicleDate.isValid() ? vehicleMaster.vehicleDate.toJSON() : null,
      createdDate: vehicleMaster.createdDate != null && vehicleMaster.createdDate.isValid() ? vehicleMaster.createdDate.toJSON() : null,
      hodApprovedDate:
        vehicleMaster.hodApprovedDate != null && vehicleMaster.hodApprovedDate.isValid() ? vehicleMaster.hodApprovedDate.toJSON() : null,
      adminApprovedDate:
        vehicleMaster.adminApprovedDate != null && vehicleMaster.adminApprovedDate.isValid()
          ? vehicleMaster.adminApprovedDate.toJSON()
          : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.vehicleDate = res.body.vehicleDate != null ? moment(res.body.vehicleDate) : null;
      res.body.createdDate = res.body.createdDate != null ? moment(res.body.createdDate) : null;
      res.body.hodApprovedDate = res.body.hodApprovedDate != null ? moment(res.body.hodApprovedDate) : null;
      res.body.adminApprovedDate = res.body.adminApprovedDate != null ? moment(res.body.adminApprovedDate) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((vehicleMaster: IVehicleMaster) => {
        vehicleMaster.vehicleDate = vehicleMaster.vehicleDate != null ? moment(vehicleMaster.vehicleDate) : null;
        vehicleMaster.createdDate = vehicleMaster.createdDate != null ? moment(vehicleMaster.createdDate) : null;
        vehicleMaster.hodApprovedDate = vehicleMaster.hodApprovedDate != null ? moment(vehicleMaster.hodApprovedDate) : null;
        vehicleMaster.adminApprovedDate = vehicleMaster.adminApprovedDate != null ? moment(vehicleMaster.adminApprovedDate) : null;
      });
    }
    return res;
  }

  protected convertDateFromClientLeave(leaveMaster: ILeaveSearch): ILeaveSearch {
    const copy: ILeaveSearch = Object.assign({}, leaveMaster, {
      leaveDateFrom: leaveMaster.leaveDateFrom != null && leaveMaster.leaveDateFrom.isValid() ? leaveMaster.leaveDateFrom.toJSON() : null,
      leaveDateTo: leaveMaster.leaveDateTo != null && leaveMaster.leaveDateTo.isValid() ? leaveMaster.leaveDateTo.toJSON() : null
    });
    return copy;
  }
}
