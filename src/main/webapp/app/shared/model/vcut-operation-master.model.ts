import { Moment } from 'moment';

export interface IVcutOperationMaster {
  id?: number;
  description?: string;
  descriptionLocal?: string;
  style?: string;
  itemName?: string;
  machine?: string;
  smv?: number;
  inspection?: boolean;
  createdBy?: string;
  createdDate?: Moment;
  lastUpdatedBy?: string;
  lastUpdatedDate?: Moment;
  vcutOperationMasters?: IVcutOperationMaster[];
}

export class VcutOperationMaster implements IVcutOperationMaster {
  constructor(
    public id?: number,
    public description?: string,
    public descriptionLocal?: string,
    public style?: string,
    public itemName?: string,
    public machine?: string,
    public smv?: number,
    public inspection?: boolean,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastUpdatedBy?: string,
    public lastUpdatedDate?: Moment,
    public vcutOperationMasters?: IVcutOperationMaster[]
  ) {}
}
