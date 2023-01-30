import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import { Observable } from 'rxjs';
import { User } from '../models/user';

const url='http://localhost:8080/api'; 
@Injectable({
providedIn: 'root'
})
export class UtilisateurService {

 
  
  constructor(private http:HttpClient) { }
 
  listeUtilisateurs(): Observable<User[]>{
    return this.http.get<User[]>(`${url+'/users'}`);
    }


}