import { Moment } from 'moment';
import { INewsDetailsBody } from 'app/shared/model//news-details-body.model';
import { INewsDetailsAttach } from 'app/shared/model//news-details-attach.model';
import { IFactoryMaster } from 'app/shared/model//factory-master.model';
import { INewsMaster } from 'app/shared/model/news-master.model';

export interface INewsDetails {
  id?: number;
  newsTitle?: string;
  displayDays?: number;
  endDate?: Moment;
  flag?: string;
  mailFlag?: boolean;
  notificationFlag?: boolean;
  createdBy?: string;
  createdDate?: Moment;
  approvedBy?: string;
  approvedDate?: Moment;
  newsDetailsBodies?: INewsDetailsBody[];
  newsDetailsAttaches?: INewsDetailsAttach[];
  factoryMasters?: IFactoryMaster[];
  newsMaster?: INewsMaster;
  empCode?: string;
  empName?: string;
  imageUrl?: string;
  newsBody?: string;
}

export class NewsDetails implements INewsDetails {
  constructor(
    public id?: number,
    public newsTitle?: string,
    public displayDays?: number,
    public endDate?: Moment,
    public flag?: string,
    public mailFlag?: boolean,
    public notificationFlag?: boolean,
    public createdBy?: string,
    public createdDate?: Moment,
    public approvedBy?: string,
    public approvedDate?: Moment,
    public newsDetailsBodies?: INewsDetailsBody[],
    public newsDetailsAttaches?: INewsDetailsAttach[],
    public factoryMasters?: IFactoryMaster[],
    public newsMaster?: INewsMaster,
    public empCode?: string,
    public empName?: string,
    public imageUrl?: string,
    public newsBody?: string
  ) {
    this.mailFlag = this.mailFlag || false;
    this.notificationFlag = this.notificationFlag || false;
  }
}
