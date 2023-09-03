export interface IPage {
  page?: number;
  size?: number;
  sort?: string;
}

export class Page implements IPage {
  constructor(public page?: number, public size?: number, public sort?: string) {}
}
