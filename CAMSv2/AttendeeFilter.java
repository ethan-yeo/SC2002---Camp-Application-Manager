package CAMSv2;

import java.util.HashSet;

/**
 * The {@code AttendeeFilter} class implements the ReportFilter interface to filter
 * a set of students based on their roles.
 *
 * @author Zhu Yu Hao
 * @since 2023-11-24
 */

public class AttendeeFilter implements IReportFilter{

    /**
     * Filters the input set of students to include only those with the role "STUDENT".
     *
     * @param students The set of students to be filtered.
     * @return A HashSet containing only students with the role "STUDENT".
     */

    @Override
    public HashSet<Student> getFilteredList(HashSet<Student> students) {
        HashSet<Student> filteredList = new HashSet<Student>();
        for (Student student : students) {
            if (student.getRole().equals(Role.STUDENT)) {
                filteredList.add(student);
            }
        }
        return filteredList;
    }
    
}
