import { Moment } from 'moment';
import { IEmployeeSearch } from 'app/shared/model/employee-search.model';
import { IVcutUserDeviceMaster } from './vcut-user-device-master.model';

export interface IVcutDeviceLineMaster {
  id?: number;
  line?: string;
  deviceId?: string;
  createdBy?: string;
  createdDate?: Moment;
  employeeSearch?: IEmployeeSearch;
  vcutUserDeviceMaster?: IVcutUserDeviceMaster[];
}
export class VcutDeviceLineMaster implements IVcutDeviceLineMaster {
  constructor(
    public id?: number,
    public line?: string,
    public deviceId?: string,
    public createdBy?: string,
    public createdDate?: Moment,
    public employeeSearch?: IEmployeeSearch,
    public vcutUserDeviceMaster?: IVcutUserDeviceMaster[]
  ) {}
}
