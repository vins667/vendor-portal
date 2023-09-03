import { Moment } from 'moment';
import { IMenuMaster } from 'app/shared/model//menu-master.model';

export interface IMenuAccessMaster {
  id?: number;
  authorityName?: string;
  createdBy?: string;
  createdDate?: Moment;
  menuMaster?: IMenuMaster;
}

export class MenuAccessMaster implements IMenuAccessMaster {
  constructor(
    public id?: number,
    public authorityName?: string,
    public createdBy?: string,
    public createdDate?: Moment,
    public menuMaster?: IMenuMaster
  ) {}
}
