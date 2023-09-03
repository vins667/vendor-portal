import { Moment } from 'moment';
import { ITdsGroupMaster } from './tds-group-master.model';

export interface ITdsDeclarationUpload {
  id?: number;
  cardNo?: string;
  name?: string;
  financialYear?: string;
  fileName?: string;
  originalFileName?: string;
  designation?: string;
  panNo?: string;
  dateOfBirth?: Moment;
  contactNumber?: string;
  emailId?: string;
  address?: string;
  monthRent?: number;
  landLoardName?: string;
  landLoardPanNo?: string;
  landLoardAddress?: string;
  createdBy?: string;
  createdDate?: Moment;
  lastUpdatedBy?: string;
  lastUpdatedDate?: Moment;
  tdsGroupMasterbean?: ITdsGroupMaster[];
  tdsGroupMaster?: ITdsGroupMaster;
}

export class TdsDeclarationUpload implements ITdsDeclarationUpload {
  constructor(
    public id?: number,
    public cardNo?: string,
    public name?: string,
    public financialYear?: string,
    public fileName?: string,
    public originalFileName?: string,
    public designation?: string,
    public panNo?: string,
    public dateOfBirth?: Moment,
    public contactNumber?: string,
    public emailId?: string,
    public address?: string,
    public monthRent?: number,
    public landLoardName?: string,
    public landLoardPanNo?: string,
    public landLoardAddress?: string,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastUpdatedBy?: string,
    public lastUpdatedDate?: Moment,
    public tdsGroupMasterbean?: ITdsGroupMaster[],
    public tdsGroupMaster?: ITdsGroupMaster
  ) {}
}
