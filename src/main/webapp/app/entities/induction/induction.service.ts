import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IJobProfile } from 'app/shared/model/job-profile.model';
import { IJobProfileSearch } from 'app/shared/model/job-profile-search.model';
import { IMaster } from 'app/shared/model/master.modal';
import { IMessage } from 'app/shared/model/message.model';

type EntityResponseType = HttpResponse<IJobProfile>;
type EntityArrayResponseType = HttpResponse<IJobProfile[]>;

@Injectable({ providedIn: 'root' })
export class InductionService {
  public resourceUrl = SERVER_API_URL + 'api/job-profiles';

  constructor(protected http: HttpClient) {}

  create(jobProfile: IJobProfile): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(jobProfile);
    return this.http
      .post<IJobProfile>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(jobProfile: IJobProfile): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(jobProfile);
    return this.http
      .put<IJobProfile>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  updateMultiple(jobProfiles: IJobProfile[]): Observable<HttpResponse<IMessage>> {
    const jobProfilesData = [];
    jobProfiles.forEach(jobProfile => {
      const copy = this.convertDateFromClient(jobProfile);
      jobProfilesData.push(copy);
    });

    return this.http.post<IMessage>(this.resourceUrl + '-multiple', jobProfilesData, { observe: 'response' });
  }

  find(id: number): Observable<EntityArrayResponseType> {
    return this.http
      .get<IJobProfile[]>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  findCustom(id: number): Observable<EntityArrayResponseType> {
    return this.http
      .get<IJobProfile[]>(`${this.resourceUrl + '-designation'}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IJobProfile[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  queryCustom(req?: IJobProfileSearch): Observable<EntityArrayResponseType> {
    return this.http
      .post<IJobProfile[]>(this.resourceUrl + '-custom', req, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  download(id: number): any {
    return this.http.get(`${this.resourceUrl}-download/${id}`, { headers: new HttpHeaders({}), responseType: 'blob' });
  }

  createUpload(file: File, id: number): Observable<EntityResponseType> {
    const formData: FormData = new FormData();
    formData.append('file', file);
    formData.append('id', id + '');
    return this.http
      .post<IJobProfile>(this.resourceUrl + '-attach', formData, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  department(): Observable<HttpResponse<IMaster[]>> {
    return this.http.get<IMaster[]>(this.resourceUrl + '-department', { observe: 'response' });
  }

  protected convertDateFromClient(jobProfile: IJobProfile): IJobProfile {
    const copy: IJobProfile = Object.assign({}, jobProfile, {
      createdDate: jobProfile.createdDate != null && jobProfile.createdDate.isValid() ? jobProfile.createdDate.toJSON() : null
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
      res.body.forEach((jobProfile: IJobProfile) => {
        jobProfile.createdDate = jobProfile.createdDate != null ? moment(jobProfile.createdDate) : null;
      });
    }
    return res;
  }
}
