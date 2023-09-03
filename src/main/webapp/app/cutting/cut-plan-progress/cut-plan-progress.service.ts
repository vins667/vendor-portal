import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICutPlanEntry } from 'app/shared/model/cut-plan-entry.model';
import { IMaster, Master } from 'app/shared/model/master.modal';
import { IProductionorderSearch } from 'app/shared/db2/model/productionorder-search.model';
import { IProductionorder } from 'app/shared/db2/model/productionorder.model';
import { IBalanceSuggestionSearch } from 'app/shared/db2/model/balance-suggestion-search.model';
import { ILotBean } from 'app/shared/db2/model/lot-bean.model';
import { ICutQuantity } from 'app/shared/model/cut-quantity.model';
import { IFullitemkeydecoder } from 'app/shared/db2/model/fulltemkeydecoder.model';
import { ICutPlanEntryDetails } from 'app/shared/model/cut-plan-entry-details.model';
import { ICutPlanSearch } from 'app/shared/model/cut-plan-search.model';
import { IUserPlant } from 'app/shared/model/user-plant.model';

type EntityResponseType = HttpResponse<ICutPlanEntry>;
type EntityArrayResponseType = HttpResponse<ICutPlanEntry[]>;

@Injectable({ providedIn: 'root' })
export class CutPlanProgressService {
  public resourceUrl = SERVER_API_URL + 'api/cut-plan-entries-progress';
  public resourceUrlUpdate = SERVER_API_URL + 'api/cut-plan-progresses';
  public resourceUrlPost = SERVER_API_URL + 'api/cut-plan-progresses-post';
  public resourceUrlFilter = SERVER_API_URL + 'api/cut-plan-progresses-filter';

  constructor(protected http: HttpClient) {}

  update(cutPlanEntry: ICutPlanEntry): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(cutPlanEntry);
    return this.http
      .put<ICutPlanEntry>(this.resourceUrlUpdate, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ICutPlanEntry>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  queryFilter(search: ICutPlanSearch): Observable<EntityArrayResponseType> {
    return this.http
      .post<ICutPlanEntry[]>(this.resourceUrlFilter, search, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  post(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ICutPlanEntry>(`${this.resourceUrlPost}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  protected convertDateFromClient(cutPlanEntry: ICutPlanEntry): ICutPlanEntry {
    const copy: ICutPlanEntry = Object.assign({}, cutPlanEntry, {
      createddate: cutPlanEntry.createddate != null && cutPlanEntry.createddate.isValid() ? cutPlanEntry.createddate.toJSON() : null,
      lastupdateddate:
        cutPlanEntry.lastupdateddate != null && cutPlanEntry.lastupdateddate.isValid() ? cutPlanEntry.lastupdateddate.toJSON() : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.createddate = res.body.createddate != null ? moment(res.body.createddate) : null;
      res.body.lastupdateddate = res.body.lastupdateddate != null ? moment(res.body.lastupdateddate) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((cutPlanEntry: ICutPlanEntry) => {
        cutPlanEntry.createddate = cutPlanEntry.createddate != null ? moment(cutPlanEntry.createddate) : null;
        cutPlanEntry.lastupdateddate = cutPlanEntry.lastupdateddate != null ? moment(cutPlanEntry.lastupdateddate) : null;
      });
    }
    return res;
  }
}
