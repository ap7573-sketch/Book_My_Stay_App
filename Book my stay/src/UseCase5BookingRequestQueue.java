import java.util.LinkedList;
import java.util.Queue;

class Reservation {
    String guestName;
    String roomType;

    Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    void display() {
        System.out.println("Guest: " + guestName + " | Room Type: " + roomType);
    }
}

class BookingRequestQueue {

    private Queue<Reservation> queue;

    public BookingRequestQueue() {
        queue = new LinkedList<>();
    }

    public void addRequest(Reservation reservation) {
        queue.add(reservation);
    }

    public void displayRequests() {
        System.out.println("Booking Requests (First-Come-First-Served)");
        System.out.println("-------------------------------------------");
        for (Reservation r : queue) {
            r.display();
        }
    }
}

public class UseCase5BookingRequestQueue {

    public static void main(String[] args) {

        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        bookingQueue.addRequest(new Reservation("Ahn", "Single Room"));
        bookingQueue.addRequest(new Reservation("Rahul", "Double Room"));
        bookingQueue.addRequest(new Reservation("Priya", "Suite Room"));

        bookingQueue.displayRequests();
    }
}