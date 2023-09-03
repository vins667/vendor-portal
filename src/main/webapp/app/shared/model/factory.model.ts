export interface IFactory {
  factCode?: string;
  factDescription?: string;
  code?: string;
  longdescription?: string;
  searchdescription?: string;
  statecode?: string;
}
export class Factory implements IFactory {
  constructor(
    public factCode?: string,
    public factDescription?: string,
    public code?: string,
    public longdescription?: string,
    public searchdescription?: string,
    public statecode?: string
  ) {}
}
