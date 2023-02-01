import 'hammerjs';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';

import { DemoMaterialModule } from '../demo-material-module';
import { CdkTableModule } from '@angular/cdk/table';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { FlexLayoutModule } from '@angular/flex-layout';

import { MaterialRoutes } from './material.routing';


import { ExpansionComponent } from './expansion/expansion.component';
import { GestionDesEtudiantsComponent } from './gestion-des-etudiants/gestion-des-etudiants.component';
import { GestionDesMatieresComponent } from './gestion-des-matieres/gestion-des-matieres.component';
import { GestionDesUEComponent } from './gestion-des-ue/gestion-des-ue.component';
import { ImporterFichierExcellUEComponent } from './gestion-des-ue/importer-fichier-excell-ue/importer-fichier-excell-ue.component';
import { GestionDesAdministrateursComponent } from './gestion-des-administrateurs/gestion-des-administrateurs.component';
import { LoginComponent } from '../login/login.component';
import { SupprimerEtudiantComponent } from './gestion-des-etudiants/supprimer-etudiant/supprimer-etudiant.component';
import { ToastrModule } from 'ngx-toastr';
import { AjouteretudiantComponent } from './gestion-des-etudiants/ajouteretudiant/ajouteretudiant.component';
import { AjouterutilisateurComponent } from './gestion-des-administrateurs/ajouterutilisateur/ajouterutilisateur.component';


@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(MaterialRoutes),
    DemoMaterialModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    FlexLayoutModule,
    CdkTableModule,

  ],
  providers: [],
  entryComponents: [],
  declarations: [
    ExpansionComponent,
    GestionDesEtudiantsComponent,
    GestionDesMatieresComponent,
    GestionDesUEComponent,
    ImporterFichierExcellUEComponent,
    GestionDesAdministrateursComponent,
    SupprimerEtudiantComponent,
    AjouteretudiantComponent,
    AjouterutilisateurComponent,

  
  ]
})
export class MaterialComponentsModule {}
