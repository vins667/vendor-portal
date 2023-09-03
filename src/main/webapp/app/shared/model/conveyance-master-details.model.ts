import { Moment } from 'moment';
import { IConveyanceMaster } from 'app/shared/model/conveyance-master.model';

export interface IConveyanceMasterDetails {
  id?: number;
  attachFile?: string;
  attachDisplayFile?: string;
  tripStart?: number;
  tripEnd?: number;
  miscAmount?: number;
  fromLocation?: string;
  toLocation?: string;
  reason?: string;
  createdBy?: string;
  createdDate?: Moment;
  lastUpdatedBy?: string;
  lastUpdatedDate?: Moment;
  conveyanceMaster?: IConveyanceMaster;
  currentFileUpload?: File;
}

export class ConveyanceMasterDetails implements IConveyanceMasterDetails {
  constructor(
    public id?: number,
    public attachFile?: string,
    public attachDisplayFile?: string,
    public tripStart?: number,
    public tripEnd?: number,
    public miscAmount?: number,
    public fromLocation?: string,
    public toLocation?: string,
    public reason?: string,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastUpdatedBy?: string,
    public lastUpdatedDate?: Moment,
    public conveyanceMaster?: IConveyanceMaster,
    public currentFileUpload?: File
  ) {}
}
