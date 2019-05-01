import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class UpdateCandidateService {

  private extractData(res: Response)
  {
    let body = res;
    return body || { };
  }

  constructor(private http: HttpClient) { }

  addCandidate(id, name, surname, phoneNumber, email, birthDate, skills): Observable<any>
  {
    console.log(skills);
    return this.http.post('http://localhost:8221/api/candidate/add/', {
      //candidate
      id, name, surname, phoneNumber, email, birthDate, skills
    }).pipe(map(this.extractData));
  }

  addCandidateSkill(id, name, surname, phoneNumber, email, birthDate, skills): Observable<any>
  {
    return this.http.put('http://localhost:8221/api/candidate/addcandidateskill',{
      id, name, surname, phoneNumber, email, birthDate, skills
    }).pipe(map(this.extractData));
  }

  updateCandidate(id, name, surname, phoneNumber, email, birthdate, skills): Observable<any>
  {
    return this.http.put('http://localhost:8221/api/candidate/update/' + id, {
      id, name, surname, phoneNumber, email, birthdate, skills
    }).pipe(map(this.extractData));
  }

  deleteCandidate(id: number): Observable<any>
  {
    return this.http.delete('http://localhost:8221/api/candidate/delete/' + id).pipe(map(this.extractData));
  }

  deleteSkills(id, name, surname, phoneNumber, email, birthDate, skills): Observable<any>
  {
    return this.http.put('http://localhost:8221/api/candidate/deletecandidateskill', {
      id, name, surname, phoneNumber, email, birthDate, skills
    }).pipe(map(this.extractData));
  }

  getCandidateSkills(id: number): Observable<any>
  {
    return this.http.get('http://localhost:8221/api/candidate/getskills/' + id).pipe(map(this.extractData));
  }

}
