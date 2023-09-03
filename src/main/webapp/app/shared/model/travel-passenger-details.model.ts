import { Moment } from 'moment';
import { ITravelApplicationMaster } from 'app/shared/model/travel-application-master.model';

export interface ITravelPassengerDetails {
  id?: number;
  passengerName?: string;
  phoneNo?: string;
  emailId?: string;
  createdBy?: string;
  createdDate?: Moment;
  lastUpdatedBy?: string;
  lastUpdatedDate?: Moment;
  travelApplicationMaster?: ITravelApplicationMaster;
}

export class TravelPassengerDetails implements ITravelPassengerDetails {
  constructor(
    public id?: number,
    public passengerName?: string,
    public phoneNo?: string,
    public emailId?: string,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastUpdatedBy?: string,
    public lastUpdatedDate?: Moment,
    public travelApplicationMaster?: ITravelApplicationMaster
  ) {}
}
