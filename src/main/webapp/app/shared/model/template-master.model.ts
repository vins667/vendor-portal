import { Moment } from 'moment';
import { ITemplateDetails } from 'app/shared/model/template-details.model';
import { ICategoryMaster } from 'app/shared/model/category-master.model';

export interface ITemplateMaster {
  id?: number;
  templateName?: string;
  flag?: string;
  createdBy?: string;
  createdDate?: Moment;
  lastUpdateBy?: string;
  lastUpdatedDate?: Moment;
  templateDetails?: ITemplateDetails[];
  categoryMaster?: ICategoryMaster;
  expend?: boolean;
}

export class TemplateMaster implements ITemplateMaster {
  constructor(
    public id?: number,
    public templateName?: string,
    public flag?: string,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastUpdateBy?: string,
    public lastUpdatedDate?: Moment,
    public templateDetails?: ITemplateDetails[],
    public categoryMaster?: ICategoryMaster,
    public expend?: boolean
  ) {}
}
