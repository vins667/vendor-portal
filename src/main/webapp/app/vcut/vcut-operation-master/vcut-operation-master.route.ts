import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { VcutOperationMaster } from 'app/shared/model/vcut-operation-master.model';
import { VcutOperationMasterService } from './vcut-operation-master.service';
import { VcutOperationMasterComponent } from './vcut-operation-master.component';
import { VcutOperationMasterDetailComponent } from './vcut-operation-master-detail.component';
import { VcutOperationMasterUpdateComponent } from './vcut-operation-master-update.component';
import { VcutOperationMasterDeletePopupComponent } from './vcut-operation-master-delete-dialog.component';
import { IVcutOperationMaster } from 'app/shared/model/vcut-operation-master.model';

@Injectable({ providedIn: 'root' })
export class VcutOperationMasterResolve implements Resolve<IVcutOperationMaster> {
  constructor(private service: VcutOperationMasterService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IVcutOperationMaster> {
    const id = route.params['id'];
    if (id) {
      return this.service.style(id).pipe(
        filter((response: HttpResponse<VcutOperationMaster>) => response.ok),
        map((vcutOperationMaster: HttpResponse<VcutOperationMaster>) => vcutOperationMaster.body)
      );
    }
    return of(new VcutOperationMaster());
  }
}

export const vcutOperationMasterRoute: Routes = [
  {
    path: '',
    component: VcutOperationMasterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'Style Operation Bulletin'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'upload',
    component: VcutOperationMasterDetailComponent,
    resolve: {
      vcutOperationMaster: VcutOperationMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Style Operation Bulletin'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: VcutOperationMasterUpdateComponent,
    resolve: {
      vcutOperationMaster: VcutOperationMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Style Operation Bulletin'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: VcutOperationMasterUpdateComponent,
    resolve: {
      vcutOperationMaster: VcutOperationMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Style Operation Bulletin'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const vcutOperationMasterPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: VcutOperationMasterDeletePopupComponent,
    resolve: {
      vcutOperationMaster: VcutOperationMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Style Operation Bulletin'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
