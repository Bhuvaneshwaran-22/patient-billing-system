import java.util.*;

class Patient {
    private static int counter = 1;
    private int id;
    private String name;
    private String contact;

    public Patient(String name, String contact) {
        this.id = counter++;
        this.name = name;
        this.contact = contact;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }

    @Override
    public String toString() {
        return "Patient[id=" + id + ", name=" + name + ", contact=" + contact + "]";
    }
}

class Doctor {
    private static int counter = 1;
    private int id;
    private String name;
    private String specialization;
    private Set<String> availableSlots;

    public Doctor(String name, String specialization, Set<String> availableSlots) {
        this.id = counter++;
        this.name = name;
        this.specialization = specialization;
        this.availableSlots = new HashSet<>(availableSlots);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public boolean isSlotAvailable(String slot) {
        return availableSlots.contains(slot);
    }

    public boolean bookSlot(String slot) {
        return availableSlots.remove(slot);
    }

    public void freeSlot(String slot) {
        availableSlots.add(slot);
    }

    public Set<String> getAvailableSlots() {
        return availableSlots;
    }

    @Override
    public String toString() {
        return "Doctor[id=" + id + ", name=" + name + ", specialization=" + specialization + "]";
    }
}

class Appointment {
    private static int counter = 1;
    private int id;
    private Patient patient;
    private Doctor doctor;
    private String slot;
    private boolean completed;

    public Appointment(Patient patient, Doctor doctor, String slot) {
        this.id = counter++;
        this.patient = patient;
        this.doctor = doctor;
        this.slot = slot;
        this.completed = false;
    }

    public int getId() {
        return id;
    }

    public Patient getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public String getSlot() {
        return slot;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void complete() {
        this.completed = true;
    }

    @Override
    public String toString() {
        return "Appointment[id=" + id + ", patient=" + patient.getName() + ", doctor=" + doctor.getName() +
                ", slot=" + slot + ", completed=" + completed + "]";
    }
}

class Prescription {
    static class Item {
        String name;
        double price;

        Item(String name, double price) {
            this.name = name;
            this.price = price;
        }
    }

    private List<Item> items = new ArrayList<>();

    public void addItem(String name, double price) {
        items.add(new Item(name, price));
    }

    public double getTotalPrice() {
        double total = 0;
        for (Item i : items) total += i.price;
        return total;
    }

    public List<Item> getItems() {
        return items;
    }
}

class Consultation {
    private static int counter = 1;
    private int id;
    private Appointment appointment;
    private Prescription prescription;
    private double consultationFee;

    public Consultation(Appointment appointment, double consultationFee) {
        this.id = counter++;
        this.appointment = appointment;
        this.consultationFee = consultationFee;
        this.prescription = new Prescription();
    }

    public int getId() {
        return id;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public Prescription getPrescription() {
        return prescription;
    }

    public double getConsultationFee() {
        return consultationFee;
    }

    @Override
    public String toString() {
        return "Consultation[id=" + id + ", appointmentId=" + appointment.getId() + ", patient=" +
                appointment.getPatient().getName() + ", doctor=" + appointment.getDoctor().getName() +
                ", consultationFee=" + consultationFee + "]";
    }
}

class Invoice {
    private static int counter = 1;
    private int id;
    private Consultation consultation;
    private double taxRate = 0.1; // 10%
    private boolean closed = false;

    public Invoice(Consultation consultation) {
        this.id = counter++;
        this.consultation = consultation;
    }

    public double calculateTotal() {
        double itemsCost = consultation.getPrescription().getTotalPrice();
        double fee = consultation.getConsultationFee();
        double tax = (itemsCost + fee) * taxRate;
        return fee + itemsCost + tax;
    }

    public int getId() {
        return id;
    }

    public Consultation getConsultation() {
        return consultation;
    }

    public boolean isClosed() {
        return closed;
    }

    public void close() {
        closed = true;
    }

    @Override
    public String toString() {
        return String.format("Invoice[id=%d, total=%.2f, closed=%b]", id, calculateTotal(), closed);
    }
}

class Payment {
    private static int counter = 1;
    private int id;
    private Invoice invoice;
    private double amount;
    private boolean settled;

    public Payment(Invoice invoice, double amount) {
        this.id = counter++;
        this.invoice = invoice;
        this.amount = amount;
        settled = false;
    }

    public boolean settle() {
        if (amount >= invoice.calculateTotal() && !invoice.isClosed()) {
            invoice.close();
            settled = true;
            return true;
        }
        return false;
    }

    public int getId() {
        return id;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public double getAmount() {
        return amount;
    }

    public boolean isSettled() {
        return settled;
    }

    @Override
    public String toString() {
        return String.format("Payment[id=%d, invoiceId=%d, amount=%.2f, settled=%b]", id, invoice.getId(), amount, settled);
    }
}

public class PatientAppointmentBillingSystem {
    static Scanner scanner = new Scanner(System.in);

