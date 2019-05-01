import { UpdateSkillService } from './../../services/skill-services/update-skill.service';
import { Component, OnInit } from '@angular/core';
import { Route } from '@angular/compiler/src/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-skill-new',
  templateUrl: './skill-new.component.html',
  styleUrls: ['./skill-new.component.css']
})
export class SkillNewComponent implements OnInit {

  constructor(private skillService: UpdateSkillService, private router: Router) { }

  ngOnInit() {
  }

  addSkill(event)
  {
    event.preventDefault();
    console.log('addSkill()');
    var target = event.target;

    let id = Math.floor((Math.random() * 10 + 12) * (Math.random() * 10 + 9)); //set random id
    let skill = target.querySelector('#skill').value;
    let description = target.querySelector('#description').value;


    this.skillService.addSkill(id, skill, description).subscribe((data: {}) =>
    {
      this.router.navigateByUrl('/candidates', {skipLocationChange: true}).then(()=>
      this.router.navigate(["/skills"])); //switch to skill list view
    })


  }

}
