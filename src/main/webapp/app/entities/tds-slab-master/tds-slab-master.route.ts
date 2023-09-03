import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { TdsSlabMaster } from 'app/shared/model/tds-slab-master.model';
import { TdsSlabMasterService } from './tds-slab-master.service';
import { TdsSlabMasterUpdateComponent } from './tds-slab-master-update.component';
import { ITdsSlabMaster } from 'app/shared/model/tds-slab-master.model';
import { TdsSlabMasterComponent } from './tds-slab-master.component';
import { TdsSlabSearchMaster } from 'app/shared/model/tds-slab-search-master.model';

@Injectable({ providedIn: 'root' })
export class TdsSlabMasterResolve implements Resolve<ITdsSlabMaster> {
  constructor(private service: TdsSlabMasterService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<TdsSlabMaster> {
    const finYear = route.params['finYear'] ? route.params['finYear'] : null;
    const gender = route.params['gender'] ? route.params['gender'] : null;
    if (finYear) {
      const tdsSlabSearchMaster = new TdsSlabSearchMaster();
      tdsSlabSearchMaster.finYear = finYear;
      tdsSlabSearchMaster.gender = gender;
      return this.service.edit(tdsSlabSearchMaster).pipe(
        filter((response: HttpResponse<TdsSlabMaster>) => response.ok),
        map((tdsSlabMaster: HttpResponse<TdsSlabMaster>) => tdsSlabMaster.body)
      );
    }
    return of(new TdsSlabMaster());
  }
}

export const tdsSlabMasterRoute: Routes = [
  {
    path: '',
    component: TdsSlabMasterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'TdsSlabMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: TdsSlabMasterUpdateComponent,
    resolve: {
      tdsSlabMaster: TdsSlabMasterResolve
    },
    data: {
      authorities: ['ROLE_ADMIN', 'ROLE_USER'],
      pageTitle: 'Tds Slab Masters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':finYear/:gender/edit',
    component: TdsSlabMasterUpdateComponent,
    resolve: {
      tdsSlabMaster: TdsSlabMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'TdsSlabMasters'
    },
    canActivate: [UserRouteAccessService]
  }
];
