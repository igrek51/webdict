import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {HardestWordComponent} from './hardest-word.component';

describe('HardestWordComponent', () => {
  let component: HardestWordComponent;
  let fixture: ComponentFixture<HardestWordComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [HardestWordComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HardestWordComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
