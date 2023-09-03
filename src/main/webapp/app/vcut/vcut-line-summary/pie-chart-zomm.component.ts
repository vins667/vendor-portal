import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'jhi-pie-chart-zoom',
  templateUrl: './pie-chart-zoom.component.html'
})
export class PieChartZommComponent {
  width = '100%';
  height = 500;
  type = 'pie2d';
  dataFormat = 'json';
  dataSource: any;

  constructor(public activeModal: NgbActiveModal) {}

  close() {
    this.activeModal.dismiss('cancel');
  }
}
