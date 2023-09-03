import { Moment } from 'moment';
import { ITravelApplicationMaster } from 'app/shared/model/travel-application-master.model';

export interface ITravelForexDetails {
  id?: number;
  forexType?: string;
  requiredAmount?: number;
  approvedAmount?: number;
  createdBy?: string;
  createdDate?: Moment;
  lastUpdatedBy?: string;
  lastUpdatedDate?: Moment;
  travelApplicationMaster?: ITravelApplicationMaster;
}

export class TravelForexDetails implements ITravelForexDetails {
  constructor(
    public id?: number,
    public forexType?: string,
    public requiredAmount?: number,
    public approvedAmount?: number,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastUpdatedBy?: string,
    public lastUpdatedDate?: Moment,
    public travelApplicationMaster?: ITravelApplicationMaster
  ) {}
}
