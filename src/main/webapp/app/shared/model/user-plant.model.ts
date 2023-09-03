import { IUserPlantId } from 'app/shared/model/user-plant-id.model';

export interface IUserPlant {
  id?: IUserPlantId;
  plantDescription?: string;
}
export class UserPlant implements IUserPlant {
  constructor(public id?: IUserPlantId, public plantDescription?: string) {}
}
