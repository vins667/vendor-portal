import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { OperationMaster } from 'app/shared/model/operation-master.model';
import { OperationMasterService } from './operation-master.service';
import { OperationMasterComponent } from './operation-master.component';
import { OperationMasterDetailComponent } from './operation-master-detail.component';
import { OperationMasterUpdateComponent } from './operation-master-update.component';
import { OperationMasterDeletePopupComponent } from './operation-master-delete-dialog.component';
import { IOperationMaster } from 'app/shared/model/operation-master.model';

@Injectable({ providedIn: 'root' })
export class OperationMasterResolve implements Resolve<IOperationMaster> {
  constructor(private service: OperationMasterService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IOperationMaster> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<OperationMaster>) => response.ok),
        map((operationMaster: HttpResponse<OperationMaster>) => operationMaster.body)
      );
    }
    return of(new OperationMaster());
  }
}

export const operationMasterRoute: Routes = [
  {
    path: '',
    component: OperationMasterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'OperationMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: OperationMasterDetailComponent,
    resolve: {
      operationMaster: OperationMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'OperationMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: OperationMasterUpdateComponent,
    resolve: {
      operationMaster: OperationMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'OperationMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: OperationMasterUpdateComponent,
    resolve: {
      operationMaster: OperationMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'OperationMasters'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const operationMasterPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: OperationMasterDeletePopupComponent,
    resolve: {
      operationMaster: OperationMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'OperationMasters'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
