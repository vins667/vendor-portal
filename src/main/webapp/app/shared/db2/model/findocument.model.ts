import { IFindocumentId } from './findocument-id.model';
import { IFindocumentline } from 'app/shared/db2/model/findocumentline.model';

export interface IFindocument {
  id?: IFindocumentId;
  currentstatus?: string;
  progressstatus?: string;
  tdspercentage?: number;
  tdsapplicableamount?: number;
  tdsamount?: number;
  financedocumentdate?: string;
  postingdate?: string;
  duedate?: string;
  projectcode?: string;
  documentamount?: number;
  exchangerate?: number;
  suppliertype?: string;
  suppliercode?: string;
  findocumentline?: IFindocumentline[];
}

export class Findocument implements IFindocument {
  constructor(
    public id?: IFindocumentId,
    public currentstatus?: string,
    public progressstatus?: string,
    public tdspercentage?: number,
    public tdsapplicableamount?: number,
    public tdsamount?: number,
    public financedocumentdate?: string,
    public postingdate?: string,
    public duedate?: string,
    public projectcode?: string,
    public documentamount?: number,
    public exchangerate?: number,
    public suppliertype?: string,
    public suppliercode?: string,
    public findocumentline?: IFindocumentline[]
  ) {}
}
