import { IUserPlantDetailsNew } from './user-plant-details-new.model';

export interface IUserPlantNew {
  login?: string;
  userPlantDetailsNew?: IUserPlantDetailsNew[];
}
export class UserPlantNew implements IUserPlantNew {
  constructor(public login?: string, public userPlantDetailsNew?: IUserPlantDetailsNew[]) {}
}
