import { ITrimTemplateDetailsBreakupId } from './trim-template-details-breakup-id.model';

export interface ITrimsTemplateDetailsBreakup {
  id?: ITrimTemplateDetailsBreakupId;
  description?: string;
  testDescription?: string;
}

export class TrimsTemplateDetailsBreakup implements ITrimsTemplateDetailsBreakup {
  constructor(public id?: ITrimTemplateDetailsBreakupId, public description?: string, public testDescription?: string) {}
}
