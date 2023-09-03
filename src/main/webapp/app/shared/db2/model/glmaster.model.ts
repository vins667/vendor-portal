import { IGlmasterId } from './glmaster-id.model';

export interface IGlmaster {
  id?: IGlmasterId;
  longdescription?: string;
  shortdescription?: string;
  searchdescription?: string;
}

export class Glmaster implements IGlmaster {
  constructor(
    public id?: IGlmasterId,
    public longdescription?: string,
    public shortdescription?: string,
    public searchdescription?: string
  ) {}
}
