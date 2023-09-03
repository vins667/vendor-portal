import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IWorkerJoining } from 'app/shared/model/worker-joining.model';
import { IWorkerRecruitment } from 'app/shared/model/worker-recruitment.model';
import { ITrailMockSearchOperation } from 'app/shared/model/trail-mock-search.model';
import { IWorkerFamilyDetails } from 'app/shared/model/worker-family-details.model';
import { IWorkerDocumentDetails } from 'app/shared/model/worker-document-details.model';
import { IWorkerMaster } from 'app/shared/model/worker-master.model';
import { IWorkerJoinFlowBean } from 'app/shared/model/worker-join-flow-bean.model';
import { IWorkerJoinFlowDetails } from 'app/shared/model/worker-join-flow-details.model';

type EntityResponseType = HttpResponse<IWorkerJoining>;
type EntityArrayResponseType = HttpResponse<IWorkerJoining[]>;
type EntityArrayResponseTypeWj = HttpResponse<IWorkerRecruitment[]>;
type EntityArrayResponseTypeWF = HttpResponse<IWorkerFamilyDetails[]>;

@Injectable({ providedIn: 'root' })
export class WorkerJoiningService {
  public resourceUrl = SERVER_API_URL + 'api/worker-joinings';
  public resourceUrlMaster = SERVER_API_URL + 'api/worker-recruitments-master';
  public resourceUrlCustom = SERVER_API_URL + 'api/worker-joinings-custom';
  public resourceUrlFamily = SERVER_API_URL + 'api/worker-family-details';
  public resourceUrlExperience = SERVER_API_URL + 'api/worker-jobs-details';
  public resourceUrlLanguage = SERVER_API_URL + 'api/worker-language-details';
  public resourceUrlNomination = SERVER_API_URL + 'api/worker-nomination-details';
  public resourceUrlEducation = SERVER_API_URL + 'api/worker-education-details';
  public resourceUrlReference = SERVER_API_URL + 'api/worker-reference-details';
  public resourceUrlDocument = SERVER_API_URL + 'api/worker-document-details';
  public resourceUrlWorkflow = SERVER_API_URL + 'api/worker-join-flow-details';
  constructor(protected http: HttpClient) {}

  create(workerJoining: IWorkerJoining): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(workerJoining);
    return this.http
      .post<IWorkerJoining>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(workerJoining: IWorkerJoining): Observable<EntityResponseType> {
    return this.http
      .post<IWorkerJoining>(this.resourceUrl + '-update', workerJoining, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IWorkerJoining>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IWorkerJoining[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  querywj(req?: ITrailMockSearchOperation): Observable<EntityArrayResponseTypeWj> {
    return this.http
      .post<IWorkerJoining[]>(this.resourceUrlCustom, req, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseTypeWj) => this.convertDateArrayFromServer(res)));
  }

  findFamily(id: number): Observable<EntityArrayResponseTypeWF> {
    return this.http.get<IWorkerFamilyDetails[]>(`${this.resourceUrlFamily}/${id}`, { observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  deleteFamily(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrlFamily}/${id}`, { observe: 'response' });
  }

  deleteExperience(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrlExperience}/${id}`, { observe: 'response' });
  }

  deleteLanguage(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrlLanguage}/${id}`, { observe: 'response' });
  }

  deleteNomination(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrlNomination}/${id}`, { observe: 'response' });
  }

