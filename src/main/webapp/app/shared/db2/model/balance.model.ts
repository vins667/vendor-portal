import { IBalanceId } from 'app/shared/db2/model/balance-id.model';

export interface IBalance {
  id?: IBalanceId;
  itemtypecompanycode?: string;
  itemtypecode?: string;
  logicalwarehousecompanycode?: string;
  logicalwarehousecode?: string;
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
  physicalwarehousecompanycode?: string;
  physicalwarehousecode?: string;
  whslocationwarehousezonecode?: string;
  warehouselocationcode?: string;
  qualitylevelcode?: number;
  lotcode?: string;
  containeritemtypecode?: string;
  containersubcode01?: string;
  containerelementcode?: string;
  elementssubcodekey?: string;
  elementscode?: string;
  customertype?: string;
  customercode?: string;
  suppliertype?: string;
  suppliercode?: string;
  projectcode?: string;
  statisticalgroupcode?: string;
  stocktypecode?: string;
  detailtype?: string;
  baseprimaryunitcode?: string;
  baseprimaryquantityunit?: number;
  basesecondaryunitcode?: string;
  basesecondaryquantityunit?: number;
  packagingcode?: string;
  packagingquantityunit?: number;
  creationdatetime?: any;
  creationuser?: string;
  lastupdatedatetime?: any;
  lastupdateuser?: string;
  absuniqueid?: number;
  selected?: boolean;
  noofrolls?: number;
  knittingdia1?: number;
  tabledia1?: string;
  gsm1?: string;
  qty1?: number;
  actualissuequantity?: number;
  transactionnumber?: string;
  selectedQuantity?: number;
  selectedbasequantity?: number;
  selectedsecondaryquantity?: number;
  selectedpackagingquantity?: number;
  noPlies?: number;
  endBits?: number;
  actualRollQty?: number;
  actualEndBits?: number;
  actualNoPlies?: number;
  issuedBy?: string;
  issuedDate?: any;
  allowPlies?: boolean;
  splitPlies?: boolean;
  splitNoPlies?: number;
  splitEndBits?: number;
  page?: any;
  size?: number;
  pageNo?: number;
}

export class Balance implements IBalance {
  constructor(
    public id?: IBalanceId,
    public itemtypecompanycode?: string,
    public itemtypecode?: string,
    public logicalwarehousecompanycode?: string,
    public logicalwarehousecode?: string,
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
    public physicalwarehousecompanycode?: string,
    public physicalwarehousecode?: string,
    public whslocationwarehousezonecode?: string,
    public warehouselocationcode?: string,
    public qualitylevelcode?: number,
    public lotcode?: string,
    public containeritemtypecode?: string,
    public containersubcode01?: string,
    public containerelementcode?: string,
    public elementssubcodekey?: string,
    public elementscode?: string,
    public customertype?: string,
    public customercode?: string,
    public suppliertype?: string,
    public suppliercode?: string,
    public projectcode?: string,
    public statisticalgroupcode?: string,
    public stocktypecode?: string,
    public detailtype?: string,
    public baseprimaryunitcode?: string,
    public baseprimaryquantityunit?: number,
    public basesecondaryunitcode?: string,
    public basesecondaryquantityunit?: number,
    public packagingcode?: string,
    public packagingquantityunit?: number,
    public creationdatetime?: any,
    public creationuser?: string,
    public lastupdatedatetime?: any,
    public lastupdateuser?: string,
    public absuniqueid?: number,
    public selected?: boolean,
    public noofrolls?: number,
    public knittingdia1?: number,
    public tabledia1?: string,
    public gsm1?: string,
    public qty1?: number,
    public actualissuequantity?: number,
    public transactionnumber?: string,
    public selectedQuantity?: number,
    public selectedbasequantity?: number,
    public selectedsecondaryquantity?: number,
    public selectedpackagingquantity?: number,
    public noPlies?: number,
    public endBits?: number,
    public actualRollQty?: number,
    public actualNoPlies?: number,
    public actualEndBits?: number,
    public allowPlies?: boolean,
    public splitPlies?: boolean,
    public splitNoPlies?: number,
    public splitEndBits?: number,
    public issuedBy?: string,
    public issuedDate?: any,
    public page?: any,
    public size?: number,
    public pageNo?: number
  ) {}
}
