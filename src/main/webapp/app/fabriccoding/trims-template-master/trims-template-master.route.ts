import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { TrimsTemplateMaster } from 'app/shared/model/trims-template-master.model';
import { TrimsTemplateMasterService } from './trims-template-master.service';
import { TrimsTemplateMasterComponent } from './trims-template-master.component';
import { TrimsTemplateMasterDetailComponent } from './trims-template-master-detail.component';
import { TrimsTemplateMasterUpdateComponent } from './trims-template-master-update.component';
import { TrimsTemplateMasterDeletePopupComponent } from './trims-template-master-delete-dialog.component';
import { ITrimsTemplateMaster } from 'app/shared/model/trims-template-master.model';

@Injectable({ providedIn: 'root' })
export class TrimsTemplateMasterResolve implements Resolve<ITrimsTemplateMaster> {
  constructor(private service: TrimsTemplateMasterService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ITrimsTemplateMaster> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<TrimsTemplateMaster>) => response.ok),
        map((trimsTemplateMaster: HttpResponse<TrimsTemplateMaster>) => trimsTemplateMaster.body)
      );
    }
    return of(new TrimsTemplateMaster());
  }
}

export const trimsTemplateMasterRoute: Routes = [
  {
    path: '',
    component: TrimsTemplateMasterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'TrimsTemplateMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: TrimsTemplateMasterDetailComponent,
    resolve: {
      trimsTemplateMaster: TrimsTemplateMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'TrimsTemplateMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: TrimsTemplateMasterUpdateComponent,
    resolve: {
      trimsTemplateMaster: TrimsTemplateMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'TrimsTemplateMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: TrimsTemplateMasterUpdateComponent,
    resolve: {
      trimsTemplateMaster: TrimsTemplateMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'TrimsTemplateMasters'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const trimsTemplateMasterPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: TrimsTemplateMasterDeletePopupComponent,
    resolve: {
      trimsTemplateMaster: TrimsTemplateMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'TrimsTemplateMasters'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
