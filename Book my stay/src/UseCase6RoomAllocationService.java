import java.util.*;

class Reservation {
    String guestName;
    String roomType;

    Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

class RoomInventory {
    private HashMap<String, Integer> inventory;

    RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 2);
        inventory.put("Suite Room", 1);
    }

    int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    void decreaseAvailability(String roomType) {
        inventory.put(roomType, inventory.get(roomType) - 1);
    }

    void displayInventory() {
        System.out.println("Current Inventory:");
        for (String type : inventory.keySet()) {
            System.out.println(type + " : " + inventory.get(type));
        }
        System.out.println();
    }
}

class BookingQueue {
    Queue<Reservation> queue = new LinkedList<>();

    void addRequest(Reservation r) {
        queue.add(r);
    }

    Reservation getNextRequest() {
        return queue.poll();
    }

    boolean hasRequests() {
        return !queue.isEmpty();
    }
}

class RoomAllocator {

    private HashMap<String, Set<String>> allocatedRooms = new HashMap<>();
    private Set<String> usedRoomIds = new HashSet<>();
    private int roomCounter = 1;

    void allocate(Reservation r, RoomInventory inventory) {

        if (inventory.getAvailability(r.roomType) > 0) {

            String roomId = r.roomType.replace(" ", "") + "-" + roomCounter++;

            if (!usedRoomIds.contains(roomId)) {

                usedRoomIds.add(roomId);

                allocatedRooms.putIfAbsent(r.roomType, new HashSet<>());
                allocatedRooms.get(r.roomType).add(roomId);

                inventory.decreaseAvailability(r.roomType);

                System.out.println("Reservation Confirmed");
                System.out.println("Guest: " + r.guestName);
                System.out.println("Room Type: " + r.roomType);
                System.out.println("Room ID: " + roomId);
                System.out.println();
            }

        } else {
            System.out.println("Reservation Failed for " + r.guestName + " (No rooms available)");
            System.out.println();
        }
    }
}

public class UseCase6RoomAllocationService {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();
        BookingQueue queue = new BookingQueue();
        RoomAllocator allocator = new RoomAllocator();

        queue.addRequest(new Reservation("Ahn", "Single Room"));
        queue.addRequest(new Reservation("Rahul", "Double Room"));
        queue.addRequest(new Reservation("Priya", "Suite Room"));
        queue.addRequest(new Reservation("Karan", "Single Room"));

        inventory.displayInventory();

        while (queue.hasRequests()) {
            Reservation r = queue.getNextRequest();
            allocator.allocate(r, inventory);
        }

        inventory.displayInventory();
    }
}