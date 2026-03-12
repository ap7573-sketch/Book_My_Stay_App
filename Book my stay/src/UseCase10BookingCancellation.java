import java.util.*;

class Reservation {
    String reservationId;
    String roomType;

    Reservation(String reservationId, String roomType) {
        this.reservationId = reservationId;
        this.roomType = roomType;
    }
}

class RoomInventory {

    private Map<String, Integer> inventory = new HashMap<>();

    RoomInventory() {
        inventory.put("Single Room", 1);
        inventory.put("Double Room", 1);
        inventory.put("Suite Room", 1);
    }

    void increaseAvailability(String roomType) {
        inventory.put(roomType, inventory.get(roomType) + 1);
    }

    void displayInventory() {
        System.out.println("Current Inventory:");
        for (String type : inventory.keySet()) {
            System.out.println(type + " : " + inventory.get(type));
        }
        System.out.println();
    }
}

class BookingHistory {

    Map<String, Reservation> bookings = new HashMap<>();

    void addBooking(Reservation r) {
        bookings.put(r.reservationId, r);
    }

    Reservation getBooking(String id) {
        return bookings.get(id);
    }

    void removeBooking(String id) {
        bookings.remove(id);
    }
}

class CancellationService {

    private Stack<String> rollbackStack = new Stack<>();

    void cancelBooking(String reservationId, BookingHistory history, RoomInventory inventory) {

        Reservation r = history.getBooking(reservationId);

        if (r == null) {
            System.out.println("Cancellation Failed: Reservation not found");
            return;
        }

        rollbackStack.push(reservationId);

        inventory.increaseAvailability(r.roomType);

        history.removeBooking(reservationId);

        System.out.println("Booking Cancelled: " + reservationId);
    }

    void showRollbackStack() {
        System.out.println("Rollback Stack: " + rollbackStack);
        System.out.println();
    }
}

public class UseCase10BookingCancellation {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();
        BookingHistory history = new BookingHistory();
        CancellationService cancelService = new CancellationService();

        history.addBooking(new Reservation("RES-201", "Single Room"));
        history.addBooking(new Reservation("RES-202", "Double Room"));

        inventory.displayInventory();

        cancelService.cancelBooking("RES-201", history, inventory);
        cancelService.cancelBooking("RES-999", history, inventory);

        cancelService.showRollbackStack();

        inventory.displayInventory();
    }
}