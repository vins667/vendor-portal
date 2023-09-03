import { IPolicies } from './policies.model';

export interface IPolicyGroup {
  policyGroup?: string;
  policies?: IPolicies[];
}

export class PolicyGroup implements IPolicyGroup {
  constructor(public policyGroup?: string, public policies?: IPolicies[]) {}
}
