import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { IVendorBranchDetails } from 'app/shared/model/vendor-branch-details.model';
import { IBranchSearch } from 'app/shared/model/branch-search.model';

type EntityResponseType = HttpResponse<IVendorBranchDetails>;
type EntityArrayResponseType = HttpResponse<IVendorBranchDetails[]>;

@Injectable({ providedIn: 'root' })
export class VendorBranchDetailsService {
  public resourceUrl = SERVER_API_URL + 'api/vendor-branch-details';

  constructor(protected http: HttpClient) {}

  create(vendorBranchDetails: IVendorBranchDetails): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(vendorBranchDetails);
    return this.http
      .post<IVendorBranchDetails>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(vendorBranchDetails: IVendorBranchDetails): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(vendorBranchDetails);
    return this.http
      .put<IVendorBranchDetails>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IVendorBranchDetails>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: IBranchSearch): Observable<EntityArrayResponseType> {
    return this.http
      .post<IVendorBranchDetails[]>(this.resourceUrl, req, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  transaction(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IVendorBranchDetails>(`${this.resourceUrl + '-transaction'}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  protected convertDateFromClient(vendorBranchDetails: IVendorBranchDetails): IVendorBranchDetails {
    const copy: IVendorBranchDetails = Object.assign({}, vendorBranchDetails, {
      createdDate:
        vendorBranchDetails.createdDate != null && vendorBranchDetails.createdDate.isValid()
          ? vendorBranchDetails.createdDate.toJSON()
          : null,
      lastUpdatedDate:
        vendorBranchDetails.lastUpdatedDate != null && vendorBranchDetails.lastUpdatedDate.isValid()
          ? vendorBranchDetails.lastUpdatedDate.toJSON()
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
      res.body.forEach((vendorBranchDetails: IVendorBranchDetails) => {
        vendorBranchDetails.createdDate = vendorBranchDetails.createdDate != null ? moment(vendorBranchDetails.createdDate) : null;
        vendorBranchDetails.lastUpdatedDate =
          vendorBranchDetails.lastUpdatedDate != null ? moment(vendorBranchDetails.lastUpdatedDate) : null;
      });
    }
    return res;
  }
}
