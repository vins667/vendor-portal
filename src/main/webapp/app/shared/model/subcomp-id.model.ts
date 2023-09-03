import { Moment } from 'moment';

export interface ISubcompId {
  companyCode?: string;
  subCode?: number;
}

export class SubcompId implements ISubcompId {
  constructor(public companyCode?: string, public subCode?: number) {}
}
