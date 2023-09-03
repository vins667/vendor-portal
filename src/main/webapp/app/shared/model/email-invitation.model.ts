import { Moment } from 'moment';

export interface IEmailInvitation {
  id?: number;
  emailId?: string;
  registered?: boolean;
  createdBy?: string;
  createdDate?: Moment;
}

export class EmailInvitation implements IEmailInvitation {
  constructor(
    public id?: number,
    public emailId?: string,
    public registered?: boolean,
    public createdBy?: string,
    public createdDate?: Moment
  ) {
    this.registered = this.registered || false;
  }
}
