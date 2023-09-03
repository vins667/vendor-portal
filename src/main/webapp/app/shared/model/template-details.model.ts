import { ITemplateDetailsBreakup } from './template-details-breakup.model';

export interface ITemplateDetails {
  id?: number;
  specification?: string;
  required?: boolean;
  showInBid?: boolean;
  fieldType?: string;
  fieldValue?: string;
  templateDetailsBreakups?: ITemplateDetailsBreakup[];
}

export class TemplateDetails implements ITemplateDetails {
  constructor(
    public id?: number,
    public specification?: string,
    public required?: boolean,
    public showInBid?: boolean,
    public fieldType?: string,
    public fieldValue?: string,
    public templateDetailsBreakups?: ITemplateDetailsBreakup[]
  ) {
    this.required = this.required || false;
    this.showInBid = this.showInBid || false;
  }
}
