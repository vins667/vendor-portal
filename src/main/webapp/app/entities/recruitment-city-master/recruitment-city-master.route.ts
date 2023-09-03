import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { RecruitmentCityMaster } from 'app/shared/model/recruitment-city-master.model';
import { RecruitmentCityMasterService } from './recruitment-city-master.service';
import { RecruitmentCityMasterComponent } from './recruitment-city-master.component';
import { RecruitmentCityMasterDetailComponent } from './recruitment-city-master-detail.component';
import { RecruitmentCityMasterUpdateComponent } from './recruitment-city-master-update.component';
import { RecruitmentCityMasterDeletePopupComponent } from './recruitment-city-master-delete-dialog.component';
import { IRecruitmentCityMaster } from 'app/shared/model/recruitment-city-master.model';

@Injectable({ providedIn: 'root' })
export class RecruitmentCityMasterResolve implements Resolve<IRecruitmentCityMaster> {
  constructor(private service: RecruitmentCityMasterService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IRecruitmentCityMaster> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<RecruitmentCityMaster>) => response.ok),
        map((recruitmentCityMaster: HttpResponse<RecruitmentCityMaster>) => recruitmentCityMaster.body)
      );
    }
    return of(new RecruitmentCityMaster());
  }
}

export const recruitmentCityMasterRoute: Routes = [
  {
    path: '',
    component: RecruitmentCityMasterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'City Master'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: RecruitmentCityMasterDetailComponent,
    resolve: {
      recruitmentCityMaster: RecruitmentCityMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'City Master'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: RecruitmentCityMasterUpdateComponent,
    resolve: {
      recruitmentCityMaster: RecruitmentCityMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'City Master'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: RecruitmentCityMasterUpdateComponent,
    resolve: {
      recruitmentCityMaster: RecruitmentCityMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'City Master'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const recruitmentCityMasterPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: RecruitmentCityMasterDeletePopupComponent,
    resolve: {
      recruitmentCityMaster: RecruitmentCityMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'City Master'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
