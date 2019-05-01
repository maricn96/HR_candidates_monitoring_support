import { TestBed } from '@angular/core/testing';

import { ShowSkillService } from './show-skill.service';

describe('ShowSkillService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ShowSkillService = TestBed.get(ShowSkillService);
    expect(service).toBeTruthy();
  });
});
