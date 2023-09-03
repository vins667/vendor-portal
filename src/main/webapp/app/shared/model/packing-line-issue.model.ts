import { Moment } from 'moment';
import { IStitchIssuePackDetails } from 'app/shared/model/stitch-issue-pack-details.model';

export interface IPackingLineIssue {
  id?: number;
  porductionCounterCode?: string;
  productionCode?: string;
  plantCode?: string;
  plantDescription?: string;
  projectcode?: string;
  style?: string;
  color?: string;
  colordescription?: string;
  destination?: string;
  destinationDesc?: string;
  line?: string;
  lineDesc?: string;
  issuedate?: Moment;
  createdby?: string;
  createddate?: Moment;
  lastupdatedby?: string;
  lastupdateddate?: Moment;
  postedBy?: string;
  postedDate?: Moment;
  scannedBy?: string;
  stitchIssuePackDetails?: IStitchIssuePackDetails[];
}

export class PackingLineIssue implements IPackingLineIssue {
  constructor(
    public id?: number,
    public porductionCounterCode?: string,
    public productionCode?: string,
    public plantCode?: string,
    public plantDescription?: string,
    public projectcode?: string,
    public style?: string,
    public color?: string,
    public colordescription?: string,
    public destination?: string,
    public destinationDesc?: string,
    public line?: string,
    public lineDesc?: string,
    public issuedate?: Moment,
    public createdby?: string,
    public createddate?: Moment,
    public lastupdatedby?: string,
    public lastupdateddate?: Moment,
    public postedBy?: string,
    public postedDate?: Moment,
    public scannedBy?: string,
    public stitchIssuePackDetails?: IStitchIssuePackDetails[]
  ) {}
}
