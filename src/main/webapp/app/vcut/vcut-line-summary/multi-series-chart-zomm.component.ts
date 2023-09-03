import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'jhi-multi-series-chart-zoom',
  templateUrl: './multi-series-chart-zoom.component.html'
})
export class MultiSeriesChartZommComponent {
  widthMultiSeries = '100%';
  heightMultiSeries = 500;
  typeMultiSeries = 'mscolumn3dlinedy';
  dataFormatMultiSeries = 'json';
  dataSourceMultiSeries: any;

  constructor(public activeModal: NgbActiveModal) {}

  close() {
    this.activeModal.dismiss('cancel');
  }
}
