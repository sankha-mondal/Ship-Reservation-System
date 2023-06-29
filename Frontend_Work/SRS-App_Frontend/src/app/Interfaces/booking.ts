export interface Booking {

    bookingId: string;  // PK
    pName: string;
    paymentAmount: any;
    bookingDate: any;
    noOfHeadCount: number;
    head_count: head_Count[];

    ship_Schedule: {
        ss_id: number;
        journeyDate: string;
        journeyTime: string;
        seatAvailability: number;
    };
}
interface head_Count {
   
    hcId: number;
    hcName: string;
    hcAge: number;
    hcGender: string;
};

// interface ShipSchedule {
    
//     ss_id: number;
//     journeyDate: string;
//     journeyTime: string;
//     seatAvailability: number;
// }
