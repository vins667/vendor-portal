import { Moment } from 'moment';
import { IGroupMasterBeans } from 'app/shared/model/group-master-bean.model';
import { ITdsSlabMaster } from 'app/shared/model/tds-slab-master.model';

export interface ITdsComputation {
  id?: number;
  financialYear?: string;
  financialYearRange?: string;
  regime?: boolean;
  cardNo?: string;
  earnLabel1?: string;
  earnAmount1?: number;
  earnLabel2?: string;
  earnAmount2?: number;
  earnLabel3?: string;
  earnAmount3?: number;
  earnLabel4?: string;
  earnAmount4?: number;
  earnLabel5?: string;
  earnAmount5?: number;
  earnLabel6?: string;
  earnAmount6?: number;
  earnLabel7?: string;
  earnAmount7?: number;
  earnLabel8?: string;
  earnAmount8?: number;
  earnLabel9?: string;
  earnAmount9?: number;
  earnLabel10?: string;
  earnAmount10?: number;
  previousEmployerAmount?: number;
  previousEmployerTdsDeduction?: number;
  incentiveAmount?: number;
  pendingMonth?: number;
  rentDeclare?: number;
  rentExempt?: number;
  earnTotal?: number;
  standardDeduction?: number;
  standardTotal?: number;
  totalTaxIncome?: number;
  taxValue?: number;
  cessValue?: number;
  totalTaxLiability?: number;
  taxDeductValue?: number;
  balanceTaxValue?: number;
  name?: string;
  designation?: string;
  panNo?: string;
  dateOfBirth?: Moment;
  contactNumber?: string;
  emailId?: string;
  address?: string;
  monthRent?: string;
  landLoardName?: string;
  landLoardPanNo?: string;
  landLoardAddress?: string;
  processDate?: any;
  locked?: boolean;
  groupMasterBeans?: IGroupMasterBeans[];
  tdsSlabMasters?: ITdsSlabMaster[];
}

export class TdsComputation implements ITdsComputation {
  constructor(
    public id?: number,
    public financialYear?: string,
    public regime?: boolean,
    public financialYearRange?: string,
    public cardNo?: string,
    public earnLabel1?: string,
    public earnAmount1?: number,
    public earnLabel2?: string,
    public earnAmount2?: number,
    public earnLabel3?: string,
    public earnAmount3?: number,
    public earnLabel4?: string,
    public earnAmount4?: number,
    public earnLabel5?: string,
    public earnAmount5?: number,
    public earnLabel6?: string,
    public earnAmount6?: number,
    public earnLabel7?: string,
    public earnAmount7?: number,
    public earnLabel8?: string,
    public earnAmount8?: number,
    public earnLabel9?: string,
    public earnAmount9?: number,
    public earnLabel10?: string,
    public earnAmount10?: number,
    public previousEmployerAmount?: number,
    public previousEmployerTdsDeduction?: number,
    public incentiveAmount?: number,
    public pendingMonth?: number,
    public rentDeclare?: number,
    public rentExempt?: number,
    public earnTotal?: number,
    public standardDeduction?: number,
    public standardTotal?: number,
    public totalTaxIncome?: number,
    public taxValue?: number,
    public cessValue?: number,
    public totalTaxLiability?: number,
    public taxDeductValue?: number,
    public balanceTaxValue?: number,
    public name?: string,
    public designation?: string,
    public panNo?: string,
    public dateOfBirth?: Moment,
    public contactNumber?: string,
    public emailId?: string,
    public address?: string,
    public monthRent?: string,
    public landLoardName?: string,
    public landLoardPanNo?: string,
    public landLoardAddress?: string,
    public processDate?: any,
    public locked?: boolean,
    public groupMasterBeans?: IGroupMasterBeans[],
    public tdsSlabMasters?: ITdsSlabMaster[]
  ) {}
}
