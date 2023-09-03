import { IBankMaster } from './bank-master.model';
import { ICost } from './cost.model';
import { ICountry } from './country.model';
import { IDepartmentMaster } from './department-master.model';
import { IDesignationMaster } from './designation-master.model';
import { IEducationMaster } from './education-master.model';
import { IEducationTypeMaster } from './education-type-master.model';
import { IInstituteMaster } from './institute-master.model';
import { ILanguageMaster } from './language-master.model';
import { INominationTypeMaster } from './nomination-type-master.model';
import { IOccupationMaster } from './occupation-master.model';
import { IRelationMaster } from './relation-master.model';
import { ISubdept } from './subdept.model';
import { IRecruitmentCountryMaster } from './recruitment-country-master.model';
import { ICatgory } from './catgory.model';
import { IFloor } from './floor.model';
import { IFoodcat } from './foodcat.model';
import { IGrade } from './grade.model';
import { ISection } from './section.model';
import { IStaffWork } from './staff-work.model';
import { IWoff } from './woff.model';
import { IShift } from './shift.model';

export interface IWorkerMaster {
  bankMasters?: IBankMaster[];
  catgories?: ICatgory[];
  costs?: ICost[];
  countries?: IRecruitmentCountryMaster[];
  departmentMasters?: IDepartmentMaster[];
  designationMasters?: IDesignationMaster[];
  educationMasters?: IEducationMaster[];
  educationTypeMasters?: IEducationTypeMaster[];
  floors?: IFloor[];
  foodcats?: IFoodcat[];
  grades?: IGrade[];
  instituteMasters?: IInstituteMaster[];
  languageMasters?: ILanguageMaster[];
  nominationTypeMasters?: INominationTypeMaster[];
  occupationMasters?: IOccupationMaster[];
  relationMasters?: IRelationMaster[];
  sections?: ISection[];
  staffWorks?: IStaffWork[];
  subdepts?: ISubdept[];
  woffs?: IWoff[];
  shifts?: IShift[];
}
export class WorkerMaster implements IWorkerMaster {
  constructor(
    public bankMasters?: IBankMaster[],
    public catgories?: ICatgory[],
    public costs?: ICost[],
    public countries?: IRecruitmentCountryMaster[],
    public departmentMasters?: IDepartmentMaster[],
    public designationMasters?: IDesignationMaster[],
    public educationMasters?: IEducationMaster[],
    public educationTypeMasters?: IEducationTypeMaster[],
    public floors?: IFloor[],
    public foodcats?: IFoodcat[],
    public grades?: IGrade[],
    public instituteMasters?: IInstituteMaster[],
    public languageMasters?: ILanguageMaster[],
    public nominationTypeMasters?: INominationTypeMaster[],
    public occupationMasters?: IOccupationMaster[],
    public relationMasters?: IRelationMaster[],
    public sections?: ISection[],
    public staffWorks?: IStaffWork[],
    public subdepts?: ISubdept[],
    public woffs?: IWoff[],
    public shifts?: IShift[]
  ) {}
}
