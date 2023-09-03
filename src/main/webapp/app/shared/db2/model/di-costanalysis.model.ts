import { IDiCostananlysisId } from 'app/shared/db2/model/di-costanalysis-id.model';

export interface IDiCostananlysis {
  id?: IDiCostananlysisId;
  description?: string;
  uomcode?: string;
  costline?: number;
  reqqty?: number;
  price?: number;
  actualprice?: number;
  createdby?: string;
  createddate?: any;
}

export class DiCostananlysis implements IDiCostananlysis {
  constructor(
    public id?: IDiCostananlysisId,
    public description?: string,
    public uomcode?: string,
    public costline?: number,
    public reqqty?: number,
    public price?: number,
    public actualprice?: number,
    public createdby?: string,
    public createddate?: any
  ) {}
}
