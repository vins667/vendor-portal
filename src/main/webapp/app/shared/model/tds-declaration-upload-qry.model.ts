export interface ITdsDeclarationUploadQry {
  id?: number;
  financialYear?: string;
  cardNo?: string;
  name?: string;
  contactNumber?: string;
  emailId?: string;
  factory?: string;
  department?: string;
  designation?: string;
  fileName?: string;
  originalFileName?: string;
}
export class TdsDeclarationUploadQry implements ITdsDeclarationUploadQry {
  constructor(
    public id?: number,
    public financialYear?: string,
    public cardNo?: string,
    public name?: string,
    public contactNumber?: string,
    public emailId?: string,
    public factory?: string,
    public department?: string,
    public designation?: string,
    public fileName?: string,
    public originalFileName?: string
  ) {}
}
