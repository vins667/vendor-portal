import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { CompOffMaster } from 'app/shared/model/comp-off-master.model';
import { CompOffMasterService } from './comp-off-master.service';
import { CompOffMasterComponent } from './comp-off-master.component';
import { CompOffMasterDetailComponent } from './comp-off-master-detail.component';
import { CompOffMasterUpdateComponent } from './comp-off-master-update.component';
import { CompOffMasterDeletePopupComponent } from './comp-off-master-delete-dialog.component';
import { ICompOffMaster } from 'app/shared/model/comp-off-master.model';

@Injectable({ providedIn: 'root' })
export class CompOffMasterResolve implements Resolve<ICompOffMaster> {
  constructor(private service: CompOffMasterService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<CompOffMaster> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<CompOffMaster>) => response.ok),
        map((compOffMaster: HttpResponse<CompOffMaster>) => compOffMaster.body)
      );
    }
    return of(new CompOffMaster());
  }
}

export const compOffMasterRoute: Routes = [
  {
    path: '',
    component: CompOffMasterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,desc',
      pageTitle: 'CompOffMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: CompOffMasterDetailComponent,
    resolve: {
      compOffMaster: CompOffMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'CompOffMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: CompOffMasterUpdateComponent,
    resolve: {
      compOffMaster: CompOffMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'CompOffMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: CompOffMasterUpdateComponent,
    resolve: {
      compOffMaster: CompOffMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'CompOffMasters'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const compOffMasterPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: CompOffMasterDeletePopupComponent,
    resolve: {
      compOffMaster: CompOffMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'CompOffMasters'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
