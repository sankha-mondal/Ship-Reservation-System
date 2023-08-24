import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ResSDSSOnSourDest } from '../Interfaces/res-sd-ss-on-sour-dest';
import { ShipSchedule } from '../Interfaces/ship-schedule';

@Injectable({
  providedIn: 'root'
})
export class ShipScheduleService {

  constructor(public http: HttpClient) { }  // DI for HttpClient

  private apiUrlsss = "http://localhost:8585/ship_schedule";

  // =================================================================================================================  

  getAll_ShipSchedul() : Observable<ShipSchedule[]> {
    return this.http.get<ShipSchedule[]>(this.apiUrlsss+"/getAll");
  }

  store_ShipSchedul(routeId: string, shipDetId: string, ShipSchReq: ShipSchedule): Observable<string> {
    return this.http.post(this.apiUrlsss+"/store/"+routeId+"/"+shipDetId, ShipSchReq, {responseType:'text'});
  } 


  // http://localhost:8585/ship_schedule/delete/{ss_Id}

  delete_ShipSchedule(ss_Id: number):Observable<string> {
    return this.http.delete(this.apiUrlsss+"/delete/"+ss_Id,{responseType:"text"});
  }

  // http://localhost:8585/ship_schedule/update/{ss_id}

  update_routeDetails(routeId: any, routeDetails:any):Observable<string> {
    return this.http.put(this.apiUrlsss+"/update/"+routeId, routeDetails,{responseType:"text"})
  }

  get_ship_details_schedule(source: any, destination: any): Observable<ResSDSSOnSourDest[]> {
    return this.http.get<ResSDSSOnSourDest[]>(this.apiUrlsss+"/find_SDSS_on_Source_Destination/"+source+"/"+destination)
  }

  update_SeatAvailability(ss_Id: any, shipSchReq: any): Observable<string>  {
    return this.http.put(this.apiUrlsss+"/update_Ship_SeatAvai/"+ss_Id, shipSchReq,{responseType:"text"})
  }


}
