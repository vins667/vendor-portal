import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { VcutPlanChangeMaster } from 'app/shared/model/vcut-plan-change-master.model';
import { VcutPlanChangeMasterService } from './vcut-plan-change-master.service';
import { VcutPlanChangeMasterComponent } from './vcut-plan-change-master.component';
import { VcutPlanChangeMasterDetailComponent } from './vcut-plan-change-master-detail.component';
import { VcutPlanChangeMasterUpdateComponent } from './vcut-plan-change-master-update.component';
import { VcutPlanChangeMasterDeletePopupComponent } from './vcut-plan-change-master-delete-dialog.component';
import { IVcutPlanChangeMaster } from 'app/shared/model/vcut-plan-change-master.model';

@Injectable({ providedIn: 'root' })
export class VcutPlanChangeMasterResolve implements Resolve<IVcutPlanChangeMaster> {
  constructor(private service: VcutPlanChangeMasterService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IVcutPlanChangeMaster> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<VcutPlanChangeMaster>) => response.ok),
        map((vcutPlanChangeMaster: HttpResponse<VcutPlanChangeMaster>) => vcutPlanChangeMaster.body)
      );
    }
    return of(new VcutPlanChangeMaster());
  }
}

export const vcutPlanChangeMasterRoute: Routes = [
  {
    path: '',
    component: VcutPlanChangeMasterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'Plan Change Master'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: VcutPlanChangeMasterDetailComponent,
    resolve: {
      vcutPlanChangeMaster: VcutPlanChangeMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Plan Change Master'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: VcutPlanChangeMasterUpdateComponent,
    resolve: {
      vcutPlanChangeMaster: VcutPlanChangeMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Plan Change Master'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: VcutPlanChangeMasterUpdateComponent,
    resolve: {
      vcutPlanChangeMaster: VcutPlanChangeMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Plan Change Master'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const vcutPlanChangeMasterPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: VcutPlanChangeMasterDeletePopupComponent,
    resolve: {
      vcutPlanChangeMaster: VcutPlanChangeMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Plan Change Master'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
