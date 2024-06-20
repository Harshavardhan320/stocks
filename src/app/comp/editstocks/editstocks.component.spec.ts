import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditstocksComponent } from './editstocks.component';

describe('EditstocksComponent', () => {
  let component: EditstocksComponent;
  let fixture: ComponentFixture<EditstocksComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EditstocksComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditstocksComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
