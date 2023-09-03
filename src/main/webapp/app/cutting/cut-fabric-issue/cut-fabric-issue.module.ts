import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { CutFabricIssueUpdateComponent } from './cut-fabric-issue-update.component';
import { CutFabricIssueComponent } from './cut-fabric-issue.component';
import { CutFabricIssueRoute } from './cut-fabric-issue.route';
import { SnotifyModule } from 'ng-snotify';

const ENTITY_STATES = [...CutFabricIssueRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES), SnotifyModule],
  declarations: [CutFabricIssueComponent, CutFabricIssueUpdateComponent],
  entryComponents: [CutFabricIssueComponent, CutFabricIssueUpdateComponent]
})
export class VamaniportalCutFabricIssueModule {}
