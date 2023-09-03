import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { TdsDeclarationUploadQry } from 'app/shared/model/tds-declaration-upload-qry.model';
import { TdsDeclarationUploadQryService } from './tds-declaration-upload-qry.service';
import { TdsDeclarationUploadQryComponent } from './tds-declaration-upload-qry.component';
import { ITdsDeclarationUploadQry } from 'app/shared/model/tds-declaration-upload-qry.model';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';

@Injectable({ providedIn: 'root' })
export class TdsDeclarationUploadQryResolve implements Resolve<ITdsDeclarationUploadQry> {
  constructor(private service: TdsDeclarationUploadQryService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ITdsDeclarationUploadQry> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<TdsDeclarationUploadQry>) => response.ok),
        map((tdsDeclarationUploadQry: HttpResponse<TdsDeclarationUploadQry>) => tdsDeclarationUploadQry.body)
      );
    }
    return of(new TdsDeclarationUploadQry());
  }
}

export const tdsDeclarationUploadQryRoute: Routes = [
  {
    path: '',
    component: TdsDeclarationUploadQryComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'Tds Declaration Upload Qry'
    },
    canActivate: [UserRouteAccessService]
  }
];
