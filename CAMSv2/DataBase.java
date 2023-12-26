package CAMSv2;


/**
 * The {@code DataBase} is an Abstract class representing a database with methods to load data from and write data to a CSV file.
 */
public abstract class DataBase {

    /**
     * Loads data from a CSV file into the database.
     */
    public abstract void loadToCSV();

    /**
     * Writes data from the database to a CSV file.
     */
    public abstract void writeToCSV();
}
