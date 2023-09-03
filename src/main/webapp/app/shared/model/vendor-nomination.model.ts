import { Moment } from 'moment';
import { IBuyerMaster } from 'app/shared/model/buyer-master.model';

export interface IVendorNomination {
  id?: number;
  vendorId?: number;
  createdBy?: string;
  createdDate?: Moment;
  lastUpdatedBy?: string;
  lastUpdatedDate?: Moment;
  buyerMasters?: IBuyerMaster[];
}

export class VendorNomination implements IVendorNomination {
  constructor(
    public id?: number,
    public vendorId?: number,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastUpdatedBy?: string,
    public lastUpdatedDate?: Moment,
    public buyerMasters?: IBuyerMaster[]
  ) {}
}
