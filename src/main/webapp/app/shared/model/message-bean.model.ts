export interface IMessageBean {
  exist?: boolean;
  errorMessage?: string;
}

export class MessageBean implements IMessageBean {
  constructor(public exist?: boolean, public errorMessage?: string) {}
}
