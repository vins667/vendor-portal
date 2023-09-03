import { Component, Input, OnInit, Output, EventEmitter, ViewChild, ViewEncapsulation } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import 'rxjs/add/operator/debounceTime';
import 'rxjs/add/operator/distinctUntilChanged';
import { INewsDetails } from 'app/shared/model/news-details.model';
import { NewsDetailsService } from './news-details.service';
import { IFactoryMaster } from 'app/shared/model/factory-master.model';
import { FactoryMasterService } from 'app/entities/factory-master';
import { INewsMaster } from 'app/shared/model/news-master.model';
import { NewsMasterService } from 'app/entities/news-master';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import 'rxjs/add/operator/debounceTime';
import { INewsDetailsAttach, NewsDetailsAttach } from 'app/shared/model/news-details-attach.model';
import { INewsDetailsBody, NewsDetailsBody } from 'app/shared/model/news-details-body.model';
import * as FileSaver from 'file-saver';
import { SnotifyService } from 'ng-snotify';
import { EmployeeViewService } from 'app/entities/employee-view';
import { intersectionwith } from 'lodash/intersectionWith';
import { differenceWith } from 'lodash/differenceWith';
import { toastConfig } from 'app/core/toast/toast-config';
import { IItemsMovedEvent } from 'app/shared/model/items-moved-event';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { NewsDetailsPopupPreviewComponent } from './news-details-popup-preview.component';

@Component({
  selector: 'jhi-news-details-update',
  templateUrl: './news-details-update.component.html',
  encapsulation: ViewEncapsulation.None
})
export class NewsDetailsUpdateComponent implements OnInit {
  newsDetails: INewsDetails;
  isSaving: boolean;
  isNewJoining: boolean;
  newsmasters: INewsMaster[];
  factorymasters: IFactoryMaster[];
  endDateDp: any;
  createdDate: string;
  approvedDate: string;
  ckeditorContent: string;
  displayAttach: Boolean;
  selectedFiles: FileList[] = [];
  currentFileUpload: File[] = [];
  attachButtons: INewsDetailsAttach[] = [];
  newsDetailsBodies: INewsDetailsBody[] = [];

  // private variables to manage class
  searchTermAvailable = '';
  searchTermSelected = '';
  availableItems: Array<IFactoryMaster> = [];
  selectedItems: Array<IFactoryMaster> = [];
  listBoxForm: FormGroup;
  availableListBoxControl: FormControl = new FormControl();
  selectedListBoxControl: FormControl = new FormControl();
  availableSearchInputControl: FormControl = new FormControl();
  selectedSearchInputControl: FormControl = new FormControl();

  // field to use for value of option
  @Input() valueField = 'id';
  // field to use for displaying option text
  @Input() textField = 'name';
  // text to display as title above component
  @Input() title: string;
  // time to debounce search output in ms
  @Input() debounceTime = 500;
  // show/hide button to move all items between boxes
  @Input() moveAllButton = true;
  // text displayed over the available items list box
  @Input() availableText = 'Available items';
  // text displayed over the selected items list box
  @Input() selectedText = 'Selected items';
  // set placeholder text in available items list box
  @Input() availableFilterPlaceholder = 'Filter...';
  // set placeholder text in selected items list box
  @Input() selectedFilterPlaceholder = 'Filter...';

  // event called when item or items from available items(left box) is selected
  @Output() onAvailableItemSelected: EventEmitter<{} | Array<{}>> = new EventEmitter<{} | Array<{}>>();
  // event called when item or items from selected items(right box) is selected
  @Output() onSelectedItemsSelected: EventEmitter<{} | Array<{}>> = new EventEmitter<{} | Array<{}>>();
  // event called when items are moved between boxes, returns state of both boxes and item moved
  @Output() onItemsMoved: EventEmitter<IItemsMovedEvent> = new EventEmitter<IItemsMovedEvent>();

  @ViewChild('myckeditor', { static: false }) ckeditor: any;

