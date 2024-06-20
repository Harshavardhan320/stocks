import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BuyorsellstocksComponent } from './buyorsellstocks.component';

describe('BuyorsellstocksComponent', () => {
  let component: BuyorsellstocksComponent;
  let fixture: ComponentFixture<BuyorsellstocksComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BuyorsellstocksComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BuyorsellstocksComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
