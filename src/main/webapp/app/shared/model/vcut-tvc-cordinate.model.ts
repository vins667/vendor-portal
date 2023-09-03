export interface IVcutTvcCordinate {
  coordinateType?: string;
  coordinateX?: string;
  coordinateY?: string;
}

export class VcutTvcCordinate implements IVcutTvcCordinate {
  constructor(public coordinateType?: string, public coordinateX?: string, public coordinateY?: string) {}
}
