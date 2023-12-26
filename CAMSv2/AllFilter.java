package CAMSv2;

import java.util.HashSet;

/**
 * The {@code AllFilter} implements the ReportFilter interface and represents
 * a filter that includes all students in the input set without any filtering.
 *
 * @author Zhu Yu Hao
 * @since 2023-11-24
 */

public class AllFilter implements IReportFilter{

    /**
     * Returns the input set of students without any filtering.
     *
     * @param students The set of students to be returned without filtering.
     * @return The input set of students without any changes.
     */

    @Override
    public HashSet<Student> getFilteredList(HashSet<Student> students) {
        return students;
    }
    
}
