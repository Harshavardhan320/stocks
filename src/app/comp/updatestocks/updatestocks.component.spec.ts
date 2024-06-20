import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdatestocksComponent } from './updatestocks.component';

describe('UpdatestocksComponent', () => {
  let component: UpdatestocksComponent;
  let fixture: ComponentFixture<UpdatestocksComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UpdatestocksComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UpdatestocksComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
