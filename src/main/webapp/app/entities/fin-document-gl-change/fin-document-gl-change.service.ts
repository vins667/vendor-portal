import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { IFindocument } from 'app/shared/db2/model/findocument.model';
import { IFindocumentSearch } from 'app/shared/db2/model/findocument-search.model';
import { IFindocumentId } from 'app/shared/db2/model/findocument-id.model';
import { IViewfindocument } from 'app/shared/db2/model/viewfindocument.model';
import { IMasterParameters } from 'app/shared/db2/model/master-parameters.model';
import { IDebitNoteEntry } from 'app/shared/db2/model/debit-note-entry.model';
import { IMrnBean } from 'app/shared/model/mrn-bean.model';
import { IFinDocumentGlChange } from './fin-document-gl-change.model';
import { IGlmaster } from 'app/shared/db2/model/glmaster.model';

type EntityResponseType = HttpResponse<IFindocument>;
type EntityArrayResponseType = HttpResponse<IFindocument[]>;
type EntityArrayResponse = HttpResponse<IFinDocumentGlChange[]>;
type EntityResponse = HttpResponse<IFindocumentSearch>;

@Injectable({ providedIn: 'root' })
export class FinDocumentGlChangeService {
  public findocument?: IFindocument;
  public resourceUrl = SERVER_API_URL + 'api/debit-note-entry';
  public resourceUrlDetails = SERVER_API_URL + 'api/debit-note-entry-delete';
  public resourceUrlFindocument = SERVER_API_URL + 'api/findocuments/';
  public resourceUrlFindocumentAll = SERVER_API_URL + 'api/findocuments-gl-change/';
  public resourceUrlViewfindocument = SERVER_API_URL + 'api/viewfindocuments';
  public resourceUrlGstType = SERVER_API_URL + 'api/debit-note-entry-gst';
  public resourceUrldebitNote = SERVER_API_URL + 'api/viewfindocuments-save';
  public resourceUrldebitNoteLock = SERVER_API_URL + 'api/debit-note-entry-savelock';
  public resourceUrldebitNoteDetail = SERVER_API_URL + 'api/debit-note-details';
  public resourceUrldebitNoteMrn = SERVER_API_URL + 'api/debit-note-entry-mrn-fetch';
  public resourceUrlGlMasterAll = SERVER_API_URL + 'api/glmasters-code/';

  constructor(protected http: HttpClient) {}

  create(viewfindocuments: IViewfindocument[]): Observable<EntityArrayResponse> {
    return this.http
      .post<IFinDocumentGlChange[]>(this.resourceUrldebitNote, viewfindocuments, { observe: 'response' })
      .pipe(map((res: EntityArrayResponse) => res));
  }

  lock(finDocumentGlChange: IFinDocumentGlChange[]): Observable<EntityArrayResponse> {
    return this.http
      .post<IDebitNoteEntry[]>(this.resourceUrldebitNoteLock, finDocumentGlChange, { observe: 'response' })
      .pipe(map((res: EntityArrayResponse) => res));
  }

  queryFilter(findocumentSearch: IFindocumentSearch): Observable<EntityArrayResponseType> {
    const findocumentcode = findocumentSearch.code;
    return this.http
      .get<IFindocument[]>(`${this.resourceUrlFindocumentAll}/${findocumentcode}`, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => res));
  }

  linedetailbyId(id: IFindocumentId): Observable<HttpResponse<IViewfindocument[]>> {
    return this.http.post<IViewfindocument[]>(this.resourceUrlViewfindocument, id, { observe: 'response' });
  }

  deleteDetail(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrlDetails}/${id}`, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IFindocument>(`${this.resourceUrl}/${id}`, { observe: 'response' }).pipe(map((res: EntityResponseType) => res));
  }

  getgsttype(masterparameters: IMasterParameters): Observable<HttpResponse<IMasterParameters>> {
    return this.http.post<IMasterParameters>(this.resourceUrlGstType, masterparameters, { observe: 'response' });
  }

  getDebitNoteDetail(masterparameters: IMasterParameters): Observable<HttpResponse<IFinDocumentGlChange[]>> {
    return this.http.post<IFinDocumentGlChange[]>(this.resourceUrldebitNoteDetail, masterparameters, { observe: 'response' });
  }

  getDebitNoteMrn(masterparameters: IMasterParameters): Observable<HttpResponse<IMrnBean[]>> {
    return this.http.post<IMrnBean[]>(this.resourceUrldebitNoteMrn, masterparameters, { observe: 'response' });
  }
}
