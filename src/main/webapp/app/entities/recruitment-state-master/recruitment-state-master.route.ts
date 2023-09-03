import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { RecruitmentStateMaster } from 'app/shared/model/recruitment-state-master.model';
import { RecruitmentStateMasterService } from './recruitment-state-master.service';
import { RecruitmentStateMasterComponent } from './recruitment-state-master.component';
import { RecruitmentStateMasterDetailComponent } from './recruitment-state-master-detail.component';
import { RecruitmentStateMasterUpdateComponent } from './recruitment-state-master-update.component';
import { RecruitmentStateMasterDeletePopupComponent } from './recruitment-state-master-delete-dialog.component';
import { IRecruitmentStateMaster } from 'app/shared/model/recruitment-state-master.model';

@Injectable({ providedIn: 'root' })
export class RecruitmentStateMasterResolve implements Resolve<IRecruitmentStateMaster> {
  constructor(private service: RecruitmentStateMasterService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IRecruitmentStateMaster> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<RecruitmentStateMaster>) => response.ok),
        map((recruitmentStateMaster: HttpResponse<RecruitmentStateMaster>) => recruitmentStateMaster.body)
      );
    }
    return of(new RecruitmentStateMaster());
  }
}

export const recruitmentStateMasterRoute: Routes = [
  {
    path: '',
    component: RecruitmentStateMasterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'State Master'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: RecruitmentStateMasterDetailComponent,
    resolve: {
      recruitmentStateMaster: RecruitmentStateMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'State Master'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: RecruitmentStateMasterUpdateComponent,
    resolve: {
      recruitmentStateMaster: RecruitmentStateMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'State Master'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: RecruitmentStateMasterUpdateComponent,
    resolve: {
      recruitmentStateMaster: RecruitmentStateMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'State Master'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const recruitmentStateMasterPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: RecruitmentStateMasterDeletePopupComponent,
    resolve: {
      recruitmentStateMaster: RecruitmentStateMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'State Master'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
