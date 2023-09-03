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
export class CutPlanMrkrEntryService {
  public resourceUrl = SERVER_API_URL + 'api/cut-plan-entries';
  public resourceUrlUserPlant = SERVER_API_URL + 'api/user-plants';
  public resourceUrlFilter = SERVER_API_URL + 'api/cut-plan-entries-filter';
  public resourceUrlDb2Project = SERVER_API_URL + 'api/db2-productionorder-project';
  public resourceUrlDb2Country = SERVER_API_URL + 'api/db2-productiondemand-countries';
  public resourceUrldb2Color = SERVER_API_URL + 'api/db2-productiondemand-colors';
  public resourceUrlDb2Quantity = SERVER_API_URL + 'api/db2-productiondemand-quantities';
  public resourceUrldb2Marker = SERVER_API_URL + 'api/marker-master-list';
  public resourceUrlOrders = SERVER_API_URL + 'api/productionorders';
  public resourceUrlSuggestion = SERVER_API_URL + 'api/balance-suggestions';
  public resourceUrldb2reservationItem = SERVER_API_URL + 'api/db2-productionreservation-itemcode';
  public resourceUrlFabricIssueList = SERVER_API_URL + 'api/cut-fabric-issue-details';
  public resourceUrlDb2Destinations = SERVER_API_URL + 'api/db2-salesorders-destinations';

  constructor(protected http: HttpClient) {}

  create(cutPlanEntry: ICutPlanEntry): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(cutPlanEntry);
    return this.http
      .post<ICutPlanEntry>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(cutPlanEntry: ICutPlanEntry): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(cutPlanEntry);
    return this.http
      .put<ICutPlanEntry>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ICutPlanEntry>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  release(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ICutPlanEntry>(`${this.resourceUrl + '-release'}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ICutPlanEntry[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  plants(): Observable<HttpResponse<IUserPlant[]>> {
    return this.http.get<IUserPlant[]>(this.resourceUrlUserPlant, { observe: 'response' });
  }

  destPlants(master?: IMaster): Observable<HttpResponse<IUserPlant[]>> {
    return this.http.post<IUserPlant[]>(this.resourceUrlUserPlant, master, { observe: 'response' });
  }

  queryFilter(search: ICutPlanSearch): Observable<EntityArrayResponseType> {
    return this.http
      .post<ICutPlanEntry[]>(this.resourceUrlFilter, search, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  queryCustom(search: IProductionorderSearch): Observable<HttpResponse<IProductionorder[]>> {
    return this.http.post<IProductionorder[]>(this.resourceUrlOrders, search, { observe: 'response' });
  }

  queryCountryById(productOrder: string, styleCode: string): Observable<HttpResponse<IMaster[]>> {
    const master = new Master();
    master.name = productOrder;
    master.desc = styleCode;
    return this.http.post<IMaster[]>(this.resourceUrlDb2Country, master, { observe: 'response' });
  }

  fetchlistbyId(master: Master): Observable<HttpResponse<ICutPlanEntryDetails[]>> {
    return this.http.post<ICutPlanEntryDetails[]>(this.resourceUrlFabricIssueList, master, { observe: 'response' });
  }

  queryColorByCountry(master: Master): Observable<HttpResponse<IMaster[]>> {
    return this.http.post<IMaster[]>(this.resourceUrldb2Color, master, { observe: 'response' });
  }

  getAllReservationItemByPo(master: Master): Observable<HttpResponse<IFullitemkeydecoder[]>> {
    return this.http.post<IFullitemkeydecoder[]>(this.resourceUrldb2reservationItem, master, { observe: 'response' });
  }

  getAllDetailByPo(ponumber: string): Observable<HttpResponse<IMaster>> {
    const master = new Master();
    master.name = ponumber;
    return this.http.post<IMaster>(this.resourceUrlDb2Project, master, { observe: 'response' });
  }

  getAllMarkerByKey(master: Master): Observable<HttpResponse<IMaster[]>> {
    return this.http.post<IMaster[]>(this.resourceUrldb2Marker, master, { observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  suggestion(search?: IBalanceSuggestionSearch): Observable<HttpResponse<ILotBean[]>> {
    return this.http.post<ILotBean[]>(this.resourceUrlSuggestion, search, { observe: 'response' });
  }

  fetchQuantity(search?: IBalanceSuggestionSearch): Observable<HttpResponse<ICutQuantity[]>> {
    return this.http.post<ICutQuantity[]>(this.resourceUrlDb2Quantity, search, { observe: 'response' });
  }

  queryColorByDestination(master: Master): Observable<HttpResponse<IMaster[]>> {
    return this.http.post<IMaster[]>(this.resourceUrlDb2Destinations, master, { observe: 'response' });
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
