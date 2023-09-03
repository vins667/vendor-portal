export interface ITrailMockSearchOperation {
  name?: string;
  aadharNo?: string;
  department?: string;
  designation?: string;
  size?: number;
  pageNo?: number;
}

export class TrailMockSearchOperation implements ITrailMockSearchOperation {
  constructor(
    public name?: string,
    public aadharNo?: string,
    public department?: string,
    public designation?: string,
    public size?: number,
    public pageNo?: number
  ) {}
}
