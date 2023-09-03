import { Moment } from 'moment';
import { IConveyanceMasterDetails } from './conveyance-master-details.model';

export interface IConveyanceMaster {
  id?: number;
  empCode?: string;
  name?: string;
  flag?: string;
  conveyanceDate?: string;
  conveyanceType?: string;
  vehicleNo?: string;
  totalDistance?: number;
  rate?: number;
  totalAmount?: number;
  approvedBy?: string;
  approvedDate?: Moment;
  hrApproved?: string;
  hrApprovedDate?: Moment;
  createdBy?: string;
  createdDate?: Moment;
  lastUpdatedBy?: string;
  lastUpdatedDate?: Moment;
  processFlow?: boolean;
  vehicleType?: string;
  miscAmount?: number;
  conveyanceMasterDetails?: IConveyanceMasterDetails[];
}

export class ConveyanceMaster implements IConveyanceMaster {
  constructor(
    public id?: number,
    public empCode?: string,
    public name?: string,
    public flag?: string,
    public conveyanceDate?: string,
    public conveyanceType?: string,
    public vehicleNo?: string,
    public totalDistance?: number,
    public rate?: number,
    public totalAmount?: number,
    public approvedBy?: string,
    public approvedDate?: Moment,
    public hrApproved?: string,
    public hrApprovedDate?: Moment,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastUpdatedBy?: string,
    public lastUpdatedDate?: Moment,
    public processFlow?: boolean,
    public vehicleType?: string,
    public miscAmount?: number,
    public conveyanceMasterDetails?: IConveyanceMasterDetails[]
  ) {}
}
