export interface IFindocumentlineId {
  findocumentcompanycode?: string;
  findocumentbusinessunitcode?: string;
  findocumentfinancialyearcode?: number;
  findocdocumenttemplatecode?: string;
  findocumentcode?: string;
  findocstatisticalgroupcode?: string;
}

export class FindocumentlineId implements IFindocumentlineId {
  constructor(
    public findocumentcompanycode?: string,
    public findocumentbusinessunitcode?: string,
    public findocumentfinancialyearcode?: number,
    public findocdocumenttemplatecode?: string,
    public findocumentcode?: string,
    public findocstatisticalgroupcode?: string
  ) {}
}
