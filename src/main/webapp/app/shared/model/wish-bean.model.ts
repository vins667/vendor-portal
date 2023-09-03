import { IEmployeeView } from 'app/shared/model/employee-view.model';

export interface IWishBean {
  messageType?: string;
  celebrationMessageText?: string;
  employeeView?: IEmployeeView;
}
export class WishBean implements IWishBean {
  constructor(public messageType?: string, public celebrationMessageText?: string, public employeeView?: IEmployeeView) {}
}
