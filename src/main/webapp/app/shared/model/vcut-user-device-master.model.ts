import { Moment } from 'moment';
import { IVcutDeviceLineMaster } from 'app/shared/model/vcut-device-line-master.model';
import { IUser } from 'app/core/user/user.model';

export interface IVcutUserDeviceMaster {
  id?: number;
  user?: IUser;
  createdBy?: string;
  createdDate?: Moment;
  userName?: string;
  searchUserId?: string;
  searchUserName?: string;
  checked?: boolean;
  vcutDeviceLineMaster?: IVcutDeviceLineMaster;
}

export class VcutUserDeviceMaster implements IVcutUserDeviceMaster {
  constructor(
    public id?: number,
    public user?: IUser,
    public createdBy?: string,
    public createdDate?: Moment,
    public userName?: string,
    public searchUserId?: string,
    public searchUserName?: string,
    public checked?: boolean,
    public vcutDeviceLineMaster?: IVcutDeviceLineMaster
  ) {}
}
