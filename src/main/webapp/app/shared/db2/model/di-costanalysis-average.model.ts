import { IDiCostananlysisAverageId } from 'app/shared/db2/model/di-costanalysis-average-id.model';

export interface IDiCostananlysisAverage {
  id?: IDiCostananlysisAverageId;
  description?: string;
  uomcode?: string;
  costline?: number;
  reqqty?: number;
  price?: number;
  actualreqqty?: number;
  createdby?: string;
  createddate?: any;
}

export class DiCostananlysisAverage implements IDiCostananlysisAverage {
  constructor(
    public id?: IDiCostananlysisAverageId,
    public description?: string,
    public uomcode?: string,
    public costline?: number,
    public reqqty?: number,
    public price?: number,
    public actualreqqty?: number,
    public createdby?: string,
    public createddate?: any
  ) {}
}
