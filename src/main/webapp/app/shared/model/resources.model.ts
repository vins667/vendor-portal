import { IResourcesId } from './resources-id.model';

export interface IResources {
  id?: IResourcesId;
  code?: string;
  longdescription?: string;
  shortdescription?: string;
  searchdescription?: string;
  resourceAllocate?: number;
}

export class Resources implements IResources {
  constructor(
    public id?: IResourcesId,
    public code?: string,
    public longdescription?: string,
    public shortdescription?: string,
    public searchdescription?: string,
    public resourceAllocate?: number
  ) {}
}
