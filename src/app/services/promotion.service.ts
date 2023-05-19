import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

const url="http://localhost:8080/api/promotion/"
@Injectable({
  providedIn: 'root'
})
export class PromotionService {

  constructor(private httpClient: HttpClient) { }

  /**
   * 
   * @returns une liste de promotions
   */
  getpromotions():Observable<any[]>{ return this.httpClient.get<any[]>(url+'promotions');}

  /**
   * 
   * @returns une liste des années 
   */
  getannees():Observable<any[]>  { return this.httpClient.get<any[]>(url+'anneesAll');}
}
