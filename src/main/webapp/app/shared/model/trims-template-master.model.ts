import { Moment } from 'moment';
import { ITrimsTemplateDetails } from './trims-template-details.model';

export interface ITrimsTemplateMaster {
  id?: number;
  accessoriesCode?: string;
  description?: string;
  flag?: string;
  createdBy?: string;
  createdDate?: Moment;
  lastUpdatedBy?: string;
  lastUpdatedDate?: Moment;
  trimsTemplateDetails?: ITrimsTemplateDetails[];
}

export class TrimsTemplateMaster implements ITrimsTemplateMaster {
  constructor(
    public id?: number,
    public accessoriesCode?: string,
    public description?: string,
    public flag?: string,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastUpdatedBy?: string,
    public lastUpdatedDate?: Moment,
    public trimsTemplateDetails?: ITrimsTemplateDetails[]
  ) {}
}
