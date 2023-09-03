export interface ICutPlanBundleSizesBean {
  sizeCode?: string;
  quantity?: number;
}

export class CutPlanBundleSizesBean implements ICutPlanBundleSizesBean {
  constructor(public sizeCode?: string, public quantity?: number) {}
}
