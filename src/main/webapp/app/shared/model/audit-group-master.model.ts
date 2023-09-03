import { Moment } from 'moment';

export interface IAuditGroupMaster {
    id?: number;
    description?: string;
    flag?: string;
    createdBy?: string;
    createdDate?: Moment;
    lastUpdatedBy?: string;
    lastUpdatedDate?: Moment;
}

export class AuditGroupMaster implements IAuditGroupMaster {
    constructor(
        public id?: number,
        public description?: string,
        public flag?: string,
        public createdBy?: string,
        public createdDate?: Moment,
        public lastUpdatedBy?: string,
        public lastUpdatedDate?: Moment
    ) {}
}
