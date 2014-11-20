package interviewquestions.facebook;

import java.util.*;

/**
 * The key idea is to find a celebrity as quick as possible and then exclude all
 * other normal people.
 */
public class FindCelebrities {
    public List<String> getCelebrities(String[] people) {
        List<String> result = new ArrayList<String>();
        if (people == null)
            return result;

        int len = people.length;
        boolean[] normal = new boolean[len];
        for (int i = 0; i < len; i++) {
            // If this is a normal people, skip it
            if (normal[i])
                continue;

            // If has not yet decided, then check every other p
            for (int j = 0; j < len; j++) {
                if (j == i)
                    continue;
                // If i knows j, then i must not be celebrity
                if (knows(people[i], people[j])) {
                    normal[i] = true;
                    break;
                }
                // If i does not know j, then j not a celebrity;
                normal[j] = true;
            }
        }

        for (int i = 0; i < len; i++) {
            if (normal[i])
                continue;
            result.add(people[i]);
            break;
        }
        return result;
    }

    private boolean knows(String A, String B) {
        return true;
    }
}
