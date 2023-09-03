import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CommonService {
  navbarToggleValue: boolean;
  sidebarToggleValue: boolean;
  sidebarMiniToggleValue: boolean;
  mobileFeature: boolean;

  constructor() {
    this.navbarToggleValue = false;
    this.sidebarToggleValue = true;
    this.sidebarMiniToggleValue = false;
    this.mobileFeature = false;
  }

  sidebarToggle(): void {
    this.sidebarToggleValue = !this.sidebarToggleValue;
  }

  sidebarMiniToggle(): void {
    this.sidebarMiniToggleValue = !this.sidebarMiniToggleValue;
  }

  navbarToggle(): void {
    this.navbarToggleValue = !this.navbarToggleValue;
  }

  mobileToggle(): void {
    this.mobileFeature = !this.mobileFeature;
  }
}
