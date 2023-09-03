import { Moment } from 'moment';
import { IWorkerFamilyDetails } from 'app/shared/model//worker-family-details.model';
import { INominationTypeMaster } from 'app/shared/model//nomination-type-master.model';
import { IWorkerJoining } from 'app/shared/model//worker-joining.model';

export interface IWorkerNominationDetails {
  id?: number;
  nominationPercentage?: number;
  createdBy?: string;
  createdDate?: Moment;
  workerFamilyDetails?: IWorkerFamilyDetails;
  nominationTypeMaster?: INominationTypeMaster;
  workerJoining?: IWorkerJoining;
}

export class WorkerNominationDetails implements IWorkerNominationDetails {
  constructor(
    public id?: number,
    public nominationPercentage?: number,
    public createdBy?: string,
    public createdDate?: Moment,
    public workerFamilyDetails?: IWorkerFamilyDetails,
    public nominationTypeMaster?: INominationTypeMaster,
    public workerJoining?: IWorkerJoining
  ) {}
}
