import { CandidateComponent } from './candidate/candidate.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SkillComponent } from './skill/skill.component';

const routes: Routes = [
  {path: 'candidates', component: CandidateComponent},
  {path: 'skills', component: SkillComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
