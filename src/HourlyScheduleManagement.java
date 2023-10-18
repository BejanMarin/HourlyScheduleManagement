import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

class WorkShift {
    private Date startTime;
    private Date endTime;

    public WorkShift(Date startTime, Date endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }
}

public class HourlyScheduleManagement {
    private static List<WorkShift> shifts = new ArrayList<>();
    private static SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nHourly Schedule Management");
            System.out.println("1. Add Work Shift");
            System.out.println("2. Calculate Working Time");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    addWorkShift(scanner);
                    break;
                case 2:
                    calculateWorkingTime();
                    break;
                case 3:
                    System.out.println("Exiting the program. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    private static void addWorkShift(Scanner scanner) {
        try {
            System.out.print("Enter shift start time (HH:mm): ");
            Date startTime = timeFormat.parse(scanner.nextLine());

            System.out.print("Enter shift end time (HH:mm): ");
            Date endTime = timeFormat.parse(scanner.nextLine());

            WorkShift shift = new WorkShift(startTime, endTime);
            shifts.add(shift);
            System.out.println("Work shift added successfully!");
        } catch (ParseException e) {
            System.out.println("Invalid time format. Please enter times in HH:mm format.");
        }
    }

    private static void calculateWorkingTime() {
        if (shifts.isEmpty()) {
            System.out.println("No work shifts to calculate.");
            return;
        }

        long totalWorkingTimeMillis = 0;
        for (WorkShift shift : shifts) {
            long shiftDurationMillis = shift.getEndTime().getTime() - shift.getStartTime().getTime();
            totalWorkingTimeMillis += shiftDurationMillis;
        }

        long totalWorkingTimeMinutes = totalWorkingTimeMillis / (60 * 1000);
        System.out.println("Total working time: " + totalWorkingTimeMinutes + " minutes");
    }
}