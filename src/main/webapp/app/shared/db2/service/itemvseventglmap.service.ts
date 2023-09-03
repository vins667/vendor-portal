import { Injectable } from '@angular/core';
import { SERVER_API_URL } from 'app/app.constants';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { IItemvseventglmap } from 'app/shared/db2/model/itemvseventglmap.model';
import { IItemvseventglmapSearch } from 'app/shared/db2/model/itemvseventglmap-search.model';

type EntityResponseType = HttpResponse<IItemvseventglmap>;
@Injectable({ providedIn: 'root' })
export class ItemvseventglmapService {
  public resourceUrl = SERVER_API_URL + 'api/db2-itemvseventglmaps';

  constructor(protected http: HttpClient) {}

  query(search: IItemvseventglmapSearch): Observable<EntityResponseType> {
    return this.http.post<IItemvseventglmap>(`${this.resourceUrl}`, search, { observe: 'response' });
  }
}
