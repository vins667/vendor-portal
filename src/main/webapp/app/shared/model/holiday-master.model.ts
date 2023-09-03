import { Moment } from 'moment';
import { IFactoryMaster } from 'app/shared/model//factory-master.model';

export interface IHolidayMaster {
  id?: number;
  holidayDate?: Moment;
  holidayName?: string;
  createdBy?: string;
  createdDate?: Moment;
  factoryMaster?: IFactoryMaster;
}

export class HolidayMaster implements IHolidayMaster {
  constructor(
    public id?: number,
    public holidayDate?: Moment,
    public holidayName?: string,
    public createdBy?: string,
    public createdDate?: Moment,
    public factoryMaster?: IFactoryMaster
  ) {}
}
