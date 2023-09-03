import { Moment } from 'moment';
import { ITravelFlightDetails } from 'app/shared/model/travel-flight-details.model';
import { ITravelAccommodationDetails } from 'app/shared/model/travel-accommodation-details.model';
import { ITravelForexDetails } from './travel-forex-details.model';
import { ITravelLuggageDetails } from './travel-luggage-details.model';
import { ITravelPassengerDetails } from './travel-passenger-details.model';
import { ITravelMasterAttach } from './travel-master-attach.model';

export interface ITravelApplicationMaster {
  id?: number;
  empCode?: string;
  name?: string;
  travelDestination?: string;
  travelDays?: number;
  travelPurpose?: string;
  createdBy?: string;
  createdDate?: Moment;
  lastUpdatedBy?: string;
  lastUpdatedDate?: Moment;
  travelFromdate?: Moment;
  travelTodate?: Moment;
  fromDate?: Moment;
  toDate?: Moment;
  status?: string;
  hodCode?: string;
  hodApprovedBy?: string;
  hrApprovedBy?: string;
  hodApprovedDate?: Moment;
  hrApprovedDate?: Moment;
  travelFlightDetails?: ITravelFlightDetails[];
  travelAccommodationDetails?: ITravelAccommodationDetails[];
  travelForexDetails?: ITravelForexDetails[];
  travelLuggageDetails?: ITravelLuggageDetails[];
  travelPassengerDetails?: ITravelPassengerDetails[];
  travelMasterAttach?: ITravelMasterAttach;
}

export class TravelApplicationMaster implements ITravelApplicationMaster {
  constructor(
    public id?: number,
    public empCode?: string,
    public name?: string,
    public travelDestination?: string,
    public travelDays?: number,
    public travelPurpose?: string,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastUpdatedBy?: string,
    public lastUpdatedDate?: Moment,
    public travelFromdate?: Moment,
    public travelTodate?: Moment,
    public status?: string,
    public hodCode?: string,
    public hodApprovedBy?: string,
    public hrApprovedBy?: string,
    public hodApprovedDate?: Moment,
    public hrApprovedDate?: Moment,
    public travelFlightDetails?: ITravelFlightDetails[],
    public travelAccommodationDetails?: ITravelAccommodationDetails[],
    public travelForexDetails?: ITravelForexDetails[],
    public travelLuggageDetails?: ITravelLuggageDetails[],
    public travelPassengerDetails?: ITravelPassengerDetails[],
    public travelMasterAttach?: ITravelMasterAttach
  ) {}
}
