import { TestBed } from '@angular/core/testing';

import { UpdateCandidateService } from './update-candidate.service';

describe('UpdateCandidateService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: UpdateCandidateService = TestBed.get(UpdateCandidateService);
    expect(service).toBeTruthy();
  });
});
