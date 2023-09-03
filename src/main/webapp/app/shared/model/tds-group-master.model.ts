import { Moment } from 'moment';
import { ITdsDeclarationUploadDetailBean } from './tds-declaration-upload-detail-bean.model';

export interface ITdsGroupMaster {
  id?: number;
  year?: number;
  groupCode?: string;
  groupDescription?: string;
  groupLimit?: number;
  groupOrder?: number;
  createdBy?: string;
  createdDate?: Moment;
  lastUpdatedBy?: string;
  lastUpdatedDate?: Moment;
  tdsDeclarationUploadDetailBean?: ITdsDeclarationUploadDetailBean[];
}

export class TdsGroupMaster implements ITdsGroupMaster {
  constructor(
    public id?: number,
    public year?: number,
    public groupCode?: string,
    public groupDescription?: string,
    public groupLimit?: number,
    public groupOrder?: number,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastUpdatedBy?: string,
    public lastUpdatedDate?: Moment,
    public tdsDeclarationUploadDetailBean?: ITdsDeclarationUploadDetailBean[]
  ) {}
}
