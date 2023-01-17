import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import { Observable } from 'rxjs';

const url='http://localhost:8080/'; 
@Injectable({
providedIn: 'root'
})
export class UtilisateurService {

  token!:any;
  helper = new JwtHelperService
  
  constructor(private http:HttpClient) { }
  
  ngOnInit() { }
  
  authenticate(username:any,password:any):Observable<any>
  {
  const body = new HttpParams()
  .set('username', username)
  .set('password', password)
  const httpOptions=
  {
    headers: new HttpHeaders({
    'Access-Control-Allow-Origin':'*',
    'Access-Control-Allow-Methods':'GET, POST, OPTIONS, PUT, PATCH, DELETE',
    'Access-Control-Allow-Headers':'X-Requested-With,content-type',
    'Access-Control-Allow-Credentials':'true',
    'Content-type':'application/x-www-form-urlencoded'
    })
  }
  return this.http.post(url+'api/login',body,httpOptions);
  }
  
}