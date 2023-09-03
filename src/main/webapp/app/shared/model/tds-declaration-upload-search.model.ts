import { ITdsGroupMaster } from './tds-group-master.model';

export interface ITdsDeclarationUploadSearch {
  id?: number;
  cardNo?: string;
  name?: string;
  financialYear?: string;
  poNo?: string;
  size?: number;
  pageNo?: number;
}

export class TdsDeclarationUploadSearch implements ITdsDeclarationUploadSearch {
  constructor(
    public id?: number,
    public cardNo?: string,
    public name?: string,
    public financialYear?: string,
    public poNo?: string,
    public size?: number,
    public pageNo?: number
  ) {}
}
