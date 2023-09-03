import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MenuAccessMaster } from 'app/shared/model/menu-access-master.model';
import { MenuAccessMasterService } from './menu-access-master.service';
import { MenuAccessMasterComponent } from './menu-access-master.component';
import { MenuAccessMasterDetailComponent } from './menu-access-master-detail.component';
import { MenuAccessMasterUpdateComponent } from './menu-access-master-update.component';
import { MenuAccessMasterDeletePopupComponent } from './menu-access-master-delete-dialog.component';
import { IMenuAccessMaster } from 'app/shared/model/menu-access-master.model';

@Injectable({ providedIn: 'root' })
export class MenuAccessMasterResolve implements Resolve<IMenuAccessMaster> {
  constructor(private service: MenuAccessMasterService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<MenuAccessMaster> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MenuAccessMaster>) => response.ok),
        map((menuAccessMaster: HttpResponse<MenuAccessMaster>) => menuAccessMaster.body)
      );
    }
    return of(new MenuAccessMaster());
  }
}

export const menuAccessMasterRoute: Routes = [
  {
    path: '',
    component: MenuAccessMasterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'MenuAccessMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MenuAccessMasterDetailComponent,
    resolve: {
      menuAccessMaster: MenuAccessMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'MenuAccessMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MenuAccessMasterUpdateComponent,
    resolve: {
      menuAccessMaster: MenuAccessMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'MenuAccessMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MenuAccessMasterUpdateComponent,
    resolve: {
      menuAccessMaster: MenuAccessMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'MenuAccessMasters'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const menuAccessMasterPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MenuAccessMasterDeletePopupComponent,
    resolve: {
      menuAccessMaster: MenuAccessMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'MenuAccessMasters'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
