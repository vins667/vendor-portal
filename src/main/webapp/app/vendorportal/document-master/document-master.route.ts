import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { DocumentMaster } from 'app/shared/model/document-master.model';
import { DocumentMasterService } from './document-master.service';
import { DocumentMasterComponent } from './document-master.component';
import { DocumentMasterDetailComponent } from './document-master-detail.component';
import { DocumentMasterUpdateComponent } from './document-master-update.component';
import { DocumentMasterDeletePopupComponent } from './document-master-delete-dialog.component';
import { IDocumentMaster } from 'app/shared/model/document-master.model';

@Injectable({ providedIn: 'root' })
export class DocumentMasterResolve implements Resolve<IDocumentMaster> {
  constructor(private service: DocumentMasterService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<DocumentMaster> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<DocumentMaster>) => response.ok),
        map((documentMaster: HttpResponse<DocumentMaster>) => documentMaster.body)
      );
    }
    return of(new DocumentMaster());
  }
}

export const documentMasterRoute: Routes = [
  {
    path: '',
    component: DocumentMasterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'DocumentMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: DocumentMasterDetailComponent,
    resolve: {
      documentMaster: DocumentMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'DocumentMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: DocumentMasterUpdateComponent,
    resolve: {
      documentMaster: DocumentMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'DocumentMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: DocumentMasterUpdateComponent,
    resolve: {
      documentMaster: DocumentMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'DocumentMasters'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const documentMasterPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: DocumentMasterDeletePopupComponent,
    resolve: {
      documentMaster: DocumentMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'DocumentMasters'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
