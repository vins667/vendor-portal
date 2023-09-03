import { Moment } from 'moment';
import { ICutPlanEntry } from 'app/shared/model/cut-plan-entry.model';

export interface ICutPlanEntryDetails {
  id?: number;
  markercode?: string;
  itemtypecode?: string;
  decosubcode01?: string;
  decosubcode02?: string;
  decosubcode03?: string;
  decosubcode04?: string;
  decosubcode05?: string;
  decosubcode06?: string;
  decosubcode07?: string;
  decosubcode08?: string;
  decosubcode09?: string;
  decosubcode10?: string;
  summerizedDescription?: string;
  logicalwarehousecode?: string;
  physicalwarehousecode?: string;
  qualitylevelcode?: string;
  suppliertype?: string;
  suppliercode?: string;
  projectcode?: string;
  containerelementcode?: string;
  elementssubcodekey?: string;
  lotcode?: string;
  elementscode?: string;
  baseprimaryunitcode?: string;
  baseprimaryquantityunit?: number;
  basesecondaryunitcode?: string;
  basesecondaryquantityunit?: number;
  noPlies?: number;
  endBits?: number;
  allowPlies?: boolean;
  splitNoPlies?: number;
  splitEndBits?: number;
  splitFlag?: string;
  splitTransactionnumber?: number;
  createdby?: string;
  createddate?: Moment;
  lastupdatedby?: string;
  lastupdateddate?: Moment;
  issuedBy?: string;
  issuedDate?: any;
  transactionId?: number;
  resourceCode?: string;
  resourceDescription?: string;
  cutPlanEntries?: ICutPlanEntry[];
}

export class CutPlanEntryDetails implements ICutPlanEntryDetails {
  constructor(
    public id?: number,
    public markercode?: string,
    public itemtypecode?: string,
    public decosubcode01?: string,
    public decosubcode02?: string,
    public decosubcode03?: string,
    public decosubcode04?: string,
    public decosubcode05?: string,
    public decosubcode06?: string,
    public decosubcode07?: string,
    public decosubcode08?: string,
    public decosubcode09?: string,
    public decosubcode10?: string,
    public summerizedDescription?: string,
    public logicalwarehousecode?: string,
    public physicalwarehousecode?: string,
    public qualitylevelcode?: string,
    public suppliertype?: string,
    public suppliercode?: string,
    public projectcode?: string,
    public containerelementcode?: string,
    public elementssubcodekey?: string,
    public lotcode?: string,
    public elementscode?: string,
    public baseprimaryunitcode?: string,
    public baseprimaryquantityunit?: number,
    public basesecondaryunitcode?: string,
    public basesecondaryquantityunit?: number,
    public noPlies?: number,
    public endBits?: number,
    public allowPlies?: boolean,
    public splitNoPlies?: number,
    public splitEndBits?: number,
    public splitFlag?: string,
    public splitTransactionnumber?: number,
    public createdby?: string,
    public createddate?: Moment,
    public lastupdatedby?: string,
    public lastupdateddate?: Moment,
    public issuedBy?: string,
    public issuedDate?: any,
    public transactionId?: number,
    public resourceCode?: string,
    public resourceDescription?: string,
    public cutPlanEntries?: ICutPlanEntry[]
  ) {
    this.allowPlies = this.allowPlies || false;
  }
}
