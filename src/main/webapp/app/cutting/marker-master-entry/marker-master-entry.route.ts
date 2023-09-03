import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MarkerMasterEntry } from 'app/shared/model/marker-master-entry.model';
import { MarkerMasterEntryService } from './marker-master-entry.service';
import { MarkerMasterEntryComponent } from './marker-master-entry.component';
import { MarkerMasterEntryDetailComponent } from './marker-master-entry-detail.component';
import { MarkerMasterEntryUpdateComponent } from './marker-master-entry-update.component';
import { MarkerMasterEntryDeletePopupComponent } from './marker-master-entry-delete-dialog.component';
import { IMarkerMasterEntry } from 'app/shared/model/marker-master-entry.model';

@Injectable({ providedIn: 'root' })
export class MarkerMasterEntryResolve implements Resolve<IMarkerMasterEntry> {
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

export const markerMasterEntryRoute: Routes = [
  {
    path: '',
    component: MarkerMasterEntryComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'style,asc',
      pageTitle: 'MarkerMasterEntries'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MarkerMasterEntryDetailComponent,
    resolve: {
      markerMasterEntry: MarkerMasterEntryResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'MarkerMasterEntries'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MarkerMasterEntryUpdateComponent,
    resolve: {
      markerMasterEntry: MarkerMasterEntryResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'MarkerMasterEntries'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'edit',
    component: MarkerMasterEntryUpdateComponent,
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'MarkerMasterEntries'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const markerMasterEntryPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MarkerMasterEntryDeletePopupComponent,
    resolve: {
      markerMasterEntry: MarkerMasterEntryResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'MarkerMasterEntries'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
