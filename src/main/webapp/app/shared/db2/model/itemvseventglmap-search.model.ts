export interface IItemvseventglmapSearch {
  division?: string;
  itemtypecode?: string;
  subcode01?: string;
  customersuppliertype?: string;
}

export class ItemvseventglmapSearch implements IItemvseventglmapSearch {
  constructor(public division?: string, public itemtypecode?: string, public subcode01?: string, public customersuppliertype?: string) {}
}
