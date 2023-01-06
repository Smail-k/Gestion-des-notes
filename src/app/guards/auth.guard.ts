import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, CanLoad, Route, Router, RouterStateSnapshot, UrlSegment, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { UtilisateurService } from '../services/user.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate,CanLoad {

 constructor(
  private router:Router,
  public auth: UtilisateurService){}

/**
  Refuser la connexion si l'utilisateur n'est pas authentifié 
  **/
  canLoad( ): boolean 
  {if(localStorage.getItem('at')!=null)
  return true;
  else
  this.router.navigate(['/login'])
  return false;
  }

  canActivate(route: ActivatedRouteSnapshot,state: RouterStateSnapshot): 
  Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree 
    {
    if(localStorage.getItem('at')!=null)
      return true;
    else
      this.router.navigate(['/login'])
      return false;
    }

  
}