  deleteEducation(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrlEducation}/${id}`, { observe: 'response' });
  }

  deleteReference(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrlReference}/${id}`, { observe: 'response' });
  }

  uploadSignature(file: File, id: number, docId: number, docType: string): Observable<EntityResponseType> {
    const formData: FormData = new FormData();
    formData.append('file', file);
    formData.append('id', id + '');
    formData.append('docId', docId + '');
    formData.append('docType', docType);
    return this.http.post<IWorkerDocumentDetails>(this.resourceUrlDocument + '-signature', formData, { observe: 'response' });
  }

  uploadDocument(file: File, id: number, docId: number, docType: string): Observable<EntityResponseType> {
    const formData: FormData = new FormData();
    formData.append('file', file);
    formData.append('id', id + '');
    formData.append('docId', docId + '');
    formData.append('docType', docType);
    return this.http.post<IWorkerDocumentDetails>(this.resourceUrlDocument + '-document', formData, { observe: 'response' });
  }

  download(id: number): any {
    return this.http.get(`${this.resourceUrlDocument}-download/${id}`, { headers: new HttpHeaders({}), responseType: 'blob' });
  }

  downloadSignature(id: number): any {
    return this.http.get(`${this.resourceUrlDocument}-signature-download/${id}`, {
      headers: new HttpHeaders({}),
      responseType: 'blob'
    });
  }

  master(): Observable<HttpResponse<IWorkerMaster>> {
    return this.http.get<IWorkerMaster>(this.resourceUrlMaster, { observe: 'response' });
  }

  workFlow(id: number): Observable<HttpResponse<IWorkerJoinFlowBean>> {
    return this.http.get<IWorkerJoinFlowBean>(`${this.resourceUrlWorkflow}/${id}`, { observe: 'response' });
  }

  updateFlow(workerJoinFlowDetails: IWorkerJoinFlowDetails): Observable<HttpResponse<IWorkerJoinFlowDetails>> {
    const copy = this.convertDateFromClientWorkFlow(workerJoinFlowDetails);
    return this.http
      .put<IWorkerJoinFlowDetails>(this.resourceUrlWorkflow, copy, { observe: 'response' })
      .pipe(map((res: HttpResponse<IWorkerJoinFlowDetails>) => this.convertDateFromServerWorkFlow(res)));
  }

  protected convertDateFromClient(workerJoining: IWorkerJoining): IWorkerJoining {
    const copy: IWorkerJoining = Object.assign({}, workerJoining, {
      createdDate: workerJoining.createdDate != null && workerJoining.createdDate.isValid() ? workerJoining.createdDate.toJSON() : null,
      lastUpdatedDate:
        workerJoining.lastUpdatedDate != null && workerJoining.lastUpdatedDate.isValid() ? workerJoining.lastUpdatedDate.toJSON() : null
    });
    if (copy.workerJobsDetails) {
      copy.workerJobsDetails.forEach(workerJobsDetails => {
        workerJobsDetails.fromDate = workerJobsDetails.fromDate != null ? moment(workerJobsDetails.fromDate) : null;
        workerJobsDetails.toDate = workerJobsDetails.toDate != null ? moment(workerJobsDetails.toDate) : null;
      });
    }
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
      res.body.forEach((workerJoining: IWorkerJoining) => {
        workerJoining.createdDate = workerJoining.createdDate != null ? moment(workerJoining.createdDate) : null;
        workerJoining.lastUpdatedDate = workerJoining.lastUpdatedDate != null ? moment(workerJoining.lastUpdatedDate) : null;
      });
    }
    return res;
  }

  protected convertDateArrayFromServerWj(res: EntityArrayResponseTypeWj): EntityArrayResponseTypeWj {
    if (res.body) {
      res.body.forEach((workerJoining: IWorkerRecruitment) => {
        workerJoining.createdDate = workerJoining.createdDate != null ? moment(workerJoining.createdDate) : null;
        workerJoining.lastUpdatedDate = workerJoining.lastUpdatedDate != null ? moment(workerJoining.lastUpdatedDate) : null;
      });
    }
    return res;
  }

  protected convertDateFromClientWorkFlow(workerJoinFlowDetails: IWorkerJoinFlowDetails): IWorkerJoinFlowDetails {
    const copy: IWorkerJoinFlowDetails = Object.assign({}, workerJoinFlowDetails, {
      authDate:
        workerJoinFlowDetails.authDate != null && workerJoinFlowDetails.authDate.isValid() ? workerJoinFlowDetails.authDate.toJSON() : null
    });
    return copy;
  }

  protected convertDateFromServerWorkFlow(res: HttpResponse<IWorkerJoinFlowDetails>): EntityResponseType {
    if (res.body) {
      res.body.authDate = res.body.authDate != null ? moment(res.body.authDate) : null;
    }
    return res;
  }
}
