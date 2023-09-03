export interface IFindocumentId {
  companycode?: string;
  businessunitcode?: string;
  financialyearcode?: number;
  documenttemplatecode?: string;
  code?: string;
  statisticalgroupcode?: string;
}

export class FindocumentId implements IFindocumentId {
  constructor(
    public companycode?: string,
    public businessunitcode?: string,
    public financialyearcode?: number,
    public documenttemplatecode?: string,
    public code?: string,
    public statisticalgroupcode?: string
  ) {}
}
