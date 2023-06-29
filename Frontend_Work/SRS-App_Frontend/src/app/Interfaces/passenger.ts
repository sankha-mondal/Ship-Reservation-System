export interface Passenger {

    pEmail: string;  // PK
    pName: string;
    pPhone: string;
    pPassword: string;
    pRole: string;
    pAddress: string;
    url: string;
    booking: Booking[];
}
interface Booking {

    bookingId: string;  // PK
    pName: string;
    paymentAmount: Number;
    bookingDate: Date;
 }
