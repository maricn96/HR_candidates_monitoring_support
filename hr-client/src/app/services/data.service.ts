import { BehaviorSubject } from 'rxjs';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
/*
 *  Component that contain and spread data for skill update
 */
export class DataService {

  //contain skill id
  private skid = new BehaviorSubject<number>(0);
  currentSkid = this.skid.asObservable();

  //contain skill name
  private skill = new BehaviorSubject<string>('');
  currentSkill = this.skill.asObservable();

  //contain skill description
  private description = new BehaviorSubject<string>('');
  currentDescription = this.description.asObservable();

  constructor() { }

  changeSkill(newsk: string)
  {
    this.skill.next(newsk);
  }

  changeDescription(newdesc: string)
  {
    this.description.next(newdesc);
  }

  changeSkid(newskid: number)
  {
    this.skid.next(newskid);
  }
}
