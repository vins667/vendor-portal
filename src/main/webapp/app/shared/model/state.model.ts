import { Moment } from 'moment';
import { ICountry } from 'app/shared/model//country.model';

export interface IState {
  id?: number;
  stateCode?: string;
  stateName?: string;
  geoCode?: number;
  gstStateCode?: string;
  status?: string;
  createdBy?: string;
  createdDate?: Moment;
  lastUpdatedBy?: string;
  lastUpdatedDate?: Moment;
  country?: ICountry;
}

export class State implements IState {
  constructor(
    public id?: number,
    public stateCode?: string,
    public stateName?: string,
    public geoCode?: number,
    public gstStateCode?: string,
    public status?: string,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastUpdatedBy?: string,
    public lastUpdatedDate?: Moment,
    public country?: ICountry
  ) {}
}
