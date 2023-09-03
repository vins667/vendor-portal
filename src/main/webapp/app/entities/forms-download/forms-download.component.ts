import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';
import { IFormsDownload } from 'app/shared/model/forms-download.model';
import { AccountService } from 'app/core/auth/account.service';
import { FormsDownloadService } from './forms-download.service';
import * as FileSaver from 'file-saver';

@Component({
  selector: 'jhi-forms-download',
  templateUrl: './forms-download.component.html'
})
export class FormsDownloadComponent implements OnInit, OnDestroy {
  formsDownloads: IFormsDownload[];
  currentAccount: any;
  eventSubscriber: Subscription;
  page = 1;
  totalPages: number;
  isLoaded = false;

  constructor(
    protected formsDownloadService: FormsDownloadService,
    protected jhiAlertService: JhiAlertService,
    protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {}

  afterLoadComplete(pdfData: any) {
    this.totalPages = pdfData.numPages;
    this.isLoaded = true;
  }

  nextPage() {
    this.page++;
  }

  prevPage() {
    this.page--;
  }

  changePage() {
    this.page = 1;
  }

  loadAll() {
    this.formsDownloadService.query().subscribe(
      (res: HttpResponse<IFormsDownload[]>) => {
        this.formsDownloads = res.body;
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
  }

  download(formsDownload: IFormsDownload): any {
    this.formsDownloadService.download(formsDownload.id).subscribe(
      res => {
        FileSaver.saveAs(res, `${formsDownload.fileName}`);
      },
      res => {
        this.onError(res.message);
      }
    );
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInFormsDownloads();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IFormsDownload) {
    return item.id;
  }

  registerChangeInFormsDownloads() {
    this.eventSubscriber = this.eventManager.subscribe('formsDownloadListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
