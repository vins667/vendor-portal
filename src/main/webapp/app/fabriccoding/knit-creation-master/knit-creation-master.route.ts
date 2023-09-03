import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { KnitCreationMaster } from 'app/shared/model/knit-creation-master.model';
import { KnitCreationMasterService } from './knit-creation-master.service';
import { KnitCreationMasterComponent } from './knit-creation-master.component';
import { KnitCreationMasterDetailComponent } from './knit-creation-master-detail.component';
import { KnitCreationMasterUpdateComponent } from './knit-creation-master-update.component';
import { KnitCreationMasterDeletePopupComponent } from './knit-creation-master-delete-dialog.component';
import { IKnitCreationMaster } from 'app/shared/model/knit-creation-master.model';

@Injectable({ providedIn: 'root' })
export class KnitCreationMasterResolve implements Resolve<IKnitCreationMaster> {
  constructor(private service: KnitCreationMasterService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IKnitCreationMaster> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<KnitCreationMaster>) => response.ok),
        map((knitCreationMaster: HttpResponse<KnitCreationMaster>) => knitCreationMaster.body)
      );
    }
    return of(new KnitCreationMaster());
  }
}

export const knitCreationMasterRoute: Routes = [
  {
    path: '',
    component: KnitCreationMasterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'KnitCreationMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: KnitCreationMasterDetailComponent,
    resolve: {
      knitCreationMaster: KnitCreationMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'KnitCreationMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: KnitCreationMasterUpdateComponent,
    resolve: {
      knitCreationMaster: KnitCreationMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'KnitCreationMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: KnitCreationMasterUpdateComponent,
    resolve: {
      knitCreationMaster: KnitCreationMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'KnitCreationMasters'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const knitCreationMasterPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: KnitCreationMasterDeletePopupComponent,
    resolve: {
      knitCreationMaster: KnitCreationMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'KnitCreationMasters'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
