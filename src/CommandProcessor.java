import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * The purpose of this class is to parse a text file into its appropriate, line
 * by line commands for the format specified in the project spec.
 */
public class CommandProcessor {

    private Database data;

    /**
     * Constructor that initializes a new database instance
     */
    public CommandProcessor() {
        data = new Database();
    }


    /**
     * Constructor that allows a custom database instance to be passed
     * 
     * @param dataIn
     *            The database object to manipulate
     */
    public CommandProcessor(Database dataIn) {
        data = dataIn != null ? dataIn : new Database();
    }


    /**
     * Reads the command file and processes each line.
     * 
     * @param commandFile
     *            The file containing the commands to be processed.
     * @throws FileNotFoundException
     *             If the file doesn't exist.
     */
    public void readCmdFile(File commandFile) throws FileNotFoundException {
        Scanner scanner = new Scanner(commandFile);

        // Reads each line of the command file
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (!line.isEmpty()) {
                processCommand(line);
            }
        }

        // Close the scanner after processing all lines
        scanner.close();
    }


    /**
     * Processes a single command line from the file.
     * 
     * @param line
     *            A single command line to be processed.
     */
    public void processCommand(String line) {
        String[] parts = line.split("\\s+");
        String command = parts[0];

        if (command.equals("insert") && parts.length == 4) {
            String name = parts[1];
            int x = Integer.parseInt(parts[2]);
            int y = Integer.parseInt(parts[3]);
            data.insert(name, x, y);
        }
        else if (command.equals("remove") && parts.length == 2) {
            String name = parts[1];
            data.remove(name);
        }
        else if (command.equals("remove") && parts.length == 3) {
            int x = Integer.parseInt(parts[1]);
            int y = Integer.parseInt(parts[2]);
            data.remove(x, y);
        }
        else if (command.equals("regionsearch") && parts.length == 5) {
            int x = Integer.parseInt(parts[1]);
            int y = Integer.parseInt(parts[2]);
            int w = Integer.parseInt(parts[3]);
            int h = Integer.parseInt(parts[4]);
            data.regionsearch(x, y, w, h);
        }
        else if (command.equals("duplicates")) {
            data.duplicates();
        }
        else if (command.equals("search") && parts.length == 2) {
            String name = parts[1];
            data.search(name);
        }
        else if (command.equals("dump")) {
            data.dump();
        }
        else {
            System.out.println("Unrecognized command: " + line);
        }
    }


    /**
     * Retrieves the currently stored database object.
     * 
     * @return The currently held Database object.
     */
    public Database getData() {
        return data;
    }
}
