import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IVendorAuditQuesMaster } from 'app/shared/model/vendor-audit-ques-master.model';
import { IVendorAuditGroupMasterBean } from 'app/shared/model/vendor-audit-group-master-bean.model';

type EntityResponseType = HttpResponse<IVendorAuditQuesMaster>;
type EntityArrayResponseType = HttpResponse<IVendorAuditQuesMaster[]>;

@Injectable({ providedIn: 'root' })
export class VendorAuditQuesMasterService {
    public resourceUrl = SERVER_API_URL + 'api/vendor-audit-ques-masters';
  public resourceUrlDetails = SERVER_API_URL + 'api/vendor-audit-ques-details';

    constructor(protected http: HttpClient) {}

    create(vendorAuditQuesMaster: IVendorAuditQuesMaster): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(vendorAuditQuesMaster);
        return this.http
            .post<IVendorAuditQuesMaster>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(vendorAuditQuesMaster: IVendorAuditQuesMaster): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(vendorAuditQuesMaster);
        return this.http
            .put<IVendorAuditQuesMaster>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IVendorAuditQuesMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    findDetails(id: number): Observable<HttpResponse<IVendorAuditGroupMasterBean[]>> {
      return this.http
        .get<IVendorAuditGroupMasterBean[]>(`${this.resourceUrlDetails + '-master-id'}/${id}`, {observe: 'response'});
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IVendorAuditQuesMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    deleteDetail(id: number): Observable<HttpResponse<any>> {
      return this.http.delete<any>(`${this.resourceUrlDetails}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(vendorAuditQuesMaster: IVendorAuditQuesMaster): IVendorAuditQuesMaster {
        const copy: IVendorAuditQuesMaster = Object.assign({}, vendorAuditQuesMaster, {
            createdDate:
                vendorAuditQuesMaster.createdDate != null && vendorAuditQuesMaster.createdDate.isValid()
                    ? vendorAuditQuesMaster.createdDate.toJSON()
                    : null,
            lastUpdatedDate:
                vendorAuditQuesMaster.lastUpdatedDate != null && vendorAuditQuesMaster.lastUpdatedDate.isValid()
                    ? vendorAuditQuesMaster.lastUpdatedDate.toJSON()
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
            res.body.forEach((vendorAuditQuesMaster: IVendorAuditQuesMaster) => {
                vendorAuditQuesMaster.createdDate =
                    vendorAuditQuesMaster.createdDate != null ? moment(vendorAuditQuesMaster.createdDate) : null;
                vendorAuditQuesMaster.lastUpdatedDate =
                    vendorAuditQuesMaster.lastUpdatedDate != null ? moment(vendorAuditQuesMaster.lastUpdatedDate) : null;
            });
        }
        return res;
    }
}
