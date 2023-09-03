import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { IConveyanceMaster } from 'app/shared/model/conveyance-master.model';
import { createRequestOption } from 'app/shared/util/request-util';
import { ConveyanceSearchMaster } from 'app/shared/model/conveyance-serach-master.model';
import { IConveyanceMasterDetails } from 'app/shared/model/conveyance-master-details.model';

type EntityResponseType = HttpResponse<IConveyanceMaster>;
type EntityArrayResponseType = HttpResponse<IConveyanceMaster[]>;

@Injectable({ providedIn: 'root' })
export class ConveyanceMasterService {
  public resourceUrl = SERVER_API_URL + 'api/conveyance-masters';
  public resourceDetalsUrl = SERVER_API_URL + 'api/conveyance-master-details';
  constructor(protected http: HttpClient) {}

  create(conveyanceMaster: IConveyanceMaster): Observable<EntityResponseType> {
    const formData: FormData = new FormData();
    conveyanceMaster.conveyanceMasterDetails.forEach((conveyanceAttach: IConveyanceMasterDetails) => {
      if (conveyanceAttach.currentFileUpload) {
        formData.append('file', conveyanceAttach.currentFileUpload);
      } else {
        formData.append('file', new File(new Array<Blob>(), 'fake.pdf', { type: 'application/pdf' }));
      }
      formData.append('tripStart', conveyanceAttach.tripStart + '');
      formData.append('tripEnd', conveyanceAttach.tripEnd + '');
      if (conveyanceAttach.miscAmount) {
        formData.append('miscAmount', conveyanceAttach.miscAmount + '');
      } else {
        formData.append('miscAmount', 0 + '');
      }
      formData.append('fromLocation', conveyanceAttach.fromLocation);
      formData.append('toLocation', conveyanceAttach.toLocation);
      formData.append('reason', conveyanceAttach.reason);
    });
    formData.append('conveyanceType', conveyanceMaster.conveyanceType);
    formData.append('conveyanceDate', conveyanceMaster.conveyanceDate);
    formData.append('vehicleNo', conveyanceMaster.vehicleNo);
    formData.append('totalDistance', conveyanceMaster.totalDistance + '');
    formData.append('rate', conveyanceMaster.rate + '');
    formData.append('totalAmount', conveyanceMaster.totalAmount + '');
    formData.append('approvedBy', conveyanceMaster.approvedBy);
    formData.append('vehicleType', conveyanceMaster.vehicleType);
    return this.http
      .post<IConveyanceMaster>(this.resourceUrl, formData, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(conveyanceMaster: IConveyanceMaster): Observable<EntityResponseType> {
    const formData: FormData = new FormData();
    let index = 0;
    conveyanceMaster.conveyanceMasterDetails.forEach((conveyanceAttach: IConveyanceMasterDetails) => {
      if (conveyanceAttach.currentFileUpload) {
        formData.append('file', conveyanceAttach.currentFileUpload);
      } else {
        formData.append('file', new File(new Array<Blob>(), 'fake.pdf', { type: 'application/pdf' }));
      }
      formData.append('tripStart', conveyanceAttach.tripStart + '');
      formData.append('tripEnd', conveyanceAttach.tripEnd + '');
      if (conveyanceAttach.miscAmount) {
        formData.append('miscAmount', conveyanceAttach.miscAmount + '');
      } else {
        formData.append('miscAmount', 0 + '');
      }
      formData.append('fromLocation', conveyanceAttach.fromLocation);
      formData.append('toLocation', conveyanceAttach.toLocation);
      formData.append('reason', conveyanceAttach.reason);
      if (conveyanceAttach.id) {
        formData.append('detailId', conveyanceAttach.id + '');
      } else {
        formData.append('detailId', '');
      }
      ++index;
    });
    if (index === conveyanceMaster.conveyanceMasterDetails.length) {
      formData.append('id', conveyanceMaster.id + '');
      formData.append('conveyanceType', conveyanceMaster.conveyanceType);
      formData.append('conveyanceDate', conveyanceMaster.conveyanceDate);
      formData.append('vehicleNo', conveyanceMaster.vehicleNo);
      formData.append('totalDistance', conveyanceMaster.totalDistance + '');
      formData.append('rate', conveyanceMaster.rate + '');
      formData.append('totalAmount', conveyanceMaster.totalAmount + '');
      formData.append('approvedBy', conveyanceMaster.approvedBy);
      formData.append('vehicleType', conveyanceMaster.vehicleType);

      return this.http
        .post<IConveyanceMaster>(this.resourceUrl + '-update', formData, { observe: 'response' })
        .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }
  }

  download(id: number): any {
    return this.http.get(`${this.resourceUrl}-download/${id}`, { headers: new HttpHeaders({}), responseType: 'blob' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IConveyanceMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IConveyanceMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  queryCustom(req?: ConveyanceSearchMaster): Observable<EntityArrayResponseType> {
    return this.http
      .post<IConveyanceMaster[]>(this.resourceUrl + '-qry', req, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  deleteDetail(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceDetalsUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(conveyanceMaster: IConveyanceMaster): IConveyanceMaster {
    const copy: IConveyanceMaster = Object.assign({}, conveyanceMaster, {
      createdDate:
        conveyanceMaster.createdDate != null && conveyanceMaster.createdDate.isValid() ? conveyanceMaster.createdDate.toJSON() : null,
      lastUpdatedDate:
        conveyanceMaster.lastUpdatedDate != null && conveyanceMaster.lastUpdatedDate.isValid()
          ? conveyanceMaster.lastUpdatedDate.toJSON()
          : null
    });
    return copy;
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
