import { ITermsofdeliveryId } from './termsofdelivery-id.model';

export interface ITermsofdelivery {
  id?: ITermsofdeliveryId;
  longdescription?: string;
  shortdescription?: string;
  searchdescription?: string;
}

export class Termsofdelivery implements ITermsofdelivery {
  constructor(
    public id?: ITermsofdeliveryId,
    public longdescription?: string,
    public shortdescription?: string,
    public searchdescription?: string
  ) {}
}
