export interface IItemtypeId {
  companycode?: string;
  code?: string;
}

export class ItemtypeId implements IItemtypeId {
  constructor(public companycode?: string, public code?: string) {}
}
