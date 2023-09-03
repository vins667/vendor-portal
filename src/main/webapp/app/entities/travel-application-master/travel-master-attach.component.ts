import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';
import { ITravelMasterAttach, TravelMasterAttach } from 'app/shared/model/travel-master-attach.model';
import { TravelApplicationMasterService } from './travel-application-master.service';
import * as FileSaver from 'file-saver';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { ITravelApplicationMaster } from 'app/shared/model/travel-application-master.model';
import { SnotifyService } from 'ng-snotify';

@Component({
  selector: 'jhi-travel-master-attach',
  templateUrl: './travel-master-attach.component.html'
})
export class TravelMasterAttachComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;
  travelApplicationMaster: ITravelApplicationMaster;
  travelMasterAttach: ITravelMasterAttach;
  travelMasterAttaches: ITravelMasterAttach[];
  isDownloading = false;

  constructor(
    protected travelApplicationMasterService: TravelApplicationMasterService,
    protected jhiAlertService: JhiAlertService,
    protected activatedRoute: ActivatedRoute,
    protected snotifyService: SnotifyService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  ngOnInit(): void {
    this.travelMasterAttach = new TravelMasterAttach();
  }

  ngOnDestroy(): void {
    this.ngbModalRef = null;
  }

  close() {
    this.activeModal.dismiss('cancel');
  }

  downloadFile(travelAttach: ITravelMasterAttach) {
    if (travelAttach.id) {
      this.isDownloading = true;
      this.travelApplicationMasterService.download(travelAttach.id).subscribe(
        res => {
          FileSaver.saveAs(res, travelAttach.attachFile);
          this.isDownloading = false;
        },
        res => {
          this.isDownloading = false;
        }
      );
    }
  }
}
