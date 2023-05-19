import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { SemestreNote } from '../models/semestrenote';
import { EtudiantRattrapage } from '../models/etudiant_rattrapage';

@Injectable({
  providedIn: 'root'
})
export class NoteService {

  apiURL: string = 'http://localhost:8080/api/etudiants';
  constructor(private http: HttpClient) { 
  }

  listeEtudiant(promo:any,annee:any): Observable<any[]>{
    return this.http.get<any>(`${this.apiURL+'/moyenne'}/?promo=${promo}&annee=${annee}`);
    }


    listeNotesSemestre(promo:any,annee:any): Observable<any[]>{
   
      return this.http.get<any>(`${this.apiURL+'/note/semestre'}?promo=${promo}&anneeUniv=${annee}`);
    }

    listeEtudiantRattrapagee(annee:any,promo:any): Observable<EtudiantRattrapage[]>{
   
      return this.http.get<any>(`${this.apiURL+'/rattrapages'}?annee=${annee}&promo=${promo}`);
    }
}
