import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { LeaveMaster } from 'app/shared/model/leave-master.model';
import { LeaveMasterHrService } from './leave-master-hr.service';
import { LeaveMasterHrApprovalComponent } from './leave-master-hr-approval.component';
import { ILeaveMaster } from 'app/shared/model/leave-master.model';

@Injectable({ providedIn: 'root' })
export class LeaveMasterResolve implements Resolve<ILeaveMaster> {
  constructor(private service: LeaveMasterHrService) {}

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

export const leaveMasterHrRoute: Routes = [
  {
    path: 'approval',
    component: LeaveMasterHrApprovalComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,dsc',
      pageTitle: 'Leave Approval HR'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const leaveMasterHrPopupRoute: Routes = [];
