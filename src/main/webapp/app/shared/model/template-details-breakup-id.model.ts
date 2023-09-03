export interface ITemplateDetailsBreakupId {
  id?: number;
  templateDetailsId?: number;
}

export class TemplateDetailsBreakupId implements ITemplateDetailsBreakupId {
  constructor(public id?: number, public templateDetailsId?: number) {}
}