  protected ngbModalRef: NgbModalRef;

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected newsDetailsService: NewsDetailsService,
    protected newsMasterService: NewsMasterService,
    protected factoryMasterService: FactoryMasterService,
    protected activatedRoute: ActivatedRoute,
    public fb: FormBuilder,
    private snotifyService: SnotifyService,
    private router: Router,
    protected modalService: NgbModal,
    private employeeViewService: EmployeeViewService
  ) {
    this.listBoxForm = this.fb.group({
      availableListBox: this.availableListBoxControl,
      selectedListBox: this.selectedListBoxControl,
      availableSearchInput: this.availableSearchInputControl,
      selectedSearchInput: this.selectedSearchInputControl
    });
  }

  ngOnInit() {
    this.isSaving = false;
    this.isNewJoining = false;
    this.availableItems = [];
    this.availableText = 'Available Factories';
    this.selectedText = 'Selected Factories';
    this.newsMasterService.query().subscribe(
      (res: HttpResponse<INewsMaster[]>) => {
        this.newsmasters = res.body;
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
    this.activatedRoute.data.subscribe(({ newsDetails }) => {
      this.newsDetails = newsDetails;
      this.createdDate = this.newsDetails.createdDate != null ? this.newsDetails.createdDate.format(DATE_TIME_FORMAT) : null;
      this.approvedDate = this.newsDetails.approvedDate != null ? this.newsDetails.approvedDate.format(DATE_TIME_FORMAT) : null;
      this.ckeditorContent = '';
      if (this.newsDetails.newsDetailsBodies && this.newsDetails.newsDetailsBodies.length > 0) {
        this.newsDetails.newsDetailsBodies.forEach(newsDetailsBody => {
          this.ckeditorContent += newsDetailsBody.newsBody;
        });
        this.selectedItems = this.newsDetails.factoryMasters;
      }
    });

    this.factoryMasterService.query().subscribe(
      (res: HttpResponse<IFactoryMaster[]>) => {
        this.factorymasters = res.body;
        this.availableItems = this.factorymasters;
        if (this.selectedItems && this.selectedItems.length > 0) {
          for (let i = this.availableItems.length - 1; i >= 0; i--) {
            for (let j = 0; j < this.selectedItems.length; j++) {
              if (this.availableItems[i] && this.availableItems[i].id === this.selectedItems[j].id) {
                this.availableItems.splice(i, 1);
              }
            }
          }
        }
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
    if ((this.newsDetails !== null && this.newsDetails.id === null) || this.newsDetails.id === undefined) {
      this.newsDetails.flag = 'E';
      this.newsDetails.mailFlag = false;
      this.newsDetails.notificationFlag = false;
    }

    this.availableListBoxControl.valueChanges.subscribe((items: Array<{}>) => this.onAvailableItemSelected.emit(items));
    this.selectedListBoxControl.valueChanges.subscribe((items: Array<{}>) => this.onSelectedItemsSelected.emit(items));
    this.availableSearchInputControl.valueChanges
      .debounceTime(this.debounceTime)
      .distinctUntilChanged()
      .subscribe((search: string) => (this.searchTermAvailable = search));
    this.selectedSearchInputControl.valueChanges
      .debounceTime(this.debounceTime)
      .distinctUntilChanged()
      .subscribe((search: string) => (this.searchTermSelected = search));

    this.attachButtons.push(new NewsDetailsAttach());
  }

  previousState() {
    window.history.back();
  }

  callDetails() {
    if (this.newsDetails.empCode !== undefined) {
      this.newsDetails.empName = '';
      this.employeeViewService.findByCard(this.newsDetails.empCode).subscribe(employeeView => {
        this.newsDetails.empName = employeeView.body.name;
        if (this.newsDetails.empName === undefined || this.newsDetails.empName === null) {
          this.snotifyService.error('In-valid card No ' + this.newsDetails.empCode + '!', '', toastConfig);
          this.newsDetails.empCode = '';
        }
      });
    } else {
      this.newsDetails.empName = '';
    }
  }

  selectFile(event) {
    this.selectedFiles.push(event.target.files);
  }

  saveUpload() {
    if (this.selectedFiles != null) {
      this.selectedFiles.forEach(fileList => {
        this.currentFileUpload.push(fileList.item(0));
      });
    }
    if (this.newsDetails.id !== undefined) {
      this.subscribeToSaveResponse(this.newsDetailsService.createUpload(this.currentFileUpload, this.newsDetails.id));
    }
  }

  download(newsDetailsAttach: INewsDetailsAttach): any {
    this.newsDetailsService.download(newsDetailsAttach.id).subscribe(
      res => {
        FileSaver.saveAs(res, `${newsDetailsAttach.attachDisplayFile}`);
      },
      res => {
        this.onError(res.message);
      }
    );
  }

  previewByUID() {
    this.ngbModalRef = this.modalService.open(NewsDetailsPopupPreviewComponent as Component, {
      size: 'lg',
      backdrop: 'static',
      windowClass: 'mediumModal'
    });
    this.ngbModalRef.componentInstance.ckeditorContent = this.ckeditorContent;
    this.ngbModalRef.componentInstance.newsTitle = this.newsDetails.newsTitle;
  }

  save() {
    this.isSaving = true;
    this.newsDetails.createdDate = this.createdDate != null ? moment(this.createdDate, DATE_TIME_FORMAT) : null;
    this.newsDetails.approvedDate = this.approvedDate != null ? moment(this.approvedDate, DATE_TIME_FORMAT) : null;
    this.newsDetails.newsDetailsAttaches = this.attachButtons;
    if (this.ckeditorContent !== null) {
      if (this.ckeditorContent.length > 2000) {
        const content: string[] = this.splitNChars(this.ckeditorContent, 2000);
        content.forEach(value => {
          const newsBody = new NewsDetailsBody();
          newsBody.newsBody = value;
          this.newsDetailsBodies.push(newsBody);
        });
        this.newsDetails.newsDetailsBodies = this.newsDetailsBodies;
        this.newsDetails.factoryMasters = this.selectedItems;
      } else {
        const newsBody = new NewsDetailsBody();
        newsBody.newsBody = this.ckeditorContent;
        this.newsDetailsBodies.push(newsBody);
        this.newsDetails.newsDetailsBodies = this.newsDetailsBodies;
        this.newsDetails.factoryMasters = this.selectedItems;
      }
    }
    if (this.newsDetails.id !== undefined) {
      this.subscribeToSaveResponse(this.newsDetailsService.update(this.newsDetails));
    } else {
      this.newsDetails.createdDate = moment(new Date());
      this.newsDetails.createdBy = 'temp';
      this.newsDetails.endDate = moment(new Date());
      this.subscribeToSaveResponse(this.newsDetailsService.create(this.newsDetails));
    }
  }

  splitNChars(txt, num) {
    const result = [];
    for (let i = 0; i < txt.length; i += num) {
      result.push(txt.substr(i, num));
    }
    return result;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<INewsDetails>>) {
    result.subscribe((res: HttpResponse<INewsDetails>) => this.onSaveSuccess(res), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess(result: HttpResponse<INewsDetails>) {
    this.newsDetails = result.body;
    if (this.newsDetails.id !== undefined) {
            this.ckeditorContent = '';
            this.newsDetailsBodies = [];
            this.newsDetails.newsDetailsBodies.forEach(newsDetailsBody => {
              this.ckeditorContent += newsDetailsBody.newsBody;
            });
            this.selectedItems = this.newsDetails.factoryMasters;
            this.isSaving = false;
            this.snotifyService.success('Save successfully!!!', '', toastConfig);
            this.router.navigate(['news-details/' + result.body.id + '/edit']);
      }
    // this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  trackFactoryMasterById(index: number, item: IFactoryMaster) {
    return item.id;
  }

  trackNewsMasterById(index: number, item: INewsMaster) {
    return item.id;
  }

  addMoreAttachment() {
    this.attachButtons.push(new NewsDetailsAttach());
  }

  getSelected(selectedVals: Array<any>, option: any) {
    if (selectedVals) {
      for (let i = 0; i < selectedVals.length; i++) {
        if (option.id === selectedVals[i].id) {
          return selectedVals[i];
        }
      }
    }
    return option;
  }

  displayAttachMethod() {
    if (this.displayAttach === true) {
      this.displayAttach = false;
    } else {
      this.displayAttach = true;
    }
  }

  // input to set search term for available list box from the outside
  @Input()
  set availableSearch(searchTerm: string) {
    this.searchTermAvailable = searchTerm;
    this.availableSearchInputControl.setValue(searchTerm);
  }
  // input to set search term for selected list box from the outside
  @Input()
  set selectedSearch(searchTerm: string) {
    this.searchTermSelected = searchTerm;
    this.selectedSearchInputControl.setValue(searchTerm);
  }

  // control value accessors
  _onChange = (_: any) => {};
  _onTouched = () => {};

  /**
   * Move all items from available to selected
   */
  moveAllItemsToSelected(): void {
    if (!this.availableItems.length) {
      return;
    }
    this.selectedItems = [...this.selectedItems, ...this.availableItems];
    this.availableItems = [];
    this.onItemsMoved.emit({
      available: this.availableItems,
      selected: this.selectedItems,
      movedItems: this.availableListBoxControl.value,
      from: 'available',
      to: 'selected'
    });
    this.availableListBoxControl.setValue([]);
    this.writeValue(this.getValues());
  }

  /**
   * Move all items from selected to available
   */
  moveAllItemsToAvailable(): void {
    if (!this.selectedItems.length) {
      return;
    }
    this.availableItems = [...this.availableItems, ...this.selectedItems];
    this.selectedItems = [];
    this.onItemsMoved.emit({
      available: this.availableItems,
      selected: this.selectedItems,
      movedItems: this.selectedListBoxControl.value,
      from: 'selected',
      to: 'available'
    });
    this.selectedListBoxControl.setValue([]);
    this.writeValue([]);
  }

  /**
   * Move marked items from available items to selected items
   */
  moveMarkedAvailableItemsToSelected(): void {
    // first move items to selected
    this.selectedItems = [
      ...this.selectedItems,
      ...intersectionwith(
        this.availableItems,
        this.availableListBoxControl.value,
        (item: IFactoryMaster, value: number) => item.id === value
      )
    ];
    // now filter available items to not include marked values
    this.availableItems = [
      ...differenceWith(this.availableItems, this.availableListBoxControl.value, (item: IFactoryMaster, value: number) => item.id === value)
    ];
    // clear marked available items and emit event
    this.onItemsMoved.emit({
      available: this.availableItems,
      selected: this.selectedItems,
      movedItems: this.availableListBoxControl.value,
      from: 'available',
      to: 'selected'
    });
    this.availableListBoxControl.setValue([]);
    this.availableSearchInputControl.setValue('');
    this.writeValue(this.getValues());
  }

  /**
   * Move marked items from selected items to available items
   */
  moveMarkedSelectedItemsToAvailable(): void {
    // first move items to available
    this.availableItems = [
      ...this.availableItems,
      ...intersectionwith(this.selectedItems, this.selectedListBoxControl.value, (item: IFactoryMaster, value: number) => item.id === value)
    ];
    // now filter available items to not include marked values
    this.selectedItems = [
      ...differenceWith(this.selectedItems, this.selectedListBoxControl.value, (item: IFactoryMaster, value: number) => item.id === value)
    ];
    // clear marked available items and emit event
    this.onItemsMoved.emit({
      available: this.availableItems,
      selected: this.selectedItems,
      movedItems: this.selectedListBoxControl.value,
      from: 'selected',
      to: 'available'
    });
    this.selectedListBoxControl.setValue([]);
    this.selectedSearchInputControl.setValue('');
    this.writeValue(this.getValues());
  }

  /**
   * Move single item from available to selected
   * @param item
   */
  moveAvailableItemToSelected(item: IFactoryMaster): void {
    this.availableItems = this.availableItems.filter((listItem: IFactoryMaster) => listItem.id !== item.id);
    this.selectedItems = [...this.selectedItems, item];
    this.onItemsMoved.emit({
      available: this.availableItems,
      selected: this.selectedItems,
      movedItems: [item.id],
      from: 'available',
      to: 'selected'
    });
    this.availableSearchInputControl.setValue('');
    this.availableListBoxControl.setValue([]);
    this.writeValue(this.getValues());
  }

  /**
   * Move single item from selected to available
   * @param item
   */
  moveSelectedItemToAvailable(item: IFactoryMaster): void {
    this.selectedItems = this.selectedItems.filter((listItem: IFactoryMaster) => listItem.id !== item.id);
    this.availableItems = [...this.availableItems, item];
    this.onItemsMoved.emit({
      available: this.availableItems,
      selected: this.selectedItems,
      movedItems: [item.id],
      from: 'selected',
      to: 'available'
    });
    this.selectedSearchInputControl.setValue('');
    this.selectedListBoxControl.setValue([]);
    this.writeValue(this.getValues());
  }

  /**
   * Utility method to get values from
   * selected items
   */
  private getValues(): number[] {
    return (this.selectedItems || []).map((item: IFactoryMaster) => item.id);
  }

  /**
   * Function to pass to ngFor to improve performance, tracks items
   * by the value field
   * @param index
   * @param item
   */
  trackByValue(index: number, item: {}): string {
    return item[this.valueField];
  }

  /* Methods from ControlValueAccessor interface, required for ngModel and formControlName - begin */
  writeValue(value: any): void {
    if (this.selectedItems && value && value.length > 0) {
      this.selectedItems = [
        ...this.selectedItems,
        ...intersectionwith(this.availableItems, value, (item: IFactoryMaster, val: number) => item.id === val)
      ];
      this.availableItems = [...differenceWith(this.availableItems, value, (item: IFactoryMaster, val: number) => item.id === val)];
    }
    this._onChange(value);
  }

  newJoining() {
    if (this.newsDetails.newsMaster.newsName === 'NEW JOINING') {
      this.isNewJoining = true;
      this.newsDetails.displayDays = 2;
      this.newsDetails.mailFlag = false;
      this.newsDetails.newsTitle = 'New Joining';
    } else {
      this.isNewJoining = false;
      this.newsDetails.displayDays = 1;
      this.newsDetails.newsTitle = '';
    }
  }
}
