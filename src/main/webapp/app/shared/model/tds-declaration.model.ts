import { Moment } from 'moment';
import { IGroupMasterBeans } from './group-master-bean.model';
export interface ITdsDeclaration {
  id?: number;
  regime?: boolean;
  cardNo?: string;
  name?: string;
  designation?: string;
  panNo?: string;
  dateOfBirth?: Moment;
  contactNumber?: string;
  emailId?: string;
  address?: string;
  monthRent?: number;
  landLoardName?: string;
  landLoardPanNo?: string;
  landLoardAddress?: string;
  tempLock?: string;
  previousEmployerAmount?: number;
  previousEmployerTdsDeduction?: number;
  incentiveAmount?: number;
  groupMasterBeans?: IGroupMasterBeans[];
}

export class TdsDeclaration implements ITdsDeclaration {
  constructor(
    public id?: number,
    public regime?: boolean,
    public cardNo?: string,
    public name?: string,
    public designation?: string,
    public panNo?: string,
    public dateOfBirth?: Moment,
    public contactNumber?: string,
    public emailId?: string,
    public address?: string,
    public monthRent?: number,
    public landLoardName?: string,
    public landLoardPanNo?: string,
    public landLoardAddress?: string,
    public tempLock?: string,
    public previousEmployerAmount?: number,
    public previousEmployerTdsDeduction?: number,
    public incentiveAmount?: number,
    public groupMasterBeans?: IGroupMasterBeans[]
  ) {}
}
