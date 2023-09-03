import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { GstGovtUpload } from 'app/shared/model/gst-govt-upload.model';
import { GstGovtUploadService } from './gst-govt-upload.service';
import { GstGovtUploadComponent } from './gst-govt-upload.component';
import { GstGovtUploadDetailComponent } from './gst-govt-upload-detail.component';
import { GstGovtUploadUpdateComponent } from './gst-govt-upload-update.component';
import { GstGovtUploadDeletePopupComponent } from './gst-govt-upload-delete-dialog.component';
import { IGstGovtUpload } from 'app/shared/model/gst-govt-upload.model';
import { GstGovtUploadPopupComponent } from './gst-govt-upload-popup.component';

@Injectable({ providedIn: 'root' })
export class GstGovtUploadResolve implements Resolve<IGstGovtUpload> {
  constructor(private service: GstGovtUploadService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IGstGovtUpload> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<GstGovtUpload>) => response.ok),
        map((gstGovtUpload: HttpResponse<GstGovtUpload>) => gstGovtUpload.body)
      );
    }
    return of(new GstGovtUpload());
  }
}

export const gstGovtUploadRoute: Routes = [
  {
    path: '',
    component: GstGovtUploadComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'GstGovtUploads'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: GstGovtUploadDetailComponent,
    resolve: {
      gstGovtUpload: GstGovtUploadResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'GstGovtUploads'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'upload',
    component: GstGovtUploadPopupComponent,
    resolve: {
      GstVoplUpload: GstGovtUploadResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Govt Excel Upload'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: GstGovtUploadUpdateComponent,
    resolve: {
      gstGovtUpload: GstGovtUploadResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'GstGovtUploads'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: GstGovtUploadUpdateComponent,
    resolve: {
      gstGovtUpload: GstGovtUploadResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'GstGovtUploads'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const gstGovtUploadPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: GstGovtUploadDeletePopupComponent,
    resolve: {
      gstGovtUpload: GstGovtUploadResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'GstGovtUploads'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
