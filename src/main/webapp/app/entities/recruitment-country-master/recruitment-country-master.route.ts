import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { RecruitmentCountryMaster } from 'app/shared/model/recruitment-country-master.model';
import { RecruitmentCountryMasterService } from './recruitment-country-master.service';
import { RecruitmentCountryMasterComponent } from './recruitment-country-master.component';
import { RecruitmentCountryMasterDetailComponent } from './recruitment-country-master-detail.component';
import { RecruitmentCountryMasterUpdateComponent } from './recruitment-country-master-update.component';
import { RecruitmentCountryMasterDeletePopupComponent } from './recruitment-country-master-delete-dialog.component';
import { IRecruitmentCountryMaster } from 'app/shared/model/recruitment-country-master.model';

@Injectable({ providedIn: 'root' })
export class RecruitmentCountryMasterResolve implements Resolve<IRecruitmentCountryMaster> {
  constructor(private service: RecruitmentCountryMasterService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IRecruitmentCountryMaster> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<RecruitmentCountryMaster>) => response.ok),
        map((recruitmentCountryMaster: HttpResponse<RecruitmentCountryMaster>) => recruitmentCountryMaster.body)
      );
    }
    return of(new RecruitmentCountryMaster());
  }
}

export const recruitmentCountryMasterRoute: Routes = [
  {
    path: '',
    component: RecruitmentCountryMasterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'Country Master'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: RecruitmentCountryMasterDetailComponent,
    resolve: {
      recruitmentCountryMaster: RecruitmentCountryMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Country Master'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: RecruitmentCountryMasterUpdateComponent,
    resolve: {
      recruitmentCountryMaster: RecruitmentCountryMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Country Master'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: RecruitmentCountryMasterUpdateComponent,
    resolve: {
      recruitmentCountryMaster: RecruitmentCountryMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Country Master'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const recruitmentCountryMasterPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: RecruitmentCountryMasterDeletePopupComponent,
    resolve: {
      recruitmentCountryMaster: RecruitmentCountryMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Country Master'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
