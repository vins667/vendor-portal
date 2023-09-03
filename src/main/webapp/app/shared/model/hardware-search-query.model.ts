export interface IHardwareSearchQuery {
  assetCode?: string;
  storageMin?: number;
  storageMax?: number;
  memoryMin?: number;
  memoryMax?: number;
  size?: number;
  pageNo?: number;
}

export class HardwareSearchQuery implements IHardwareSearchQuery {
  constructor(
    public assetCode?: string,
    public storageMin?: number,
    public storageMax?: number,
    public memoryMin?: number,
    public memoryMax?: number,
    public size?: number,
    public pageNo?: number
  ) {}
}
