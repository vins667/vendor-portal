import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IVcutStyleImage, VcutStyleImage } from 'app/shared/model/vcut-style-image.model';
import { VcutStyleImageService } from './vcut-style-image.service';
import { SnotifyService } from 'ng-snotify';
import { toastConfig } from 'app/core/toast/toast-config';

@Component({
  selector: 'jhi-vcut-style-image-update',
  templateUrl: './vcut-style-image-update.component.html'
})
export class VcutStyleImageUpdateComponent implements OnInit {
  isSaving: boolean;
  frontImageFile: File;
  backImageFile: File;

  editForm = this.fb.group({
    id: [],
    style: [null, [Validators.required, Validators.maxLength(100)]],
    frontImage: [null],
    backImage: [null],
    createdBy: [null, [Validators.maxLength(50)]],
    createdDate: [],
    lastUpdatedBy: [null, [Validators.maxLength(50)]],
    lastUpdatedDate: []
  });

  constructor(
    protected vcutStyleImageService: VcutStyleImageService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder,
    protected snotifyService: SnotifyService
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ vcutStyleImage }) => {
      this.updateForm(vcutStyleImage);
    });
  }

  updateForm(vcutStyleImage: IVcutStyleImage) {
    this.editForm.patchValue({
      id: vcutStyleImage.id,
      style: vcutStyleImage.style,
      frontImage: vcutStyleImage.frontImage,
      backImage: vcutStyleImage.backImage,
      createdBy: vcutStyleImage.createdBy,
      createdDate: vcutStyleImage.createdDate != null ? vcutStyleImage.createdDate.format(DATE_TIME_FORMAT) : null,
      lastUpdatedBy: vcutStyleImage.lastUpdatedBy,
      lastUpdatedDate: vcutStyleImage.lastUpdatedDate != null ? vcutStyleImage.lastUpdatedDate.format(DATE_TIME_FORMAT) : null
    });
  }

  previousState() {
    window.history.back();
  }
  uploadFrontImage(event) {
    if (event.target.files.length > 0) {
      const fileName = event.target.files[0].name;
      const fileSize = event.target.files[0].size;
      if (fileSize > 5242880) {
        this.snotifyService.error('File size should not exceed 5MB!!!', '', toastConfig);
        event.target.value = null;
        this.frontImageFile = null;
      } else {
        if (fileName && fileName.lastIndexOf('.') !== -1) {
          const ext = fileName.substr(fileName.lastIndexOf('.') + 1, fileName.length);
          if (ext.toUpperCase() === 'JPG') {
            if (this.frontImageFile !== event.target.files[0]) {
              this.frontImageFile = event.target.files[0];
              const reader = new FileReader();
              reader.readAsDataURL(event.target.files[0]);
              reader.onload = (eventt: ProgressEvent) => {
                this.editForm.patchValue({
                  frontImage: (eventt.target as FileReader).result
                });
              };
            }
          } else {
            this.snotifyService.error('JPG File Only!!!', '', toastConfig);
            event.target.value = null;
            this.frontImageFile = null;
          }
        } else {
          this.snotifyService.error('Not Valid File!!!', '', toastConfig);
          event.target.value = null;
          this.frontImageFile = null;
        }
      }
    }
  }

  uploadBackImage(event) {
    if (event.target.files.length > 0) {
      const fileName = event.target.files[0].name;
      const fileSize = event.target.files[0].size;
      if (fileSize > 5242880) {
        this.snotifyService.error('File size should not exceed 5MB!!!', '', toastConfig);
        event.target.value = null;
        this.backImageFile = null;
      } else {
        if (fileName && fileName.lastIndexOf('.') !== -1) {
          const ext = fileName.substr(fileName.lastIndexOf('.') + 1, fileName.length);
          if (ext.toUpperCase() === 'JPG') {
            if (this.backImageFile !== event.target.files[0]) {
              this.backImageFile = event.target.files[0];

              const reader = new FileReader();
              reader.readAsDataURL(event.target.files[0]);
              reader.onload = (eventt: ProgressEvent) => {
                this.editForm.patchValue({
                  backImage: (eventt.target as FileReader).result
                });
              };
            }
          } else {
            this.snotifyService.error('JPG Image Only!!!', '', toastConfig);
            event.target.value = null;
            this.backImageFile = null;
          }
        } else {
          this.snotifyService.error('Not Valid File!!!', '', toastConfig);
          event.target.value = null;
          this.backImageFile = null;
        }
      }
    }
  }

  save() {
    this.isSaving = true;
    const vcutStyleImage = this.createFromForm();
    if (vcutStyleImage.id !== undefined) {
      this.subscribeToSaveResponse(this.vcutStyleImageService.update(this.frontImageFile, this.backImageFile, vcutStyleImage));
    } else {
      this.subscribeToSaveResponse(this.vcutStyleImageService.create(this.frontImageFile, this.backImageFile, vcutStyleImage));
    }
  }

  private createFromForm(): IVcutStyleImage {
    return {
      ...new VcutStyleImage(),
      id: this.editForm.get(['id']).value,
      style: this.editForm.get(['style']).value,
      frontImage: this.editForm.get(['frontImage']).value,
      backImage: this.editForm.get(['backImage']).value,
      createdBy: this.editForm.get(['createdBy']).value,
      createdDate:
        this.editForm.get(['createdDate']).value != null ? moment(this.editForm.get(['createdDate']).value, DATE_TIME_FORMAT) : undefined,
      lastUpdatedBy: this.editForm.get(['lastUpdatedBy']).value,
      lastUpdatedDate:
        this.editForm.get(['lastUpdatedDate']).value != null
          ? moment(this.editForm.get(['lastUpdatedDate']).value, DATE_TIME_FORMAT)
          : undefined
    };
  }
  protected subscribeToSaveResponse(result: Observable<HttpResponse<IVcutStyleImage>>) {
    result.subscribe(
      (res: HttpResponse<IVcutStyleImage>) => this.onSaveSuccess(),
      (res: HttpErrorResponse) => this.onSaveError(res.headers)
    );
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(res: HttpHeaders) {
    this.isSaving = false;
    this.snotifyService.error(res.get('x-vcutstyleimage-error'), '', toastConfig);
  }
}
