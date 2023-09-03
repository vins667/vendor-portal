export interface IVcutStylePlanSessionBreakupId {
  startTime?: string;
  vcutStylePlanUploadId?: number;
}

export class VcutStylePlanSessionBreakupId implements IVcutStylePlanSessionBreakupId {
  constructor(public startTime?: string, public vcutStylePlanUploadId?: number) {}
}
