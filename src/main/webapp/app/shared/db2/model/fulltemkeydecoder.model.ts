import { IFullitemkeydecoderId } from './fulltemkeydecoder-id.model';
export interface IFullitemkeydecoder {
  id?: IFullitemkeydecoderId;
  itemtypecompanycode?: string;
  summarizeddescription?: string;
  uom?: string;
  tariffcode?: string;
  ordersubcode01?: string;
  ordersubcode02?: string;
  ordersubcode03?: string;
  ordersubcode04?: string;
  ordersubcode05?: string;
  ordersubcode06?: string;
  ordersubcode07?: string;
  ordersubcode08?: string;
  ordersubcode09?: string;
  ordersubcode10?: string;
  subcode01Description?: string;
}

export class Fullitemkeydecoder implements IFullitemkeydecoder {
  constructor(
    public id?: IFullitemkeydecoderId,
    public itemtypecompanycode?: string,
    public summarizeddescription?: string,
    public uom?: string,
    public tariffcode?: string,
    public ordersubcode01?: string,
    public ordersubcode02?: string,
    public ordersubcode03?: string,
    public ordersubcode04?: string,
    public ordersubcode05?: string,
    public ordersubcode06?: string,
    public ordersubcode07?: string,
    public ordersubcode08?: string,
    public ordersubcode09?: string,
    public ordersubcode10?: string,
    public subcode01Description?: string
  ) {}
}
