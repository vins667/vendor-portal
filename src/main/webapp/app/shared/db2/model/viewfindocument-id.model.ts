export interface IViewfindocumentId {
  companycode?: string;
  businessunitcode?: string;
  financialyearcode?: number;
  documenttemplatecode?: string;
  findocumentcode?: string;
  linenumber?: number;
}

export class ViewfindocumentId implements IViewfindocumentId {
  constructor(
    public companycode?: string,
    public businessunitcode?: string,
    public financialyearcode?: number,
    public documenttemplatecode?: string,
    public findocumentcode?: string,
    public linenumber?: number
  ) {}
}
