import { ICutPlanBundleMatrixBreakup } from './cut-plan-bundle-matrix-breakup.model';

export interface ICutPlanBundleMatrixBean {
  id?: number;
  porductionCounterCode?: string;
  productionCode?: string;
  plantCode?: string;
  style?: string;
  color?: string;
  sizeCode?: string;
  allotedQty?: number;
  bundledQty?: number;
  balanceQty?: number;
  bundleSize?: number;
  bundlePcs?: number;
  destination?: string;
  expend?: boolean;
  cutPlanBundleMatrixBreakups?: ICutPlanBundleMatrixBreakup[];
  cutPlanBundleMatrixExistBreakups?: ICutPlanBundleMatrixBreakup[];
}
export class CutPlanBundleMatrixBean implements ICutPlanBundleMatrixBean {
  constructor(
    public id?: number,
    public porductionCounterCode?: string,
    public productionCode?: string,
    public plantCode?: string,
    public style?: string,
    public color?: string,
    public sizeCode?: string,
    public allotedQty?: number,
    public bundledQty?: number,
    public balanceQty?: number,
    public bundleSize?: number,
    public bundlePcs?: number,
    public destination?: string,
    public expend?: boolean,
    public cutPlanBundleMatrixBreakups?: ICutPlanBundleMatrixBreakup[],
    public cutPlanBundleMatrixExistBreakups?: ICutPlanBundleMatrixBreakup[]
  ) {}
}
