import java.util.*;

class Service {
    String name;
    double price;

    Service(String name, double price) {
        this.name = name;
        this.price = price;
    }

    void display() {
        System.out.println(name + " : " + price);
    }
}

class AddOnServiceManager {

    private Map<String, List<Service>> reservationServices = new HashMap<>();

    void addService(String reservationId, Service service) {
        reservationServices.putIfAbsent(reservationId, new ArrayList<>());
        reservationServices.get(reservationId).add(service);
    }

    double calculateTotalCost(String reservationId) {
        double total = 0;
        List<Service> services = reservationServices.getOrDefault(reservationId, new ArrayList<>());

        for (Service s : services) {
            total += s.price;
        }

        return total;
    }

    void displayServices(String reservationId) {
        List<Service> services = reservationServices.getOrDefault(reservationId, new ArrayList<>());

        System.out.println("Reservation ID: " + reservationId);
        System.out.println("Selected Services:");

        for (Service s : services) {
            s.display();
        }

        System.out.println("Total Add-On Cost: " + calculateTotalCost(reservationId));
    }
}

public class UseCase7AddOnServiceSelection {

    public static void main(String[] args) {

        AddOnServiceManager manager = new AddOnServiceManager();

        String reservationId = "RES-101";

        Service breakfast = new Service("Breakfast", 500);
        Service airportPickup = new Service("Airport Pickup", 1200);
        Service spa = new Service("Spa Access", 1500);

        manager.addService(reservationId, breakfast);
        manager.addService(reservationId, airportPickup);
        manager.addService(reservationId, spa);

        manager.displayServices(reservationId);
    }
}