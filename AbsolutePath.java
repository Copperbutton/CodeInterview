package interviewquestions.facebook;

import java.util.*;

/**
 * Given a current absolute path, e.g., "/usr/bin/mail", and a relative one,
 * e.g, "../../../etc/xyz/../abc" return the absolute path created from the
 * combination of the first two paths. In the example strings, the answer should
 * be "/etc/abc".
 */

public class AbsolutePath {
    public String getAbsolutePath(String absolute, String relative) {
        Stack<String> stack = new Stack<String>();

        // Add absolute path to stack
        String[] abTokens = absolute.trim().split("/");
        for (String token : abTokens) {
            if (token.equals(""))
                continue;
            stack.push(token);
        }

        // Put relative path to stack
        String[] relTokens = relative.split("/");
        for (String token : relTokens) {
            if (token.equals(".") || token.equals(""))
                continue;
            else if (token.equals("..")) {
                if (!stack.isEmpty())
                    stack.pop();
            } else {
                stack.push(token);
            }
        }

        // Convert stack to string builder
        StringBuilder builder = new StringBuilder();
        while (!stack.isEmpty()) {
            builder.insert(0, stack.pop());
            builder.insert(0, "/");
        }

        return builder.length() == 0 ? "/" : builder.toString();
    }
}
