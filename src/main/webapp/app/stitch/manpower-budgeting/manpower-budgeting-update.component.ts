import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { IManpowerBudgeting, ManpowerBudgeting } from './manpower-budgeting.model';
import { ManpowerBudgetingService } from './manpower-budgeting.service';
import { IFactoryMaster } from 'app/shared/model/factory-master.model';
import { FactoryMasterService } from 'app/entities/factory-master';
import { DepartmentMasterService } from 'app/entities/department-master';
import { ManpowerSearch } from 'app/shared/model/manpower-search.model';
import { IManpowerBean } from 'app/shared/model/manpower-bean.model';
import { IResources } from 'app/shared/model/resources.model';
import { IManpowerTypeMaster } from 'app/shared/model/manpower-type-master.model';
import { Master } from 'app/shared/model/master.modal';
import { DateTimeAdapter, OWL_DATE_TIME_FORMATS, OWL_DATE_TIME_LOCALE } from 'ng-pick-datetime';
import { MomentDateTimeAdapter } from 'ng-pick-datetime-moment';
import { SnotifyPosition, SnotifyService } from 'ng-snotify';
import { toastConfig } from 'app/core/toast/toast-config';
export const MY_MOMENT_FORMATS = {
  parseInput: 'DD-MM-YYYY LT',
  fullPickerInput: 'DD-MM-YYYY LT',
  datePickerInput: 'DD-MM-YYYY',
  timePickerInput: 'HH:mm',
  monthYearLabel: 'MMM YYYY',
  dateA11yLabel: 'LL',
  monthYearA11yLabel: 'MMMM YYYY'
};

@Component({
  selector: 'jhi-manpower-budgeting-update',
  templateUrl: './manpower-budgeting-update.component.html',
  providers: [
    // `MomentDateTimeAdapter` and `OWL_MOMENT_DATE_TIME_FORMATS` can be automatically provided by importing
    // `OwlMomentDateTimeModule` in your applications root module. We provide it at the component level
    // here, due to limitations of our example generation script.
    { provide: DateTimeAdapter, useClass: MomentDateTimeAdapter, deps: [OWL_DATE_TIME_LOCALE] },
    { provide: OWL_DATE_TIME_FORMATS, useValue: MY_MOMENT_FORMATS }
  ]
})
export class ManpowerBudgetingUpdateComponent implements OnInit {
  isSaving = false;
  isProcess = false;
  factories?: IFactoryMaster[] = [];
  departments?: IManpowerTypeMaster[] = [];
  resources: IResources[] = [];
  manpowerBeans?: IManpowerBean[] = [];
  employeeCountTotal = 0;
  balanceCountTotal = 0;

  editForm = this.fb.group({
    factoryCode: [null, [Validators.required, Validators.maxLength(10)]],
    factoryName: [null, [Validators.maxLength(100)]],
    nowFactoryName: [null, [Validators.maxLength(10)]],
    departmentCode: [null, [Validators.required, Validators.maxLength(10)]],
    departmentName: [null, [Validators.maxLength(100)]],
    type: [null, [Validators.required, Validators.maxLength(10)]],
    dateFrom: []
  });

  constructor(
    protected manpowerBudgetingService: ManpowerBudgetingService,
    protected factoryMasterService: FactoryMasterService,
    protected activatedRoute: ActivatedRoute,
    protected snotifyService: SnotifyService,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.editForm.get(['type']).setValue('REGULAR');
    this.factoryMasterService.now().subscribe(factories => {
      this.factories = factories.body;
    });

    this.manpowerBudgetingService.types().subscribe(departments => {
      this.departments = departments.body;
    });
    this.activatedRoute.data.subscribe(({ manpowerBudgeting }) => {
      this.updateForm(manpowerBudgeting);
    });
  }

  previousState(): void {
    window.history.back();
  }

  clear(): void {
    this.editForm.get(['factoryCode']).setValue(null);
    this.editForm.get(['factoryName']).setValue(null);
    this.editForm.get(['nowFactoryName']).setValue(null);
    this.editForm.get(['departmentCode']).setValue(null);
    this.editForm.get(['departmentName']).setValue(null);
    this.editForm.get(['type']).setValue('REGULAR');
    this.editForm.get(['dateFrom']).setValue(undefined);
    this.manpowerBeans = [];
    this.resources = [];
  }

  save(): void {
    if (
      this.editForm.get(['factoryCode']).value &&
      this.editForm.get(['departmentCode']).value &&
      this.editForm.get(['type']).value &&
      this.editForm.get(['dateFrom']).value &&
      this.manpowerBeans.length > 0
    ) {
      this.isSaving = true;
      const manpowerBudgeting = this.createFromForm();
      this.snotifyService.confirm('Are you sure to save?', 'Confirm', {
        timeout: 25000,
        showProgressBar: false,
        closeOnClick: false,
        pauseOnHover: true,
        position: SnotifyPosition.centerTop,
        buttons: [
          { text: 'Yes', action: toast => this.saveTo(toast, manpowerBudgeting), bold: false },
          { text: 'No', action: toast => this.snotifyService.remove(toast.id) }
        ]
      });
    }
  }

