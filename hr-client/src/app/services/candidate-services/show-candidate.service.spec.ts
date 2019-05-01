import { TestBed } from '@angular/core/testing';

import { ShowCandidateService } from './show-candidate.service';

describe('ShowCandidateService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ShowCandidateService = TestBed.get(ShowCandidateService);
    expect(service).toBeTruthy();
  });
});
