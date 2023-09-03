import { Moment } from 'moment';

export interface IDocumentMaster {
  id?: number;
  documentName?: string;
  documentDescription?: string;
  requiredField?: boolean;
  status?: string;
  createdBy?: string;
  createdDate?: Moment;
  lastUpdatedBy?: string;
  lastUpdatedDate?: Moment;
}

export class DocumentMaster implements IDocumentMaster {
  constructor(
    public id?: number,
    public documentName?: string,
    public documentDescription?: string,
    public requiredField?: boolean,
    public status?: string,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastUpdatedBy?: string,
    public lastUpdatedDate?: Moment
  ) {
    this.requiredField = this.requiredField || false;
  }
}
