import { ShowSkillService } from './../../services/skill-services/show-skill.service';
import { UpdateCandidateService } from './../../services/candidate-services/update-candidate.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-candidate-new',
  templateUrl: './candidate-new.component.html',
  styleUrls: ['./candidate-new.component.css']
})
export class CandidateNewComponent implements OnInit {

  // candidate: any = {
  //   id: '',
  //   name: '',
  //   surname: '',
  //   phoneNumber: '',
  //   email: '',
  //   birthDate: '',
  //   skills: []
  // }

  nameerr: boolean = false;
  surnameerr: boolean = false;
  phoneerr: boolean = false;
  phoneNaN: boolean = false;
  emailerr: boolean = false;
  emailreg: boolean = false;
  bdateerr: boolean = false;

  skillsInserted: any; //list of skills taken from combo box
  skillsToSend: any = [];

  //all skills (objects)
  skillsAll: any = [];

  //list of skills (strings only)
  skillsApi: any = [];

  constructor(private candService: UpdateCandidateService, private router: Router, private skillService: ShowSkillService) { }

  ngOnInit() {
    this.selectSkill();
  }

  addSKillsToCandidate(skill: string)
  {
    this.skillsInserted = [];
    this.skillsInserted = skill;
    // console.log(this.skillsInserted);
  }

  addCandidate(event)
  {
    event.preventDefault();
    console.log('addCandidate()');
    var target = event.target;
    
    let id = Math.floor((Math.random() * 10 + 12) * (Math.random() * 10 + 9)); //random id (anyway )
    let name = target.querySelector('#firstname').value;
    let surname = target.querySelector('#surname').value;
    let phoneNumber = target.querySelector('#phone').value;
    let email = target.querySelector('#email').value;
    let birthDate: string = target.querySelector('#birthdate').value;
    birthDate = birthDate.trim();

    this.skillsToSend = [];

    this.skillsAll.forEach(api => {
      this.skillsInserted.forEach(item => {
        if(api.skill == item)
        {
          this.skillsToSend.push(api);
        }
      });
    });

    //validation section
    if(name.length < 2 || name.length > 32)
    {
      this.nameerr = true;
      return;
    }
    else
      this.nameerr = false;

    if(surname.length < 2 || surname.length > 32)
    {
      this.surnameerr = true;
      return;
    }
    else
      this.surnameerr = false;

    if(phoneNumber.length == 0)
    {
      this.phoneerr = true;
      return;
    }
    else
      this.phoneerr = false;

    if(!/^[0-9]*$/.test(phoneNumber))
    {
      this.phoneNaN = true;
      return;
    }
    else
      this.phoneNaN = false;

    if(email.length == 0)
    {
      this.emailerr = true;
      return;
    }
    else
      this.emailerr = false;

    if(!/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(email))
    {
      this.emailreg = true;
      return;
    }
    else
      this.emailreg = false;

    if(!/^\d{4}-\d{2}-\d{2}/.test(birthDate) || birthDate === null)
    {
      this.bdateerr = true;
      return;
    }
    else
      this.bdateerr = false;

    // this.candidate = {
    //   id: id,
    //   name: name,
    //   surname: surname,
    //   birthDate: birthDate,
    //   phoneNumber: phoneNumber,
    //   email: email,
    //   skills: [
    //     this.skillsToSend
    //   ]
    // }
    
    this.candService.addCandidate(id, name, surname, phoneNumber, email, birthDate, this.skillsToSend).subscribe((data: {}) =>
    {
      this.router.navigateByUrl('/skills', {skipLocationChange: true}).then(()=>
      this.router.navigate(["/candidates"])); //switch to candidate list section
    })
  }

  //takes list of skills for combo box
  selectSkill()
  {
    this.skillService.getAllSkills().subscribe(res =>
    {
      this.skillsAll = res;
      res.forEach(element => {
        this.skillsApi.push(element.skill.toString());
      });
    })
  }


  

}
