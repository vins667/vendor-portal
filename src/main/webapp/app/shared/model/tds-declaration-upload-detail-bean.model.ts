export interface ITdsDeclarationUploadDetailBean {
  id?: number;
  fileName?: string;
  originalFileName?: string;
  accept?: boolean;
  reject?: boolean;
  approvalFlag?: string;
}

export class TdsDeclarationUploadDetailBean implements ITdsDeclarationUploadDetailBean {
  constructor(
    public id?: number,
    public fileName?: string,
    public originalFileName?: string,
    public accept?: boolean,
    public reject?: boolean,
    public approvalFlag?: string
  ) {}
}
