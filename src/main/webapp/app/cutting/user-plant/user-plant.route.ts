import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { UserPlantService } from './user-plant.service';
import { UserPlantComponent } from './user-plant.component';
import { UserPlantDetailComponent } from './user-plant-detail.component';
import { UserPlantUpdateComponent } from './user-plant-update.component';
import { UserPlantDeletePopupComponent } from './user-plant-delete-dialog.component';
import { IUserPlantNew, UserPlantNew } from 'app/shared/model/user-plant-new.model';

@Injectable({ providedIn: 'root' })
export class UserPlantResolve implements Resolve<IUserPlantNew> {
  constructor(private service: UserPlantService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IUserPlantNew> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<UserPlantNew>) => response.ok),
        map((userPlant: HttpResponse<UserPlantNew>) => userPlant.body)
      );
    }
    return of(new UserPlantNew());
  }
}

export const userPlantRoute: Routes = [
  {
    path: '',
    component: UserPlantUpdateComponent,
    resolve: {
      userPlant: UserPlantResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'UserPlants'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: UserPlantDetailComponent,
    resolve: {
      userPlant: UserPlantResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'UserPlants'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const userPlantPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: UserPlantDeletePopupComponent,
    resolve: {
      userPlant: UserPlantResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'UserPlants'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
