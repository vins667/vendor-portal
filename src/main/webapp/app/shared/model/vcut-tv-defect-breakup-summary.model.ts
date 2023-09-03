export interface IVcutTvDefectBreakupSummary {
  id?: number;
  description?: string;
  descriptionLocal?: string;
  hours?: number;
  style?: string;
  activeHour?: string;
  countDefect?: number;
  countDefectCum?: number;
}

export class VcutTvDefectBreakupSummary implements IVcutTvDefectBreakupSummary {
  constructor(
    public id?: number,
    public description?: string,
    public descriptionLocal?: string,
    public hours?: number,
    public style?: string,
    public activeHour?: string,
    public countDefect?: number,
    public countDefectCum?: number
  ) {}
}
