import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { FormsDownload } from 'app/shared/model/forms-download.model';
import { FormsDownloadService } from './forms-download.service';
import { FormsDownloadComponent } from './forms-download.component';
import { IFormsDownload } from 'app/shared/model/forms-download.model';

@Injectable({ providedIn: 'root' })
export class FormsDownloadResolve implements Resolve<IFormsDownload> {
  constructor(private service: FormsDownloadService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<FormsDownload> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<FormsDownload>) => response.ok),
        map((formsDownload: HttpResponse<FormsDownload>) => formsDownload.body)
      );
    }
    return of(new FormsDownload());
  }
}

export const formsDownloadRoute: Routes = [
  {
    path: '',
    component: FormsDownloadComponent,
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'FormsDownloads'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const formsDownloadPopupRoute: Routes = [];
