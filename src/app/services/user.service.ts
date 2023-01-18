import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import { Observable } from 'rxjs';

const url='http://localhost:8080/'; 
@Injectable({
providedIn: 'root'
})
export class UtilisateurService {

 
  
  constructor(private http:HttpClient) { }
 

  /**
   * 
   * @param file le fichier qui contient les etudiants à importer
   * @returns une chaine de caractere de succées
   */
  importModules(file:FormData):Observable<any>
  { 
    // Envoi de la requête POST
    return this.http.post(url+ 'api/excel',file); }

}