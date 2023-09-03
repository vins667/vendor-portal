import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { IVcutStyleImage } from 'app/shared/model/vcut-style-image.model';
import { createRequestOption } from 'app/shared/util/request-util';
import { VcutStylePlanUploadSearch } from 'app/shared/model/vcut-style-plan-upload-search.model';

type EntityResponseType = HttpResponse<IVcutStyleImage>;
type EntityArrayResponseType = HttpResponse<IVcutStyleImage[]>;

@Injectable({ providedIn: 'root' })
export class VcutStyleImageService {
  public resourceUrl = SERVER_API_URL + 'api/vcut-style-images';

  constructor(protected http: HttpClient) {}

  create(file1: File, file2: File, vcutStyleImage: IVcutStyleImage): Observable<EntityResponseType> {
    const formData: FormData = new FormData();
    formData.append('frontImage', file1);
    formData.append('backImage', file2);
    formData.append('style', vcutStyleImage.style);
    return this.http
      .post<IVcutStyleImage>(this.resourceUrl, formData, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(file1: File, file2: File, vcutStyleImage: IVcutStyleImage): Observable<EntityResponseType> {
    const formData: FormData = new FormData();
    formData.append('frontImage', file1);
    formData.append('backImage', file2);
    formData.append('id', vcutStyleImage.id + '');
    formData.append('style', vcutStyleImage.style);
    return this.http
      .post<IVcutStyleImage>(this.resourceUrl + '-update', formData, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IVcutStyleImage>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IVcutStyleImage[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  queryCustom(req?: VcutStylePlanUploadSearch): Observable<EntityArrayResponseType> {
    return this.http
      .post<IVcutStyleImage[]>(this.resourceUrl + '-qry', req, { observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(vcutStyleImage: IVcutStyleImage): IVcutStyleImage {
    const copy: IVcutStyleImage = Object.assign({}, vcutStyleImage, {
      createdDate: vcutStyleImage.createdDate != null && vcutStyleImage.createdDate.isValid() ? vcutStyleImage.createdDate.toJSON() : null,
      lastUpdatedDate:
        vcutStyleImage.lastUpdatedDate != null && vcutStyleImage.lastUpdatedDate.isValid() ? vcutStyleImage.lastUpdatedDate.toJSON() : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.createdDate = res.body.createdDate != null ? moment(res.body.createdDate) : null;
      res.body.lastUpdatedDate = res.body.lastUpdatedDate != null ? moment(res.body.lastUpdatedDate) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((vcutStyleImage: IVcutStyleImage) => {
        vcutStyleImage.createdDate = vcutStyleImage.createdDate != null ? moment(vcutStyleImage.createdDate) : null;
        vcutStyleImage.lastUpdatedDate = vcutStyleImage.lastUpdatedDate != null ? moment(vcutStyleImage.lastUpdatedDate) : null;
      });
    }
    return res;
  }
}
