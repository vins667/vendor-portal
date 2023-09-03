import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import {
    AuditQuesBuyerMappingComponent,
    AuditQuesBuyerMappingDetailComponent,
    AuditQuesBuyerMappingUpdateComponent,
    AuditQuesBuyerMappingDeletePopupComponent,
    AuditQuesBuyerMappingDeleteDialogComponent,
    BuyerMasterSearchComponent,
    auditQuesBuyerMappingRoute,
    auditQuesBuyerMappingPopupRoute
} from './';
import { MatTableModule } from '@angular/material/table';
import {MatInputModule} from '@angular/material/input';
import {MatPaginatorModule} from '@angular/material/paginator';
import {MatSortModule} from '@angular/material/sort';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import { MatTabsModule } from '@angular/material';
import { CdkTableModule } from '@angular/cdk/table';

const ENTITY_STATES = [...auditQuesBuyerMappingRoute, ...auditQuesBuyerMappingPopupRoute];

@NgModule({
    imports: [VamaniportalSharedModule,
      RouterModule.forChild(ENTITY_STATES),
      MatInputModule,
      MatTableModule,
      MatPaginatorModule,
      MatSortModule,
      MatProgressSpinnerModule,
      MatTabsModule,
      CdkTableModule
    ],
    declarations: [
        AuditQuesBuyerMappingComponent,
        AuditQuesBuyerMappingDetailComponent,
        AuditQuesBuyerMappingUpdateComponent,
        AuditQuesBuyerMappingDeleteDialogComponent,
        AuditQuesBuyerMappingDeletePopupComponent,
        BuyerMasterSearchComponent
    ],
    entryComponents: [
        AuditQuesBuyerMappingComponent,
        AuditQuesBuyerMappingUpdateComponent,
        AuditQuesBuyerMappingDeleteDialogComponent,
        AuditQuesBuyerMappingDeletePopupComponent,
        BuyerMasterSearchComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalAuditQuesBuyerMappingModule {}
