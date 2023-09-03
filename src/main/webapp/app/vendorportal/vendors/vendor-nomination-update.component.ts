import { Component, Input, OnInit, Output, EventEmitter, ViewChild } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { IBuyerMaster } from 'app/shared/model/buyer-master.model';
import { JhiAlertService } from 'ng-jhipster';
import { BuyerMasterService } from 'app/vendorportal/buyer-master';
import { FormGroup, FormControl, FormBuilder } from '@angular/forms';
import { SnotifyService } from 'ng-snotify';
import { toastConfig } from 'app/core/toast/toast-config';
import { JhiEventManager } from 'ng-jhipster';
import { IItemsMovedEvent } from 'app/shared/model/items-moved-event';
import { intersectionwith } from 'lodash/intersectionWith';
import { differenceWith } from 'lodash/differenceWith';
import { IVendorNomination, VendorNomination } from 'app/shared/model/vendor-nomination.model';
import { debounceTime, distinctUntilChanged } from 'rxjs/operators';
@Component({
  selector: 'jhi-vendor-nomination-update',
  templateUrl: './vendor-nomination-update.component.html'
})
export class VendorNominationUpdateComponent implements OnInit {
  @Input() vendorNomination: VendorNomination;
  @Input() vendorNominationTransaction: VendorNomination;
  @Input() approvalStatus: string;
  isSaving: boolean;
  buyermasters: IBuyerMaster[];
  createdDate: string;
  lastUpdatedDate: string;
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
  // field to use for value of option
  @Input() valueField = 'id';
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

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected buyerMasterService: BuyerMasterService,
    protected activatedRoute: ActivatedRoute,
    public fb: FormBuilder,
    private snotifyService: SnotifyService,
    protected eventManager: JhiEventManager
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
    this.availableItems = [];
    this.availableText = 'Available Buyers';
    this.selectedText = 'Selected Buyers';
    if (this.vendorNominationTransaction) {
      this.selectedItems = this.vendorNominationTransaction.buyerMasters;
    } else if (this.vendorNomination) {
      this.selectedItems = this.vendorNomination.buyerMasters;
    }
    this.buyerMasterService.query().subscribe(resbuyer => {
      this.buyermasters = resbuyer.body;
      this.availableItems = this.buyermasters;
      if (this.selectedItems && this.selectedItems.length > 0) {
        for (let i = this.availableItems.length - 1; i >= 0; i--) {
          for (let j = 0; j < this.selectedItems.length; j++) {
            if (this.availableItems[i] && this.availableItems[i].id === this.selectedItems[j].id) {
              this.availableItems.splice(i, 1);
            }
          }
        }
      }
    });
    //  This is code of dual box selection..............................
    this.availableListBoxControl.valueChanges.subscribe((items: Array<{}>) => this.onAvailableItemSelected.emit(items));
    this.selectedListBoxControl.valueChanges.subscribe((items: Array<{}>) => this.onSelectedItemsSelected.emit(items));
    this.availableSearchInputControl.valueChanges
      .pipe(
        debounceTime(this.debounceTime),
        distinctUntilChanged()
      )
      .subscribe((search: string) => (this.searchTermAvailable = search));
    this.selectedSearchInputControl.valueChanges
      .pipe(
        debounceTime(this.debounceTime),
        distinctUntilChanged()
      )
      .subscribe((search: string) => (this.searchTermSelected = search));
  }

  previousState() {
    window.history.back();
  }

  save() {}

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IVendorNomination>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.snotifyService.success('Nomination Details save successfully', '', toastConfig);
    this.eventManager.broadcast({ name: 'tabModification', content: '' });
    // this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  trackBuyerMasterById(item: IBuyerMaster) {
    return item.id;
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
      ...intersectionwith(this.availableItems, this.availableListBoxControl.value, (item: IBuyerMaster, value: number) => item.id === value)
    ];
    // now filter available items to not include marked values
    this.availableItems = [
      ...differenceWith(this.availableItems, this.availableListBoxControl.value, (item: IBuyerMaster, value: number) => item.id === value)
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
      ...intersectionwith(this.selectedItems, this.selectedListBoxControl.value, (item: IBuyerMaster, value: number) => item.id === value)
    ];
    // now filter available items to not include marked values
    this.selectedItems = [
      ...differenceWith(this.selectedItems, this.selectedListBoxControl.value, (item: IBuyerMaster, value: number) => item.id === value)
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
    this.availableItems = this.availableItems.filter((listItem: IBuyerMaster) => listItem.id !== item.id);
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
    this.selectedItems = this.selectedItems.filter((listItem: IBuyerMaster) => listItem.id !== item.id);
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
    return (this.selectedItems || []).map((item: IBuyerMaster) => item.id);
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
        ...intersectionwith(this.availableItems, value, (item: IBuyerMaster, val: number) => item.id === val)
      ];
      this.availableItems = [...differenceWith(this.availableItems, value, (item: IBuyerMaster, val: number) => item.id === val)];
    }
    this._onChange(value);
  }
}
