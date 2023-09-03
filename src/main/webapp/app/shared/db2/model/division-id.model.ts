export interface IDivisionId {
  companycode?: string;
  code?: string;
}

export class DivisionId implements IDivisionId {
  constructor(public companycode?: string, public code?: string) {}
}
