import { Moment } from 'moment';
import { ITemplateDetails } from 'app/shared/model//template-details.model';

export interface ITemplateDetailsValue {
  id?: number;
  description?: string;
  createdBy?: string;
  createdDate?: Moment;
}

export class TemplateDetailsValue implements ITemplateDetailsValue {
  constructor(public id?: number, public description?: string, public createdBy?: string, public createdDate?: Moment) {}
}
