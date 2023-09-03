import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'jhi-stacks-chart-zoom',
  templateUrl: './stacks-chart-zoom.component.html'
})
export class StacksChartZommComponent {
  widthStacks = '100%';
  heightStacks = 500;
  typeStacks = 'pyramid';
  dataFormatStacks = 'json';
  dataSourceStacks: any;

  constructor(public activeModal: NgbActiveModal) {}

  close() {
    this.activeModal.dismiss('cancel');
  }
}
