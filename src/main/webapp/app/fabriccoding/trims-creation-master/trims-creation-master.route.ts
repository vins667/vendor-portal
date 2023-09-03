import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { TrimsCreationMaster } from 'app/shared/model/trims-creation-master.model';
import { TrimsCreationMasterService } from './trims-creation-master.service';
import { TrimsCreationMasterComponent } from './trims-creation-master.component';
import { TrimsCreationMasterDetailComponent } from './trims-creation-master-detail.component';
import { TrimsCreationMasterUpdateComponent } from './trims-creation-master-update.component';
import { TrimsCreationMasterDeletePopupComponent } from './trims-creation-master-delete-dialog.component';
import { ITrimsCreationMaster } from 'app/shared/model/trims-creation-master.model';

@Injectable({ providedIn: 'root' })
export class TrimsCreationMasterResolve implements Resolve<ITrimsCreationMaster> {
  constructor(private service: TrimsCreationMasterService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<TrimsCreationMaster> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<TrimsCreationMaster>) => response.ok),
        map((trimsCreationMaster: HttpResponse<TrimsCreationMaster>) => trimsCreationMaster.body)
      );
    }
    return of(new TrimsCreationMaster());
  }
}

export const trimsCreationMasterRoute: Routes = [
  {
    path: '',
    component: TrimsCreationMasterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'TrimsCreationMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: TrimsCreationMasterDetailComponent,
    resolve: {
      trimsCreationMaster: TrimsCreationMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'TrimsCreationMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: TrimsCreationMasterUpdateComponent,
    resolve: {
      trimsCreationMaster: TrimsCreationMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'TrimsCreationMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: TrimsCreationMasterUpdateComponent,
    resolve: {
      trimsCreationMaster: TrimsCreationMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'TrimsCreationMasters'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const trimsCreationMasterPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: TrimsCreationMasterDeletePopupComponent,
    resolve: {
      trimsCreationMaster: TrimsCreationMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'TrimsCreationMasters'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
