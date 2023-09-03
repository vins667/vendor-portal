import { Runtime } from 'inspector';
import { Timestamp } from 'rxjs';
import { FinancialyearId, IFinfinancialyearId } from 'app/shared/db2/model/finfinancialyear-id.model';

export interface IFinfinancialyear {
  id?: IFinfinancialyearId;
  longdescription?: string;
  shortdescription?: string;
  searchdescription?: string;
  fromdate?: any;
  todate?: any;
  yearclosed?: boolean;
  creationdatetime?: any;
  creationuser?: string;
  lastupdatedatetime?: any;
  lastupdateuser?: any;
  owningcompanycode?: string;
  creationdatetimeutc?: any;
  lastupdatedatetimeutc?: any;
}
export class Finfinancialyear implements IFinfinancialyear {
  constructor(
    public id?: IFinfinancialyearId,
    public longdescription?: string,
    public shortdescription?: string,
    public searchdescription?: string,
    public fromdate?: any,
    public todate?: any,
    public yearclosed?: boolean,
    public creationdatetime?: any,
    public creationuser?: any,
    public lastupdatedatetime?: any,
    public lastupdateuser?: any,
    public owningcompanycode?: string,
    public creationdatetimeutc?: any,
    public lastupdatedatetimeutc?: any
  ) {}
}
