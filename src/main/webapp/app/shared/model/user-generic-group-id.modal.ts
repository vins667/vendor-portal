export interface IUserGenericGroupId {
  usergengrouptypecompanycode?: string;
  usergenericgrouptypecode?: string;
  code?: string;
}

export class UserGenericGroupId implements IUserGenericGroupId {
  constructor(public usergengrouptypecompanycode?: string, public usergenericgrouptypecode?: string, public code?: string) {}
}
