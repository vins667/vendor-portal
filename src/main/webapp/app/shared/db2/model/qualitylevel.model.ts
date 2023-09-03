import { IQualitylevelId } from 'app/shared/db2/model/qualitylevel-id.model';

export interface IQualitylevel {
  id?: IQualitylevelId;
  longdescription?: string;
  shortdescription?: string;
  searchdescription?: string;
  creationdatetime?: any;
  creationuser?: string;
  lastupdatedatetime?: any;
  lastupdateuser?: string;
  absuniqueid?: number;
}

export class Qualitylevel implements IQualitylevel {
  constructor(
    public id?: IQualitylevelId,
    public longdescription?: string,
    public shortdescription?: string,
    public searchdescription?: string,
    public creationdatetime?: any,
    public creationuser?: string,
    public lastupdatedatetime?: any,
    public lastupdateuser?: string,
    public absuniqueid?: number
  ) {}
}
