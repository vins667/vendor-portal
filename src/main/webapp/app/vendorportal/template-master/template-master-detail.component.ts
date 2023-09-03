import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ITemplateMaster } from 'app/shared/model/template-master.model';

@Component({
  selector: 'jhi-template-master-detail',
  templateUrl: './template-master-detail.component.html'
})
export class TemplateMasterDetailComponent implements OnInit {
  templateMaster: ITemplateMaster;
  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ templateMaster }) => {
      this.templateMaster = templateMaster;
      if (this.templateMaster.templateDetails && this.templateMaster.templateDetails.length > 0) {
        this.templateMaster.templateDetails.forEach(templateDetail => {
          if (templateDetail.templateDetailsBreakups) {
            let values = '';
            templateDetail.templateDetailsBreakups.forEach((breakUp, i) => {
              if (i === 0) {
                values = breakUp.description;
              } else {
                values += ', ' + breakUp.description;
              }
            });
            templateDetail.fieldValue = values;
          }
        });
      }
    });
  }

  previousState() {
    window.history.back();
  }
}
