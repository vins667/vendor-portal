export interface IResourcesId {
  companycode?: string;
  code?: string;
}

export class ResourcesId implements IResourcesId {
  constructor(public companycode?: string, public code?: string) {}
}
