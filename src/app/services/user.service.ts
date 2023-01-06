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
  
  authenticate(username:string,password:string):Observable<any>
  {
  const body = new HttpParams()
  .set('username', username)
  .set('password', password)
  const httpOptions=
  {
    headers: new HttpHeaders({
    'Access-Control-Allow-Origin':'*',
    'Content-type':'application/x-www-form-urlencoded'
    })
  }
  return this.http.post(url+'api/login',body,httpOptions);
  }
  
}