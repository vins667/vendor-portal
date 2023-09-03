import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { IVendorDocument, VendorDocument } from 'app/shared/model/vendor-document.model';
import { IDocumentMaster } from 'app/shared/model/document-master.model';
import { DocumentMasterService } from 'app/vendorportal/document-master';
import { FormGroup } from '@angular/forms';
import * as FileSaver from 'file-saver';
import { SnotifyService } from 'ng-snotify';
import { toastConfig } from 'app/core/toast/toast-config';
import { JhiEventManager } from 'ng-jhipster';

@Component({
  selector: 'jhi-vendor-document-update',
  templateUrl: './vendor-document-update.component.html'
})
export class VendorDocumentUpdateComponent implements OnInit {
  @Input() vendorDocuments: IVendorDocument[];
  @Input() approvalStatus: string;
  @Input() countryCode: string;
  vendorDocumentsTemp: IVendorDocument[];
  isSaving: boolean;
  createdDate: string;
  lastUpdatedDate: string;
  documentMasters: IDocumentMaster[];
  uploadForm: FormGroup;
  myFiles: File[] = [];
  files: File[];
  isDownloading = false;
  constructor(
    protected documentMasterService: DocumentMasterService,
    protected activatedRoute: ActivatedRoute,
    private snotifyService: SnotifyService,
    protected eventManager: JhiEventManager
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.vendorDocumentsTemp = [];
    if (this.vendorDocuments && this.countryCode) {
      this.documentMasterService.queryCountry(this.countryCode).subscribe((res: HttpResponse<IDocumentMaster[]>) => {
        this.documentMasters = res.body;
        this.documentMasters.forEach(documentMaster => {
          let exist = false;
          let selectVal: any;
          let ctr = 0;
          this.vendorDocuments.forEach(vendorDocument => {
            ++ctr;
            if (documentMaster.id === vendorDocument.documentId) {
              exist = true;
              selectVal = vendorDocument;
            }
          });
          if (ctr === this.vendorDocuments.length && exist === false) {
            this.vendorDocumentsTemp.push(new VendorDocument());
          } else if (ctr === this.vendorDocuments.length) {
            this.vendorDocumentsTemp.push(selectVal);
          }
        });
      });
    } else {
      if (this.countryCode) {
        this.documentMasterService.queryCountry(this.countryCode).subscribe((res: HttpResponse<IDocumentMaster[]>) => {
          this.documentMasters = res.body;
          this.documentMasters.forEach(documentMaster => {
            this.vendorDocumentsTemp.push(new VendorDocument());
          });
        });
      }
    }
  }
  onFileSelect(event, index, documentId) {
    if (event.target.files.length > 0) {
      if (this.myFiles[0] !== event.target.files[0]) {
        this.vendorDocumentsTemp[index].documentId = documentId;
        if (index === 0) {
          this.myFiles[index] = event.target.files[0];
        } else {
          this.myFiles[index] = event.target.files[0];
        }
      }
    }
  }
  previousState() {
    window.history.back();
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IVendorDocument>>) {
    result.subscribe((res: HttpResponse<IVendorDocument>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.snotifyService.success('Document save successfully', '', toastConfig);
    this.eventManager.broadcast({ name: 'tabModification', content: '' });
  }

  protected onSaveError() {
    this.isSaving = false;
  }

  download(id, fileName, tableType) {
    if (id) {
      this.isDownloading = true;
      if (tableType && tableType === 'T') {
        this.documentMasterService.downloadTransaction(id).subscribe(
          res => {
            FileSaver.saveAs(res, fileName);
            this.isDownloading = false;
          },
          res => {
            this.isDownloading = false;
          }
        );
      } else {
        this.documentMasterService.download(id).subscribe(
          res => {
            FileSaver.saveAs(res, fileName);
            this.isDownloading = false;
          },
          res => {
            this.isDownloading = false;
          }
        );
      }
    }
  }
}
