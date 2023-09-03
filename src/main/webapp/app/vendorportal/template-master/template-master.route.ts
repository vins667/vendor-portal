import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { TemplateMaster } from 'app/shared/model/template-master.model';
import { TemplateMasterService } from './template-master.service';
import { TemplateMasterComponent } from './template-master.component';
import { TemplateMasterDetailComponent } from './template-master-detail.component';
import { TemplateMasterUpdateComponent } from './template-master-update.component';
import { TemplateMasterDeletePopupComponent } from './template-master-delete-dialog.component';
import { ITemplateMaster } from 'app/shared/model/template-master.model';

@Injectable({ providedIn: 'root' })
export class TemplateMasterResolve implements Resolve<ITemplateMaster> {
  constructor(private service: TemplateMasterService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ITemplateMaster> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<TemplateMaster>) => response.ok),
        map((templateMaster: HttpResponse<TemplateMaster>) => templateMaster.body)
      );
    }
    return of(new TemplateMaster());
  }
}

@Injectable({ providedIn: 'root' })
export class TemplateMasterCopyResolve implements Resolve<ITemplateMaster> {
  constructor(private service: TemplateMasterService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ITemplateMaster> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.copy(id).pipe(
        filter((response: HttpResponse<TemplateMaster>) => response.ok),
        map((templateMaster: HttpResponse<TemplateMaster>) => templateMaster.body)
      );
    }
    return of(new TemplateMaster());
  }
}

export const templateMasterRoute: Routes = [
  {
    path: '',
    component: TemplateMasterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_ADMIN', 'ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'TemplateMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: TemplateMasterDetailComponent,
    resolve: {
      templateMaster: TemplateMasterResolve
    },
    data: {
      authorities: ['ROLE_ADMIN', 'ROLE_USER'],
      pageTitle: 'TemplateMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: TemplateMasterUpdateComponent,
    resolve: {
      templateMaster: TemplateMasterResolve
    },
    data: {
      authorities: ['ROLE_ADMIN', 'ROLE_USER'],
      pageTitle: 'TemplateMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: TemplateMasterUpdateComponent,
    resolve: {
      templateMaster: TemplateMasterResolve
    },
    data: {
      authorities: ['ROLE_ADMIN', 'ROLE_USER'],
      pageTitle: 'TemplateMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/copy',
    component: TemplateMasterUpdateComponent,
    resolve: {
      templateMaster: TemplateMasterCopyResolve
    },
    data: {
      authorities: ['ROLE_ADMIN', 'ROLE_USER'],
      pageTitle: 'TemplateMasters'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const templateMasterPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: TemplateMasterDeletePopupComponent,
    resolve: {
      templateMaster: TemplateMasterResolve
    },
    data: {
      authorities: ['ROLE_ADMIN', 'ROLE_USER'],
      pageTitle: 'TemplateMasters'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
