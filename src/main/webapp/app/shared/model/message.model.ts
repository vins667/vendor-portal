export interface IMessage {
  type?: string;
  msg?: string;
}

export class Message implements IMessage {
  constructor(public type?: string, public msg?: string) {}
}
