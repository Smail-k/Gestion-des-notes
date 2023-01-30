import { Routes } from '@angular/router';


import { ExpansionComponent } from './expansion/expansion.component';
import { GestionDesAdministrateursComponent } from './gestion-des-administrateurs/gestion-des-administrateurs.component';
import { GestionDesEtudiantsComponent } from './gestion-des-etudiants/gestion-des-etudiants.component';
import { GestionDesMatieresComponent } from './gestion-des-matieres/gestion-des-matieres.component';
import { GestionDesUEComponent } from './gestion-des-ue/gestion-des-ue.component';
import { ImporterFichierExcellUEComponent } from './gestion-des-ue/importer-fichier-excell-ue/importer-fichier-excell-ue.component';
import { LoginComponent } from '../login/login.component';
import { IsSignedGuard } from '../guards/is-signed.guard';
import { DashboardComponent } from '../dashboard/dashboard.component';


export const MaterialRoutes: Routes = [
  {
    canActivate:[IsSignedGuard],
    path: 'gestionEtudiant',
    component: GestionDesEtudiantsComponent
  },
  {
    canActivate:[IsSignedGuard],
    path: 'Dashboard',
    component: DashboardComponent
  },
  {
    canActivate:[IsSignedGuard],
    path: 'gestionMatieres',
    component: GestionDesMatieresComponent
  },
  {
    canActivate:[IsSignedGuard],
    path: 'ImporterUE',
    component: ImporterFichierExcellUEComponent
  },
  {
    canActivate:[IsSignedGuard],
    path: 'gestionUE',
    component: GestionDesUEComponent
  },

  {
    canActivate:[IsSignedGuard],
    path: 'gestionDesUtilisateurs',
    component: GestionDesAdministrateursComponent
  },

 

];
