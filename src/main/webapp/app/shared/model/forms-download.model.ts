import { Moment } from 'moment';

export interface IFormsDownload {
  id?: number;
  formName?: string;
  fileName?: string;
  createdBy?: string;
  createdDate?: Moment;
}

export class FormsDownload implements IFormsDownload {
  constructor(
    public id?: number,
    public formName?: string,
    public fileName?: string,
    public createdBy?: string,
    public createdDate?: Moment
  ) {}
}
