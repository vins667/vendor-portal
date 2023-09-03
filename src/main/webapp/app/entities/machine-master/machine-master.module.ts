import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import {
  MachineMasterComponent,
  MachineMasterDetailComponent,
  MachineMasterUpdateComponent,
  MachineMasterDeletePopupComponent,
  MachineMasterDeleteDialogComponent,
  machineMasterRoute,
  machineMasterPopupRoute
} from './';

const ENTITY_STATES = [...machineMasterRoute, ...machineMasterPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MachineMasterComponent,
    MachineMasterDetailComponent,
    MachineMasterUpdateComponent,
    MachineMasterDeleteDialogComponent,
    MachineMasterDeletePopupComponent
  ],
  entryComponents: [
    MachineMasterComponent,
    MachineMasterUpdateComponent,
    MachineMasterDeleteDialogComponent,
    MachineMasterDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalMachineMasterModule {}
