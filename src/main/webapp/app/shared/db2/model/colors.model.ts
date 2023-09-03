export interface IColor {
  code?: string;
  longdescription?: string;
  shortdescription?: string;
  searchdescription?: string;
}

export class Color implements IColor {
  constructor(public code?: string, public longdescription?: string, public shortdescription?: string, public searchdescription?: string) {}
}
