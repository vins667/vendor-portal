import { Moment } from 'moment';

export interface ICountry {
  id?: number;
  countryCode?: string;
  countryName?: string;
  status?: string;
  createdBy?: string;
  createdDate?: Moment;
  lastUpdatedBy?: string;
  lastUpdatedDate?: Moment;
}

export class Country implements ICountry {
  constructor(
    public id?: number,
    public countryCode?: string,
    public countryName?: string,
    public status?: string,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastUpdatedBy?: string,
    public lastUpdatedDate?: Moment
  ) {}
}
