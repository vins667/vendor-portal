import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IEmployeeInformationUpdate } from 'app/shared/model/employee-information-update.model';

type EntityResponseType = HttpResponse<IEmployeeInformationUpdate>;
type EntityArrayResponseType = HttpResponse<IEmployeeInformationUpdate[]>;

@Injectable({ providedIn: 'root' })
export class EmployeeInformationUpdateService {
  public resourceUrl = SERVER_API_URL + 'api/employee-information-updates';

  constructor(protected http: HttpClient) {}

  create(file: File, employeeInformationUpdate: IEmployeeInformationUpdate): Observable<EntityResponseType> {
    const formData: FormData = new FormData();
    formData.append('file', file);
    formData.append('id', employeeInformationUpdate.id + '');
    formData.append('correspondenceAddress', employeeInformationUpdate.correspondenceAddress);
    formData.append('mobileNumber', employeeInformationUpdate.mobileNumber);
    return this.http
      .post<IEmployeeInformationUpdate>(this.resourceUrl, formData, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(file: File, employeeInformationUpdate: IEmployeeInformationUpdate): Observable<EntityResponseType> {
    const formData: FormData = new FormData();
    formData.append('file', file);
    formData.append('id', employeeInformationUpdate.id + '');
    formData.append('correspondenceAddress', employeeInformationUpdate.correspondenceAddress);
    formData.append('mobileNumber', employeeInformationUpdate.mobileNumber);
    return this.http
      .post<IEmployeeInformationUpdate>(this.resourceUrl + '-update', formData, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IEmployeeInformationUpdate>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  customFind(): Observable<EntityResponseType> {
    return this.http
      .get<IEmployeeInformationUpdate>(`${this.resourceUrl + '-custom'}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IEmployeeInformationUpdate[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(employeeInformationUpdate: IEmployeeInformationUpdate): IEmployeeInformationUpdate {
    const copy: IEmployeeInformationUpdate = Object.assign({}, employeeInformationUpdate, {
      createdDate:
        employeeInformationUpdate.createdDate != null && employeeInformationUpdate.createdDate.isValid()
          ? employeeInformationUpdate.createdDate.toJSON()
          : null,
      lastUpdatedDate:
        employeeInformationUpdate.lastUpdatedDate != null && employeeInformationUpdate.lastUpdatedDate.isValid()
          ? employeeInformationUpdate.lastUpdatedDate.toJSON()
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
      res.body.forEach((employeeInformationUpdate: IEmployeeInformationUpdate) => {
        employeeInformationUpdate.createdDate =
          employeeInformationUpdate.createdDate != null ? moment(employeeInformationUpdate.createdDate) : null;
        employeeInformationUpdate.lastUpdatedDate =
          employeeInformationUpdate.lastUpdatedDate != null ? moment(employeeInformationUpdate.lastUpdatedDate) : null;
      });
    }
    return res;
  }
}
