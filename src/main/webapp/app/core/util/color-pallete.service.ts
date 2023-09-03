import { Injectable } from '@angular/core';

@Injectable({ providedIn: 'root' })
export class ColorPalleteService {
  colors = new Map();

  constructor() {
    this.colors.set(0, '#DCA3C2');
    this.colors.set(1, '#C7CEF4');
    this.colors.set(2, '#D1A69B');
    this.colors.set(3, '#7894CF');
    this.colors.set(4, '#97D2E1');
    this.colors.set(5, '#EBB8BD');
    this.colors.set(6, '#9B9655');
    this.colors.set(7, '#F5D755');
    this.colors.set(8, '#B5DCDD');
    this.colors.set(9, '#61AAC5');
    this.colors.set(10, '#F8EFD4');
    this.colors.set(11, '#D27AE7');
    this.colors.set(12, '#F2F4F9');
    this.colors.set(13, '#51A2DA');
    this.colors.set(14, '#B8D1FF');
    this.colors.set(15, '#C2CEE5');
  }

  public colorCodes(key: number): string {
    if (this.colors.has(key)) {
      return this.colors.get(key);
    } else {
      return undefined;
    }
  }
}
