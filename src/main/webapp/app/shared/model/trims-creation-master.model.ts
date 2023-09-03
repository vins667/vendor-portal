import { ITrimsTemplateMaster } from 'app/shared/model//trims-template-master.model';

export interface ITrimsCreationMaster {
  id?: number;
  code?: string;
  description?: string;
  trimsTemplateMaster?: ITrimsTemplateMaster;
}

export class TrimsCreationMaster implements ITrimsCreationMaster {
  constructor(public id?: number, public code?: string, public description?: string, public trimsTemplateMaster?: ITrimsTemplateMaster) {}
}
