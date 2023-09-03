import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { LeaveMaster } from 'app/shared/model/leave-master.model';
import { LeaveMasterHodService } from './leave-master-hod.service';
import { LeaveMasterApprovalComponent } from './leave-master-approval.component';
import { ILeaveMaster } from 'app/shared/model/leave-master.model';

@Injectable({ providedIn: 'root' })
export class LeaveMasterResolve implements Resolve<ILeaveMaster> {
  constructor(private service: LeaveMasterHodService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<LeaveMaster> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<LeaveMaster>) => response.ok),
        map((leaveMaster: HttpResponse<LeaveMaster>) => leaveMaster.body)
      );
    }
    return of(new LeaveMaster());
  }
}

export const leaveMasterHodRoute: Routes = [
  {
    path: 'approval',
    component: LeaveMasterApprovalComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,dsc',
      pageTitle: 'Leave Approval HOD'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const leaveMasterHodPopupRoute: Routes = [];
