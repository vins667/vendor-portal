import { ITermsofshippingId } from './termsofshipping-id.model';

export interface ITermsofshipping {
  id?: ITermsofshippingId;
  longdescription?: string;
  shortdescription?: string;
  searchdescription?: string;
}

export class Termsofshipping implements ITermsofshipping {
  constructor(
    public id?: ITermsofshippingId,
    public longdescription?: string,
    public shortdescription?: string,
    public searchdescription?: string
  ) {}
}
