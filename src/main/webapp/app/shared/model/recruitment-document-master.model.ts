import { Moment } from 'moment';

export interface IRecruitmentDocumentMaster {
  id?: number;
  description?: string;
  documentMandatory?: boolean;
  forceDocumentType?: string;
  attachType?: string;
  createdBy?: string;
  createdDate?: Moment;
  lastUpdatedBy?: string;
  lastUpdatedDate?: Moment;
  documentType?: string;
}

export class RecruitmentDocumentMaster implements IRecruitmentDocumentMaster {
  constructor(
    public id?: number,
    public description?: string,
    public documentMandatory?: boolean,
    public forceDocumentType?: string,
    public attachType?: string,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastUpdatedBy?: string,
    public lastUpdatedDate?: Moment,
    public documentType?: string
  ) {
    this.documentMandatory = this.documentMandatory || false;
  }
}
