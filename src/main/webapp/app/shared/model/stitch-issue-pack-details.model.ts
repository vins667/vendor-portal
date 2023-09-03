export interface IStitchIssuePackDetails {
  id?: number;
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
  logicalwarehousecode?: string;
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
  baseprimaryunitcode?: string;
  baseprimaryquantityunit?: number;
  basesecondaryunitcode?: string;
  basesecondaryquantityunit?: number;
  packagingcode?: string;
  packagingquantityunit?: number;
  productCode?: string;
  createdby?: string;
  createddate?: any;
  lastupdatedby?: string;
  lastupdateddate?: any;
  stitchStockDetailsId?: number;
  cutPlanBundleDetailsId?: number;
  cutPlanBundleId?: number;
  stitchIssuePackId?: number;
  packingLineIssueDetailsId?: number;
  checked?: any;
}

export class StitchIssuePackDetails implements IStitchIssuePackDetails {
  constructor(
    public id?: number,
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
    public logicalwarehousecode?: string,
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
    public baseprimaryunitcode?: string,
    public baseprimaryquantityunit?: number,
    public basesecondaryunitcode?: string,
    public basesecondaryquantityunit?: number,
    public packagingcode?: string,
    public packagingquantityunit?: number,
    public productCode?: string,
    public createdby?: string,
    public createddate?: any,
    public lastupdatedby?: string,
    public lastupdateddate?: any,
    public stitchStockDetailsId?: number,
    public cutPlanBundleDetailsId?: number,
    public cutPlanBundleId?: number,
    public stitchIssuePackId?: number,
    public packingLineIssueDetailsId?: number,
    public checked?: any
  ) {}
}
