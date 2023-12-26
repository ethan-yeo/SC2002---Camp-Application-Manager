package CAMSv2;

import java.util.HashSet;
/**
 * Implementation of the {@code ReportFilter} interface for filtering students who are Camp Committee Members (CCMs).
 */
public class CCMFilter implements IReportFilter{

    /**
     * Filters the provided set of students to include only those who are Camp Committee Members (CCMs).
     *
     * @param students The set of students to be filtered.
     * @return A filtered set containing only Camp Committee Members.
     */
    @Override
    public HashSet<Student> getFilteredList(HashSet<Student> students) {
        HashSet<Student> filteredList = new HashSet<Student>();
        for (Student student : students) {
            if (student.getRole().equals(Role.CAMP_COMMITTEE_MEMBER)) {
                filteredList.add(student);
            }
        }
        return filteredList;
    }
    
}
