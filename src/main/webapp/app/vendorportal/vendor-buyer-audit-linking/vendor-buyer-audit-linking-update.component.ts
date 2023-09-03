import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService, JhiEventManager } from 'ng-jhipster';
import { IVendorBuyerAuditLinking } from 'app/shared/model/vendor-buyer-audit-linking.model';
import { VendorBuyerAuditLinkingService } from './vendor-buyer-audit-linking.service';
import { IVendorMaster } from 'app/shared/model/vendor-master.model';
import { IBuyerMaster } from 'app/shared/model/buyer-master.model';
import { IFactoryMaster } from 'app/shared/model/factory-master.model';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { IItemsMovedEvent } from 'app/shared/model/items-moved-event';
import { intersectionwith } from 'lodash/intersectionWith';
import { differenceWith } from 'lodash/differenceWith';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { VendorMasterSearchComponent } from './vendor-master-search.component';
import { Master } from 'app/shared/model/master.modal';

@Component({
  selector: 'jhi-vendor-buyer-audit-linking-update',
  templateUrl: './vendor-buyer-audit-linking-update.component.html'
})
export class VendorBuyerAuditLinkingUpdateComponent implements OnInit {
  vendorBuyerAuditLinking: IVendorBuyerAuditLinking;
  isSaving: boolean;

  vendormasters: IVendorMaster[];

  buyermasters: IBuyerMaster[];
  createdDate: string;
  lastUpdatedDate: string;
  protected ngbModalRef: NgbModalRef;

  // private variables to manage class
  searchTermAvailable = '';
  searchTermSelected = '';
  availableItems: Array<IBuyerMaster> = [];
  selectedItems: Array<IBuyerMaster> = [];
  listBoxForm: FormGroup;
  availableListBoxControl: FormControl = new FormControl();
  selectedListBoxControl: FormControl = new FormControl();
  availableSearchInputControl: FormControl = new FormControl();
  selectedSearchInputControl: FormControl = new FormControl();
  searchText = '';

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

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected vendorBuyerAuditLinkingService: VendorBuyerAuditLinkingService,
    public fb: FormBuilder,
    protected activatedRoute: ActivatedRoute,
    protected modalService: NgbModal,
    private eventManager: JhiEventManager
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
    this.registerChangeInSelectedVendorMasters();
    this.availableItems = [];
    this.availableText = 'Available Factories';
    this.selectedText = 'Selected Factories';
    this.activatedRoute.data.subscribe(({ vendorBuyerAuditLinking }) => {
      this.vendorBuyerAuditLinking = vendorBuyerAuditLinking;
      if (this.vendorBuyerAuditLinking && this.vendorBuyerAuditLinking.buyerMasters) {
        this.selectedItems = this.vendorBuyerAuditLinking.buyerMasters;
      }
      this.createdDate =
        this.vendorBuyerAuditLinking.createdDate != null ? this.vendorBuyerAuditLinking.createdDate.format(DATE_TIME_FORMAT) : null;
      this.lastUpdatedDate =
        this.vendorBuyerAuditLinking.lastUpdatedDate != null ? this.vendorBuyerAuditLinking.lastUpdatedDate.format(DATE_TIME_FORMAT) : null;
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    if (this.selectedItems && this.selectedItems.length > 0) {
      this.isSaving = true;
      this.vendorBuyerAuditLinking.createdDate = this.createdDate != null ? moment(this.createdDate, DATE_TIME_FORMAT) : null;
      this.vendorBuyerAuditLinking.lastUpdatedDate = this.lastUpdatedDate != null ? moment(this.lastUpdatedDate, DATE_TIME_FORMAT) : null;
      this.vendorBuyerAuditLinking.buyerMasters = this.selectedItems;
      this.subscribeToSaveResponse(this.vendorBuyerAuditLinkingService.create(this.vendorBuyerAuditLinking));
    } else {
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IVendorBuyerAuditLinking>>) {
    result.subscribe((res: HttpResponse<IVendorBuyerAuditLinking>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  trackVendorMasterById(index: number, item: IVendorMaster) {
    return item.id;
  }

  trackBuyerMasterById(index: number, item: IBuyerMaster) {
    return item.id;
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
      ...intersectionwith(this.availableItems, this.availableListBoxControl.value, (item, value) => item.buyerCode === value)
    ];
    // now filter available items to not include marked values
    this.availableItems = [
      ...differenceWith(this.availableItems, this.availableListBoxControl.value, (item, value) => item.buyerCode === value)
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
      ...intersectionwith(
        this.selectedItems,
        this.selectedListBoxControl.value,
        (item: IBuyerMaster, value: string) => item.buyerCode === value
      )
    ];
    // now filter available items to not include marked values
    this.selectedItems = [
      ...differenceWith(
        this.selectedItems,
        this.selectedListBoxControl.value,
        (item: IBuyerMaster, value: string) => item.buyerCode === value
      )
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
  moveAvailableItemToSelected(item: IBuyerMaster): void {
    this.availableItems = this.availableItems.filter((listItem: IBuyerMaster) => listItem.buyerCode !== item.buyerCode);
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
  moveSelectedItemToAvailable(item: IBuyerMaster): void {
    this.selectedItems = this.selectedItems.filter((listItem: IBuyerMaster) => listItem.buyerCode !== item.buyerCode);
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
  private getValues(): string[] {
    return (this.selectedItems || []).map((item: IBuyerMaster) => item.buyerCode);
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
        ...intersectionwith(this.availableItems, value, (item: IBuyerMaster, val: string) => item.buyerCode === val)
      ];
      this.availableItems = [...differenceWith(this.availableItems, value, (item: IBuyerMaster, val: string) => item.buyerCode === val)];
    }
    this._onChange(value);
  }

  searchContent() {
    this.ngbModalRef = this.modalService.open(VendorMasterSearchComponent as Component, {
      size: 'lg',
      backdrop: 'static',
      windowClass: 'xlModal'
    });
  }

  registerChangeInSelectedVendorMasters() {
    this.eventManager.subscribe('selectedVendorMasterBuyerLink', message => {
      const vendorMasters = message.content;
      this.vendorBuyerAuditLinking.vendorMaster = vendorMasters;
    });
  }

  searchBuyer() {
    if (this.searchText && this.searchText.length > 0) {
      const masterSearch = new Master();
      masterSearch.desc = this.searchText;
      this.vendorBuyerAuditLinkingService.searchBuyer(masterSearch).subscribe(buyers => {
        this.availableItems = buyers.body;
      });
    }
  }
}
