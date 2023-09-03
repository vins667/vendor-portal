import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { BuyerMaster } from 'app/shared/model/buyer-master.model';
import { BuyerMasterService } from './buyer-master.service';
import { BuyerMasterComponent } from './buyer-master.component';
import { BuyerMasterDetailComponent } from './buyer-master-detail.component';
import { BuyerMasterUpdateComponent } from './buyer-master-update.component';
import { BuyerMasterDeletePopupComponent } from './buyer-master-delete-dialog.component';
import { IBuyerMaster } from 'app/shared/model/buyer-master.model';

@Injectable({ providedIn: 'root' })
export class BuyerMasterResolve implements Resolve<IBuyerMaster> {
  constructor(private service: BuyerMasterService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IBuyerMaster> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<BuyerMaster>) => response.ok),
        map((buyerMaster: HttpResponse<BuyerMaster>) => buyerMaster.body)
      );
    }
    return of(new BuyerMaster());
  }
}

export const buyerMasterRoute: Routes = [
  {
    path: '',
    component: BuyerMasterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_ADMIN', 'ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'BuyerMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: BuyerMasterDetailComponent,
    resolve: {
      buyerMaster: BuyerMasterResolve
    },
    data: {
      authorities: ['ROLE_ADMIN', 'ROLE_USER'],
      pageTitle: 'BuyerMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: BuyerMasterUpdateComponent,
    resolve: {
      buyerMaster: BuyerMasterResolve
    },
    data: {
      authorities: ['ROLE_ADMIN', 'ROLE_USER'],
      pageTitle: 'BuyerMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: BuyerMasterUpdateComponent,
    resolve: {
      buyerMaster: BuyerMasterResolve
    },
    data: {
      authorities: ['ROLE_ADMIN', 'ROLE_USER'],
      pageTitle: 'BuyerMasters'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const buyerMasterPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: BuyerMasterDeletePopupComponent,
    resolve: {
      buyerMaster: BuyerMasterResolve
    },
    data: {
      authorities: ['ROLE_ADMIN', 'ROLE_USER'],
      pageTitle: 'BuyerMasters'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
