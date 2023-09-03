import { ITrimsTemplateDetailsBreakup } from './trims-template-details-breakup.model';

export interface ITrimsTemplateDetails {
  id?: number;
  specification?: string;
  required?: boolean;
  fieldType?: string;
  display?: boolean;
  fieldValue?: string;
  fieldValueDropDown?: string;
  trimTemplateDetailsBreakup?: ITrimsTemplateDetailsBreakup[];
  expend?: boolean;
}

export class TrimsTemplateDetails implements ITrimsTemplateDetails {
  constructor(
    public id?: number,
    public specification?: string,
    public required?: boolean,
    public fieldType?: string,
    public display?: boolean,
    public fieldValue?: string,
    public fieldValueDropDown?: string,
    public trimTemplateDetailsBreakup?: ITrimsTemplateDetailsBreakup[],
    public expend?: boolean
  ) {}
}
