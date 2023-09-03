import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable, Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';
import { IJobProfile } from 'app/shared/model/job-profile.model';
import { AccountService } from 'app/core/auth/account.service';
import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { JobProfileService } from './job-profile.service';
import { IJobProfileSearch, JobProfileSearch } from 'app/shared/model/job-profile-search.model';
import * as FileSaver from 'file-saver';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { toastConfig } from 'app/core/toast/toast-config';
import { SnotifyService } from 'ng-snotify';
import { JobProfileSortComponent } from './job-profile-sort.component';

@Component({
  selector: 'jhi-job-profile',
  templateUrl: './job-profile.component.html'
})
export class JobProfileComponent implements OnInit, OnDestroy {
  currentAccount: any;
  jobProfiles: IJobProfile[];
  error: any;
  success: any;
  eventSubscriber: Subscription;
  routeData: any;
  links: any;
  totalItems: any;
  queryCount: any;
  itemsPerPage: any;
  page: any;
  predicate: any;
  previousPage: any;
  reverse: any;
  closeResult: string;

  jobSearch: IJobProfileSearch;
  selectJobProfile: IJobProfile;
  selectedFile: FileList;
  currentFileUpload: File;
  extn: string;

  constructor(
    protected jobProfileService: JobProfileService,
    protected parseLinks: JhiParseLinks,
    protected jhiAlertService: JhiAlertService,
    protected accountService: AccountService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    private modalService: NgbModal,
    private snotifyService: SnotifyService
  ) {
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.routeData = this.activatedRoute.data.subscribe(data => {
      this.page = data.pagingParams.page;
      this.previousPage = data.pagingParams.page;
      this.reverse = data.pagingParams.ascending;
      this.predicate = data.pagingParams.predicate;
    });
  }

  loadAll() {
    this.jobSearch.size = ITEMS_PER_PAGE;
    this.jobSearch.pageNo = 0;
    this.page = 0;
    this.jobProfileService
      .queryCustom(this.jobSearch)
      .subscribe(
        (res: HttpResponse<IJobProfile[]>) => this.paginateJobProfiles(res.body, res.headers),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  loadAllNew() {
    this.jobSearch.size = this.itemsPerPage;
    // this.page = this.page - 1;
    this.jobSearch.pageNo = this.page - 1;
    this.jobProfileService
      .queryCustom(this.jobSearch)
      .subscribe(
        (res: HttpResponse<IJobProfile[]>) => this.paginateJobProfiles(res.body, res.headers),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  loadPage(page: number) {
    if (page !== this.previousPage) {
      this.previousPage = page;
      this.transition();
    }
  }

  search() {
    this.jobSearch.size = ITEMS_PER_PAGE;
    this.jobSearch.pageNo = 0;
    this.page = 0;
    this.jobProfileService
      .queryCustom(this.jobSearch)
      .subscribe(
        (res: HttpResponse<IJobProfile[]>) => this.paginateJobProfiles(res.body, res.headers),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  open(content, jobProfile) {
    this.selectJobProfile = jobProfile;
    this.modalService.open(content, { ariaLabelledBy: 'modal-basic-title' }).result.then(
      result => {
        this.closeResult = `Closed with: ${result}`;
      },
      reason => {
        this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
      }
    );
  }

  openSort() {
    this.modalService.open(JobProfileSortComponent, { size: 'lg', backdrop: 'static' }).result.then(
      result => {
        this.closeResult = `Closed with: ${result}`;
      },
      reason => {
        this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
      }
    );
  }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
  }

  transition() {
    this.router.navigate(['/job-profile/upload'], {
      queryParams: {
        page: this.page,
        size: this.itemsPerPage,
        sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
      }
    });
    this.loadAllNew();
  }

  clear() {
    this.page = 0;
    this.router.navigate([
      '/job-profile',
      {
        page: this.page,
        sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
      }
    ]);
    this.loadAll();
  }

  ngOnInit() {
    this.jobSearch = new JobProfileSearch();
    this.jobSearch.status = 'P';
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInJobProfiles();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IJobProfile) {
    return item.id;
  }

  registerChangeInJobProfiles() {
    this.eventSubscriber = this.eventManager.subscribe('jobProfileListModification', response => this.loadAll());
  }

  sort() {
    const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateJobProfiles(data: IJobProfile[], headers: HttpHeaders) {
    this.links = this.parseLinks.parse(headers.get('link'));
    this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
    this.queryCount = this.totalItems;
    this.jobProfiles = data;
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  download(jobProfile: IJobProfile): any {
    this.jobProfileService.download(jobProfile.id).subscribe(
      res => {
        FileSaver.saveAs(res, `${jobProfile.fileName}`);
      },
      res => {
        this.onError(res.message);
      }
    );
  }

  selectFile(event) {
    this.selectedFile = event.target.files;
    const file = event.target.files[0];
    const fileName = file.name;
    this.extn = fileName.substring(fileName.lastIndexOf('.') + 1, fileName.length);
    if (this.extn !== null && this.extn !== 'pdf') {
      this.snotifyService.error('Only Pdf File Allowed!!!', '', toastConfig);
    }
  }

  saveUpload() {
    if (this.selectedFile && this.extn !== null) {
      this.currentFileUpload = this.selectedFile.item(0);
      if (this.extn !== null && this.extn === 'pdf') {
        if (this.selectJobProfile.id !== undefined) {
          this.subscribeToSaveResponse(this.jobProfileService.createUpload(this.currentFileUpload, this.selectJobProfile.id));
        }
      } else {
        this.snotifyService.error('Only Pdf File Allowed!!!', '', toastConfig);
      }
    } else {
      this.snotifyService.error('Please choose file!!!', '', toastConfig);
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IJobProfile>>) {
    result.subscribe((res: HttpResponse<IJobProfile>) => this.onSaveSuccess(res), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess(result: HttpResponse<IJobProfile>) {
    this.snotifyService.success('Save successfully!!!', '', toastConfig);
    this.modalService.dismissAll();
    this.search();
  }

  protected onSaveError() {}
}
