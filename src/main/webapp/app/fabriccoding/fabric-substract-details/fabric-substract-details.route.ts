import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { FabricSubstractDetails } from 'app/shared/model/fabric-substract-details.model';
import { FabricSubstractDetailsService } from './fabric-substract-details.service';
import { FabricSubstractDetailsComponent } from './fabric-substract-details.component';
import { FabricSubstractDetailsDetailComponent } from './fabric-substract-details-detail.component';
import { FabricSubstractDetailsUpdateComponent } from './fabric-substract-details-update.component';
import { FabricSubstractDetailsDeletePopupComponent } from './fabric-substract-details-delete-dialog.component';
import { IFabricSubstractDetails } from 'app/shared/model/fabric-substract-details.model';

@Injectable({ providedIn: 'root' })
export class FabricSubstractDetailsResolve implements Resolve<IFabricSubstractDetails> {
  constructor(private service: FabricSubstractDetailsService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IFabricSubstractDetails> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<FabricSubstractDetails>) => response.ok),
        map((fabricSubstractDetails: HttpResponse<FabricSubstractDetails>) => fabricSubstractDetails.body)
      );
    }
    return of(new FabricSubstractDetails());
  }
}

export const fabricSubstractDetailsRoute: Routes = [
  {
    path: '',
    component: FabricSubstractDetailsComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'FabricSubstractDetails'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: FabricSubstractDetailsDetailComponent,
    resolve: {
      fabricSubstractDetails: FabricSubstractDetailsResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'FabricSubstractDetails'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: FabricSubstractDetailsUpdateComponent,
    resolve: {
      fabricSubstractDetails: FabricSubstractDetailsResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'FabricSubstractDetails'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: FabricSubstractDetailsUpdateComponent,
    resolve: {
      fabricSubstractDetails: FabricSubstractDetailsResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'FabricSubstractDetails'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const fabricSubstractDetailsPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: FabricSubstractDetailsDeletePopupComponent,
    resolve: {
      fabricSubstractDetails: FabricSubstractDetailsResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'FabricSubstractDetails'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
