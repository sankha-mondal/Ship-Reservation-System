import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PaymentPassengerComponent } from './payment-passenger.component';

describe('PaymentPassengerComponent', () => {
  let component: PaymentPassengerComponent;
  let fixture: ComponentFixture<PaymentPassengerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PaymentPassengerComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PaymentPassengerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
