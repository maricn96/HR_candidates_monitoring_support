import { TestBed } from '@angular/core/testing';

import { UpdateSkillService } from './update-skill.service';

describe('UpdateSkillService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: UpdateSkillService = TestBed.get(UpdateSkillService);
    expect(service).toBeTruthy();
  });
});
