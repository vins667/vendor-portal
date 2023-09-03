import { Moment } from 'moment';
export interface ITermsConditionDetails {
  id?: number;
  termsLine?: string;
  createdBy?: string;
  createdDate?: Moment;
  lastUpdatedBy?: string;
  lastUpdatedDate?: Moment;
}

export class TermsConditionDetails implements ITermsConditionDetails {
  constructor(
    public id?: number,
    public termsLine?: string,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastUpdatedBy?: string,
    public lastUpdatedDate?: Moment
  ) {}
}
