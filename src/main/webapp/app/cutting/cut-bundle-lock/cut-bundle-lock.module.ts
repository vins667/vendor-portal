import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';

import { SnotifyModule } from 'ng-snotify';
import { CutBundleLockRoute } from 'app/cutting/cut-bundle-lock/cut-bundle-lock.route';
import { CutBundleLockComponent } from 'app/cutting/cut-bundle-lock/cut-bundle-lock.component';
import { CutBundleLockDeleteDialogComponent } from 'app/cutting/cut-bundle-lock/cut-bundle-lock-delete-dialog.component';

const ENTITY_STATES = [...CutBundleLockRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES), SnotifyModule],
  declarations: [CutBundleLockComponent, CutBundleLockDeleteDialogComponent],
  entryComponents: [CutBundleLockComponent, CutBundleLockDeleteDialogComponent]
})
export class VamaniportalCutBundleLockModule {}
