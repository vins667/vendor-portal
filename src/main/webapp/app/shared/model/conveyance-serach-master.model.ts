import { Moment } from 'moment';

export interface IConveyanceSearchMaster {
  conveyanceDate?: Moment;
  conveyanceDateTo?: Moment;
  status?: string;
  size?: number;
  pageNo?: number;
}
export class ConveyanceSearchMaster implements IConveyanceSearchMaster {
  constructor(
    public conveyanceDate?: Moment,
    public conveyanceDateTo?: Moment,
    public status?: string,
    public size?: number,
    public pageNo?: number
  ) {}
}