    static Map<Integer, Patient> patients = new HashMap<>();
    static Map<Integer, Doctor> doctors = new HashMap<>();
    static Map<Integer, Appointment> appointments = new HashMap<>();
    static Map<Integer, Consultation> consultations = new HashMap<>();
    static Map<Integer, Invoice> invoices = new HashMap<>();
    static Map<Integer, Payment> payments = new HashMap<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Add Patient");
            System.out.println("2. Add Doctor");
            System.out.println("3. Schedule Appointment");
            System.out.println("4. Record Consultation");
            System.out.println("5. Generate Invoice");
            System.out.println("6. Record Payment");
            System.out.println("7. List Appointments");
            System.out.println("8. Exit");
            System.out.print("Choose option: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1: addPatient(); break;
                case 2: addDoctor(); break;
                case 3: scheduleAppointment(); break;
                case 4: recordConsultation(); break;
                case 5: generateInvoice(); break;
                case 6: recordPayment(); break;
                case 7: listAppointments(); break;
                case 8: System.out.println("Exiting..."); return;
                default: System.out.println("Invalid option");
            }
        }
    }

    private static void addPatient() {
        System.out.print("Enter patient name: ");
        String name = scanner.nextLine();
        System.out.print("Enter patient contact: ");
        String contact = scanner.nextLine();
        Patient p = new Patient(name, contact);
        patients.put(p.getId(), p);
        System.out.println("Patient added with ID: " + p.getId());
    }

    private static void addDoctor() {
        System.out.print("Enter doctor name: ");
        String name = scanner.nextLine();
        System.out.print("Enter specialization: ");
        String spec = scanner.nextLine();
        System.out.print("Enter available slots (comma separated, e.g. 9AM-10AM,10AM-11AM): ");
        String[] slots = scanner.nextLine().split(",");
        Set<String> availableSlots = new HashSet<>(Arrays.asList(slots));
        Doctor d = new Doctor(name, spec, availableSlots);
        doctors.put(d.getId(), d);
        System.out.println("Doctor added with ID: " + d.getId());
    }

    private static void scheduleAppointment() {
        System.out.print("Enter patient ID: ");
        int pid = Integer.parseInt(scanner.nextLine());
        Patient p = patients.get(pid);
        if (p == null) {
            System.out.println("Patient not found");
            return;
        }

        System.out.print("Enter doctor ID: ");
        int did = Integer.parseInt(scanner.nextLine());
        Doctor d = doctors.get(did);
        if (d == null) {
            System.out.println("Doctor not found");
            return;
        }

        System.out.println("Available slots: " + d.getAvailableSlots());
        System.out.print("Choose slot: ");
        String slot = scanner.nextLine();

        if (!d.isSlotAvailable(slot)) {
            System.out.println("Slot not available");
            return;
        }

        d.bookSlot(slot);
        Appointment a = new Appointment(p, d, slot);
        appointments.put(a.getId(), a);
        System.out.println("Appointment scheduled with ID: " + a.getId() + " at slot " + slot);
    }

    private static void recordConsultation() {
        System.out.print("Enter appointment ID: ");
        int aid = Integer.parseInt(scanner.nextLine());
        Appointment a = appointments.get(aid);
        if (a == null) {
            System.out.println("Appointment not found");
            return;
        }
        if (a.isCompleted()) {
            System.out.println("Consultation already recorded for this appointment");
            return;
        }
        a.complete();

        System.out.print("Enter consultation fee: ");
        double fee = Double.parseDouble(scanner.nextLine());

        Consultation c = new Consultation(a, fee);

        System.out.println("Add prescribed items (format: name,price), empty line to stop:");
        while (true) {
            System.out.print("Item: ");
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) break;
            String[] parts = line.split(",");
            if (parts.length != 2) {
                System.out.println("Invalid format");
                continue;
            }
            try {
                String itemName = parts[0].trim();
                double itemPrice = Double.parseDouble(parts[1].trim());
                c.getPrescription().addItem(itemName, itemPrice);
            } catch (Exception e) {
                System.out.println("Invalid price format");
            }
        }

        consultations.put(c.getId(), c);
        System.out.println("Consultation recorded: " + c);
    }

    private static void generateInvoice() {
        System.out.print("Enter consultation ID: ");
        int cid = Integer.parseInt(scanner.nextLine());
        Consultation c = consultations.get(cid);
        if (c == null) {
            System.out.println("Consultation not found");
            return;
        }

        Invoice inv = new Invoice(c);
        invoices.put(inv.getId(), inv);

        System.out.printf("Invoice generated with ID: %d, Total: %.2f\n", inv.getId(), inv.calculateTotal());
    }

    private static void recordPayment() {
        System.out.print("Enter invoice ID: ");
        int iid = Integer.parseInt(scanner.nextLine());
        Invoice inv = invoices.get(iid);
        if (inv == null) {
            System.out.println("Invoice not found");
            return;
        }
        if (inv.isClosed()) {
            System.out.println("Invoice already paid and closed");
            return;
        }

        System.out.print("Enter payment amount: ");
        double amt = Double.parseDouble(scanner.nextLine());

        Payment pmt = new Payment(inv, amt);
        if (pmt.settle()) {
            payments.put(pmt.getId(), pmt);
            System.out.println("Payment settled and invoice closed: " + pmt);
        } else {
            System.out.println("Payment amount insufficient or invoice already closed");
        }
    }

    private static void listAppointments() {
        if (appointments.isEmpty()) {
            System.out.println("No appointments found");
            return;
        }
        for (Appointment a : appointments.values()) {
            System.out.println(a);
        }
    }
}
