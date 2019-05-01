import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UpdateSkillService {

  private extractData(res: Response)
  {
    let body = res;
    return body || {};
  }

  constructor(private http: HttpClient) { }

  addSkill(id, skill, description): Observable<any>
  {
    return this.http.post('http://localhost:8221/api/skill/add/', {
      id, skill, description
    }).pipe(map(this.extractData));
  }

  updateSkill(id, skill, description): Observable<any>
  {
    return this.http.put('http://localhost:8221/api/skill/update/' + id, {
      id, skill, description
    }).pipe(map(this.extractData));
  }

  deleteSkill(id: number): Observable<any>
  {
    return this.http.delete('http://localhost:8221/api/skill/delete/' + id).pipe(map(this.extractData));
  }
}
