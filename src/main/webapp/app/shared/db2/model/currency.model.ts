export interface ICurrency {
  code?: string;
  longdescription?: string;
  shortdescription?: string;
  searchdescription?: string;
}

export class Currency implements ICurrency {
  constructor(public code?: string, public longdescription?: string, public shortdescription?: string, public searchdescription?: string) {}
}
