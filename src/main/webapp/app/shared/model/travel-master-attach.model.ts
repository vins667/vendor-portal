import { Moment } from 'moment';
import { ITravelApplicationMaster } from 'app/shared/model/travel-application-master.model';

export interface ITravelMasterAttach {
  id?: number;
  attachFile?: string;
  attachDisplayFile?: string;
  attchType?: string;
  createdBy?: string;
  createdDate?: Moment;
  travelApplicationMaster?: ITravelApplicationMaster;
}

export class TravelMasterAttach implements ITravelMasterAttach {
  constructor(
    public id?: number,
    public attachFile?: string,
    public attachDisplayFile?: string,
    public attchType?: string,
    public createdBy?: string,
    public createdDate?: Moment,
    public travelApplicationMaster?: ITravelApplicationMaster
  ) {}
}
