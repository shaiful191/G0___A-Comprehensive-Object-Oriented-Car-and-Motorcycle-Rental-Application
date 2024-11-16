import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class Vehicle {
    private String vehicleId;
    private String brand;
    private String model;
    private double basePricePerDay;
    private boolean isAvailable;

    public Vehicle(String vehicleId, String brand, String model, double basePricePerDay) {
        this.vehicleId = vehicleId;
        this.brand = brand;
        this.model = model;
        this.basePricePerDay = basePricePerDay;
        this.isAvailable = true;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public double getBasePricePerDay() {
        return basePricePerDay;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void rent() {
        isAvailable = false;
    }

    public void returnVehicle() {
        isAvailable = true;
    }

    public abstract double calculatePrice(int rentalDays);
}

class Car extends Vehicle {
    private String fuelType;
    private int numberOfSeats;

    public Car(String carId, String brand, String model, double basePricePerDay, String fuelType, int numberOfSeats) {
        super(carId, brand, model, basePricePerDay);
        this.fuelType = fuelType;
        this.numberOfSeats = numberOfSeats;
    }

    public String getFuelType() {
        return fuelType;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    @Override
    public double calculatePrice(int rentalDays) {
        return getBasePricePerDay() * rentalDays;
    }
}

class Motorcycle extends Vehicle {
    public Motorcycle(String motorcycleId, String brand, String model, double basePricePerDay) {
        super(motorcycleId, brand, model, basePricePerDay);
    }


    @Override
    public double calculatePrice(int rentalDays) {
        return getBasePricePerDay() * rentalDays;
    }
}

class Customer {
    private String customerId;
    private String name;

    Customer(String customerId, String name) {
        this.customerId = customerId;
        this.name = name;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }
}

class Rental {
    private Vehicle vehicle;
    private Customer customer;
    private int days;

    public Rental(Vehicle vehicle, Customer customer, int days) {
        this.vehicle = vehicle;
        this.customer = customer;
        this.days = days;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Customer getCustomer() {
        return customer;
    }

    public int getDays() {
        return days;
    }
}

class RentalSystem {
    private List<Vehicle> vehicles;
    private List<Customer> customers;
    private List<Rental> rentals;

    public RentalSystem() {
        vehicles = new ArrayList<>();
        customers = new ArrayList<>();
        rentals = new ArrayList<>();
    }

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void rentVehicle(Vehicle vehicle, Customer customer, int days) {
        if (vehicle.isAvailable()) {
            vehicle.rent();
            rentals.add(new Rental(vehicle, customer, days));
        } else {
            System.out.println("Vehicle is not available for rent.");
        }
    }

    public void returnVehicle(Vehicle vehicle) {
        Rental rentalToRemove = null;
        for (Rental rental : rentals) {
            if (rental.getVehicle() == vehicle) {
                rentalToRemove = rental;
                break;
            }
        }
        if (rentalToRemove != null) {
            vehicle.returnVehicle();
            rentals.remove(rentalToRemove);
        } else {
            System.out.println("Vehicle was not rented.");
        }
    }

    public void menu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("**** Welcome To Go ****");
            System.out.println("1. Rent a Vehicle");
            System.out.println("2. Return a Vehicle");
            System.out.println("3. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 1) {
                System.out.println("\n== Rent a Vehicle ==\n");
                System.out.print("Enter your name: ");
                String customerName = scanner.nextLine();

                System.out.println("\nAvailable Vehicles: ");
                for (Vehicle vehicle : vehicles) {
                    if (vehicle.isAvailable()) {
                        if (vehicle instanceof Car) {
                            Car car = (Car) vehicle;
                            System.out.println("Car - "+car.getVehicleId() + ", " + car.getBrand() + ", " + car.getModel() +
                                    ", " + car.getFuelType() + ", " + car.getNumberOfSeats() + " seats.");
                        } else {
                            System.out.println("Motorcycle - "+vehicle.getVehicleId() + ", " + vehicle.getBrand() + ", " + vehicle.getModel() + ".");
                        }
                    }
                }


                System.out.print("Enter the vehicle ID you want to rent: ");
                String choiceVehicleId = scanner.nextLine();
                System.out.println();

                System.out.print("Enter the number of days for rental: ");
                int rentalDays = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                Customer newCustomer = new Customer("CUS" + (customers.size() + 1), customerName);
                addCustomer(newCustomer);

                Vehicle selectedVehicle = null;
                for (Vehicle vehicle : vehicles) {
                    if (vehicle.getVehicleId().equals(choiceVehicleId) && vehicle.isAvailable()) {
                        selectedVehicle = vehicle;
                        break;
                    }
                }

                if (selectedVehicle != null) {
                    double totalPrice = selectedVehicle.calculatePrice(rentalDays);
                    System.out.println("\n== Rental Information ==\n");
                    System.out.println("Customer ID: " + newCustomer.getCustomerId());
                    System.out.println("Customer Name: " + newCustomer.getName());
                    System.out.println("Vehicle: " + selectedVehicle.getBrand() + " " + selectedVehicle.getModel());
                    System.out.println("Rental Days: " + rentalDays);
                    System.out.printf("Total Price: $%.2f%n", totalPrice);

                    System.out.print("\nConfirm rental (Y/N): ");
                    String confirm = scanner.nextLine();

                    if (confirm.equalsIgnoreCase("Y")) {
                        rentVehicle(selectedVehicle, newCustomer, rentalDays);
                        System.out.println("\nVehicle Rented Successfully.");
                    } else {
                        System.out.println("\nRental canceled.");
                    }
                } else {
                    System.out.println("\nInvalid vehicle selection or vehicle not available for rent.");
                }
            } else if (choice == 2) {
                System.out.println("\n== Return a Vehicle ==\n");
                System.out.print("Enter the vehicle ID you want to return: ");
                String vehicleId = scanner.nextLine();

                Vehicle vehicleToReturn = null;
                for (Vehicle vehicle : vehicles) {
                    if (vehicle.getVehicleId().equals(vehicleId) && !vehicle.isAvailable()) {
                        vehicleToReturn = vehicle;
                        break;
                    }
                }
                if (vehicleToReturn != null) {
                    Customer customer = null;
                    for (Rental rental : rentals) {
                        if (rental.getVehicle() == vehicleToReturn) {
                            customer = rental.getCustomer();
                            break;
                        }
                    }
                    if (customer != null) {
                        returnVehicle(vehicleToReturn);
                        System.out.println("Vehicle returned successfully by " + customer.getName());
                    } else {
                        System.out.println("Vehicle was not rented or rental information is missing.");
                    }
                } else {
                    System.out.println("Invalid vehicle ID or vehicle is not rented.");
                }
            } else if (choice == 3) {
                break;
            } else {
                System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
        System.out.println("\nThank you for using the Go!");
    }
}

public class Main {
    public static void main(String[] args) {

        Car car1 = new Car("C001", "Toyota", "Camry", 60.0, "Petrol", 5);
        Car car2 = new Car("C002", "Honda", "Accord", 70.0, "Diesel", 6);
        Car car3 = new Car("C003", "Tesla", "Model 3", 150.0, "Electric", 10);

        Motorcycle motorcycle1 = new Motorcycle("M001", "Harley-Davidson", "Iron 883", 40.0);
        Motorcycle motorcycle2 = new Motorcycle("M002", "Yamaha", "MT-07", 35.0);

        RentalSystem rentalSystem = new RentalSystem();

        rentalSystem.addVehicle(car1);
        rentalSystem.addVehicle(car2);
        rentalSystem.addVehicle(car3);

        rentalSystem.addVehicle(motorcycle1);
        rentalSystem.addVehicle(motorcycle2);

        rentalSystem.menu();
    }
}
