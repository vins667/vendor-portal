import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ReportTypeMaster } from 'app/shared/model/report-type-master.model';
import { ReportTypeMasterService } from './report-type-master.service';
import { ReportTypeMasterComponent } from './report-type-master.component';
import { ReportTypeMasterDetailComponent } from './report-type-master-detail.component';
import { ReportTypeMasterUpdateComponent } from './report-type-master-update.component';
import { ReportTypeMasterDeletePopupComponent } from './report-type-master-delete-dialog.component';
import { IReportTypeMaster } from 'app/shared/model/report-type-master.model';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';

@Injectable({ providedIn: 'root' })
export class ReportTypeMasterResolve implements Resolve<IReportTypeMaster> {
  constructor(private service: ReportTypeMasterService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IReportTypeMaster> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<ReportTypeMaster>) => response.ok),
        map((reportTypeMaster: HttpResponse<ReportTypeMaster>) => reportTypeMaster.body)
      );
    }
    return of(new ReportTypeMaster());
  }
}

export const reportTypeMasterRoute: Routes = [
  {
    path: '',
    component: ReportTypeMasterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'ReportTypeMaster'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ReportTypeMasterDetailComponent,
    resolve: {
      reportTypeMaster: ReportTypeMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'ReportTypeMaster'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ReportTypeMasterUpdateComponent,
    resolve: {
      reportTypeMaster: ReportTypeMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'ReportTypeMaster'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ReportTypeMasterUpdateComponent,
    resolve: {
      reportTypeMaster: ReportTypeMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'ReportTypeMaster'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const reportTypeMasterPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: ReportTypeMasterDeletePopupComponent,
    resolve: {
      reportTypeMaster: ReportTypeMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'ReportTypeMaster'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
