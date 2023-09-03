import { Routes } from '@angular/router';
import { passwordRoute, passwordResetInitRoute, registerRoute, settingsRoute } from './';

const ACCOUNT_ROUTES = [passwordRoute, passwordResetInitRoute, registerRoute, settingsRoute];

export const accountState: Routes = [
  {
    path: '',
    children: ACCOUNT_ROUTES
  }
];
