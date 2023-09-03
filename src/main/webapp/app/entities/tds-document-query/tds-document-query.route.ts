import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { TdsDocumentQuery, ITdsDocumentQuery } from './tds-document-query.model';
import { TdsDocumentQueryService } from './tds-document-query.service';
import { TdsDocumentQueryComponent } from './tds-document-query.component';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { TdsDocumentQueryUpdateComponent } from 'app/entities/tds-document-query/tds-document-query-update.component';

@Injectable({ providedIn: 'root' })
export class TdsDocumentQueryResolve implements Resolve<ITdsDocumentQuery> {
  constructor(private service: TdsDocumentQueryService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ITdsDocumentQuery> {
    const cardNo = route.params['cardNo'] ? route.params['cardNo'] : null;
    const year = route.params['year'] ? route.params['year'] : null;
    if (cardNo && year) {
      return this.service.find(cardNo, year).pipe(
        filter((response: HttpResponse<TdsDocumentQuery>) => response.ok),
        map((tdsDeclarationUploadQry: HttpResponse<TdsDocumentQuery>) => tdsDeclarationUploadQry.body)
      );
    }
    return of(new TdsDocumentQuery());
  }
}

export const tdsDeclarationUploadQryRoute: Routes = [
  {
    path: '',
    component: TdsDocumentQueryComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'Tds Document Query'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':cardNo/:year/edit',
    component: TdsDocumentQueryUpdateComponent,
    resolve: {
      tdsDeclarationUpload: TdsDocumentQueryResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_USER'],
      pageTitle: 'Tds Document Query'
    },
    canActivate: [UserRouteAccessService]
  }
];
