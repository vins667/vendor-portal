import { Component, ElementRef, Input, OnInit, ViewChild } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { PackingProductionEntryService } from './packing-production-entry.service';
import { SnotifyService } from 'ng-snotify';
import { IStitchIssuePackDetails } from 'app/shared/model/stitch-issue-pack-details.model';
import { toastConfig } from 'app/core/toast/toast-config';
import { HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { IPackingProductionEntry } from 'app/shared/model/packing-production-entry.model';

@Component({
  selector: 'jhi-packing-production-selection',
  templateUrl: './packing-production-selection.component.html'
})
export class PackingProductionSelectionComponent implements OnInit {
  isSaving = false;
  isProcess = false;
  plantCode?: string;
  scanId?: string;
  @Input() packingProductionEntry?: IPackingProductionEntry;
  stitchIssuePackDetails?: IStitchIssuePackDetails[] = [];

  @ViewChild('scanner', { static: true }) scannerElement: ElementRef;

  constructor(
    protected packingLineIssueService: PackingProductionEntryService,
    protected activatedRoute: ActivatedRoute,
    protected snotifyService: SnotifyService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  ngOnInit(): void {
    this.scannerElement.nativeElement.focus();
  }

  previousState(): void {
    window.history.back();
  }

  cancel(): void {
    this.activeModal.dismiss();
  }

  fetchData(): void {
    if (this.scanId && this.packingProductionEntry.scannedBy === 'BNDL') {
      this.packingLineIssueService.bundle(Number(this.scanId)).subscribe(
        stitchIssuePackDetails => {
          if (stitchIssuePackDetails.body && stitchIssuePackDetails.body.length > 0) {
            stitchIssuePackDetails.body.forEach(stitchIssuePackDetail => {
              let i = 0;
              let exist = false;
              this.stitchIssuePackDetails.forEach(stitchIssuePackDetailMain => {
                if (stitchIssuePackDetail.cutPlanBundleDetailsId === stitchIssuePackDetailMain.cutPlanBundleDetailsId) {
                  exist = true;
                }
                ++i;
              });
              if (i === this.stitchIssuePackDetails.length && exist === false) {
                stitchIssuePackDetail.checked = true;
                this.stitchIssuePackDetails.push(stitchIssuePackDetail);
              }
            });
            this.scanId = undefined;
            this.scannerElement.nativeElement.focus();
          } else {
            this.snotifyService.error('Invalid Bundle', '', toastConfig);
            this.scanId = undefined;
            this.scannerElement.nativeElement.focus();
          }
        },
        (res: HttpErrorResponse) => this.onError(res.headers)
      );
    } else if (this.scanId) {
      this.packingLineIssueService.piece(Number(this.scanId)).subscribe(
        stitchIssuePackDetails => {
          if (stitchIssuePackDetails.body && stitchIssuePackDetails.body.length > 0) {
            stitchIssuePackDetails.body.forEach(stitchIssuePackDetail => {
              let i = 0;
              let exist = false;
              this.stitchIssuePackDetails.forEach(stitchIssuePackDetailMain => {
                if (stitchIssuePackDetail.cutPlanBundleDetailsId === stitchIssuePackDetailMain.cutPlanBundleDetailsId) {
                  exist = true;
                }
                ++i;
              });
              if (i === this.stitchIssuePackDetails.length && exist === false) {
                stitchIssuePackDetail.checked = true;
                this.stitchIssuePackDetails.push(stitchIssuePackDetail);
              }
            });
            this.scanId = undefined;
            this.scannerElement.nativeElement.focus();
          } else {
            this.snotifyService.error('Invalid Bundle', '', toastConfig);
            this.scanId = undefined;
            this.scannerElement.nativeElement.focus();
          }
        },
        (res: HttpErrorResponse) => this.onError(res.headers)
      );
    }
  }

  addAll(): void {
    this.eventManager.broadcast({ name: 'packingProductionEntryDetailFilter', content: this.stitchIssuePackDetails });
    this.activeModal.dismiss();
  }

  protected onError(res: HttpHeaders) {
    this.snotifyService.error(res.get('X-vamaniportalApp-error'), '', toastConfig);
    this.scanId = undefined;
    this.scannerElement.nativeElement.focus();
  }
}
