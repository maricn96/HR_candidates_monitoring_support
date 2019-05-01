import { DataService } from './../../services/data.service';
import { BehaviorSubject } from 'rxjs';
import { UpdateSkillService } from './../../services/skill-services/update-skill.service';
import { Component, OnInit, Inject} from '@angular/core';
import { ShowSkillService } from 'src/app/services/skill-services/show-skill.service';
import { Router } from '@angular/router';
import { MatDialog, MatDialogRef } from '@angular/material';

@Component({
  selector: 'app-skill-list',
  templateUrl: './skill-list.component.html',
  styleUrls: ['./skill-list.component.css']
})
export class SkillListComponent implements OnInit {

  skillsApi: any = [];

  skillsEmpty: boolean = false;

  //skill update data
  skid: number;
  skill: string;
  description: string;
  ///////////////////

  ID_CHECK: number;
  updateFlag: boolean = false;

  private subject: BehaviorSubject<any> = new BehaviorSubject(null);

  constructor(private show: ShowSkillService, private skillService: UpdateSkillService, 
    private router: Router, private dialog: MatDialog, private data: DataService) { }

  ngOnInit() {
    this.showSkills();

    //contain skill update data
    this.data.currentSkill.subscribe(sk => this.skill = sk);
    this.data.currentDescription.subscribe(sk => this.description = sk);
    this.data.currentSkid.subscribe(sk => this.skid = sk);
  }

  showSkills()
  {
    this.skillsApi = [];
    this.show.getAllSkills().subscribe((data: {}) =>
    {
      this.skillsApi = data;
      if(this.skillsApi === undefined || this.skillsApi.length == 0)
        this.skillsEmpty = true;
      else
        this.skillsEmpty = false;
    });
  }

  //function that open dialog window for skill update
  updateOpenDialog(id: number)
  { 
    this.updateFlag = true;
    this.skillsApi.forEach(sk => {
      if(sk.id == id)
      {
        this.ID_CHECK = sk.id;
        this.data.changeSkill(sk.skill);
        this.data.changeDescription(sk.description);
      }
    });

    const dialogRef = this.dialog.open(DialogOverview, {
      width: '250px',
      height: '400px',
      data: {skill: this.skill, description: this.description}
    });
  }

  saveUpdateChanges(id: number)
  {
    this.skillService.updateSkill(id, this.skill, this.description).subscribe(res =>
    {
      this.updateFlag = false;
      this.ID_CHECK = 9999;
      this.dialog.closeAll();
      this.showSkills();
    })
  }


  deleteSkill(id: number) {
    let skname: string;

    this.skillsApi.forEach(elem => {
      if (id == elem.id) {
        skname = elem.skill;
      }
    });

    if (confirm("Are you sure you want to delete skill " + skname)) {
      this.skillService.deleteSkill(id).subscribe(data =>
      {
          this.subject.next(this.showSkills());
      })
    }

  }
}




@Component({
  selector: 'dialog-overview-example-dialog',
  templateUrl: 'dialog-overview.html',
})
/*
 *  Component that support skill update (works with dialog window)
 */
export class DialogOverview implements OnInit {

  skid: number;
  description: string;
  skill: string;

  constructor(
    public dialogRef: MatDialogRef<DialogOverview>, private data: DataService) {}

  ngOnInit()
  {
    this.data.currentSkill.subscribe(sk => this.skill = sk);
    this.data.currentDescription.subscribe(sk => this.description = sk);
    this.data.currentSkid.subscribe(sk => this.skid = sk);
  }

  //takes data for skill update
  updateSkill(event)
  {
    var target = event.target;
    this.data.changeSkill(target.querySelector('#skill').value);
    this.data.changeDescription(target.querySelector('#description').value);
    this.dialogRef.close();
  }

}