import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GestionDesAdministrateursComponent } from './gestion-des-administrateurs.component';

describe('GestionDesAdministrateursComponent', () => {
  let component: GestionDesAdministrateursComponent;
  let fixture: ComponentFixture<GestionDesAdministrateursComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GestionDesAdministrateursComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GestionDesAdministrateursComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
