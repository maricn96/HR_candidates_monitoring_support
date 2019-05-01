import { ShowSkillService } from './../../services/skill-services/show-skill.service';
import { SearchService } from './../../services/search.service';
import { UpdateCandidateService } from './../../services/candidate-services/update-candidate.service';
import { Component, OnInit } from '@angular/core';
import { ShowCandidateService } from '../../services/candidate-services/show-candidate.service';
import { Router } from '@angular/router';
import { BehaviorSubject } from 'rxjs';

@Component({
  selector: 'app-candidate-list',
  templateUrl: './candidate-list.component.html',
  styleUrls: ['./candidate-list.component.css']
})
export class CandidateListComponent implements OnInit {

  searchEmptyFlag: boolean = false;

  //turns true if user is searching by name or skills
  searchFlag: boolean = false;

  candidatesEmpty: boolean = false;
  
  candidatesApi: any = [];
  skillsApi: any = [];
  

  private subject: BehaviorSubject<any> = new BehaviorSubject(null);

  constructor(private showcandService: ShowCandidateService, private candService: UpdateCandidateService, private router: Router,
                private searchService: SearchService, private showSkills: ShowSkillService) {

  }

  ngOnInit() {
    this.showCandidates();
    this.getSkills();
  }

  showCandidates()
  {
    this.searchEmptyFlag = false;
    this.searchFlag = false;
    this.candidatesApi = [];
    this.showcandService.getAllCandidates().subscribe((data: {}) =>
    {
      this.candidatesApi = data;
      if(this.candidatesApi === undefined || this.candidatesApi.length == 0)
        this.candidatesEmpty = true; //show empty candidates message
      else
        this.candidatesEmpty = false;
    });
  }

  getSkills()
  {
    this.showSkills.getAllSkills().subscribe(res =>
      {
        this.skillsApi = res;
      })
  }



  ID_CHECK: number = 0; 
  skillsForCheckBox: any = [];
  showSelectBox(id: number)
  {
    this.candidatesApi.forEach(element => {
      if(element.id == id)
      {
        this.ID_CHECK = id; 
      }
    });

    this.skillsForCheckBox = []; //list of the rest skills for selected candidate

    this.candService.getCandidateSkills(id).subscribe(data =>
    {
       this.skillsForCheckBox = data;
       if(data == null)
        this.skillsForCheckBox = this.skillsApi;
    })
  }

  skillsInserted: any;
  getSkillsFromUpdate(skill: string)
  {
    this.skillsInserted = skill;
    console.log(this.skillsInserted);
  }

  addSkills(event, id: number)
  {
    event.preventDefault();

    var name = '';
    var surname = '';
    var phone = '';
    var email = '';
    var date = '';

    
    this.candidatesApi.forEach(element => {
      if(element.id == id)
      {
        element.skills.forEach(item => {
          this.skillsInserted.push(item);
        });
      }
    });

    this.candidatesApi.forEach(element => {
      if(element.id == id)
      {
        name = element.name;
        surname = element.surname;
        phone = element.phoneNumber;
        email = element.email;
        date = element.birthDate;
      }
    });

    if(this.skillsInserted == [])
    {
      this.ID_CHECK = 9999; //leave combo box
      return;
    }

    //send data to server
    this.candService.addCandidateSkill(id, name, surname, phone, email, date, this.skillsInserted).subscribe(res =>
    {
      console.log(res);
      if (res == null) {
        console.log("ERROR");
      }
      else {
        this.skillsInserted = [];
        this.ID_CHECK = 9999; //leave combo box
        this.subject.next(this.showCandidates()); //refresh candidates list
      }
    })

  }

  deleteCandSkills(candidate: any, skId: number)
  {
    let id: number = skId;
    let skill: string;

    this.getSkills();

    this.skillsApi.forEach(element => {
      if(element.id == skId)
      {
        skill = element.skill;
      }
    });

    let object = [
      {
        "id": id,
        "skill": skill
      }
    ]
    
    // console.log(candidate + "OBJEKAT" + object);

    this.candService.deleteSkills(candidate.id, candidate.name, candidate.surname,
    candidate.phoneNumber, candidate.email, candidate.birthDate, object).subscribe(res =>
    {
        this.ID_CHECK = 9999; //leave combo box
        this.subject.next(this.showCandidates());
    })
  }


  deleteCand(id: number) 
  {
    let candname: string;

    this.candidatesApi.forEach(elem => {
      if (id == elem.id) {
        candname = elem.name;
      }
    });

    if (confirm("Are you sure you want to delete candidate " + candname)) {
      this.candService.deleteCandidate(id).subscribe(data =>
      {
        this.subject.next(this.showCandidates());
      })
    }

  }

  searchCandidate(event)
  {
    event.preventDefault();

    let name = event.target.querySelector('#searchinput').value;

    this.searchService.searchCandidates(name).subscribe(res =>
    {
      if(res.length == 0) //check if search result is empty
      {
        this.searchEmptyFlag = true;
        this.candidatesApi = [];
        this.searchFlag = true;
      }
      else
      {
        this.candidatesApi = res;
        this.searchFlag = true;
      }
      
    })

  }

  resetSearch()
  {
    this.searchFlag = false;
    this.searchEmptyFlag = false;
    this.showCandidates();
  }

}


