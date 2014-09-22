package interviewquestions.vertica;

import java.lang.String;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;

public class ServerFault {

    public ServerFault() {
        users = new ArrayList<User>();
        posts = new ArrayList<Post>();
    }

    // Represents an entry in users.xml
    // Some fields omitted due to laziness
    class User {
        public String Id;
        public String DisplayName;
    };

    // Represents an entry in posts.xml
    // Some fields omitted due to laziness
    class Post {
        public String Id;
        public String PostTypeId;
        public String OwnerUserId;
    };

    String parseFieldFromLine(String line, String key) {
        // We're looking for a thing that looks like:
        // [key]="[value]"
        // as part of a larger String.
        // We are given [key], and want to return [value].

        // Find the start of the pattern
        String keyPattern = key + "=\"";
        int idx = line.indexOf(keyPattern);

        // No match
        if (idx == -1)
            return "";

        // Find the closing quote at the end of the pattern
        int start = idx + keyPattern.length();

        int end = start;
        while (line.charAt(end) != '"') {
            end++;
        }

        // Extract [value] from the overall String and return it
        return line.substring(start, end);
    }

    // Keep track of all users
    ArrayList<User> users;

    void readUsers(String filename) throws FileNotFoundException, IOException {
        BufferedReader b = new BufferedReader(new InputStreamReader(
                new FileInputStream(filename), Charset.forName("UTF-8")));
        String line;
        while ((line = b.readLine()) != null) {
            User u = new User();
            u.Id = parseFieldFromLine(line, "Id");
            u.DisplayName = parseFieldFromLine(line, "DisplayName");
            users.add(u);
        }
    }

    // Keep track of all posts
    ArrayList<Post> posts;

    void readPosts(String filename) throws FileNotFoundException, IOException {
        BufferedReader b = new BufferedReader(new InputStreamReader(
                new FileInputStream(filename), Charset.forName("UTF-8")));
        String line;
        while ((line = b.readLine()) != null) {
            Post p = new Post();
            /** Why parse it so many times? can't we just read into file? */
            p.Id = parseFieldFromLine(line, "Id");
            p.PostTypeId = parseFieldFromLine(line, "PostTypeId");
            p.OwnerUserId = parseFieldFromLine(line, "OwnerUserId");
            posts.add(p);
        }
    }

    /**
     * Instead of find it by scanning, we can use a map to record userID and
     * name, then recreate a user.
     * */
    User findUser(String Id) {
        for (User u : users) {
            if (u.Id.equals(Id)) {
                return u;
            }
        }
        return new User();
    }

    // Some data for the map
    class MapData {
        public String DisplayName;
        public int Count;
    };

    public void run() throws FileNotFoundException, IOException {
        // Load our data
        readUsers("/Users/zhangxiaokang/Documents/Work&Intern/Interview/Vertica/users-short.xml");
        readPosts("/Users/zhangxiaokang/Documents/Work&Intern/Interview/Vertica/posts-short.xml");

        // Calculate the users with the most questions
        /**
         * We can use a heap to store the questions.
         */
        Map<String, MapData> questions = new HashMap<String, MapData>();

        for (int i = 0; i < posts.size(); i++) {
            Post p = posts.get(i);
            /** This can be very time consuming*/
            User u_p = findUser(p.OwnerUserId);
            if (questions.get(u_p.Id) == null) {
                questions.put(u_p.Id, new MapData());
            }
            questions.get(u_p.Id).DisplayName = u_p.DisplayName;
            if (p.PostTypeId.equals("1")) {
                questions.get(u_p.Id).Count++;
            }
        }

        System.out.println("Top 10 users with the most questions:");
        for (int i = 0; i < 10; i++) {
            String key = "";
            MapData max_data = new MapData();
            max_data.DisplayName = "";
            max_data.Count = 0;

            for (Map.Entry<String, MapData> it : questions.entrySet()) {
                if (it.getValue().Count >= max_data.Count) {
                    key = it.getKey();
                    max_data = it.getValue();
                }
            }

            questions.remove(key);

            System.out.print(max_data.Count);
            System.out.print('\t');
            System.out.println(max_data.DisplayName);
        }

        System.out.println();
        System.out.println();

        // Calculate the users with the most answers
        /**
         * If we only need post type of question and answer, why do we need so many?
         * */
        
        Map<String, MapData> answers = new HashMap<String, MapData>();

        for (int i = 0; i < posts.size(); i++) {
            Post p = posts.get(i);
            User u_p = findUser(p.OwnerUserId);
            if (answers.get(u_p.Id) == null) {
                answers.put(u_p.Id, new MapData());
            }
            answers.get(u_p.Id).DisplayName = u_p.DisplayName;
            if (p.PostTypeId.equals("2")) {
                answers.get(u_p.Id).Count++;
            }
        }

        System.out.println("Top 10 users with the most answers:");
        for (int i = 0; i < 10; i++) {
            String key = "";
            MapData max_data = new MapData();
            max_data.DisplayName = "";
            max_data.Count = 0;

            for (Map.Entry<String, MapData> it : answers.entrySet()) {
                if (it.getValue().Count >= max_data.Count) {
                    key = it.getKey();
                    max_data = it.getValue();
                }
            }

            answers.remove(key);

            System.out.print(max_data.Count);
            System.out.print('\t');
            System.out.println(max_data.DisplayName);
        }
    }

    public static void main(String[] args) throws FileNotFoundException,
            IOException {
        ServerFault s = new ServerFault();
        s.run();
    }

}
