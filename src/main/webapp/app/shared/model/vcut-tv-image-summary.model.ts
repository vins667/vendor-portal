import { IVcutTvcCordinate } from './vcut-tvc-cordinate.model';

export interface IVcutTvImageSummary {
  currentDateTime?: string;
  day?: string;
  finishDays?: string;
  finishHours?: string;
  finishMinutes?: string;
  buyerName?: string;
  style?: string;
  color?: string;
  poNo?: string;
  imageFront?: string;
  imageBack?: string;
  frontCordinates?: IVcutTvcCordinate[];
  backCordinates?: IVcutTvcCordinate[];
}

export class VcutTvImageSummary implements IVcutTvImageSummary {
  constructor(
    public currentDateTime?: string,
    public day?: string,
    public finishDays?: string,
    public finishHours?: string,
    public finishMinutes?: string,
    public buyerName?: string,
    public style?: string,
    public color?: string,
    public poNo?: string,
    public imageFront?: string,
    public imageBack?: string,
    public frontCordinates?: IVcutTvcCordinate[],
    public backCordinates?: IVcutTvcCordinate[]
  ) {}
}
