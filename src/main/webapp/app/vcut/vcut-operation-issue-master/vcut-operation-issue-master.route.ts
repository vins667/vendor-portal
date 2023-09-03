import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { VcutOperationIssueMaster } from 'app/shared/model/vcut-operation-issue-master.model';
import { VcutOperationIssueMasterService } from './vcut-operation-issue-master.service';
import { VcutOperationIssueMasterComponent } from './vcut-operation-issue-master.component';
import { VcutOperationIssueMasterDetailComponent } from './vcut-operation-issue-master-detail.component';
import { VcutOperationIssueMasterUpdateComponent } from './vcut-operation-issue-master-update.component';
import { VcutOperationIssueMasterDeletePopupComponent } from './vcut-operation-issue-master-delete-dialog.component';
import { IVcutOperationIssueMaster } from 'app/shared/model/vcut-operation-issue-master.model';

@Injectable({ providedIn: 'root' })
export class VcutOperationIssueMasterResolve implements Resolve<IVcutOperationIssueMaster> {
  constructor(private service: VcutOperationIssueMasterService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IVcutOperationIssueMaster> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<VcutOperationIssueMaster>) => response.ok),
        map((vcutOperationIssueMaster: HttpResponse<VcutOperationIssueMaster>) => vcutOperationIssueMaster.body)
      );
    }
    return of(new VcutOperationIssueMaster());
  }
}

export const vcutOperationIssueMasterRoute: Routes = [
  {
    path: '',
    component: VcutOperationIssueMasterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'Operation Issue Masters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: VcutOperationIssueMasterDetailComponent,
    resolve: {
      vcutOperationIssueMaster: VcutOperationIssueMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Operation Issue Masters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: VcutOperationIssueMasterUpdateComponent,
    resolve: {
      vcutOperationIssueMaster: VcutOperationIssueMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Operation Issue Masters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: VcutOperationIssueMasterUpdateComponent,
    resolve: {
      vcutOperationIssueMaster: VcutOperationIssueMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Operation Issue Masters'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const vcutOperationIssueMasterPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: VcutOperationIssueMasterDeletePopupComponent,
    resolve: {
      vcutOperationIssueMaster: VcutOperationIssueMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Operation Issue Masters'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
