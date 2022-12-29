import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GestionDesUEComponent } from './gestion-des-ue.component';

describe('GestionDesUEComponent', () => {
  let component: GestionDesUEComponent;
  let fixture: ComponentFixture<GestionDesUEComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GestionDesUEComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GestionDesUEComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
