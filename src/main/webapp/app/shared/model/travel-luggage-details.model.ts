import { Moment } from 'moment';
import { ITravelApplicationMaster } from 'app/shared/model/travel-application-master.model';

export interface ITravelLuggageDetails {
  id?: number;
  luggageCount?: number;
  luggageType?: string;
  approxWeight?: number;
  extraLuggageReq?: boolean;
  createdBy?: string;
  createdDate?: Moment;
  lastUpdatedBy?: string;
  lastUpdatedDate?: Moment;
  travelApplicationMaster?: ITravelApplicationMaster;
}

export class TravelLuggageDetails implements ITravelLuggageDetails {
  constructor(
    public id?: number,
    public luggageCount?: number,
    public luggageType?: string,
    public approxWeight?: number,
    public extraLuggageReq?: boolean,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastUpdatedBy?: string,
    public lastUpdatedDate?: Moment,
    public travelApplicationMaster?: ITravelApplicationMaster
  ) {
    this.extraLuggageReq = this.extraLuggageReq || false;
  }
}
