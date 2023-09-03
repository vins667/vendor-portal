export interface IUnitOfMeasure {
  code?: string;
  longdescription?: string;
}

export class UnitOfMeasure implements IUnitOfMeasure {
  constructor(public code?: string, public longdescription?: string) {}
}
