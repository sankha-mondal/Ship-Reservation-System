import { Time } from "@angular/common";

export interface ShipSchedule {

    ss_id: number;
    journeyDate: Date;
    journeyTime: Time;
    seatAvailability: number;
}

