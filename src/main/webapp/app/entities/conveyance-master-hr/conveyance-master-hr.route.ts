import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ConveyanceMasterHrService } from './conveyance-master-hr.service';
import { ConveyanceMasterHrComponent } from './conveyance-master-hr.component';
import { ConveyanceMasterHrUpdateComponent } from './conveyance-master-hr-update.component';
import { IConveyanceMaster, ConveyanceMaster } from 'app/shared/model/conveyance-master.model';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';

@Injectable({ providedIn: 'root' })
export class ConveyanceMasterHrResolve implements Resolve<IConveyanceMaster> {
  constructor(private service: ConveyanceMasterHrService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IConveyanceMaster> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<ConveyanceMaster>) => response.ok),
        map((conveyanceMasterHr: HttpResponse<ConveyanceMaster>) => conveyanceMasterHr.body)
      );
    }
    return of(new ConveyanceMaster());
  }
}
export const conveyanceMasterHrRoute: Routes = [
  {
    path: '',
    component: ConveyanceMasterHrComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ADMIN_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'Conveyance Master Hr'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ConveyanceMasterHrUpdateComponent,
    resolve: {
      conveyanceMaster: ConveyanceMasterHrResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ADMIN_USER'],
      pageTitle: 'Conveyance Master Hr'
    },
    canActivate: [UserRouteAccessService]
  }
];
