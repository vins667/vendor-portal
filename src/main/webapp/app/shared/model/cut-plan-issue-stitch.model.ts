import { Moment } from 'moment';
import { ICutIssueStitchDetails } from 'app/shared/model/cut-issue-stitch-details.model';

export interface ICutPlanIssueStitch {
  id?: number;
  transactionType?: string;
  plantCode?: string;
  plantDescription?: string;
  destinationPlantCode?: string;
  destinationPlantDescription?: string;
  projectcode?: string;
  style?: string;
  color?: string;
  colordescription?: string;
  destination?: string;
  destinationDesc?: string;
  termsofdeliverycode?: string;
  termsofdeliverydescription?: string;
  termsofshippingcode?: string;
  termsofshippingdescription?: string;
  eway?: string;
  createdby?: string;
  createddate?: Moment;
  lastupdatedby?: string;
  lastupdateddate?: Moment;
  postedBy?: string;
  postedDate?: Moment;
  recieptPostedBy?: string;
  recieptPostedDate?: Moment;
  cutIssueStitchDetails?: ICutIssueStitchDetails[];
}

export class CutPlanIssueStitch implements ICutPlanIssueStitch {
  constructor(
    public id?: number,
    public transactionType?: string,
    public plantCode?: string,
    public plantDescription?: string,
    public destinationPlantCode?: string,
    public destinationPlantDescription?: string,
    public projectcode?: string,
    public style?: string,
    public color?: string,
    public colordescription?: string,
    public destination?: string,
    public destinationDesc?: string,
    public termsofdeliverycode?: string,
    public termsofdeliverydescription?: string,
    public termsofshippingcode?: string,
    public termsofshippingdescription?: string,
    public eway?: string,
    public createdby?: string,
    public createddate?: Moment,
    public lastupdatedby?: string,
    public lastupdateddate?: Moment,
    public postedBy?: string,
    public postedDate?: Moment,
    public recieptPostedBy?: string,
    public recieptPostedDate?: Moment,
    public cutIssueStitchDetails?: ICutIssueStitchDetails[]
  ) {}
}
