import { Moment } from 'moment';
import { IYarnCountMaster } from 'app/shared/model/yarn-count-master.model';
import { IYarnTypeMaster } from 'app/shared/model/yarn-type-master.model';
import { IKnitTypeMaster } from 'app/shared/model/knit-type-master.model';
import { IKnitProcessMaster } from 'app/shared/model/knit-process-master.model';

export interface IKnitCreationMaster {
  id?: number;
  code?: string;
  description?: string;
  createdBy?: string;
  createdDate?: Moment;
  lastUpdatedBy?: string;
  lastUpdatedDate?: Moment;
  yarnCountMaster?: IYarnCountMaster;
  yarnTypeMaster?: IYarnTypeMaster;
  knitTypeMaster?: IKnitTypeMaster;
  knitProcessMaster?: IKnitProcessMaster;
}

export class KnitCreationMaster implements IKnitCreationMaster {
  constructor(
    public id?: number,
    public code?: string,
    public description?: string,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastUpdatedBy?: string,
    public lastUpdatedDate?: Moment,
    public yarnCountMaster?: IYarnCountMaster,
    public yarnTypeMaster?: IYarnTypeMaster,
    public knitTypeMaster?: IKnitTypeMaster,
    public knitProcessMaster?: IKnitProcessMaster
  ) {}
}
