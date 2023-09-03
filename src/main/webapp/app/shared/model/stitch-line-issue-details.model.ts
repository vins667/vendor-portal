export interface IStitchLineIssueDetails {
  id?: number;
  detailId?: number;
  productionCode?: string;
  groupstepnumber?: number;
  demandcountercode?: string;
  demandcode?: string;
  plantCode?: string;
  itemtypecode?: string;
  subcode01?: string;
  subcode02?: string;
  subcode03?: string;
  subcode04?: string;
  subcode05?: string;
  subcode06?: string;
  subcode07?: string;
  subcode08?: string;
  subcode09?: string;
  subcode10?: string;
  summerizeddescription?: string;
  logicalwarehousecode?: string;
  primaryquantity?: number;
  primaryuomcode?: string;
  secondaryquantity?: number;
  secondaryuomcode?: string;
  physicalwarehousecode?: string;
  whslocationwarehousezonecode?: string;
  warehouselocationcode?: string;
  lotcode?: string;
  projectcode?: string;
  bundleCode?: string;
  startPiece?: string;
  endPiece?: string;
  colorCode?: string;
  line?: string;
  lineDesc?: string;
}

export class StitchLineIssueDetails implements IStitchLineIssueDetails {
  constructor(
    public id?: number,
    public detailId?: number,
    public productionCode?: string,
    public groupstepnumber?: number,
    public demandcountercode?: string,
    public demandcode?: string,
    public plantCode?: string,
    public itemtypecode?: string,
    public subcode01?: string,
    public subcode02?: string,
    public subcode03?: string,
    public subcode04?: string,
    public subcode05?: string,
    public subcode06?: string,
    public subcode07?: string,
    public subcode08?: string,
    public subcode09?: string,
    public subcode10?: string,
    public summerizeddescription?: string,
    public logicalwarehousecode?: string,
    public primaryquantity?: number,
    public primaryuomcode?: string,
    public secondaryquantity?: number,
    public secondaryuomcode?: string,
    public physicalwarehousecode?: string,
    public whslocationwarehousezonecode?: string,
    public warehouselocationcode?: string,
    public lotcode?: string,
    public projectcode?: string,
    public bundleCode?: string,
    public startPiece?: string,
    public endPiece?: string,
    public colorCode?: string,
    public line?: string,
    public lineDesc?: string
  ) {}
}
