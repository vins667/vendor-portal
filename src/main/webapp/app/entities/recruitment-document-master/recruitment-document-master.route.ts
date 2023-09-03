import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { RecruitmentDocumentMaster } from 'app/shared/model/recruitment-document-master.model';
import { RecruitmentDocumentMasterService } from './recruitment-document-master.service';
import { RecruitmentDocumentMasterComponent } from './recruitment-document-master.component';
import { RecruitmentDocumentMasterDetailComponent } from './recruitment-document-master-detail.component';
import { RecruitmentDocumentMasterUpdateComponent } from './recruitment-document-master-update.component';
import { RecruitmentDocumentMasterDeletePopupComponent } from './recruitment-document-master-delete-dialog.component';
import { IRecruitmentDocumentMaster } from 'app/shared/model/recruitment-document-master.model';

@Injectable({ providedIn: 'root' })
export class RecruitmentDocumentMasterResolve implements Resolve<IRecruitmentDocumentMaster> {
  constructor(private service: RecruitmentDocumentMasterService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<RecruitmentDocumentMaster> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<RecruitmentDocumentMaster>) => response.ok),
        map((recruitmentDocumentMaster: HttpResponse<RecruitmentDocumentMaster>) => recruitmentDocumentMaster.body)
      );
    }
    return of(new RecruitmentDocumentMaster());
  }
}

export const recruitmentDocumentMasterRoute: Routes = [
  {
    path: '',
    component: RecruitmentDocumentMasterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'Document Master'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: RecruitmentDocumentMasterDetailComponent,
    resolve: {
      recruitmentDocumentMaster: RecruitmentDocumentMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Document Master'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: RecruitmentDocumentMasterUpdateComponent,
    resolve: {
      recruitmentDocumentMaster: RecruitmentDocumentMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Document Master'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: RecruitmentDocumentMasterUpdateComponent,
    resolve: {
      recruitmentDocumentMaster: RecruitmentDocumentMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Document Master'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const recruitmentDocumentMasterPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: RecruitmentDocumentMasterDeletePopupComponent,
    resolve: {
      recruitmentDocumentMaster: RecruitmentDocumentMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Document Master'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
