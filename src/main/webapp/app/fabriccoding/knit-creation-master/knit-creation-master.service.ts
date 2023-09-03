import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IKnitCreationMaster } from 'app/shared/model/knit-creation-master.model';
import { IKnitCreationSearchMaster } from 'app/shared/model/knit-creation-search-master.model';
import { IYarnCountMaster } from 'app/shared/model/yarn-count-master.model';
import { IMaster } from 'app/shared/model/master.modal';

type EntityResponseType = HttpResponse<IKnitCreationMaster>;
type EntityArrayResponseType = HttpResponse<IKnitCreationMaster[]>;

@Injectable({ providedIn: 'root' })
export class KnitCreationMasterService {
  public resourceUrl = SERVER_API_URL + 'api/knit-creation-masters';

  constructor(protected http: HttpClient) {}

  create(knitCreationMaster: IKnitCreationMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(knitCreationMaster);
    return this.http
      .post<IKnitCreationMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(knitCreationMaster: IKnitCreationMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(knitCreationMaster);
    return this.http
      .put<IKnitCreationMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IKnitCreationMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IKnitCreationMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  queryCustom(masterSearch?: IMaster): Observable<EntityArrayResponseType> {
    return this.http
      .post<IKnitCreationMaster[]>(this.resourceUrl + '-custom', masterSearch, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  queryYarnCount(knitCreationSearchMaster?: IKnitCreationSearchMaster): Observable<EntityArrayResponseType> {
    return this.http
      .post<IYarnCountMaster[]>(this.resourceUrl + '-count', knitCreationSearchMaster, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  queryYarnType(knitCreationSearchMaster?: IKnitCreationSearchMaster): Observable<EntityArrayResponseType> {
    return this.http
      .post<IYarnCountMaster[]>(this.resourceUrl + '-type', knitCreationSearchMaster, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  queryKnitType(knitCreationSearchMaster?: IKnitCreationSearchMaster): Observable<EntityArrayResponseType> {
    return this.http
      .post<IYarnCountMaster[]>(this.resourceUrl + '-knit', knitCreationSearchMaster, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  queryProcess(knitCreationSearchMaster?: IKnitCreationSearchMaster): Observable<EntityArrayResponseType> {
    return this.http
      .post<IYarnCountMaster[]>(this.resourceUrl + '-process', knitCreationSearchMaster, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(knitCreationMaster: IKnitCreationMaster): IKnitCreationMaster {
    const copy: IKnitCreationMaster = Object.assign({}, knitCreationMaster, {
      createdDate:
        knitCreationMaster.createdDate != null && knitCreationMaster.createdDate.isValid() ? knitCreationMaster.createdDate.toJSON() : null,
      lastUpdatedDate:
        knitCreationMaster.lastUpdatedDate != null && knitCreationMaster.lastUpdatedDate.isValid()
          ? knitCreationMaster.lastUpdatedDate.toJSON()
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
      res.body.forEach((knitCreationMaster: IKnitCreationMaster) => {
        knitCreationMaster.createdDate = knitCreationMaster.createdDate != null ? moment(knitCreationMaster.createdDate) : null;
        knitCreationMaster.lastUpdatedDate = knitCreationMaster.lastUpdatedDate != null ? moment(knitCreationMaster.lastUpdatedDate) : null;
      });
    }
    return res;
  }
}
