import { Pipe, PipeTransform } from '@angular/core';
import { DecimalPipe } from '@angular/common';

@Pipe({ name: 'myNumber' })
export class MyNumberPipe implements PipeTransform {
  transform(value: any, args: string) {
    value = isNaN(value) ? 0 : +value;
    return this.decimalPipe.transform(value, args);
  }
  constructor(private decimalPipe: DecimalPipe) {}
}
