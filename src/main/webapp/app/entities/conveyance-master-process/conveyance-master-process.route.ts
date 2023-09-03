import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ConveyanceMasterProcessComponent } from './conveyance-master-process.component';

export const conveyanceMasterProcessRoute: Routes = [
  {
    path: '',
    component: ConveyanceMasterProcessComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ADMIN_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'Conveyance Master Hr'
    },
    canActivate: [UserRouteAccessService]
  }
];
