import { IFindocumentId } from './findocument-id.model';
import { IFindocumentlineId } from 'app/shared/db2/model/findocumentline-id.model';

export interface IFindocumentline {
  id?: IFindocumentlineId;
  glcode?: string;
  slcustomersuppliertype?: string;
  slcustomersuppliercode?: string;
  amountindc?: number;
  amountincc?: number;
  documentcurrencycode?: string;
  companycurrencycode?: string;
  exchangerate?: number;
  profitcentercode?: string;
  costcentercode?: string;
  currentstatus?: string;
}

export class Findocumentline implements IFindocumentline {
  constructor(
    public id?: IFindocumentlineId,
    public glcode?: string,
    public slcustomersuppliertype?: string,
    public slcustomersuppliercode?: string,
    public amountindc?: number,
    public amountincc?: number,
    public documentcurrencycode?: string,
    public companycurrencycode?: string,
    public exchangerate?: number,
    public profitcentercode?: string,
    public costcentercode?: string,
    public currentstatus?: string
  ) {}
}
