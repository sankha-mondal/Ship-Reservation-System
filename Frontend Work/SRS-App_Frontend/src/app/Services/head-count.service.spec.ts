import { TestBed } from '@angular/core/testing';

import { HeadCountService } from './head-count.service';

describe('HeadCountService', () => {
  let service: HeadCountService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HeadCountService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
