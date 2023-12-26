package CAMSv2;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;

/**

 * The {@code CampCommitteeDatabase} class represents Database manager for Camp Committee Members, handling data loading, writing, and retrieval.
 * This class manages a HashSet of CampCommitteeMember objects and handles CSV file operations.
 * @author Zhu YuHao
 * @since 13-11-2023
 */

public class CampCommitteeDataBase extends DataBase{
    private static CampCommitteeDataBase instance;
    private HashSet<CampCommitteeMember> campCommitteeMembersList = new HashSet<>();
    private String filePath = System.getProperty("user.dir") + "\\CAMSv2\\Data CSV\\CampCommitteeMember_List.csv";



    private CampCommitteeDataBase() {
    }

    /**
     * Retrieves the singleton instance of CampCommitteeDataBase.
     * @return The instance of CampCommitteeDataBase.
     */
    // can remove filePath argument and instead intialize into the class itself
    public static CampCommitteeDataBase getInstance() {
        if (instance == null) {
            instance = new CampCommitteeDataBase();
        }
        return instance;
    }

    /**
     * Prints the CCM (Camp Committee Member) database.
     * This method prints the names and email IDs of
     * all camp committee members stored in the list.
     */
    public void printList() {
        System.out.println("PRINTING CCM DATABASE...");
        for (CampCommitteeMember campCommitteeMember : campCommitteeMembersList) {
            System.out.println(campCommitteeMember.getName());
            System.out.println(campCommitteeMember.getEmailID());
        }
    }


    /**
     * Loads data from the CSV file into the database.
     */
    public void loadToCSV(){
        campCommitteeMembersList.clear();
        try (InputStream inputStream = new FileInputStream(this.filePath);
             Reader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(inputStreamReader)) {
            reader.mark(1);
            String line;

            if (reader.read() != 0xFEFF) {
                reader.reset(); // Reset if the first character is not the BOM
            }

            while ((line = reader.readLine()) != null) {
                if (line.equals("")) {return;}

                String[] values = line.split(",");
                String name = values[0].trim();
                String emailID = values[1];
                String faculty = values[2].trim();
                String password = values[3].trim();
                String campName = values[4].trim();

                UserGroup userGroup = UserGroup.NTU;
                // convert faculty to user group
                try {
                    userGroup = UserGroup.valueOf(faculty);
                } catch (Exception e) {
                    System.out.println("Cannot convert UserGroup in CSV into ENUM");
                }
                Camp camp = CampManager.getInstance().getCamp(campName);
                // if (camp == null) {return;}
                CampCommitteeMember campCommitteeMember = new CampCommitteeMember(name, emailID, userGroup, password, Role.CAMP_COMMITTEE_MEMBER, camp);
                campCommitteeMembersList.add(campCommitteeMember);
            }
        } catch (IOException e) {e.printStackTrace();}

    }

    /**
     * Writes the database content to the CSV file.
     */
    public void writeToCSV() {
        try (PrintWriter printWriter = new PrintWriter(new FileWriter(filePath, false))) {
            if(campCommitteeMembersList.size() == 0){
                // printWriter.println("");
                return;
            }
            for (CampCommitteeMember campCommitteeMember: campCommitteeMembersList) {
                if (campCommitteeMember.getCamp() == null) {continue;}
                printWriter.println(campCommitteeMember.getName() + "," + campCommitteeMember.getEmailID() + "," +
                                    campCommitteeMember.getFaculty() + "," + campCommitteeMember.getPassword() + "," +
                                    campCommitteeMember.getCamp().getCampName());
            }
        }
        catch (IOException e) {
            System.out.println("CSV file not found");
        }
    }

    /**
     * Retrieves the Camp Committee Members list.
     * @return The HashSet containing Camp Committee Members.
     */
    public HashSet<CampCommitteeMember> getCampCommitteeMembersList() {
        return campCommitteeMembersList;
    }

}
