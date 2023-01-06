import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class IsSignedGuard implements CanActivate {
  constructor(private router: Router) { }

/** 
 *Autoriser l'acces au dashboard si l'utilisateur est connecté
  */ 
  canActivate(route: ActivatedRouteSnapshot,state: RouterStateSnapshot)
  : Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree 
  {
  if(localStorage.getItem('at')!=null )
  {this.router.navigate(["/"]);
    return false;}
  else
    return true;}
  
}
