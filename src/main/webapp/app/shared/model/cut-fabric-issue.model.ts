import { Moment } from 'moment';

export interface ICutFabricIssue {
  id?: number;
  markercode?: string;
  logicalwarehousecode?: string;
  physicalwarehousecode?: string;
  qualitylevelcode?: string;
  suppliertype?: string;
  suppliercode?: string;
  projectcode?: string;
  lotcode?: string;
  itemcode?: string;
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
  noplies?: number;
  color?: string;
  containerelementcode?: string;
  elementssubcodekey?: string;
  elementscode?: string;
  baseprimaryunitcode?: string;
  baseprimaryquantityunit?: number;
  basesecondaryunitcode?: string;
  basesecondaryquantityunit?: number;
  endbits?: number;
  createdby?: string;
  createddate?: Moment;
}

export class CutFabricIssue implements ICutFabricIssue {
  constructor(
    public id?: number,
    public markercode?: string,
    public logicalwarehousecode?: string,
    public physicalwarehousecode?: string,
    public qualitylevelcode?: string,
    public suppliertype?: string,
    public suppliercode?: string,
    public projectcode?: string,
    public lotcode?: string,
    public itemcode?: string,
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
    public noplies?: number,
    public color?: string,
    public containerelementcode?: string,
    public elementssubcodekey?: string,
    public elementscode?: string,
    public baseprimaryunitcode?: string,
    public baseprimaryquantityunit?: number,
    public basesecondaryunitcode?: string,
    public basesecondaryquantityunit?: number,
    public endbits?: number,
    public createdby?: string,
    public createddate?: Moment
  ) {}
}
