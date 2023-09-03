export interface IFinDocumentGlChange {
  id?: number;
  companycode?: string;
  businessunitcode?: string;
  financialyearcode?: number;
  documenttemplatecode?: string;
  code?: string;
  itemtype?: string;
  hsncode?: string;
  description?: string;
  uom?: string;
  qty?: number;
  rate?: number;
  basicvalue?: number;
  gstrate?: number;
  cgstvalue?: number;
  sgstvalue?: number;
  igstvalue?: number;
  othercharges?: number;
  totalValue?: number;
  remarks?: string;
  lockedby?: string;
}

export class FinDocumentGlChange implements IFinDocumentGlChange {
  constructor(
    public id?: number,
    public companycode?: string,
    public businessunitcode?: string,
    public financialyearcode?: number,
    public documenttemplatecode?: string,
    public code?: string,
    public hsncode?: string,
    public itemtype?: string,
    public description?: string,
    public uom?: string,
    public qty?: number,
    public rate?: number,
    public basicvalue?: number,
    public gstrate?: number,
    public cgstvalue?: number,
    public sgstvalue?: number,
    public igstvalue?: number,
    public othercharges?: number,
    public totalValue?: number,
    public remarks?: string,
    public lockedby?: string
  ) {}
}
