import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';

@Injectable({
  providedIn: 'root'
})
export class NoteService {

  apiURL: string = 'http://localhost:8080/api/etudiants';
  constructor(private http: HttpClient) { 
  }

  listeEtudiant(promo:any,annee:any): Observable<Object[]>{
    return this.http.get<Object[]>(`${this.apiURL+'/moyenne'}/?promo=${promo}&annee=${annee}`);
    }

}
