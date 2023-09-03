import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IPackingProductionEntry } from 'app/shared/model/packing-production-entry.model';
import { IProductionorderSearch } from 'app/shared/db2/model/productionorder-search.model';
import { IProductionorder } from 'app/shared/db2/model/productionorder.model';
import { IPackingProductionEntrySearch } from 'app/shared/model/packing-production-entry-search.model';
import { IMaster } from 'app/shared/model/master.modal';
import { IPackingProgressEntry } from 'app/shared/model/packing-progress-entry.model';
import { IStitchIssuePackDetails } from 'app/shared/model/stitch-issue-pack-details.model';

type EntityResponseType = HttpResponse<IPackingProductionEntry>;
type EntityArrayResponseType = HttpResponse<IPackingProductionEntry[]>;

@Injectable({ providedIn: 'root' })
export class PackingProductionEntryService {
  public resourceUrl = SERVER_API_URL + 'api/packing-production-entries';
  public resourceUrlPost = SERVER_API_URL + 'api/packing-production-entries-post';
  public resourceUrlQuery = SERVER_API_URL + 'api/packing-production-entries-filter';
  public resourceUrlFilter = SERVER_API_URL + 'api/production-order-filters-sewing';
  public resourceUrlOrders = SERVER_API_URL + 'api/productionorders-packing';
  public resourceUrlOperators = SERVER_API_URL + 'api/resources-users';
  public resourceUrlByWC = SERVER_API_URL + 'api/resourcesbyworkcenters';
  public resourceUrlBySewing = SERVER_API_URL + 'api/db2-sewing-operations';
  public resourceUrlOrdersDetails = SERVER_API_URL + 'api/productionordersdetailssewprd';
  public resourceUrlFilterBundle = SERVER_API_URL + 'api/packing-production-entries-bundle';
  public resourceUrlFilterPiece = SERVER_API_URL + 'api/packing-production-entries-piece';
  public resourceUrlDetails = SERVER_API_URL + 'api/packing-progress-details';

  constructor(protected http: HttpClient) {}

  create(sewingProductionEntry: IPackingProductionEntry): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(sewingProductionEntry);
    return this.http
      .post<IPackingProductionEntry>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(sewingProductionEntry: IPackingProductionEntry): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(sewingProductionEntry);
    return this.http
      .put<IPackingProductionEntry>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IPackingProductionEntry>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IPackingProductionEntry[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  queryFilter(req?: IPackingProductionEntrySearch): Observable<EntityArrayResponseType> {
    return this.http
      .post<IPackingProductionEntry[]>(this.resourceUrlQuery, req, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  queryCustom(search: IProductionorderSearch): Observable<HttpResponse<IProductionorder[]>> {
    return this.http.post<IProductionorder[]>(this.resourceUrlOrders, search, { observe: 'response' });
  }

  fetchDetails(search: IMaster): Observable<HttpResponse<IPackingProgressEntry[]>> {
    return this.http.post<IPackingProgressEntry[]>(this.resourceUrlBySewing, search, { observe: 'response' });
  }

  bundle(id: number): Observable<HttpResponse<IStitchIssuePackDetails[]>> {
    return this.http.get<IStitchIssuePackDetails[]>(`${this.resourceUrlFilterBundle}/${id}`, { observe: 'response' });
  }

  piece(id: number): Observable<HttpResponse<IStitchIssuePackDetails[]>> {
    return this.http.get<IStitchIssuePackDetails[]>(`${this.resourceUrlFilterPiece}/${id}`, { observe: 'response' });
  }

  post(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IPackingProductionEntry>(`${this.resourceUrlPost}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  deleteDetails(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrlDetails}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(sewingProductionEntry: IPackingProductionEntry): IPackingProductionEntry {
    const copy: IPackingProductionEntry = Object.assign({}, sewingProductionEntry, {
      createddate:
        sewingProductionEntry.createddate && sewingProductionEntry.createddate.isValid()
          ? sewingProductionEntry.createddate.toJSON()
          : undefined,
      updateddate:
        sewingProductionEntry.updateddate && sewingProductionEntry.updateddate.isValid()
          ? sewingProductionEntry.updateddate.toJSON()
          : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.createddate = res.body.createddate ? moment(res.body.createddate) : undefined;
      res.body.updateddate = res.body.updateddate ? moment(res.body.updateddate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((sewingProductionEntry: IPackingProductionEntry) => {
        sewingProductionEntry.createddate = sewingProductionEntry.createddate ? moment(sewingProductionEntry.createddate) : undefined;
        sewingProductionEntry.updateddate = sewingProductionEntry.updateddate ? moment(sewingProductionEntry.updateddate) : undefined;
      });
    }
    return res;
  }
}
