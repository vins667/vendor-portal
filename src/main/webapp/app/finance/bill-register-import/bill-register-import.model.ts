import { IBillRegisterImportDetails } from 'app/finance/bill-register-import/bill-register-import-details.model';

export interface IBillRegisterImport {
  id?: number;
  companycode?: string;
  divisioncode?: string;
  invoicetypecode?: string;
  code?: string;
  invoicedate?: any;
  style?: string;
  customercode?: string;
  customername?: string;
  createdby?: string | null;
  createddate?: any;
  updatedby?: string | null;
  updateddate?: any;
  billRegisterDetailsCHA?: IBillRegisterImportDetails[];
  billRegisterDetailsFOR?: IBillRegisterImportDetails[];
  billRegisterDetailsTRA?: IBillRegisterImportDetails[];

  billType?: string;
  billNumber?: string;
  customerSupplierType?: string;
  customerSupplierCode?: string;
  customerSupplierName?: string;
  billDate?: any;
  registerfor?: any;
  selecttype?: any;
}

export class BillRegisterImport implements IBillRegisterImport {
  constructor(
    public id?: number,
    public companycode?: string,
    public divisioncode?: string,
    public invoicetypecode?: string,
    public code?: string,
    public invoicedate?: any,
    public style?: string,
    public customercode?: string,
    public customername?: string,
    public createdby?: string | null,
    public createddate?: any,
    public updatedby?: string | null,
    public updateddate?: any,
    public billRegisterDetailsCHA?: IBillRegisterImportDetails[],
    public billRegisterDetailsFOR?: IBillRegisterImportDetails[],
    public billRegisterDetailsTRA?: IBillRegisterImportDetails[],
    public billType?: string,
    public billNumber?: string,
    public customerSupplierType?: string,
    public customerSupplierCode?: string,
    public customerSupplierName?: string,
    public billDate?: any,
    public registerfor?: any,
    public selecttype?: any
  ) {}
}

export function getBillRegisterImportIdentifier(billRegister: IBillRegisterImport): number | undefined {
  return billRegister.id;
}
