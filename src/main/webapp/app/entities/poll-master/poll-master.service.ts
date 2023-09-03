import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IPollMaster } from 'app/shared/model/poll-master.model';

type EntityResponseType = HttpResponse<IPollMaster>;
type EntityArrayResponseType = HttpResponse<IPollMaster[]>;

@Injectable({ providedIn: 'root' })
export class PollMasterService {
  public resourceUrl = SERVER_API_URL + 'api/poll-masters';

  constructor(protected http: HttpClient) {}

  create(pollMaster: IPollMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(pollMaster);
    return this.http
      .post<IPollMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(pollMaster: IPollMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(pollMaster);
    return this.http
      .put<IPollMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IPollMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IPollMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  publish(id: number): Observable<EntityResponseType> {
    return this.http.get<IPollMaster>(`${this.resourceUrl + '-publish'}/${id}`, { observe: 'response' });
  }

  download(id: number): any {
    return this.http.get(`${this.resourceUrl}-report/${id}`, { headers: new HttpHeaders({}), responseType: 'blob' });
  }

  protected convertDateFromClient(pollMaster: IPollMaster): IPollMaster {
    const copy: IPollMaster = Object.assign({}, pollMaster, {
      endDate: pollMaster.endDate != null && pollMaster.endDate.isValid() ? pollMaster.endDate.toJSON() : null,
      createdDate: pollMaster.createdDate != null && pollMaster.createdDate.isValid() ? pollMaster.createdDate.toJSON() : null,
      approvedDate: pollMaster.approvedDate != null && pollMaster.approvedDate.isValid() ? pollMaster.approvedDate.toJSON() : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.endDate = res.body.endDate != null ? moment(res.body.endDate) : null;
      res.body.createdDate = res.body.createdDate != null ? moment(res.body.createdDate) : null;
      res.body.approvedDate = res.body.approvedDate != null ? moment(res.body.approvedDate) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((pollMaster: IPollMaster) => {
        pollMaster.endDate = pollMaster.endDate != null ? moment(pollMaster.endDate) : null;
        pollMaster.createdDate = pollMaster.createdDate != null ? moment(pollMaster.createdDate) : null;
        pollMaster.approvedDate = pollMaster.approvedDate != null ? moment(pollMaster.approvedDate) : null;
      });
    }
    return res;
  }
}
