export interface IJobProfileSearch {
  department?: string;
  designation?: string;
  status?: string;
  size?: number;
  pageNo?: number;
}

export class JobProfileSearch implements IJobProfileSearch {
  constructor(
    public department?: string,
    public designation?: string,
    public status?: string,
    public size?: number,
    public pageNo?: number
  ) {}
}
