import { Moment } from 'moment';
import { IStitchIssuePackDetails } from './stitch-issue-pack-details.model';

export interface IStitchIssuePack {
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
  scannedBy?: string;
  stitchIssuePackDetails?: IStitchIssuePackDetails[];
}

export class StitchIssuePack implements IStitchIssuePack {
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
    public scannedBy?: string,
    public stitchIssuePackDetails?: IStitchIssuePackDetails[]
  ) {}
}
