export interface IBranchSearch {
  vendorId?: number;
  size?: number;
  pageNo?: number;
}

export class BranchSearch implements IBranchSearch {
  constructor(public vendorId?: number, public size?: number, public pageNo?: number) {}
}
