import { ICutPlanBundleSizesBean } from 'app/shared/model/cut-plan-bundle-sizes-bean.model';

export interface ICutPlanBundleDetailsBean {
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
  cutPlanBundleSizesBeans?: ICutPlanBundleSizesBean[];
}

export class CutPlanBundleDetailsBean implements ICutPlanBundleDetailsBean {
  constructor(
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
    public cutPlanBundleSizesBeans?: ICutPlanBundleSizesBean[]
  ) {}
}
