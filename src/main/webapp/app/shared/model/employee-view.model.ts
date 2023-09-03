import { Moment } from 'moment';

export interface IEmployeeView {
  login?: string;
  empCode?: string;
  factory?: string;
  factoryDesc?: string;
  name?: string;
  subCode?: string;
  subName?: string;
  subSname?: string;
  subCodeDesc?: string;
  subCodeAddress?: string;
  imagePath?: string;
  depCode?: string;
  depCodeDesc?: string;
  desCode?: string;
  desCodeDesc?: string;
  add1?: string;
  gCode?: string;
  doj?: Moment;
  dob?: Moment;
  cardNo?: string;
  email?: string;
  phone?: string;
  payCode?: string;
  pan?: string;
  sftCode?: string;
  adhNo?: string;
  uan?: string;
  supervisor?: string;
  tempLock?: string;
  resignDate?: Moment;
  rdate?: Moment;
}

export class EmployeeView implements IEmployeeView {
  constructor(
    public login?: string,
    public empCode?: string,
    public factory?: string,
    public factoryDesc?: string,
    public name?: string,
    public subCode?: string,
    public subName?: string,
    public subSname?: string,
    public subCodeDesc?: string,
    public subCodeAddress?: string,
    public imagePath?: string,
    public depCode?: string,
    public depCodeDesc?: string,
    public desCode?: string,
    public desCodeDesc?: string,
    public add1?: string,
    public gCode?: string,
    public doj?: Moment,
    public dob?: Moment,
    public cardNo?: string,
    public email?: string,
    public phone?: string,
    public payCode?: string,
    public pan?: string,
    public sftCode?: string,
    public adhNo?: string,
    public uan?: string,
    public supervisor?: string,
    public tempLock?: string,
    public resignDate?: Moment,
    public rdate?: Moment
  ) {}
}
