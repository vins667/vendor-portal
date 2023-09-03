import { ITemplateDetailsBreakupId } from './template-details-breakup-id.model';

export interface ITemplateDetailsBreakup {
  id?: ITemplateDetailsBreakupId;
  description?: string;
}

export class TemplateDetailsBreakup implements ITemplateDetailsBreakup {
  constructor(public id?: ITemplateDetailsBreakupId, public description?: string) {}
}
