import {NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';
import {errorRoute} from './layouts/error/error.route';
import {navbarRoute} from './layouts/navbar/navbar.route';
import {DEBUG_INFO_ENABLED} from 'app/app.constants';

const LAYOUT_ROUTES = [navbarRoute, ...errorRoute];

@NgModule({
  imports: [
    RouterModule.forRoot(
      [
        {
          path: 'admin',
          loadChildren: () => import('./admin/admin.module').then(m => m.VamaniportalAdminModule)
        },
        {
          path: 'account',
          loadChildren: () => import('./account/account.module').then(m => m.VamaniportalAccountModule)
        },
        {
          path: 'nda',
          loadChildren: () => import('./pdf-viewer/pdf-viewer.module').then(m => m.VamaniportalPdfViewerModule)
        },
        {
          path: 'dashboard',
          loadChildren: () => import('./dashboard/dashboard.module').then(m => m.VamaniportalDashboardModule)
        },
        {
          path: 'web',
          loadChildren: () => import('./login/web-login.module').then(m => m.VamaniportalWebLoginModule)
        },
        {
          path: 'external-url',
          loadChildren: () => import('./external/external.module').then(m => m.VamaniportalExternalModule)
        },
        ...LAYOUT_ROUTES
      ],
      {enableTracing: DEBUG_INFO_ENABLED}
    )
  ],
  exports: [RouterModule]
})
export class VamaniportalAppRoutingModule {
}
