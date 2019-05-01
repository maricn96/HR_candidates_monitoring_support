import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ShowSkillService {

  private extractData(res: Response)
  {
    let body = res;
    return body || { };
  }

  constructor(private http: HttpClient) { }

  getAllSkills(): Observable<any>
  {
    return this.http.get('http://localhost:8221/api/skill/getall').pipe(map(this.extractData));
  }

}
