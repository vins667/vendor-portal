export interface IHardwareQueryBean {
  id?: number;
  assetCode?: string;
  ipAddress?: string;
  hardDisk?: string;
  memory?: string;
  osName?: string;
}

export class HardwareQueryBean implements IHardwareQueryBean {
  constructor(
    public id?: number,
    public assetCode?: string,
    public ipAddress?: string,
    public hardDisk?: string,
    public memory?: string,
    public osName?: string
  ) {}
}
