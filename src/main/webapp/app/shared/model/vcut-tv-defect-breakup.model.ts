import { IVcutTvDefectBreakupSummary } from './vcut-tv-defect-breakup-summary.model';

export interface IVcutTvDefectBreakup {
  id?: number;
  description?: string;
  descriptionLocal?: string;
  hours?: number;
  defectCount?: number;
  summaries?: IVcutTvDefectBreakupSummary[];
}

export class VcutTvDefectBreakup implements IVcutTvDefectBreakup {
  constructor(
    public id?: number,
    public description?: string,
    public descriptionLocal?: string,
    public defectCount?: number,
    public summaries?: IVcutTvDefectBreakupSummary[]
  ) {}
}
