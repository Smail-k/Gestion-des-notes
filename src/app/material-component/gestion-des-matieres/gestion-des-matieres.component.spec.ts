import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GestionDesMatieresComponent } from './gestion-des-matieres.component';

describe('GestionDesMatieresComponent', () => {
  let component: GestionDesMatieresComponent;
  let fixture: ComponentFixture<GestionDesMatieresComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GestionDesMatieresComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GestionDesMatieresComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
