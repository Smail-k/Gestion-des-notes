import { Routes } from '@angular/router';


import { ExpansionComponent } from './expansion/expansion.component';
import { GestionDesAdministrateursComponent } from './gestion-des-administrateurs/gestion-des-administrateurs.component';
import { GestionDesEtudiantsComponent } from './gestion-des-etudiants/gestion-des-etudiants.component';
import { GestionDesMatieresComponent } from './gestion-des-matieres/gestion-des-matieres.component';
import { GestionDesParametresComponent } from './gestion-des-parametres/gestion-des-parametres.component';
import { GestionDesUEComponent } from './gestion-des-ue/gestion-des-ue.component';
import { ImporterFichierExcellUEComponent } from './gestion-des-ue/importer-fichier-excell-ue/importer-fichier-excell-ue.component';
import { LoginComponent } from '../login/login.component';


export const MaterialRoutes: Routes = [
  {
    path: 'gestionEtudiant',
    component: GestionDesEtudiantsComponent
  },
  {
    path: 'gestionMatieres',
    component: GestionDesMatieresComponent
  },
  {
    path: 'ImporterUE',
    component: ImporterFichierExcellUEComponent
  },
  {
    path: 'gestionUE',
    component: GestionDesUEComponent
  },
  {
    path: 'gestionDesParametres',
    component: GestionDesParametresComponent
  },
  {
    path: 'gestionDesUtilisateurs',
    component: GestionDesAdministrateursComponent
  },

 

];
