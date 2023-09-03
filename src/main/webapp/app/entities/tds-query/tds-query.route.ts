import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { TdsDeclaration } from 'app/shared/model/tds-declaration.model';
import { TdsQueryService } from './tds-query.service';
import { ITdsDeclaration } from 'app/shared/model/tds-declaration.model';
import { TdsQueryComponent } from './tds-query.component';
import { TdsQueryUpdateComponent } from './tds-query-update.component';
import { TdsDeclarationSearch } from 'app/shared/model/tds-declaration-search.model';

@Injectable({ providedIn: 'root' })
export class TdsQueryResolve implements Resolve<ITdsDeclaration> {
  constructor(private service: TdsQueryService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<TdsDeclaration> {
    const cardNo = route.params['cardNo'] ? route.params['cardNo'] : null;
    if (cardNo) {
      const tdsDeclarationSearch = new TdsDeclarationSearch();
      tdsDeclarationSearch.cardNo = cardNo;
      return this.service.edit(tdsDeclarationSearch).pipe(
        filter((response: HttpResponse<TdsDeclaration>) => response.ok),
        map((tdsDeclaration: HttpResponse<TdsDeclaration>) => tdsDeclaration.body)
      );
    }
    return of(new TdsDeclaration());
  }
}

export const tdsQueryRoute: Routes = [
  {
    path: '',
    component: TdsQueryComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'TDS Query'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':year/:cardNo/edit',
    component: TdsQueryUpdateComponent,
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'NewsDetails'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const tdsQueryPopupRoute: Routes = [];
