import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ImporterFichierExcellUEComponent } from './importer-fichier-excell-ue.component';

describe('ImporterFichierExcellUEComponent', () => {
  let component: ImporterFichierExcellUEComponent;
  let fixture: ComponentFixture<ImporterFichierExcellUEComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ImporterFichierExcellUEComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ImporterFichierExcellUEComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
