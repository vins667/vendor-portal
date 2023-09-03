export interface IEventSearch {
  date?: any;
  cardNo?: string;
}

export class EventSearch implements IEventSearch {
  constructor(public date?: any, public cardNo?: string) {}
}
