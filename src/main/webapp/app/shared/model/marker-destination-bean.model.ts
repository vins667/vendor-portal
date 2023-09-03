import { ISizesBean } from 'app/shared/model/sizes-bean.model';
import { IDestinationBean } from 'app/shared/model/destination-bean.model';

export interface IMarkerDestinationBean {
  sizeCodes?: ISizesBean[];
  destinationBeans?: IDestinationBean[];
}
export class MarkerDestinationBean implements IMarkerDestinationBean {
  constructor(public sizeCodes?: ISizesBean[], public destinationBeans?: IDestinationBean[]) {}
}
