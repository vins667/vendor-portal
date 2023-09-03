import { Moment } from 'moment';
import { IPoliciesGroup } from 'app/shared/model//policies-group.model';

export interface IPolicies {
  id?: number;
  policyName?: string;
  policyFile?: string;
  createdBy?: string;
  createdDate?: Moment;
  ordering?: number;
  policiesGroup?: IPoliciesGroup;
}

export class Policies implements IPolicies {
  constructor(
    public id?: number,
    public policyName?: string,
    public policyFile?: string,
    public createdBy?: string,
    public createdDate?: Moment,
    public ordering?: number,
    public policiesGroup?: IPoliciesGroup
  ) {}
}
