import { Moment } from 'moment';
import { IVendorAuditQuesDetails } from 'app/shared/model//vendor-audit-ques-details.model';
import {IVendorAuditGroupMasterBean} from 'app/shared/model/vendor-audit-group-master-bean.model';

export interface IVendorAuditQuesMaster {
    id?: number;
    auditName?: string;
    createdBy?: string;
    createdDate?: Moment;
    lastUpdatedBy?: string;
    lastUpdatedDate?: Moment;
    vendorAuditGroupMasterBean?: IVendorAuditGroupMasterBean[];
    vendorAuditQuesDetails?: IVendorAuditQuesDetails[];
}

export class VendorAuditQuesMaster implements IVendorAuditQuesMaster {
    constructor(
        public id?: number,
        public auditName?: string,
        public createdBy?: string,
        public createdDate?: Moment,
        public lastUpdatedBy?: string,
        public lastUpdatedDate?: Moment,
        public vendorAuditGroupMasterBean?: IVendorAuditGroupMasterBean[],
        public vendorAuditQuesDetails?: IVendorAuditQuesDetails[]
    ) {}
}
