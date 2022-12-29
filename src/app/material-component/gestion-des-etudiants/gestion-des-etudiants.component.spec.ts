import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GestionDesEtudiantsComponent } from './gestion-des-etudiants.component';

describe('GestionDesEtudiantsComponent', () => {
  let component: GestionDesEtudiantsComponent;
  let fixture: ComponentFixture<GestionDesEtudiantsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GestionDesEtudiantsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GestionDesEtudiantsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
