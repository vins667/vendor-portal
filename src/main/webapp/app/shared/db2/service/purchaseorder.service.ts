import { Injectable } from '@angular/core';
import { SERVER_API_URL } from 'app/app.constants';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { createRequestOption } from 'app/shared/util/request-util';
import { IPurchaseorder } from 'app/shared/db2/model/purchaseorder.model';

type EntityArrayResponseType = HttpResponse<IPurchaseorder[]>;
@Injectable({ providedIn: 'root' })
export class PurchaseorderService {
  public resourceUrl = SERVER_API_URL + 'api/db2-purchaseorders';

  constructor(protected http: HttpClient) {}

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IPurchaseorder[]>(this.resourceUrl, { params: options, observe: 'response' });
  }
}
