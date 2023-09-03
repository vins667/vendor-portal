import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { TdsDeclaration } from 'app/shared/model/tds-declaration.model';
import { TdsDeclarationService } from './tds-declaration.service';
import { TdsDeclarationUpdateComponent } from './tds-declaration-update.component';
import { ITdsDeclaration } from 'app/shared/model/tds-declaration.model';

@Injectable({ providedIn: 'root' })
export class TdsDeclarationResolve implements Resolve<ITdsDeclaration> {
  constructor(private service: TdsDeclarationService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<TdsDeclaration> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<TdsDeclaration>) => response.ok),
        map((tdsDeclaration: HttpResponse<TdsDeclaration>) => tdsDeclaration.body)
      );
    }
    return of(new TdsDeclaration());
  }
}

export const tdsDeclarationRoute: Routes = [
  {
    path: '',
    component: TdsDeclarationUpdateComponent,
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Tds Declarations'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const tdsDeclarationPopupRoute: Routes = [];
