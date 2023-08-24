import { TestBed } from '@angular/core/testing';

import { ShipDetailsService } from './ship-details.service';

describe('ShipDetailsService', () => {
  let service: ShipDetailsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ShipDetailsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
