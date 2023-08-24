import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { RouteDetails } from '../Interfaces/route-details';

@Injectable({
  providedIn: 'root'
})
export class RouteDetailsService {

  constructor(public http: HttpClient) { }  // DI for HttpClient

  private apiUrlrds = "http://localhost:8585/route_details";

// =================================================================================================================  

  getAll_RouteDetails() : Observable<RouteDetails[]> {
    return this.http.get<RouteDetails[]>(this.apiUrlrds+"/getAll");
  }


  // http://localhost:8585/route_details/store

  //  By default all HttpClient method return type is json then we have to write responseType as Text.
  store_RouteDetails(routeDetails:RouteDetails) :Observable<string> {
    return this.http.post(this.apiUrlrds+"/store",routeDetails,{responseType:'text'});
  }


  // http://localhost:8585/route_details/delete/{routeId}

  delete_RouteDetails(routeId: string):Observable<string> {
    return this.http.delete(this.apiUrlrds+"/delete/"+routeId,{responseType:"text"});
  }

  // http://localhost:8585/route_details/update/{routeId}

  update_routeDetails(routeId: any, routeDetails:any):Observable<string> {
    return this.http.put(this.apiUrlrds+"/update/"+routeId, routeDetails,{responseType:"text"})
  }


}
