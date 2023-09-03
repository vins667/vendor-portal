export interface IUserGenericGroup {
  longdescription?: string;
}

export class UserGenericGroup implements IUserGenericGroup {
  constructor(public longdescription?: string) {}
}
