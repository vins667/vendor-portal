export interface ICompany {
  code?: string;
  longdescription?: string;
  shortdescription?: string;
  searchdescription?: string;
}

export class Company implements ICompany {
  constructor(public code?: string, public longdescription?: string, public shortdescription?: string, public searchdescription?: string) {}
}
