import { DiCostananlysis } from 'app/shared/db2/model/di-costanalysis.model';

export interface IDiCostananlysisId {
  projectcode?: string;
  fatherproductcode?: string;
  workattributes?: string;
  itemnature?: string;
  itemtypecode?: string;
  itemsubcode01?: string;
  itemsubcode02?: string;
  itemsubcode03?: string;
  itemsubcode04?: string;
  itemsubcode05?: string;
  itemsubcode06?: string;
  itemsubcode07?: string;
  itemsubcode08?: string;
  itemsubcode09?: string;
  itemsubcode10?: string;
}

export class DiCostananlysisId implements IDiCostananlysisId {
  constructor(
    public projectcode?: string,
    public fatherproductcode?: string,
    public workattributes?: string,
    public itemnature?: string,
    public itemtypecode?: string,
    public itemsubcode01?: string,
    public itemsubcode02?: string,
    public itemsubcode03?: string,
    public itemsubcode04?: string,
    public itemsubcode05?: string,
    public itemsubcode06?: string,
    public itemsubcode07?: string,
    public itemsubcode08?: string,
    public itemsubcode09?: string,
    public itemsubcode10?: string
  ) {}
}
