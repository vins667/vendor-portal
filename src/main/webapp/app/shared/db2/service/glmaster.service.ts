import { Injectable } from '@angular/core';
import { SERVER_API_URL } from 'app/app.constants';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { IGlmaster } from 'app/shared/db2/model/glmaster.model';
import { IFindocumentSearch } from 'app/shared/db2/model/findocument-search.model';
import { map } from 'rxjs/operators';

type EntityArrayResponseType = HttpResponse<IGlmaster[]>;
@Injectable({ providedIn: 'root' })
export class GlmasterService {
  public resourceUrl = SERVER_API_URL + 'api/db2-glmasters';
  public resourceUrlGlMasterAll = SERVER_API_URL + 'api/glmasters-code/';

  constructor(protected http: HttpClient) {}

  query(companycode: string): Observable<EntityArrayResponseType> {
    return this.http.get<IGlmaster[]>(`${this.resourceUrl}/${companycode}`, { observe: 'response' });
  }
  queryFilterGlCode(glmastercode: IGlmaster): Observable<EntityArrayResponseType> {
    const code = glmastercode.id.code;
    return this.http
      .get<IGlmaster[]>(`${this.resourceUrlGlMasterAll}/${code}`, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => res));
  }
}
