import { Injectable } from '@angular/core';
import {
HttpRequest,
HttpHandler,
HttpEvent,
HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';
const url='http://localhost:8080/api/login';


@Injectable()
export class AuthInterceptor implements HttpInterceptor {

constructor() {}

intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> 
{

if(request.url.includes("api/login"))
{
  request = request.clone({setHeaders: {Authorization: 'Basic ',}});
}else
{
  //lors de la communcation avec un micro, il te faut juste un token
  request = request.clone({setHeaders:{Authorization: 'Bearer '+localStorage.getItem('at'),}});
}

return next.handle(request);

}

}
