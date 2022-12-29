import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GestionDesParametresComponent } from './gestion-des-parametres.component';

describe('GestionDesParametresComponent', () => {
  let component: GestionDesParametresComponent;
  let fixture: ComponentFixture<GestionDesParametresComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GestionDesParametresComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GestionDesParametresComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
