import { IJobWorkFollowupBuyerModel } from 'app/finance/job-work-followup/job-work-followup-buyer.model';
import { IFollowupBuyer } from 'app/finance/followup-buyer/followup-buyer.model';
import { IFinfinancialyear } from 'app/shared/db2/model/finfinancialyear.model';

export interface IJobWorkFollowup {
  id?: number;
  size?: number;
  flag?: string;
  code?: string;
  name?: string;
  jobworkcode?: string;
  jobworkname?: string;
  buyercode?: string;
  buyername?: string;
  responsiblepersoncode01?: string;
  responsiblepersonname01?: string;
  responsiblepersonmail01?: string;
  responsiblepersoncode02?: string;
  responsiblepersonname02?: string;
  responsiblepersonmail02?: string;
  responsiblepersoncode03?: string;
  responsiblepersonname03?: string;
  responsiblepersonmail03?: string;
  level01reminderpersoncode01?: string;
  level01reminderpersonname01?: string;
  level01reminderpersonmail01?: string;
  level01reminderpersoncode02?: string;
  level01reminderpersonname02?: string;
  level01reminderpersonmail02?: string;
  level01reminderpersoncode03?: string;
  level01reminderpersonname03?: string;
  level01reminderpersonmail03?: string;
  level02reminderpersoncode01?: string;
  level02reminderpersonname01?: string;
  level02reminderpersonmail01?: string;
  level02reminderpersoncode02?: string;
  level02reminderpersonname02?: string;
  level02reminderpersonmail02?: string;
  level02reminderpersoncode03?: string;
  level02reminderpersonname03?: string;
  level02reminderpersonmail03?: string;
  jobWorkFollowpBuyer?: IFollowupBuyer;
  finFinancialyear?: IFinfinancialyear;
}

export class JobWorkFollowup implements IJobWorkFollowup {
  constructor(
    public id?: number,
    code?: string,
    name?: string,
    public flag?: string,
    public jobworkcode?: string,
    public jobworkname?: string,
    public buyercode?: string,
    public buyername?: string,
    public responsiblepersoncode01?: string,
    public responsiblepersonname01?: string,
    public responsiblepersonmail01?: string,
    public responsiblepersoncode02?: string,
    public responsiblepersonname02?: string,
    public responsiblepersonmail02?: string,
    public responsiblepersoncode03?: string,
    public responsiblepersonname03?: string,
    public responsiblepersonmail03?: string,
    public level01reminderpersoncode01?: string,
    public level01reminderpersonname01?: string,
    public level01reminderpersonmail01?: string,
    public level01reminderpersoncode02?: string,
    public level01reminderpersonname02?: string,
    public level01reminderpersonmail02?: string,
    public level01reminderpersoncode03?: string,
    public level01reminderpersonname03?: string,
    public level01reminderpersonmail03?: string,
    public level02reminderpersoncode01?: string,
    public level02reminderpersonname01?: string,
    public level02reminderpersonmail01?: string,
    public level02reminderpersoncode02?: string,
    public level02reminderpersonname02?: string,
    public level02reminderpersonmail02?: string,
    public level02reminderpersoncode03?: string,
    public level02reminderpersonname03?: string,
    public level02reminderpersonmail03?: string,
    public jobWorkFollowpBuyer?: IFollowupBuyer,
    public finFinancialyear?: IFinfinancialyear,
    public size?: number,
    public pageNo?: string,
    public status?: string
  ) {}
}
