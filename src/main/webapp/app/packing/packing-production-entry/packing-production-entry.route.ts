import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IPackingProductionEntry, PackingProductionEntry } from 'app/shared/model/packing-production-entry.model';
import { PackingProductionEntryService } from './packing-production-entry.service';
import { PackingProductionEntryComponent } from './packing-production-entry.component';
import { PackingProductionEntryUpdateComponent } from './packing-production-entry-update.component';

@Injectable({ providedIn: 'root' })
export class PackingProductionEntryResolve implements Resolve<IPackingProductionEntry> {
  constructor(private service: PackingProductionEntryService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IPackingProductionEntry> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((packingProductionEntry: HttpResponse<PackingProductionEntry>) => {
          if (packingProductionEntry.body) {
            return of(packingProductionEntry.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new PackingProductionEntry());
  }
}

export const sewingProductionEntryRoute: Routes = [
  {
    path: '',
    component: PackingProductionEntryComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'Packing Production Entry'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: PackingProductionEntryUpdateComponent,
    resolve: {
      packingProductionEntry: PackingProductionEntryResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Packing Production Entry'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: PackingProductionEntryUpdateComponent,
    resolve: {
      packingProductionEntry: PackingProductionEntryResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Packing Production Entry'
    },
    canActivate: [UserRouteAccessService]
  }
];
