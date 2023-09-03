import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MarkerMasterEntry } from 'app/shared/model/marker-master-entry.model';
import { IMarkerMasterEntry } from 'app/shared/model/marker-master-entry.model';
import { MarkerMasterEntryService } from '../marker-master-entry/marker-master-entry.service';
import { MarkerEntryApprovalComponent } from './marker-entry-approval.component';

@Injectable({ providedIn: 'root' })
export class MarkerEntryApprovalResolve implements Resolve<IMarkerMasterEntry> {
  constructor(private service: MarkerMasterEntryService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMarkerMasterEntry> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MarkerMasterEntry>) => response.ok),
        map((markerMasterEntry: HttpResponse<MarkerMasterEntry>) => markerMasterEntry.body)
      );
    }
    return of(new MarkerMasterEntry());
  }
}

export const MarkerEntryApprovalRoute: Routes = [
  {
    path: '',
    component: MarkerEntryApprovalComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'style,asc',
      pageTitle: 'MarkerEntryApproval'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: '',
    component: MarkerEntryApprovalComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'style,asc',
      pageTitle: 'MarkerEntryApproval'
    },
    canActivate: [UserRouteAccessService]
  }
];
