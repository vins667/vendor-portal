import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { SoftwareKeyDetailsBean } from 'app/shared/model/software-key-details-bean.model';
import { SoftwareKeyDetailsService } from './software-key-details.service';
import { SoftwareKeyDetailsComponent } from './software-key-details.component';
import { ISoftwareKeyDetailsBean } from 'app/shared/model/software-key-details-bean.model';

@Injectable({ providedIn: 'root' })
export class SoftwareKeyDetailsResolve implements Resolve<ISoftwareKeyDetailsBean> {
  constructor(private service: SoftwareKeyDetailsService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<SoftwareKeyDetailsBean> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<SoftwareKeyDetailsBean>) => response.ok),
        map((softwareKeyDetails: HttpResponse<SoftwareKeyDetailsBean>) => softwareKeyDetails.body)
      );
    }
    return of(new SoftwareKeyDetailsBean());
  }
}

export const softwareKeyDetailsRoute: Routes = [
  {
    path: '',
    component: SoftwareKeyDetailsComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'Software Key Details'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const softwareKeyDetailsPopupRoute: Routes = [{}];
