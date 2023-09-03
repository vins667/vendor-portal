import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { VcutStyleImage } from 'app/shared/model/vcut-style-image.model';
import { VcutStyleImageService } from './vcut-style-image.service';
import { VcutStyleImageComponent } from './vcut-style-image.component';
import { VcutStyleImageDetailComponent } from './vcut-style-image-detail.component';
import { VcutStyleImageUpdateComponent } from './vcut-style-image-update.component';
import { VcutStyleImageDeletePopupComponent } from './vcut-style-image-delete-dialog.component';
import { IVcutStyleImage } from 'app/shared/model/vcut-style-image.model';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';

@Injectable({ providedIn: 'root' })
export class VcutStyleImageResolve implements Resolve<IVcutStyleImage> {
  constructor(private service: VcutStyleImageService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IVcutStyleImage> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<VcutStyleImage>) => response.ok),
        map((vcutStyleImage: HttpResponse<VcutStyleImage>) => vcutStyleImage.body)
      );
    }
    return of(new VcutStyleImage());
  }
}

export const vcutStyleImageRoute: Routes = [
  {
    path: '',
    component: VcutStyleImageComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'Style Image Upload'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: VcutStyleImageDetailComponent,
    resolve: {
      vcutStyleImage: VcutStyleImageResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Style Image Upload'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: VcutStyleImageUpdateComponent,
    resolve: {
      vcutStyleImage: VcutStyleImageResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Style Image Upload'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: VcutStyleImageUpdateComponent,
    resolve: {
      vcutStyleImage: VcutStyleImageResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Style Image Upload'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const vcutStyleImagePopupRoute: Routes = [
  {
    path: ':id/delete',
    component: VcutStyleImageDeletePopupComponent,
    resolve: {
      vcutStyleImage: VcutStyleImageResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Style Image Upload'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
