import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Etudiant } from '../models/etudiant';
const httpOptions = {headers: new HttpHeaders( {'Content-Type': 'application/json'} )
};

@Injectable({
  providedIn: 'root'
})
export class EtudiantService {
  apiURL: string = 'http://localhost:8080/api/etudiants';
  etudiants: Etudiant[];
  constructor(private http: HttpClient) { 

    this.etudiants = [
      { id: 1, nom: "MBAIWODJI", numero: "m21231253", prenom: "Bienvenue" },
      { id: 2, nom: "SMIRANI", numero: "m21231215", prenom: "Ibtihel" },
    ]
  }

  listeEtudiant(): Observable<Etudiant[]>{
    return this.http.get<Etudiant[]>(this.apiURL+'/lister');
    }

  addEtudiant(etudiant: Etudiant){
    this.etudiants.push(etudiant);
  }

    /**
   * 
   * @param file le fichier qui contient les etudiants à importer
   * @returns une chaine de caractere de succées
   */
    importEtudiants(file:FormData):Observable<any>
    { 
      // Envoi de la requête POST
      return this.http.post( 'http://localhost:8080/api/excel/etudiant',file); }


}
