import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShipTicketsComponent } from './ship-tickets.component';

describe('ShipTicketsComponent', () => {
  let component: ShipTicketsComponent;
  let fixture: ComponentFixture<ShipTicketsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ShipTicketsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ShipTicketsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
