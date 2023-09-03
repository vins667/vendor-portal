import { Moment } from 'moment';
import { IVendorMaster } from 'app/shared/model//vendor-master.model';
import { IBuyerMaster } from 'app/shared/model//buyer-master.model';

export interface IVendorBuyerAuditLinking {
    id?: number;
    createdBy?: string;
    createdDate?: Moment;
    lastUpdatedBy?: string;
    lastUpdatedDate?: Moment;
    vendorMaster?: IVendorMaster;
    buyerMasters?: IBuyerMaster[];
}

export class VendorBuyerAuditLinking implements IVendorBuyerAuditLinking {
    constructor(
        public id?: number,
        public createdBy?: string,
        public createdDate?: Moment,
        public lastUpdatedBy?: string,
        public lastUpdatedDate?: Moment,
        public vendorMaster?: IVendorMaster,
        public buyerMasters?: IBuyerMaster[]
    ) {}
}
