import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { LanguageMaster } from 'app/shared/model/language-master.model';
import { LanguageMasterService } from './language-master.service';
import { LanguageMasterComponent } from './language-master.component';
import { LanguageMasterDetailComponent } from './language-master-detail.component';
import { LanguageMasterUpdateComponent } from './language-master-update.component';
import { LanguageMasterDeletePopupComponent } from './language-master-delete-dialog.component';
import { ILanguageMaster } from 'app/shared/model/language-master.model';

@Injectable({ providedIn: 'root' })
export class LanguageMasterResolve implements Resolve<ILanguageMaster> {
  constructor(private service: LanguageMasterService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ILanguageMaster> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<LanguageMaster>) => response.ok),
        map((languageMaster: HttpResponse<LanguageMaster>) => languageMaster.body)
      );
    }
    return of(new LanguageMaster());
  }
}

export const languageMasterRoute: Routes = [
  {
    path: '',
    component: LanguageMasterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'LanguageMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: LanguageMasterDetailComponent,
    resolve: {
      languageMaster: LanguageMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'LanguageMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: LanguageMasterUpdateComponent,
    resolve: {
      languageMaster: LanguageMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'LanguageMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: LanguageMasterUpdateComponent,
    resolve: {
      languageMaster: LanguageMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'LanguageMasters'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const languageMasterPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: LanguageMasterDeletePopupComponent,
    resolve: {
      languageMaster: LanguageMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'LanguageMasters'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
