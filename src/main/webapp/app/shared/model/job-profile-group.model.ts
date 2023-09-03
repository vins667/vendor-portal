import { IJobProfile } from './job-profile.model';

export interface IJobProfileGroup {
  department?: string;
  jobProfiles?: IJobProfile[];
}

export class JobProfileGroup implements IJobProfileGroup {
  constructor(public department?: string, public jobProfiles?: IJobProfile[]) {}
}
