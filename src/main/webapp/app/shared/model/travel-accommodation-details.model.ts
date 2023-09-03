import { Moment } from 'moment';
import { ITravelApplicationMaster } from 'app/shared/model/travel-application-master.model';

export interface ITravelAccommodationDetails {
  id?: number;
  accommodationDate?: Moment;
  accommodationType?: string;
  accommodationName?: string;
  accommodationTarif?: number;
  daysStay?: number;
  earlyCheckin?: boolean;
  createdBy?: string;
  createdDate?: Moment;
  lastUpdatedBy?: string;
  lastUpdatedDate?: Moment;
  travelApplicationMaster?: ITravelApplicationMaster;
}

export class TravelAccommodationDetails implements ITravelAccommodationDetails {
  constructor(
    public id?: number,
    public accommodationDate?: Moment,
    public accommodationType?: string,
    public accommodationName?: string,
    public accommodationTarif?: number,
    public daysStay?: number,
    public earlyCheckin?: boolean,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastUpdatedBy?: string,
    public lastUpdatedDate?: Moment,
    public travelApplicationMaster?: ITravelApplicationMaster
  ) {
    this.earlyCheckin = this.earlyCheckin || false;
  }
}
