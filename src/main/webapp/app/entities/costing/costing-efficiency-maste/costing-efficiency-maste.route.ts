import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { CostingEfficiencyMaste } from 'app/shared/model/costing-efficiency-maste.model';
import { CostingEfficiencyMasteService } from './costing-efficiency-maste.service';
import { CostingEfficiencyMasteComponent } from './costing-efficiency-maste.component';
import { CostingEfficiencyMasteDetailComponent } from './costing-efficiency-maste-detail.component';
import { CostingEfficiencyMasteUpdateComponent } from './costing-efficiency-maste-update.component';
import { CostingEfficiencyMasteDeletePopupComponent } from './costing-efficiency-maste-delete-dialog.component';
import { ICostingEfficiencyMaste } from 'app/shared/model/costing-efficiency-maste.model';

@Injectable({ providedIn: 'root' })
export class CostingEfficiencyMasteResolve implements Resolve<ICostingEfficiencyMaste> {
  constructor(private service: CostingEfficiencyMasteService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ICostingEfficiencyMaste> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<CostingEfficiencyMaste>) => response.ok),
        map((costingEfficiencyMaste: HttpResponse<CostingEfficiencyMaste>) => costingEfficiencyMaste.body)
      );
    }
    return of(new CostingEfficiencyMaste());
  }
}

export const costingEfficiencyMasteRoute: Routes = [
  {
    path: '',
    component: CostingEfficiencyMasteComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'Costing Efficiency Master'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: CostingEfficiencyMasteDetailComponent,
    resolve: {
      costingEfficiencyMaste: CostingEfficiencyMasteResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Costing Efficiency Master'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: CostingEfficiencyMasteUpdateComponent,
    resolve: {
      costingEfficiencyMaste: CostingEfficiencyMasteResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Costing Efficiency Master'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: CostingEfficiencyMasteUpdateComponent,
    resolve: {
      costingEfficiencyMaste: CostingEfficiencyMasteResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Costing Efficiency Master'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const costingEfficiencyMastePopupRoute: Routes = [
  {
    path: ':id/delete',
    component: CostingEfficiencyMasteDeletePopupComponent,
    resolve: {
      costingEfficiencyMaste: CostingEfficiencyMasteResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Costing Efficiency Master'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
