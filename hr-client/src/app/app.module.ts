import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CandidateListComponent } from './candidate/candidate-list/candidate-list.component';

import {MatTableModule, MatHeaderCell, MatButtonModule, MatMenuModule, MatToolbarModule, MatTabsModule, MatInputModule, MatDatepickerModule, MatGridListModule, MatCardModule, MatSelectModule, MatPaginatorModule, MatIconModule, MatTooltipModule, MatDialogModule, MatDialogTitle} from '@angular/material';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { NavigationComponent } from './navigation/navigation.component';
import { CandidateComponent } from './candidate/candidate.component';
import { SkillComponent } from './skill/skill.component';
import { CandidateNewComponent } from './candidate/candidate-new/candidate-new.component';
import { SkillListComponent, DialogOverview } from './skill/skill-list/skill-list.component';
import { SkillNewComponent } from './skill/skill-new/skill-new.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    CandidateListComponent,
    NavigationComponent,
    CandidateComponent,
    SkillComponent,
    CandidateNewComponent,
    SkillListComponent,
    SkillNewComponent,
    DialogOverview
  ],
  imports: [
    FormsModule,
    HttpClientModule,
    BrowserModule,
    AppRoutingModule,
    MatTableModule,
    MatMenuModule,
    MatButtonModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatTabsModule,
    MatInputModule,
    MatDatepickerModule,
    MatCardModule,
    MatGridListModule,
    MatSelectModule,
    MatPaginatorModule,
    MatIconModule,
    MatTooltipModule,
    MatDialogModule
  ],
  providers: [],
  bootstrap: [AppComponent],
  exports: [ DialogOverview ],
  entryComponents: [DialogOverview]
})
export class AppModule { }
