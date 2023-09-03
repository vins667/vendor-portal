import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { ICompOffMaster } from 'app/shared/model/comp-off-master.model';
import { ILeaveSearch } from 'app/shared/model/leave-search.model';
import { IMessage } from 'app/shared/model/message.model';

type EntityResponseType = HttpResponse<ICompOffMaster>;
type EntityArrayResponseType = HttpResponse<ICompOffMaster[]>;

@Injectable({ providedIn: 'root' })
export class CompOffApprovalService {
  public resourceUrl = SERVER_API_URL + 'api/comp-off-masters';

  constructor(protected http: HttpClient) {}

  queryHod(req?: ILeaveSearch): Observable<EntityArrayResponseType> {
    const copy = this.convertDateFromClientLeave(req);
    return this.http
      .post<ICompOffMaster[]>(`${this.resourceUrl + '-hod'}`, copy, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  update(SaveCompOffMasters: ICompOffMaster[]): Observable<HttpResponse<IMessage>> {
    const compOffMaster = [];
    SaveCompOffMasters.forEach(compOff => {
      const copy = this.convertDateFromClient(compOff);
      compOffMaster.push(copy);
    });
    return this.http.put<IMessage>(this.resourceUrl, compOffMaster, { observe: 'response' });
  }

  protected convertDateFromClient(compOffMaster: ICompOffMaster): ICompOffMaster {
    const copy: ICompOffMaster = Object.assign({}, compOffMaster, {
      compOffDate: compOffMaster.compOffDate != null && compOffMaster.compOffDate.isValid() ? compOffMaster.compOffDate.toJSON() : null,
      availDate: compOffMaster.availDate != null && compOffMaster.availDate.isValid() ? compOffMaster.availDate.toJSON() : null,
      hodApprovedDate:
        compOffMaster.hodApprovedDate != null && compOffMaster.hodApprovedDate.isValid() ? compOffMaster.hodApprovedDate.toJSON() : null,
      createdDate: compOffMaster.createdDate != null && compOffMaster.createdDate.isValid() ? compOffMaster.createdDate.toJSON() : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.compOffDate = res.body.compOffDate != null ? moment(res.body.compOffDate) : null;
      res.body.availDate = res.body.availDate != null ? moment(res.body.availDate) : null;
      res.body.hodApprovedDate = res.body.hodApprovedDate != null ? moment(res.body.hodApprovedDate) : null;
      res.body.createdDate = res.body.createdDate != null ? moment(res.body.createdDate) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((compOffMaster: ICompOffMaster) => {
        compOffMaster.compOffDate = compOffMaster.compOffDate != null ? moment(compOffMaster.compOffDate) : null;
        compOffMaster.availDate = compOffMaster.availDate != null ? moment(compOffMaster.availDate) : null;
        compOffMaster.hodApprovedDate = compOffMaster.hodApprovedDate != null ? moment(compOffMaster.hodApprovedDate) : null;
        compOffMaster.createdDate = compOffMaster.createdDate != null ? moment(compOffMaster.createdDate) : null;
      });
    }
    return res;
  }

  protected convertDateFromClientLeave(leaveMaster: ILeaveSearch): ILeaveSearch {
    const copy: ILeaveSearch = Object.assign({}, leaveMaster, {
      leaveDateFrom: leaveMaster.leaveDateFrom != null && leaveMaster.leaveDateFrom.isValid() ? leaveMaster.leaveDateFrom.toJSON() : null,
      leaveDateTo: leaveMaster.leaveDateTo != null && leaveMaster.leaveDateTo.isValid() ? leaveMaster.leaveDateTo.toJSON() : null
    });
    return copy;
  }
}
