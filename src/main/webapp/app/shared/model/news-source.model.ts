export interface INewsSource {
  source?: string;
  id?: string;
  name?: string;
}

export class NewsSource implements INewsSource {
  constructor(public source?: string, public id?: string, public name?: string) {}
}
