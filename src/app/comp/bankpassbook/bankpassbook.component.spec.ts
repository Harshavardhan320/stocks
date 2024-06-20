import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BankpassbookComponent } from './bankpassbook.component';

describe('BankpassbookComponent', () => {
  let component: BankpassbookComponent;
  let fixture: ComponentFixture<BankpassbookComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BankpassbookComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BankpassbookComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
