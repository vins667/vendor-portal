export interface IDirectBookingDetails {
  id?: number;
  itemtypecode?: string;
  subcode01?: string;
  subcode02?: string;
  subcode03?: string;
  subcode04?: string;
  subcode05?: string;
  subcode06?: string;
  subcode07?: string;
  subcode08?: string;
  subcode09?: string;
  subcode10?: string;
  summerizeddescription?: string;
  glcode?: string;
  gldescription?: string;
  uom?: string;
  quantity?: number;
  price?: number;
  amount?: number;
  discount?: number;
  freight?: number;
  others?: number;
  taxablevalue?: number;
  cgstperc?: number;
  cgsttaxcode?: string;
  cgstglcode?: string;
  cgstdesc?: string;
  cgstvalue?: number;
  mcgst?: boolean;
  sgstperc?: number;
  sgsttaxcode?: string;
  sgstglcode?: string;
  sgstdesc?: string;
  sgstvalue?: number;
  msgst?: boolean;
  igstperc?: number;
  igsttaxcode?: string;
  igstglcode?: string;
  igstdesc?: string;
  igstvalue?: number;
  migst?: boolean;
  gstperc?: number;
  gstvalue?: number;
  totalvalue?: number;
  hsncode?: string;
  costcentercode?: string;
  costcenterdesc?: string;
  copyFlag?: string;
}

export class DirectBookingDetails implements IDirectBookingDetails {
  constructor(
    public id?: number,
    public itemtypecode?: string,
    public subcode01?: string,
    public subcode02?: string,
    public subcode03?: string,
    public subcode04?: string,
    public subcode05?: string,
    public subcode06?: string,
    public subcode07?: string,
    public subcode08?: string,
    public subcode09?: string,
    public subcode10?: string,
    public summerizeddescription?: string,
    public glcode?: string,
    public gldescription?: string,
    public uom?: string,
    public quantity?: number,
    public price?: number,
    public amount?: number,
    public discount?: number,
    public freight?: number,
    public others?: number,
    public taxablevalue?: number,
    public cgstperc?: number,
    public cgsttaxcode?: string,
    public cgstglcode?: string,
    public cgstdesc?: string,
    public cgstvalue?: number,
    public sgstperc?: number,
    public mcgst?: boolean,
    public sgsttaxcode?: string,
    public sgstglcode?: string,
    public sgstdesc?: string,
    public sgstvalue?: number,
    public msgst?: boolean,
    public igstperc?: number,
    public igsttaxcode?: string,
    public igstglcode?: string,
    public igstdesc?: string,
    public igstvalue?: number,
    public migst?: boolean,
    public gstperc?: number,
    public gstvalue?: number,
    public totalvalue?: number,
    public hsncode?: string,
    public costcentercode?: string,
    public costcenterdesc?: string,
    public copyFlag?: string
  ) {}
}
