import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class SearchService {

  private extractData(res: Response)
  {
    let body = res;
    return body;
  }

  constructor(private http: HttpClient) { }

  searchCandidates(name: string): Observable<any>
  {
    return this.http.get('http://localhost:8221/api/candidate/search/' + name).pipe(map(this.extractData));
  }


}
