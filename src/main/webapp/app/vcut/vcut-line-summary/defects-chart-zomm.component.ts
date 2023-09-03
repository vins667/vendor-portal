import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'jhi-defects-chart-zoom',
  templateUrl: './defects-chart-zoom.component.html'
})
export class DefectsChartZommComponent {
  widthDefects = '100%';
  heightDefects = 500;
  typeDefects = 'stackedcolumn3d';
  dataFormatDefects = 'json';
  dataSourceDefects: any;

  constructor(public activeModal: NgbActiveModal) {}

  close() {
    this.activeModal.dismiss('cancel');
  }
}
