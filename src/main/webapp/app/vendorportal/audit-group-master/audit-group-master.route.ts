import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { AuditGroupMaster } from 'app/shared/model/audit-group-master.model';
import { AuditGroupMasterService } from './audit-group-master.service';
import { AuditGroupMasterComponent } from './audit-group-master.component';
import { AuditGroupMasterDetailComponent } from './audit-group-master-detail.component';
import { AuditGroupMasterUpdateComponent } from './audit-group-master-update.component';
import { AuditGroupMasterDeletePopupComponent } from './audit-group-master-delete-dialog.component';
import { IAuditGroupMaster } from 'app/shared/model/audit-group-master.model';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';

@Injectable({ providedIn: 'root' })
export class AuditGroupMasterResolve implements Resolve<IAuditGroupMaster> {
  constructor(private service: AuditGroupMasterService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IAuditGroupMaster> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<AuditGroupMaster>) => response.ok),
        map((auditGroupMaster: HttpResponse<AuditGroupMaster>) => auditGroupMaster.body)
      );
    }
    return of(new AuditGroupMaster());
  }
}

export const auditGroupMasterRoute: Routes = [
  {
    path: '',
    component: AuditGroupMasterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'AuditGroupMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: AuditGroupMasterDetailComponent,
    resolve: {
      auditGroupMaster: AuditGroupMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'AuditGroupMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: AuditGroupMasterUpdateComponent,
    resolve: {
      auditGroupMaster: AuditGroupMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'AuditGroupMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: AuditGroupMasterUpdateComponent,
    resolve: {
      auditGroupMaster: AuditGroupMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'AuditGroupMasters'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const auditGroupMasterPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: AuditGroupMasterDeletePopupComponent,
    resolve: {
      auditGroupMaster: AuditGroupMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'AuditGroupMasters'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
