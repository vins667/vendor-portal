import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'jhi-line-chart-zoom',
  templateUrl: './line-chart-zoom.component.html'
})
export class LineChartZommComponent {
  widthLine = '100%';
  heightLine = 500;
  typeLine = 'msline';
  dataFormatLine = 'json';
  dataSourceLine: any;

  constructor(public activeModal: NgbActiveModal) {}

  close() {
    this.activeModal.dismiss('cancel');
  }
}
