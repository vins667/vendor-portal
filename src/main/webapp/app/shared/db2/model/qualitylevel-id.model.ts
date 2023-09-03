export interface IQualitylevelId {
  companycode?: string;
  itemtypecode?: string;
  code?: number;
}

export class QualitylevelId implements IQualitylevelId {
  constructor(public companycode?: string, public itemtypecode?: string, public code?: number) {}
}
