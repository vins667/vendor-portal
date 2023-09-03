import { ILeaveMasterRemarksDetails } from 'app/shared/model/leave-master-remarks-details.model';
import { IMaster } from 'app/shared/model/master.modal';
import { ILeaveMaster } from 'app/shared/model/leave-master.model';

export interface ILeaveMasterRemarksDetailsBean {
  id?: number;
  empCode?: string;
  empName?: string;
  forwardCode?: string;
  forwardName?: string;
  createdBy?: string;
  createdName?: string;
  hodApprovedBy?: string;
  hodApprovedName?: string;
  leaveMasterId?: number;
  remarks?: string;
  allowEntry?: boolean;
  status?: string;
  statusList?: IMaster[];
  leaveMaster?: ILeaveMaster;
  leaveMasterRemarksDetails?: ILeaveMasterRemarksDetails[];
}

export class LeaveMasterRemarksDetailsBean implements ILeaveMasterRemarksDetailsBean {
  constructor(
    public id?: number,
    public empCode?: string,
    public empName?: string,
    public forwardCode?: string,
    public forwardName?: string,
    public createdBy?: string,
    public createdName?: string,
    public hodApprovedBy?: string,
    public hodApprovedName?: string,
    public leaveMasterId?: number,
    public remarks?: string,
    public allowEntry?: boolean,
    public status?: string,
    public statusList?: IMaster[],
    public leaveMaster?: ILeaveMaster,
    leaveMasterRemarksDetails?: ILeaveMasterRemarksDetails[]
  ) {}
}
