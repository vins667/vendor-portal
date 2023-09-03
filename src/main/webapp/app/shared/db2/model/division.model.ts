import { IDivisionId } from './division-id.model';

export interface IDivision {
  id?: IDivisionId;
  longdescription?: string;
  shortdescription?: string;
  searchdescription?: string;
}

export class Division implements IDivision {
  constructor(
    public id?: IDivisionId,
    public longdescription?: string,
    public shortdescription?: string,
    public searchdescription?: string
  ) {}
}