  saveTo(toast: any, manpowerBudgeting: IManpowerBudgeting): void {
    this.snotifyService.remove(toast.id);
    this.isProcess = true;
    this.subscribeToSaveResponse(this.manpowerBudgetingService.create(manpowerBudgeting));
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IManpowerBudgeting>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess(): void {
    this.isProcess = false;
    this.snotifyService.success('Save successfully!', '', toastConfig);
  }

  protected onSaveError(): void {
    this.isProcess = false;
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(manpowerBudgeting: IManpowerBudgeting): void {
    this.editForm.patchValue({
      factoryCode: manpowerBudgeting.factoryCode,
      factoryName: manpowerBudgeting.factoryName,
      nowFactoryName: manpowerBudgeting.nowFactoryName,
      departmentCode: manpowerBudgeting.departmentCode,
      departmentName: manpowerBudgeting.departmentName,
      type: manpowerBudgeting.type,
      dateFrom: manpowerBudgeting.dateFrom
    });
  }

  protected createFromForm(): IManpowerBudgeting {
    return {
      ...new ManpowerBudgeting(),
      factoryCode: this.editForm.get(['factoryCode'])!.value,
      factoryName: this.editForm.get(['factoryName'])!.value,
      nowFactoryName: this.editForm.get(['nowFactoryName'])!.value,
      departmentCode: this.editForm.get(['departmentCode'])!.value,
      departmentName: this.editForm.get(['departmentName'])!.value,
      type: this.editForm.get(['type'])!.value,
      dateFrom: this.editForm.get(['dateFrom'])!.value,
      manpowerBeans: this.manpowerBeans
    };
  }

  setFactoryDetail(): void {
    if (this.editForm.get(['factoryCode'])!.value) {
      this.factories.forEach(factory => {
        if (this.editForm.get(['factoryCode'])!.value === factory.factoryCode) {
          this.editForm.get(['factoryName'])!.setValue(factory.factoryCode);
          this.editForm.get(['nowFactoryName'])!.setValue(factory.nowFactoryCode);
        }
      });
    }
    this.fetchResources();
    this.fetchDetails();
  }

  setDepartmentDetail(): void {
    if (this.editForm.get(['departmentCode'])!.value) {
      this.departments.forEach(department => {
        if (department.code === this.editForm.get(['departmentCode'])!.value) {
          this.editForm.get(['departmentName'])!.setValue(department.description);
        }
      });
    }
    this.fetchResources();
    this.fetchDetails();
  }

  fetchResources(): void {
    if (this.editForm.get(['factoryCode'])!.value && this.editForm.get(['departmentCode'])!.value) {
      this.factories.forEach(factory => {
        if (this.editForm.get(['factoryCode'])!.value === factory.factoryCode) {
          const master = new Master();
          master.code = this.editForm.get(['departmentCode']).value;
          master.plantCode = factory.nowFactoryCode;
          this.manpowerBudgetingService.resourcesByPlantCode(master).subscribe(resources => {
            this.resources = resources.body;
          });
        }
      });
    }
  }

  fetchDetails(): void {
    if (
      this.editForm.get(['factoryCode'])!.value &&
      this.editForm.get(['departmentCode'])!.value &&
      this.editForm.get(['dateFrom'])!.value &&
      this.editForm.get(['type'])!.value
    ) {
      const search = new ManpowerSearch();
      search.factoryCode = this.editForm.get(['factoryCode'])!.value;
      search.deptCode = this.editForm.get(['departmentCode'])!.value;
      search.dateFrom = this.editForm.get(['dateFrom'])!.value;
      search.type = this.editForm.get(['type'])!.value;
      search.resourcesBeans = this.resources;
      this.isProcess = true;
      this.manpowerBudgetingService.fetch(search).subscribe(
        manpowers => {
          this.manpowerBeans = manpowers.body;
          let ctr = 0;
          this.manpowerBeans.forEach(manpowersTemp => {
            this.allocateResource(manpowersTemp, 0);
            ++ctr;
          });
          if (this.manpowerBeans.length === ctr) {
            let employeeCountTotal = 0;
            let balanceCountTotal = 0;
            let dtctr = 0;
            this.manpowerBeans.forEach(manpowersTemp => {
              employeeCountTotal += manpowersTemp.employeeCount;
              balanceCountTotal += manpowersTemp.balanceCount;
              ++dtctr;
            });
            if (this.manpowerBeans.length === dtctr) {
              this.employeeCountTotal = employeeCountTotal;
              this.balanceCountTotal = balanceCountTotal;
              this.resourceCalculation();
            }
          }
          this.isProcess = false;
        },
        () => {
          this.isProcess = true;
        }
      );
    }
  }

  totalRefresh(manpowerBean: IManpowerBean): void {
    manpowerBean.balanceCount = Number(manpowerBean.employeeCount);
    manpowerBean.resources.forEach(resource => {
      resource.resourceAllocate = 0;
    });
  }

  resourceCalculation(): void {
    this.resources.forEach(resource => {
      let totatByresource = 0;
      let ctr = 0;
      this.manpowerBeans.forEach(manpowerBean => {
        let resourceCtr = 0;
        manpowerBean.resources.forEach(resourceManpower => {
          if (resourceManpower.code === resource.code) {
            if (resourceManpower.resourceAllocate) {
              totatByresource += Number(resourceManpower.resourceAllocate);
            }
          }
          ++resourceCtr;
        });
        if (resourceCtr === manpowerBean.resources.length) {
          ++ctr;
        }
      });
      if (ctr === this.manpowerBeans.length) {
        resource.resourceAllocate = Number(totatByresource);
      }
    });
  }

  allocateResource(manpowerBean: IManpowerBean, subIndex: number): void {
    let totalResource = 0;
    let count = 0;
    manpowerBean.resources.forEach((resource, index) => {
      ++count;
      totalResource += Number(resource.resourceAllocate);
    });
    if (count === manpowerBean.resources.length) {
      if (totalResource > manpowerBean.employeeCount) {
        manpowerBean.balanceCount =
          Number(manpowerBean.employeeCount) - (Number(totalResource) - Number(manpowerBean.resources[subIndex].resourceAllocate));
        manpowerBean.resources[subIndex].resourceAllocate = 0;
      } else {
        manpowerBean.balanceCount = Number(manpowerBean.employeeCount) - Number(totalResource);
      }
      this.resourceCalculation();
    }
  }
}
