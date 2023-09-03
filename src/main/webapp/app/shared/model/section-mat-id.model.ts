import { Moment } from 'moment';

export interface ISectionMatId {
  companyCode?: string;
  secCode?: number;
}

export class SectionMatId implements ISectionMatId {
  constructor(public companyCode?: string, public secCode?: number) {}
}
