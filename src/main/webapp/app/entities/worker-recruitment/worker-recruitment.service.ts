import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IWorkerRecruitment } from 'app/shared/model/worker-recruitment.model';
import { ITrailMockSearchOperation } from 'app/shared/model/trail-mock-search.model';
import { Master } from 'app/shared/model/master.modal';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';

type EntityResponseType = HttpResponse<IWorkerRecruitment>;
type EntityArrayResponseType = HttpResponse<IWorkerRecruitment[]>;

@Injectable({ providedIn: 'root' })
export class WorkerRecruitmentService {
  public resourceUrl = SERVER_API_URL + 'api/worker-recruitments';
  public resourceUrlFetch = SERVER_API_URL + 'api/fetch-aadhar';

  constructor(protected http: HttpClient) {}

  create(file: File, workerRecruitment: IWorkerRecruitment): Observable<EntityResponseType> {
    const formData: FormData = new FormData();
    formData.append('file', file);
    formData.append('id', workerRecruitment.id + '');
    formData.append('aadharNo', workerRecruitment.aadharNo);
    formData.append('name', workerRecruitment.name);
    formData.append('dob', workerRecruitment.dob);
    formData.append('fatherName', workerRecruitment.fatherName);
    formData.append('address', workerRecruitment.address);
    formData.append('panNo', workerRecruitment.panNo);
    formData.append('bankBranch', workerRecruitment.bankBranch);
    formData.append('bankAccNo', workerRecruitment.bankAccNo);
    formData.append('bankMaster', workerRecruitment.bankMaster.id + '');
    formData.append('departmentMaster', workerRecruitment.departmentMaster.id + '');
    formData.append('designationMaster', workerRecruitment.designationMaster.id + '');
    formData.append('corespondAddress', workerRecruitment.corespondAddress);
    return this.http
      .post<IWorkerRecruitment>(this.resourceUrl, formData, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(file: File, workerRecruitment: IWorkerRecruitment): Observable<EntityResponseType> {
    const formData: FormData = new FormData();
    formData.append('file', file);
    formData.append('id', workerRecruitment.id + '');
    formData.append('aadharNo', workerRecruitment.aadharNo);
    formData.append('name', workerRecruitment.name);
    formData.append('dob', workerRecruitment.dob);
    formData.append('fatherName', workerRecruitment.fatherName);
    formData.append('address', workerRecruitment.address);
    formData.append('panNo', workerRecruitment.panNo);
    formData.append('bankBranch', workerRecruitment.bankBranch);
    formData.append('bankAccNo', workerRecruitment.bankAccNo);
    formData.append('bankMaster', workerRecruitment.bankMaster.id + '');
    formData.append('departmentMaster', workerRecruitment.departmentMaster.id + '');
    formData.append('designationMaster', workerRecruitment.designationMaster.id + '');
    formData.append('corespondAddress', workerRecruitment.corespondAddress);
    return this.http
      .post<IWorkerRecruitment>(this.resourceUrl + '-update', formData, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IWorkerRecruitment>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IWorkerRecruitment[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  queryCustom(req?: ITrailMockSearchOperation): Observable<EntityArrayResponseType> {
    return this.http
      .post<IWorkerRecruitment[]>(this.resourceUrl + '-custom', req, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  fetchAadhar(scan?: string, aadhar?: string): Observable<EntityResponseType> {
    const master = new Master();
    master.id = scan;
    master.desc = aadhar;
    return this.http.post<IWorkerRecruitment>(this.resourceUrlFetch, master, { observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(workerRecruitment: IWorkerRecruitment): IWorkerRecruitment {
    const copy: IWorkerRecruitment = Object.assign({}, workerRecruitment, {
      dob: workerRecruitment.dob != null && workerRecruitment.dob.isValid() ? workerRecruitment.dob.format(DATE_FORMAT) : null,
      createdDate:
        workerRecruitment.createdDate != null && workerRecruitment.createdDate.isValid() ? workerRecruitment.createdDate.toJSON() : null,
      lastUpdatedDate:
        workerRecruitment.lastUpdatedDate != null && workerRecruitment.lastUpdatedDate.isValid()
          ? workerRecruitment.lastUpdatedDate.toJSON()
          : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dob = res.body.dob != null ? moment(res.body.dob) : null;
      res.body.createdDate = res.body.createdDate != null ? moment(res.body.createdDate) : null;
      res.body.lastUpdatedDate = res.body.lastUpdatedDate != null ? moment(res.body.lastUpdatedDate) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((workerRecruitment: IWorkerRecruitment) => {
        workerRecruitment.dob = workerRecruitment.dob != null ? moment(workerRecruitment.dob) : null;
        workerRecruitment.createdDate = workerRecruitment.createdDate != null ? moment(workerRecruitment.createdDate) : null;
        workerRecruitment.lastUpdatedDate = workerRecruitment.lastUpdatedDate != null ? moment(workerRecruitment.lastUpdatedDate) : null;
      });
    }
    return res;
  }
}
