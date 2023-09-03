import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'jhi-defects-ob-chart-zoom',
  templateUrl: './defects-ob-chart-zoom.component.html'
})
export class DefectsObChartZommComponent {
  widthDefectsOB = '100%';
  heightDefectsOB = 500;
  typeDefectsOB = 'stackedcolumn3d';
  dataFormatDefectsOB = 'json';
  dataSourceDefectsOB: any;

  constructor(public activeModal: NgbActiveModal) {}

  close() {
    this.activeModal.dismiss('cancel');
  }
}
