import { Component, Input, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { IMrnBean } from 'app/shared/model/mrn-bean.model';
import { DebitNoteEntryService } from 'app/entities/debit-note-entry/debit-note-entry.service';
import { IMasterParameters } from 'app/shared/db2/model/master-parameters.model';

@Component({
  selector: 'jhi-debit-note-item-selection',
  templateUrl: './debit-note-item-selection.component.html'
})
export class DebitNoteItemSelectionComponent implements OnInit {
  isProcess = false;
  mrnBeans: IMrnBean[];
  @Input() masterParameters: IMasterParameters;

  constructor(
    protected activatedRoute: ActivatedRoute,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager,
    public debitNoteEntryService: DebitNoteEntryService
  ) {}

  ngOnInit(): void {
    this.debitNoteEntryService.getDebitNoteMrn(this.masterParameters).subscribe(masterBeans => {
      this.mrnBeans = masterBeans.body;
    });
  }

  previousState(): void {
    window.history.back();
  }

  cancel(): void {
    this.activeModal.dismiss();
  }

  loadData(masterBean?: IMrnBean): void {
    this.eventManager.broadcast({ name: 'debitNoteMrnItemSelection', content: masterBean });
    this.activeModal.dismiss();
  }
}
