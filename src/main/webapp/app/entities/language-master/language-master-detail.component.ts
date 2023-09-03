import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ILanguageMaster } from 'app/shared/model/language-master.model';

@Component({
  selector: 'jhi-language-master-detail',
  templateUrl: './language-master-detail.component.html'
})
export class LanguageMasterDetailComponent implements OnInit {
  languageMaster: ILanguageMaster;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ languageMaster }) => {
      this.languageMaster = languageMaster;
    });
  }

  previousState() {
    window.history.back();
  }
}
