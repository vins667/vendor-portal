export interface IFoodcat {
  foodCode?: number;
  foodDesc?: string;
}
export class Foodcat implements IFoodcat {
  constructor(public foodCode?: number, public foodDesc?: string) {}
}
