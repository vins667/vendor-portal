import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { TdsComputationReportService } from './tds-computation-report.service';
import { FormBuilder, FormGroup } from '@angular/forms';
import { IFactoryMaster } from 'app/shared/model/factory-master.model';
import { FactoryMasterService } from 'app/entities/factory-master';
import { IMasterBean } from 'app/shared/model/master-bean.model';
import { ITdsYearMaster } from 'app/shared/model/tds-year-master.model';
import { TdsYearMasterService } from 'app/entities/tds-year-master';
import { IMasterSearch, MasterSearch } from 'app/shared/model/master-search.model';
import * as FileSaver from 'file-saver';
@Component({
  selector: 'jhi-tds-computation-report-salary',
  templateUrl: './tds-computation-report.component.html',
  encapsulation: ViewEncapsulation.None
})
export class TdsComputationReportComponent implements OnInit {
  isDownload = false;
  listBoxForm: FormGroup;
  groups: IMasterBean[];
  factoryMasters: IFactoryMaster[];
  tdsYearMasters: ITdsYearMaster[];
  masterSearch: IMasterSearch;
  constructor(
    protected tdsComputationReportService: TdsComputationReportService,
    protected factoryMasterService: FactoryMasterService,
    protected tdsYearMasterService: TdsYearMasterService,
    public fb: FormBuilder
  ) {
    this.listBoxForm = this.fb.group({
      financialYear: [],
      groupCode: [''],
      factory: [''],
      grossSalary: 62000,
      reportType: 'PDF'
    });
  }

  ngOnInit(): void {
    this.masterSearch = new MasterSearch();
    this.factoryMasterService.group().subscribe(groups => {
      this.groups = groups.body;
    });
    this.tdsYearMasterService.query().subscribe(tdsYearMasters => {
      this.tdsYearMasters = tdsYearMasters.body;
      if (this.tdsYearMasters) {
        this.listBoxForm.controls['financialYear'].setValue(this.tdsYearMasters[0].code);
      }
    });
  }

  changeByDivision(): void {
    this.listBoxForm.controls['factory'].setValue(undefined);
    if (this.listBoxForm.controls['groupCode'].value !== undefined && this.listBoxForm.controls['groupCode'].value.length > 0) {
      this.factoryMasterService.findByGroup(this.listBoxForm.controls['groupCode'].value).subscribe(factories => {
        this.factoryMasters = factories.body;
      });
    } else {
      this.factoryMasterService.query().subscribe(factories => {
        this.factoryMasters = factories.body;
      });
    }
  }

  generateReport() {
    this.isDownload = true;
    this.masterSearch.parameters1 =
      this.listBoxForm.controls['groupCode'].value !== undefined && this.listBoxForm.controls['groupCode'].value.length > 0
        ? this.listBoxForm.controls['groupCode'].value.map(groupCode => groupCode.code)
        : undefined;
    this.masterSearch.parameters2 =
      this.listBoxForm.controls['factory'].value !== undefined && this.listBoxForm.controls['factory'].value.length > 0
        ? this.listBoxForm.controls['factory'].value.map(factory => factory.factoryCode)
        : undefined;
    this.masterSearch.paraString1 = this.listBoxForm.controls['financialYear'].value;
    this.masterSearch.paraNumber1 = this.listBoxForm.controls['grossSalary'].value;
    this.masterSearch.reportType = this.listBoxForm.controls['reportType'].value;
    this.isDownload = true;
    this.tdsComputationReportService.downloadPdf(this.masterSearch).subscribe(
      res => {
        FileSaver.saveAs(res, 'tds_computation.xlsx');
        this.isDownload = false;
      },
      res => {
        this.isDownload = false;
      }
    );
  }
}
