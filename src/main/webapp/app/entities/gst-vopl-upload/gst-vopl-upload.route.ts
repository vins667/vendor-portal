import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { GstVoplUpload } from 'app/shared/model/gst-vopl-upload.model';
import { GstVoplUploadService } from './gst-vopl-upload.service';
import { GstVoplUploadComponent } from './gst-vopl-upload.component';
import { GstVoplUploadDetailComponent } from './gst-vopl-upload-detail.component';
import { GstVoplUploadUpdateComponent } from './gst-vopl-upload-update.component';
import { GstVoplUploadDeletePopupComponent } from './gst-vopl-upload-delete-dialog.component';
import { IGstVoplUpload } from 'app/shared/model/gst-vopl-upload.model';
import { GstVoplUploadPopupComponent } from './gst-vopl-upload-popup.component';

@Injectable({ providedIn: 'root' })
export class GstVoplUploadResolve implements Resolve<IGstVoplUpload> {
  constructor(private service: GstVoplUploadService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IGstVoplUpload> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<GstVoplUpload>) => response.ok),
        map((gstVoplUpload: HttpResponse<GstVoplUpload>) => gstVoplUpload.body)
      );
    }
    return of(new GstVoplUpload());
  }
}

export const gstVoplUploadRoute: Routes = [
  {
    path: '',
    component: GstVoplUploadComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'GstVoplUploads'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: GstVoplUploadDetailComponent,
    resolve: {
      gstVoplUpload: GstVoplUploadResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'GstVoplUploads'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'upload',
    component: GstVoplUploadPopupComponent,
    resolve: {
      GstVoplUpload: GstVoplUploadResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Excel Upload'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: GstVoplUploadUpdateComponent,
    resolve: {
      gstVoplUpload: GstVoplUploadResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'GstVoplUploads'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: GstVoplUploadUpdateComponent,
    resolve: {
      gstVoplUpload: GstVoplUploadResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'GstVoplUploads'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const gstVoplUploadPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: GstVoplUploadDeletePopupComponent,
    resolve: {
      gstVoplUpload: GstVoplUploadResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'GstVoplUploads'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
