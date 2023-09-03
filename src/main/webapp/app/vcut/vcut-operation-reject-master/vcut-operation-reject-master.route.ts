import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { VcutOperationRejectMaster } from 'app/shared/model/vcut-operation-reject-master.model';
import { VcutOperationRejectMasterService } from './vcut-operation-reject-master.service';
import { VcutOperationRejectMasterComponent } from './vcut-operation-reject-master.component';
import { VcutOperationRejectMasterDetailComponent } from './vcut-operation-reject-master-detail.component';
import { VcutOperationRejectMasterUpdateComponent } from './vcut-operation-reject-master-update.component';
import { VcutOperationRejectMasterDeletePopupComponent } from './vcut-operation-reject-master-delete-dialog.component';
import { IVcutOperationRejectMaster } from 'app/shared/model/vcut-operation-reject-master.model';

@Injectable({ providedIn: 'root' })
export class VcutOperationRejectMasterResolve implements Resolve<IVcutOperationRejectMaster> {
  constructor(private service: VcutOperationRejectMasterService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IVcutOperationRejectMaster> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<VcutOperationRejectMaster>) => response.ok),
        map((vcutOperationRejectMaster: HttpResponse<VcutOperationRejectMaster>) => vcutOperationRejectMaster.body)
      );
    }
    return of(new VcutOperationRejectMaster());
  }
}

export const vcutOperationRejectMasterRoute: Routes = [
  {
    path: '',
    component: VcutOperationRejectMasterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'VcutOperationRejectMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: VcutOperationRejectMasterDetailComponent,
    resolve: {
      vcutOperationRejectMaster: VcutOperationRejectMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'VcutOperationRejectMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: VcutOperationRejectMasterUpdateComponent,
    resolve: {
      vcutOperationRejectMaster: VcutOperationRejectMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'VcutOperationRejectMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: VcutOperationRejectMasterUpdateComponent,
    resolve: {
      vcutOperationRejectMaster: VcutOperationRejectMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'VcutOperationRejectMasters'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const vcutOperationRejectMasterPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: VcutOperationRejectMasterDeletePopupComponent,
    resolve: {
      vcutOperationRejectMaster: VcutOperationRejectMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'VcutOperationRejectMasters'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
