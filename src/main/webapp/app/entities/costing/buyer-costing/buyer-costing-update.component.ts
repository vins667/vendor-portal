import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IBuyerCosting, BuyerCosting } from 'app/shared/model/buyer-costing.model';
import { BuyerCostingService } from './buyer-costing.service';
import { IBuyerCostingDetails } from 'app/shared/model/buyer-costing-details.model';
import { map, debounceTime, distinctUntilChanged } from 'rxjs/operators';
import { CostingGroupDetailsService } from '../costing-group-details/costing-group-details.service';
import { IUserGenericGroup } from 'app/shared/model/user-generic-group.modal';
import { IBuyerCostingSubDetails, BuyerCostingSubDetails } from 'app/shared/model/buyer-costing-sub-details.model';

@Component({
  selector: 'jhi-buyer-costing-update',
  templateUrl: './buyer-costing-update.component.html'
})
export class BuyerCostingUpdateComponent implements OnInit {
  isSaving: boolean;
  buyerCostingDetails: IBuyerCostingDetails[];
  userGenericGroups: IUserGenericGroup[];
  editForm = this.fb.group({
    id: [],
    orderQty: [],
    smv: [],
    subTotal: [],
    margin: [],
    sellingPrice1: [],
    sellingPrice2: [],
    wastage: [],
    currency: [],
    convRate: []
  });

  formatter = (result: any) => result.longdescription;

  constructor(
    protected buyerCostingService: BuyerCostingService,
    protected costingGroupDetailsService: CostingGroupDetailsService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ buyerCosting }) => {
      this.updateForm(buyerCosting);
    });
    this.editForm.get(['wastage']).setValue(60);
    this.editForm.get(['convRate']).setValue(60);
    this.loadDetails();
  }

  loadDetails() {
    this.buyerCostingService.queryGroupMaster().subscribe(res => {
      this.buyerCostingDetails = res.body;
      this.buyerCostingDetails[0].expend = true;
      this.buyerCostingDetails.forEach(buyerCostingDetail => {
        buyerCostingDetail.buyerCostingSubDetails.forEach(buyerCostingsubDetail => {
          buyerCostingsubDetail.itemType = null;
        });
      });
    });
  }

  updateForm(buyerCosting: IBuyerCosting) {
    this.editForm.patchValue({
      id: buyerCosting.id,
      orderQty: buyerCosting.orderQty,
      smv: buyerCosting.smv,
      subTotal: buyerCosting.subTotal,
      margin: buyerCosting.margin,
      sellingPrice1: buyerCosting.sellingPrice1,
      sellingPrice2: buyerCosting.sellingPrice2,
      wastage: buyerCosting.wastage,
      currency: buyerCosting.currency,
      convRate: buyerCosting.convRate
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const buyerCosting = this.createFromForm();
    if (buyerCosting.id !== undefined) {
      this.subscribeToSaveResponse(this.buyerCostingService.update(buyerCosting));
    } else {
      this.subscribeToSaveResponse(this.buyerCostingService.create(buyerCosting));
    }
  }

  private createFromForm(): IBuyerCosting {
    return {
      ...new BuyerCosting(),
      id: this.editForm.get(['id']).value,
      orderQty: this.editForm.get(['orderQty']).value,
      smv: this.editForm.get(['smv']).value,
      subTotal: this.editForm.get(['subTotal']).value,
      margin: this.editForm.get(['margin']).value,
      sellingPrice1: this.editForm.get(['sellingPrice1']).value,
      sellingPrice2: this.editForm.get(['sellingPrice2']).value,
      wastage: this.editForm.get(['wastage']).value,
      currency: this.editForm.get(['currency']).value,
      convRate: this.editForm.get(['convRate']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBuyerCosting>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }

  changeExpend(buyerCostingDetail, flag?: boolean) {
    buyerCostingDetail.expend = flag;
  }

  getDecription(buyerCostingSubDetail: IBuyerCostingSubDetails) {
    buyerCostingSubDetail.costingGroupDetails.forEach(costingGroupDetail => {
      if (buyerCostingSubDetail.itemType === costingGroupDetail.code) {
        this.buyerCostingService.queryUserGenericGroup(costingGroupDetail.ugcType).subscribe(res => {
          this.userGenericGroups = res.body;
        });
      }
    });
    if (buyerCostingSubDetail && buyerCostingSubDetail.itemType) {
      this.buyerCostingService.queryItemTypeCode(buyerCostingSubDetail.itemType).subscribe(res => {
        buyerCostingSubDetail.umo = res.body.defaultsecondaryuomcode;
      });
    }
  }

  search = (text$: Observable<string>) =>
    text$.pipe(
      debounceTime(200),
      distinctUntilChanged(),
      map(term =>
        term.length < 2
          ? []
          : this.userGenericGroups.filter(v => v.longdescription.toUpperCase().indexOf(term.toUpperCase()) > -1).slice(0, 10)
      )
    );

  addRow(buyerCostingDetail: IBuyerCostingDetails) {
    const buyerCostingSubDetail = new BuyerCostingSubDetails();
    buyerCostingDetail.buyerCostingSubDetails.forEach((buyerCosting: IBuyerCostingSubDetails) => {
      buyerCostingSubDetail.itemType = null;
      buyerCostingSubDetail.costingGroupDetails = buyerCosting.costingGroupDetails;
      buyerCostingSubDetail.unitOfMeasures = buyerCosting.unitOfMeasures;
    });
    buyerCostingDetail.buyerCostingSubDetails.push(buyerCostingSubDetail);
  }

  removeRow(buyerCostingDetail: IBuyerCostingDetails, h: any): void {
    buyerCostingDetail.buyerCostingSubDetails.splice(h, 1);
  }

  amountCalculate() {
    if (this.buyerCostingDetails) {
      this.buyerCostingDetails.forEach(buyerCostingDetail => {
        let totalVal = 0;
        buyerCostingDetail.buyerCostingSubDetails.forEach(buyerCostingsubDetail => {
          if (
            buyerCostingsubDetail.avg &&
            buyerCostingsubDetail.shrinkage &&
            buyerCostingsubDetail.rate &&
            buyerCostingsubDetail.processrate
          ) {
            buyerCostingsubDetail.amount =
              (buyerCostingsubDetail.avg / (1 - buyerCostingsubDetail.shrinkage)) * buyerCostingsubDetail.rate +
              buyerCostingsubDetail.avg * buyerCostingsubDetail.processrate;
          }
          if (buyerCostingsubDetail.amount) {
            totalVal += buyerCostingsubDetail.amount;
            buyerCostingDetail.totalAmount = totalVal;
          }
          if (this.editForm.get(['wastage']).value && buyerCostingsubDetail.amount) {
            buyerCostingsubDetail.amtwast = buyerCostingsubDetail.amount * (1 + this.editForm.get(['wastage']).value);
            totalVal += buyerCostingsubDetail.amtwast;
            buyerCostingDetail.totalAmtwast = totalVal;
          }
          if (this.editForm.get(['convRate']).value && buyerCostingsubDetail.amount) {
            buyerCostingsubDetail.curramt = buyerCostingsubDetail.amount / this.editForm.get(['convRate']).value;
            totalVal += buyerCostingsubDetail.curramt;
            buyerCostingDetail.totalCurrAmt = totalVal;
          }
        });
      });
    }
  }
}
