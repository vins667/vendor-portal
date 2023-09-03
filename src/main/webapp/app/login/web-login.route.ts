import {Route} from '@angular/router';
import {WebLoginComponent} from './web-login.component';

export const WEB_LOGIN_ROUTE: Route = {
  path: '',
  component: WebLoginComponent,
  data: {
    authorities: [],
    pageTitle: 'Welcome, Vamani Portal!'
  }
};
