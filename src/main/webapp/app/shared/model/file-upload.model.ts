export interface IFileUpload {
  id?: number;
  docId?: number;
  docType?: string;
  file?: any;
}

export class FileUpload {
  constructor(public id?: number, public docId?: number, public docType?: string, public file?: any) {}
}
