import { Injectable } from '@angular/core';
import { SERVER_API_URL } from 'app/app.constants';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ViewDitaxglmapping } from 'app/shared/db2/model/view-ditaxglmapping.model';
import { IMaster } from 'app/shared/model/master.modal';

type EntityArrayResponseType = HttpResponse<ViewDitaxglmapping[]>;
@Injectable({ providedIn: 'root' })
export class ViewDitaxglmappingService {
  public resourceUrl = SERVER_API_URL + 'api/db2-viewditaxglmappings';
  public resourceUrlCGST = SERVER_API_URL + 'api/hsnmapping-cgst';
  public resourceUrlIGST = SERVER_API_URL + 'api/hsnmapping-igst';

  constructor(protected http: HttpClient) {}

  query(companycode: string): Observable<EntityArrayResponseType> {
    return this.http.get<ViewDitaxglmapping[]>(`${this.resourceUrl}/${companycode}`, { observe: 'response' });
  }

  cgst(master: IMaster): Observable<EntityArrayResponseType> {
    return this.http.post<ViewDitaxglmapping[]>(`${this.resourceUrlCGST}`, master, { observe: 'response' });
  }

  igst(master: IMaster): Observable<EntityArrayResponseType> {
    return this.http.post<ViewDitaxglmapping[]>(`${this.resourceUrlIGST}`, master, { observe: 'response' });
  }
}
