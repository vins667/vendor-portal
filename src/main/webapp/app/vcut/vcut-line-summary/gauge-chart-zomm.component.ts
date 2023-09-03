import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'jhi-gauge-chart-zoom',
  templateUrl: './gauge-chart-zoom.component.html'
})
export class GaugeChartZommComponent {
  widthGauge = '100%';
  heightGauge = 500;
  typeGauge = 'angulargauge';
  dataFormatGauge = 'json';
  dataSourceGauge: any;

  constructor(public activeModal: NgbActiveModal) {}

  close() {
    this.activeModal.dismiss('cancel');
  }
}
