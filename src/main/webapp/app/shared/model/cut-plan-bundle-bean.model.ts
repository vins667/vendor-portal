import { ICutPlanBundleSizesBean } from './cut-plan-bundle-sizes-bean.model';
import { ICutPlanBundleDetailsBean } from 'app/shared/model/cut-plan-bundle-details-bean.model';
import { ICutPlanBundleMatrixBean } from 'app/shared/model/cut-plan-bundle-matrix-bean.model';

export interface ICutPlanBundleBean {
  saveFlag?: boolean;
  cutPlanBundleSizesBeans?: ICutPlanBundleSizesBean[];
  cutPlanBundleDetailsBeans?: ICutPlanBundleDetailsBean[];
  cutPlanBundleMatrixBeans?: ICutPlanBundleMatrixBean[];
}

export class CutPlanBundleBean implements ICutPlanBundleBean {
  constructor(
    public saveFlag?: boolean,
    public cutPlanBundleSizesBeans?: ICutPlanBundleSizesBean[],
    public cutPlanBundleDetailsBeans?: ICutPlanBundleDetailsBean[],
    public cutPlanBundleMatrixBeans?: ICutPlanBundleMatrixBean[]
  ) {}
}
