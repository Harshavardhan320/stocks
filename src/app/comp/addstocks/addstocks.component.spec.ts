import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddstocksComponent } from './addstocks.component';

describe('AddstocksComponent', () => {
  let component: AddstocksComponent;
  let fixture: ComponentFixture<AddstocksComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddstocksComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddstocksComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
