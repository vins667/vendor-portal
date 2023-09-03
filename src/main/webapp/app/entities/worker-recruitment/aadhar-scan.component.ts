import { Component, OnInit, ViewChild } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { WorkerRecruitmentService } from './worker-recruitment.service';
import { JhiEventManager } from 'ng-jhipster';

@Component({
  selector: 'jhi-aadhar-scan-content',
  template: `
    <div class="modal-body">
      <div align="center" class="centered" *ngIf="isDownload">
        <img src="../../../content/images/wait.gif" />
      </div>
      <h5 id="page-heading">
        <span>Scan Aadhar</span>
        <button type="button" class="close" style="top: 10px;" data-dismiss="modal" aria-hidden="true" (click)="close()">&times;</button>
      </h5>
      <hr />
      <div class="row">
        <textarea
          #aadharComp
          name="aadharScan"
          id="aadharScan"
          [(ngModel)]="aadharScan"
          class="form-control"
          style="height: 200px; background-color: #FFFFE0;"
          (blur)="callAadharAPI()"
        ></textarea>
      </div>
    </div>
  `
})
export class AadharScanComponent implements OnInit {
  aadhar?: string;
  aadharScan?: string;
  isDownload?: boolean;

  @ViewChild('aadharComp', { static: true })
  aadharComp: any;

  constructor(
    protected workerRecruitmentService: WorkerRecruitmentService,
    protected eventManager: JhiEventManager,
    public activeModal: NgbActiveModal
  ) {}

  ngOnInit() {
    this.isDownload = false;
    if (this.aadharScan === undefined || this.aadharScan === null) {
      this.aadharComp.nativeElement.focus();
    }
  }

  close() {
    this.activeModal.dismiss('cancel');
  }

  callAadharAPI() {
    if (this.aadharScan) {
      this.isDownload = true;
      this.workerRecruitmentService.fetchAadhar(this.aadharScan, this.aadhar).subscribe(workerRecruitment => {
        this.isDownload = false;
        this.eventManager.broadcast({ name: 'scanAadharCardByScanner', content: workerRecruitment.body });
        this.activeModal.dismiss('cancel');
      });
    }
  }
}
