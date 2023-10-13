import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StitchCostHeadMasterComponent } from './stitch-cost-Head-master.component';
import { IStitchCostHeadMaster, StitchCostHeadMaster } from 'app/shared/model/stitch-cost-head-master.model';
import { StitchCostHeadMasterService } from './stitch-cost-Head-master.service';

@Injectable({ providedIn: 'root' })
export class StitchCostHeadMasterResolve implements Resolve<IStitchCostHeadMaster> {
  constructor(private service: StitchCostHeadMasterService) { }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IStitchCostHeadMaster> {

    return of(new StitchCostHeadMaster());
  }
}

export const stitchCostHeadMasterRoute: Routes = [
  {
    path: '',
    component: StitchCostHeadMasterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'StitchCostHeadMasters'
    },
    canActivate: [UserRouteAccessService]
  }
];

