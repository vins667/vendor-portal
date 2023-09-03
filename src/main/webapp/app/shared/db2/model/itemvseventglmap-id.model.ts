export interface IItemvseventglmapId {
  companycode?: string;
  eventcode?: string;
  divisioncode?: string;
  mrnprefixcode?: string;
  invoicetypecode?: string;
  itemtypecode?: string;
  usergenericgrpcode?: string;
  usergenericgrpnamecode?: string;
  logicalwarehousecode?: string;
  stocktransactiontemplatecode?: string;
  document?: string;
  templatecode?: string;
  effectivefromdate?: any;
}

export class ItemvseventglmapId implements IItemvseventglmapId {
  constructor(
    public companycode?: string,
    public eventcode?: string,
    public divisioncode?: string,
    public mrnprefixcode?: string,
    public invoicetypecode?: string,
    public itemtypecode?: string,
    public usergenericgrpcode?: string,
    public usergenericgrpnamecode?: string,
    public logicalwarehousecode?: string,
    public stocktransactiontemplatecode?: string,
    public document?: string,
    public templatecode?: string,
    public effectivefromdate?: any
  ) {}
}
