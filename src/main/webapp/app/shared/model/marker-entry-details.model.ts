import { Moment } from 'moment';
import { IMarkerMasterEntry } from 'app/shared/model/marker-master-entry.model';

export interface IMarkerEntryDetails {
  id?: number;
  sizeCode?: string;
  cutQty?: number;
  balanceQty?: number;
  sizeQty?: number;
  pliesQty?: number;
  actualPliesQty?: number;
  orderQty?: number;
  plannedQty?: number;
  createdBy?: string;
  createdDate?: Moment;
  lastUpdatedBy?: string;
  lastUpdatedDate?: Moment;
}

export class MarkerEntryDetails implements IMarkerEntryDetails {
  constructor(
    public id?: number,
    public sizeCode?: string,
    public cutQty?: number,
    public balanceQty?: number,
    public sizeQty?: number,
    public pliesQty?: number,
    public actualPliesQty?: number,
    public orderQty?: number,
    public plannedQty?: number,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastUpdatedBy?: string,
    public lastUpdatedDate?: Moment
  ) {}
}
