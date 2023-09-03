import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MenuMaster } from 'app/shared/model/menu-master.model';
import { MenuMasterService } from './menu-master.service';
import { MenuMasterComponent } from './menu-master.component';
import { MenuMasterDetailComponent } from './menu-master-detail.component';
import { MenuMasterUpdateComponent } from './menu-master-update.component';
import { MenuMasterDeletePopupComponent } from './menu-master-delete-dialog.component';
import { IMenuMaster } from 'app/shared/model/menu-master.model';

@Injectable({ providedIn: 'root' })
export class MenuMasterResolve implements Resolve<IMenuMaster> {
  constructor(private service: MenuMasterService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<MenuMaster> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MenuMaster>) => response.ok),
        map((menuMaster: HttpResponse<MenuMaster>) => menuMaster.body)
      );
    }
    return of(new MenuMaster());
  }
}

export const menuMasterRoute: Routes = [
  {
    path: '',
    component: MenuMasterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'MenuMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MenuMasterDetailComponent,
    resolve: {
      menuMaster: MenuMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'MenuMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MenuMasterUpdateComponent,
    resolve: {
      menuMaster: MenuMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'MenuMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MenuMasterUpdateComponent,
    resolve: {
      menuMaster: MenuMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'MenuMasters'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const menuMasterPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MenuMasterDeletePopupComponent,
    resolve: {
      menuMaster: MenuMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'MenuMasters'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
