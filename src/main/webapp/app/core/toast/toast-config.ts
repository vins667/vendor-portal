import { SnotifyPosition, SnotifyToastConfig } from 'ng-snotify';

export const toastConfig: SnotifyToastConfig = {
  bodyMaxLength: 500,
  titleMaxLength: 200,
  backdrop: -1,
  position: SnotifyPosition.centerTop,
  timeout: 3000,
  showProgressBar: false,
  closeOnClick: true,
  pauseOnHover: false
};
