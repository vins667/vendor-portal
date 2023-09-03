import { Moment } from 'moment';
import { IConveyanceMaster } from './conveyance-master.model';

export interface IConveyanceAttaches {
  id?: number;
  attachFile?: string;
  attachDisplayFile?: string;
  tripStart?: number;
  tripEnd?: number;
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

export class ConveyanceAttaches implements IConveyanceAttaches {
  constructor(
    public id?: number,
    public attachFile?: string,
    public attachDisplayFile?: string,
    public tripStart?: number,
    public tripEnd?: number,
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
