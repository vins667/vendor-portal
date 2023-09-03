import { Moment } from 'moment';
import { IVendTypeMaster } from 'app/shared/model//vend-type-master.model';

export interface IVendSubTypeMaster {
  id?: number;
  description?: string;
  m3Code?: string;
  status?: string;
  createdBy?: string;
  createdDate?: Moment;
  lastUpdatedBy?: string;
  lastUpdatedDate?: Moment;
  vendTypeMaster?: IVendTypeMaster;
}

export class VendSubTypeMaster implements IVendSubTypeMaster {
  constructor(
    public id?: number,
    public description?: string,
    public m3Code?: string,
    public status?: string,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastUpdatedBy?: string,
    public lastUpdatedDate?: Moment,
    public vendTypeMaster?: IVendTypeMaster
  ) {}
}
