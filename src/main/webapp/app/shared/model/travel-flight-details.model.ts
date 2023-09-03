import { Moment } from 'moment';
import { ITravelApplicationMaster } from 'app/shared/model/travel-application-master.model';

export interface ITravelFlightDetails {
  id?: number;
  travelDate?: Moment;
  ticketType?: string;
  ticketNo?: string;
  departureDate?: Moment;
  arrivalDate?: Moment;
  farePrice?: number;
  createdBy?: string;
  createdDate?: Moment;
  lastUpdatedBy?: string;
  lastUpdatedDate?: Moment;
  travelApplicationMaster?: ITravelApplicationMaster;
}

export class TravelFlightDetails implements ITravelFlightDetails {
  constructor(
    public id?: number,
    public travelDate?: Moment,
    public ticketType?: string,
    public ticketNo?: string,
    public departureDate?: Moment,
    public arrivalDate?: Moment,
    public farePrice?: number,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastUpdatedBy?: string,
    public lastUpdatedDate?: Moment,
    public travelApplicationMaster?: ITravelApplicationMaster
  ) {}
}
