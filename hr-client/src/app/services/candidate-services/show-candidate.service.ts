import { map } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ShowCandidateService {

  candidates: any = [];

  private extractData(res: Response)
  {
    let body = res;
    return body || { };
  }

  constructor(private http: HttpClient)
  {

  }

  getAllCandidates(): Observable<any>
  {
    return this.http.get('http://localhost:8221/api/candidate/getall').pipe(map(this.extractData));
  }

  
}
