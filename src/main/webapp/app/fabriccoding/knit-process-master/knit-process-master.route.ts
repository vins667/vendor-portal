import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { KnitProcessMaster } from 'app/shared/model/knit-process-master.model';
import { KnitProcessMasterService } from './knit-process-master.service';
import { KnitProcessMasterComponent } from './knit-process-master.component';
import { KnitProcessMasterDetailComponent } from './knit-process-master-detail.component';
import { KnitProcessMasterUpdateComponent } from './knit-process-master-update.component';
import { KnitProcessMasterDeletePopupComponent } from './knit-process-master-delete-dialog.component';
import { IKnitProcessMaster } from 'app/shared/model/knit-process-master.model';

@Injectable({ providedIn: 'root' })
export class KnitProcessMasterResolve implements Resolve<IKnitProcessMaster> {
  constructor(private service: KnitProcessMasterService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IKnitProcessMaster> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<KnitProcessMaster>) => response.ok),
        map((knitProcessMaster: HttpResponse<KnitProcessMaster>) => knitProcessMaster.body)
      );
    }
    return of(new KnitProcessMaster());
  }
}

export const knitProcessMasterRoute: Routes = [
  {
    path: '',
    component: KnitProcessMasterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'KnitProcessMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: KnitProcessMasterDetailComponent,
    resolve: {
      knitProcessMaster: KnitProcessMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'KnitProcessMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: KnitProcessMasterUpdateComponent,
    resolve: {
      knitProcessMaster: KnitProcessMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'KnitProcessMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: KnitProcessMasterUpdateComponent,
    resolve: {
      knitProcessMaster: KnitProcessMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'KnitProcessMasters'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const knitProcessMasterPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: KnitProcessMasterDeletePopupComponent,
    resolve: {
      knitProcessMaster: KnitProcessMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'KnitProcessMasters'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
