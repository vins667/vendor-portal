import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IDocumentMaster } from 'app/shared/model/document-master.model';

@Component({
  selector: 'jhi-document-master-detail',
  templateUrl: './document-master-detail.component.html'
})
export class DocumentMasterDetailComponent implements OnInit {
  documentMaster: IDocumentMaster;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ documentMaster }) => {
      this.documentMaster = documentMaster;
    });
  }

  previousState() {
    window.history.back();
  }
}
