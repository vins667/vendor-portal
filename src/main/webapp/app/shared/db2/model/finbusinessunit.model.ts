import { IFinbusinessunitId } from './finbusinessunit-id.model';

export interface IFinbusinessunit {
  id?: IFinbusinessunitId;
  longdescription?: string;
  shortdescription?: string;
  searchdescription?: string;
}

export class Finbusinessunit implements IFinbusinessunit {
  constructor(
    public id?: IFinbusinessunitId,
    public longdescription?: string,
    public shortdescription?: string,
    public searchdescription?: string
  ) {}
}
