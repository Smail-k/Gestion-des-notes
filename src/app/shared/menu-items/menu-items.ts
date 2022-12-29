import { Injectable } from '@angular/core';


export interface BadgeItem {
  type: string;
  value: string;
}
export interface Saperator {
  name: string;
  type?: string;
}
export interface SubChildren {
  state: string;
  name: string;
  type?: string;
}
export interface ChildrenItems {
  state: string;
  name: string;
  type?: string;
  child?: SubChildren[];
}
 
export interface Menu {
  state: string;
  name: string;
  type: string;
  icon: string;
  badge?: BadgeItem[];
  saperator?: Saperator[];
  children?: ChildrenItems[];
  interfacage?:string;
}


const MENUITEMS = [
  { state: 'dashboard', name: 'Dashboard', type: 'stab', icon: 'av_timer' },
  { state: 'gestionEtudiant', type: 'link1', name: 'Gestion des etudiants', icon: 'person',
  children:[
   { state: 'gestionEtudiant', type:'link1', name: 'Importer Excell ',icon:'cloud_upload'},]  
  },
  { state: 'gestionMatieres', type: 'link2', name: 'Gestion des matières', icon: 'view_comfy',},
  { state: 'gestionUE', type: 'link1', name: 'Gestion des UE', icon: 'web',
  children:[
   { state: 'ImporterUE', type:'link1', name: 'Importer Excell',icon:'cloud_upload'},
   { state: 'gestionUE', type:'link1', name: 'Gérer UE',icon:'web'},
  ]  
  },
  { state: 'gestionDesParametres', type: 'link2', name: 'Gestion de Paramètres', icon: 'settings',},
  { state: 'gestionDesUtilisateurs', type: 'link2', name: 'Gestion des utilisateurs', icon: 'supervised_user_circle',},

];

@Injectable()
export class MenuItems {
  getMenuitem(): Menu[] {
    return MENUITEMS;
  }
}
