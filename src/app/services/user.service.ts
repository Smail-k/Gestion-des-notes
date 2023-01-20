import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import { Observable } from 'rxjs';

const url='http://localhost:8080/api/'; 
@Injectable({
providedIn: 'root'
})
export class UtilisateurService {

 
  
  constructor(private http:HttpClient) { }
 



}