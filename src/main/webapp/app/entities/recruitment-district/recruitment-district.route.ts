import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { RecruitmentDistrict } from 'app/shared/model/recruitment-district.model';
import { RecruitmentDistrictService } from './recruitment-district.service';
import { RecruitmentDistrictComponent } from './recruitment-district.component';
import { RecruitmentDistrictDetailComponent } from './recruitment-district-detail.component';
import { RecruitmentDistrictUpdateComponent } from './recruitment-district-update.component';
import { RecruitmentDistrictDeletePopupComponent } from './recruitment-district-delete-dialog.component';
import { IRecruitmentDistrict } from 'app/shared/model/recruitment-district.model';

@Injectable({ providedIn: 'root' })
export class RecruitmentDistrictResolve implements Resolve<IRecruitmentDistrict> {
  constructor(private service: RecruitmentDistrictService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IRecruitmentDistrict> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<RecruitmentDistrict>) => response.ok),
        map((recruitmentDistrict: HttpResponse<RecruitmentDistrict>) => recruitmentDistrict.body)
      );
    }
    return of(new RecruitmentDistrict());
  }
}

export const recruitmentDistrictRoute: Routes = [
  {
    path: '',
    component: RecruitmentDistrictComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'District Master'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: RecruitmentDistrictDetailComponent,
    resolve: {
      recruitmentDistrict: RecruitmentDistrictResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'District Master'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: RecruitmentDistrictUpdateComponent,
    resolve: {
      recruitmentDistrict: RecruitmentDistrictResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'District Master'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: RecruitmentDistrictUpdateComponent,
    resolve: {
      recruitmentDistrict: RecruitmentDistrictResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'District Master'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const recruitmentDistrictPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: RecruitmentDistrictDeletePopupComponent,
    resolve: {
      recruitmentDistrict: RecruitmentDistrictResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'District Master'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
