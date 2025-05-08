import java.io.*;
import java.util.*;

public class Runner {
    public static void main(String[] args) {
        Solution obj = new Solution();
        // Read input from a file named "input.txt"
        // The file should contain the commands to be executed
        // Each command should be on a new line 
        // Example command: addCinema(1, 1, 2, 5, 5)
        // Example command: addShow(1, 1, 1, 1, 1633036800, 1633040400)
        // Example command: bookTicket("TICKET123", 1, 2)

        try (BufferedReader br = new BufferedReader(new FileReader("E:\\V-D\\Java\\MachineCoding\\MOVIE TICKET booking Stuff\\input.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                executeCommand(obj, line.trim());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void executeCommand(Solution obj, String line) {
        if (line.startsWith("//") || line.isEmpty()) return; // skip comments/empty lines

        String methodName = line.substring(0, line.indexOf('(')).trim();
        String argsStr = line.substring(line.indexOf('(') + 1, line.lastIndexOf(')'));
        String[] argParts = argsStr.isEmpty() ? new String[0] : argsStr.split(",");
        
        try {
            switch (methodName) {
                case "addCinema":
                    obj.addCinema(
                        Integer.parseInt(argParts[0].trim()),
                        Integer.parseInt(argParts[1].trim()),
                        Integer.parseInt(argParts[2].trim()),
                        Integer.parseInt(argParts[3].trim()),
                        Integer.parseInt(argParts[4].trim()));
                    break;
                case "addShow":
                    obj.addShow(
                        Integer.parseInt(argParts[0].trim()),
                        Integer.parseInt(argParts[1].trim()),
                        Integer.parseInt(argParts[2].trim()),
                        Integer.parseInt(argParts[3].trim()),
                        Long.parseLong(argParts[4].trim()),
                        Long.parseLong(argParts[5].trim()));
                    break;
                case "bookTicket":
                    List<String> seats = obj.bookTicket(
                        argParts[0].trim().replaceAll("\"", ""),
                        Integer.parseInt(argParts[1].trim()),
                        Integer.parseInt(argParts[2].trim()));
                    System.out.println("Booked seats: " + seats);
                    break;
                case "cancelTicket":
                    boolean result = obj.cancelTicket(argParts[0].trim().replaceAll("\"", ""));
                    System.out.println("Cancel result: " + result);
                    break;
                case "getFreeSeatsCount":
                    int free = obj.getFreeSeatsCount(Integer.parseInt(argParts[0].trim()));
                    System.out.println("Free seats: " + free);
                    break;
                case "listCinemas":
                    List<Integer> cinemas = obj.listCinemas(
                        Integer.parseInt(argParts[0].trim()),
                        Integer.parseInt(argParts[1].trim()));
                    System.out.println("Cinemas: " + cinemas);
                    break;
                case "listShows":
                    List<Integer> shows = obj.listShows(
                        Integer.parseInt(argParts[0].trim()),
                        Integer.parseInt(argParts[1].trim()));
                    System.out.println("Shows: " + shows);
                    break;
                default:
                    System.out.println("Unknown command: " + line);
            }
        } catch (Exception e) {
            System.out.println("Error executing: " + line);
            e.printStackTrace();
        }
    }
}
// Compare this snippet from MOVIE%20TICKET%20booking%20Stuff/Solution.java: