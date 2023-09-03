export interface ITrimTemplateDetailsBreakupId {
  id?: number;
  trimTemplateDetailsId?: number;
}

export class TrimTemplateDetailsBreakupId implements ITrimTemplateDetailsBreakupId {
  constructor(public id?: number, public trimTemplateDetailsId?: number) {}
}
