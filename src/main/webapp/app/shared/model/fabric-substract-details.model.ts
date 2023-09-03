import { Moment } from 'moment';
import { IFabricSubstractMaster } from 'app/shared/model/fabric-substract-master.model';

export interface IFabricSubstractDetails {
  id?: number;
  code?: string;
  description?: string;
  flag?: string;
  createdBy?: string;
  createdDate?: Moment;
  lastUpdatedBy?: string;
  lastUpdatedDate?: Moment;
  fabricSubstractMaster?: IFabricSubstractMaster;
}

export class FabricSubstractDetails implements IFabricSubstractDetails {
  constructor(
    public id?: number,
    public code?: string,
    public description?: string,
    public flag?: string,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastUpdatedBy?: string,
    public lastUpdatedDate?: Moment,
    public fabricSubstractMaster?: IFabricSubstractMaster
  ) {}
}
