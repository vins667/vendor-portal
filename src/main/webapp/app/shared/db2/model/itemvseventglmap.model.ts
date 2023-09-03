import { IItemvseventglmapId } from 'app/shared/db2/model/itemvseventglmap-id.model';

export interface IItemvseventglmap {
  id?: IItemvseventglmapId;
  itemtypecompanycode?: string;
  usergenericgrpnametypecode?: string;
  logicalwarehousecompanycode?: string;
  stocktrntemplatecompanycode?: string;
  bookingfor?: string;
  debitglcompanycode?: string;
  debitglcode?: string;
  debitgldescription?: string;
  creditglcompanycode?: string;
  creditglcode?: string;
  differenceglcompanycode?: string;
  differenceglcode?: string;
  effectivetodate?: any;
  postingflag?: number;
  creationdatetime?: any;
  creationuser?: string;
  lastupdatedatetime?: any;
  lastupdateuser?: string;
  absuniqueid?: number;
  creationdatetimeutc?: string;
  lastupdatedatetimeutc?: string;
}

export class Itemvseventglmap implements IItemvseventglmap {
  constructor(
    public id?: IItemvseventglmapId,
    public itemtypecompanycode?: string,
    public usergenericgrpnametypecode?: string,
    public logicalwarehousecompanycode?: string,
    public stocktrntemplatecompanycode?: string,
    public bookingfor?: string,
    public debitglcompanycode?: string,
    public debitglcode?: string,
    public debitgldescription?: string,
    public creditglcompanycode?: string,
    public creditglcode?: string,
    public differenceglcompanycode?: string,
    public differenceglcode?: string,
    public effectivetodate?: any,
    public postingflag?: number,
    public creationdatetime?: any,
    public creationuser?: string,
    public lastupdatedatetime?: any,
    public lastupdateuser?: string,
    public absuniqueid?: number,
    public creationdatetimeutc?: string,
    public lastupdatedatetimeutc?: string
  ) {}
}
