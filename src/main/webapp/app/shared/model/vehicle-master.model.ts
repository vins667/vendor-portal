import { Moment } from 'moment';
import { IUser } from 'app/core/user/user.model';

export interface IVehicleMaster {
  id?: number;
  user?: IUser;
  vehicleType?: string;
  noVehicle?: number;
  vehicleDate?: Moment;
  placeFrom?: string;
  placeTo?: string;
  purpose?: string;
  flag?: string;
  createdBy?: string;
  createdDate?: Moment;
  hodApprovedBy?: string;
  hodApprovedDate?: Moment;
  vehicleNumber?: string;
  driverName?: string;
  adminRemarks?: string;
  adminApprovedBy?: string;
  adminApprovedDate?: Moment;
}

export class VehicleMaster implements IVehicleMaster {
  constructor(
    public id?: number,
    public user?: IUser,
    public vehicleType?: string,
    public noVehicle?: number,
    public vehicleDate?: Moment,
    public placeFrom?: string,
    public placeTo?: string,
    public purpose?: string,
    public flag?: string,
    public createdBy?: string,
    public createdDate?: Moment,
    public hodApprovedBy?: string,
    public hodApprovedDate?: Moment,
    public vehicleNumber?: string,
    public driverName?: string,
    public adminRemarks?: string,
    public adminApprovedBy?: string,
    public adminApprovedDate?: Moment
  ) {}
}
