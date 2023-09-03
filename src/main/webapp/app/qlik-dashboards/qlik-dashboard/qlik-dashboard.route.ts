import { ActivatedRouteSnapshot, Resolve, Route, RouterStateSnapshot } from '@angular/router';
import { QlikDashboardComponent } from './qlik-dashboard.component';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { HttpResponse } from '@angular/common/http';
import { IMenuMaster, MenuMaster } from 'app/shared/model/menu-master.model';
import { MenuMasterService } from 'app/entities/menu-master';

@Injectable({ providedIn: 'root' })
export class QlikDashboardResolve implements Resolve<IMenuMaster> {
  constructor(private service: MenuMasterService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMenuMaster> {
    const id = route.params['type'] ? route.params['type'] : null;
    if (id) {
      return this.service.findQlik(id, 'qlik-dashboard').pipe(
        filter((response: HttpResponse<IMenuMaster>) => response.ok),
        map((menuMaster: HttpResponse<IMenuMaster>) => menuMaster.body)
      );
    }
    return of(new MenuMaster());
  }
}

export const QLIK_DASHBOARD_ROUTE: Route = {
  path: ':type',
  component: QlikDashboardComponent,
  resolve: {
    menuMaster: QlikDashboardResolve
  },
  data: {
    authorities: [],
    pageTitle: 'Qlik Dashboard!'
  }
};
