export interface ICountry {
  code?: string;
  longdescription?: string;
  shortdescription?: string;
  searchdescription?: string;
}

export class Country implements ICountry {
  constructor(public code?: string, public longdescription?: string, public shortdescription?: string, public searchdescription?: string) {}
}
