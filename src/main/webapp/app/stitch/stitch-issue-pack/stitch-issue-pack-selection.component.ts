import { Component, ElementRef, Input, OnInit, ViewChild } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { StitchIssuePackService } from './stitch-issue-pack.service';
import { SnotifyService } from 'ng-snotify';
import { IStitchIssuePack } from 'app/shared/model/stitch-issue-pack.model';
import { IStitchIssuePackDetails } from 'app/shared/model/stitch-issue-pack-details.model';
import { toastConfig } from 'app/core/toast/toast-config';

@Component({
  selector: 'jhi-stitch-issue-pack-selection',
  templateUrl: './stitch-issue-pack-selection.component.html'
})
export class StitchIssuePackSelectionComponent implements OnInit {
  isSaving = false;
  isProcess = false;
  plantCode?: string;
  scanId?: string;
  @Input() stitchIssuePack?: IStitchIssuePack;
  stitchIssuePackDetails?: IStitchIssuePackDetails[] = [];

  @ViewChild('scanner', { static: true }) scannerElement: ElementRef;

  constructor(
    protected stitchIssuePackService: StitchIssuePackService,
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
    if (this.scanId && this.stitchIssuePack.scannedBy === 'BNDL') {
      this.stitchIssuePackService.bundle(Number(this.scanId)).subscribe(
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
        () => {
          this.snotifyService.error('Invalid Bundle', '', toastConfig);
          this.scanId = undefined;
          this.scannerElement.nativeElement.focus();
        }
      );
    } else {
      this.stitchIssuePackService.piece(Number(this.scanId)).subscribe(
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
            this.snotifyService.error('Invalid Piece', '', toastConfig);
            this.scanId = undefined;
            this.scannerElement.nativeElement.focus();
          }
        },
        () => {
          this.snotifyService.error('Invalid Piece', '', toastConfig);
          this.scanId = undefined;
          this.scannerElement.nativeElement.focus();
        }
      );
    }
  }

  addAll(): void {
    this.eventManager.broadcast({ name: 'stitchIssuePackEntryFilter', content: this.stitchIssuePackDetails });
    this.activeModal.dismiss();
  }
}
