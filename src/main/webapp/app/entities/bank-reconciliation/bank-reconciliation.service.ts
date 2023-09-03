import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { IFindocument } from 'app/shared/db2/model/findocument.model';
import { IFindocumentSearch } from 'app/shared/db2/model/findocument-search.model';
import { IBankReconciliation } from 'app/shared/db2/model/bank-reconciliation.model';
import { IMasterParameters } from 'app/shared/db2/model/master-parameters.model';
import { IBankReconciliationdetail } from 'app/shared/db2/model/bank-reconciliationdetail.model';
import { IDebitNoteEntry } from 'app/shared/db2/model/debit-note-entry.model';
import { IGlmaster } from 'app/shared/db2/model/glmaster.model';
type EntityResponseType = HttpResponse<IFindocument>;
type EntityArrayResponseType = HttpResponse<IFindocument[]>;
type EntityArrayGl = HttpResponse<IGlmaster[]>;
type EntitySingleResponse = HttpResponse<IBankReconciliation>;
type EntityResponse = HttpResponse<IBankReconciliation[]>;
// type EntityResponse = HttpResponse<IFindocumentSearch>;

@Injectable({ providedIn: 'root' })
export class BankReconciliationService {
  public findocument?: IFindocument;
  public resourceUrl = SERVER_API_URL + 'api/debit-note-entry';
  public resourceUrlFindocument = SERVER_API_URL + 'api/findocuments/';
  public resourceUrlglcode = SERVER_API_URL + 'api/glmasters/';
  public resourceUrlReconcileDetailList = SERVER_API_URL + 'api/bank-reconcilation-list/';
  public resourceUrlSearchbyParameter = SERVER_API_URL + 'api/bank-reconcilation-search';
  public resourceUrlSave = SERVER_API_URL + 'api/bank-reconcilation-save';
  public resourceUrlPost = SERVER_API_URL + 'api/bank-reconcilation-post';

  constructor(protected http: HttpClient) {}

  queryFilter(findocumentSearch: IFindocumentSearch): Observable<EntityArrayResponseType> {
    const findocumentcode = findocumentSearch.code;
    return this.http
      .get<IFindocument[]>(`${this.resourceUrlFindocument}/${findocumentcode}`, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => res));
  }

  create(bankReconciliation: IBankReconciliation): Observable<EntitySingleResponse> {
    return this.http.post<IBankReconciliation>(this.resourceUrlSave, bankReconciliation, { observe: 'response' });
  }

  post(bankReconciliationDetails: IBankReconciliationdetail[]): Observable<HttpResponse<IBankReconciliationdetail[]>> {
    return this.http.post<IBankReconciliationdetail[]>(this.resourceUrlPost, bankReconciliationDetails, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IFindocument>(`${this.resourceUrl}/${id}`, { observe: 'response' }).pipe(map((res: EntityResponseType) => res));
  }

  findGlCode(longdescription: string): Observable<EntityArrayResponseType> {
    return this.http
      .get<IFindocument[]>(`${this.resourceUrl}/${longdescription}`, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => res));
  }

  getbankReconcileDetailList(masterparameters: IMasterParameters): Observable<HttpResponse<IBankReconciliationdetail[]>> {
    return this.http.post<IBankReconciliationdetail[]>(this.resourceUrlReconcileDetailList, masterparameters, { observe: 'response' });
  }

  searchByparameters(masterparameters: IMasterParameters): Observable<HttpResponse<IBankReconciliationdetail[]>> {
    return this.http.post<IBankReconciliationdetail[]>(this.resourceUrlSearchbyParameter, masterparameters, { observe: 'response' });
  }

  searchFilter(longdescription: string): Observable<EntityArrayGl> {
    return this.http
      .get<IGlmaster[]>(`${this.resourceUrlglcode}/${longdescription}`, { observe: 'response' })
      .pipe(map((res: EntityArrayGl) => res));
  }
}
