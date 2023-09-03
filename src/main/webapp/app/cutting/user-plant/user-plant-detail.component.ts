import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IUserPlant } from 'app/shared/model/user-plant.model';

@Component({
  selector: 'jhi-user-plant-detail',
  templateUrl: './user-plant-detail.component.html'
})
export class UserPlantDetailComponent implements OnInit {
  userPlant: IUserPlant;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ userPlant }) => {
      this.userPlant = userPlant;
    });
  }

  previousState() {
    window.history.back();
  }
}
