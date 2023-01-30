import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Unite } from '../models/unite';

const url="http://localhost:8080/api/unite/";
@Injectable({
  providedIn: 'root'
})
export class UniteService {

  constructor(private http:HttpClient) { }
 /* listeUnite(promo:any,annee:any): Observable<Unite[]>{
    return this.http.get<Unite[]>(`${url+'/liste'}/?promo=${promo}&annee=${annee}`);
    }
  */
}
