import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { CategoryMaster } from 'app/shared/model/category-master.model';
import { CategoryMasterService } from './category-master.service';
import { CategoryMasterComponent } from './category-master.component';
import { CategoryMasterDetailComponent } from './category-master-detail.component';
import { CategoryMasterUpdateComponent } from './category-master-update.component';
import { CategoryMasterDeletePopupComponent } from './category-master-delete-dialog.component';
import { ICategoryMaster } from 'app/shared/model/category-master.model';

@Injectable({ providedIn: 'root' })
export class CategoryMasterResolve implements Resolve<ICategoryMaster> {
  constructor(private service: CategoryMasterService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ICategoryMaster> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<CategoryMaster>) => response.ok),
        map((categoryMaster: HttpResponse<CategoryMaster>) => categoryMaster.body)
      );
    }
    return of(new CategoryMaster());
  }
}

export const categoryMasterRoute: Routes = [
  {
    path: '',
    component: CategoryMasterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_ADMIN', 'ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'CategoryMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: CategoryMasterDetailComponent,
    resolve: {
      categoryMaster: CategoryMasterResolve
    },
    data: {
      authorities: ['ROLE_ADMIN', 'ROLE_USER'],
      pageTitle: 'CategoryMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: CategoryMasterUpdateComponent,
    resolve: {
      categoryMaster: CategoryMasterResolve
    },
    data: {
      authorities: ['ROLE_ADMIN', 'ROLE_USER'],
      pageTitle: 'CategoryMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: CategoryMasterUpdateComponent,
    resolve: {
      categoryMaster: CategoryMasterResolve
    },
    data: {
      authorities: ['ROLE_ADMIN', 'ROLE_USER'],
      pageTitle: 'CategoryMasters'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const categoryMasterPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: CategoryMasterDeletePopupComponent,
    resolve: {
      categoryMaster: CategoryMasterResolve
    },
    data: {
      authorities: ['ROLE_ADMIN', 'ROLE_USER'],
      pageTitle: 'CategoryMasters'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
