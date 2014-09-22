package interviewquestions.vertica;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

/**
 * MyServerFault for recoding the top K users of posting question/answer. 1) Use
 * two Minimum Heap(PriorityQueue) with initial capacity of 10 to record the
 * most frequent question post and answer post. 2) Use two hash map to record
 * the number of question/answer post of certain user. 3) Iterative through the
 * post file, if post is question/answer, then update hash map entry, compare it
 * with the root of heap to decide whether it should be added. 4) Iterative
 * through the heap, find the correspondent user name from user.xml. 5) print
 * top users.
 * 
 * Time complexity: Assume we have n posts, m users, parse one line takes P
 * time, search for top K records.
 * [1]MyServerFault: Read n posts takes O(n) time, parse userId takes O(P*n)
 * time, build or sift the heap takes O(log(K)*n) time. Read m users takes O(m)
 * time, parse userId takes O(P*m) time, parse displayName take O(k*p) time.
 * Total time complexity is O((log(k) + P)*n + P*m + k*P) time.
 * [2]ServerFault. Read m user info takes O(m) time, parse Id, DisplayName takes
 * O(2P*m). Read n posts takes O(n) time, parse Id, PostTypeId and OwnerUserId
 * takes O(3P*n) time. Select K most frequent user takes O(k*n) time. Search K
 * top user takes O(k*m) time.Therefore, total time complexity if O((k + 3P)*n +
 * (k + 2P)*m).
 * 
 * MyServerFault improved the time efficiency by (1)avoid selection sort in way 
 * of finding top K user, (2) avoid search all user K times by searching users
 * after finding top K.
 * 
 * Space complexity:
 * (1)My ServerFault: Two hash map to record the posts info, at worst O(n) in
 * total. Two priority queue to store the top users, takes K space. Two hash map
 * to store user id and display name: K space. So total space complexity is O(n)
 * space.
 * (2)ServerFaule: two list to store post and user O(n + m) space. Two hash map
 * to store user posts number of question and answer O(n). Total space
 * complexity if O(2n + m).
 * 
 * MyServerFault saved the space by (1)avoiding store all posts informations by 
 * only store needed type, (2) avoiding stored all user information by selecting
 * them after finding top K.
 * 
 * TO DO: (1)Refine parseFieldFromLine function to make parse more efficient.
 * (2) Provide a way to sort users that posted same number of questions or
 * answers.
 * 
 * @author zhangxiaokang
 */
public class MyServerFault {
    /**
     * UserStat for recording the number of a user posting question or answer.
     * amongTop used to indicate whether the record currently in heap.
     * Implemented Comparable interface for comparing in priority queue.
     */
    class UserStat implements Comparable<UserStat> {
        public String userId;
        public int posts;
        public boolean amongTop;

        public UserStat(String userId, int posts, boolean amongTop) {
            this.userId = userId;
            this.posts = posts;
            this.amongTop = amongTop;
        }

        @Override
        public int compareTo(UserStat arg) {
            return posts - arg.posts;
        }
    }

    private static int K;
    private static final String USER_ID = "Id";
    private static final String POST_TYPE_ID = "PostTypeId";
    private static final String OWNER_USER_ID = "OwnerUserId";
    private static final String DISPLAY_NAME = "DisplayName";

    /** Answer and question type ID */
    private static final String POST_TYPE_QUES = "1";
    private static final String POST_TYPE_ANSW = "2";

    /** quesMap and answMap records number of question/answer posted by a user */
    private HashMap<String, UserStat> quesMap;
    private HashMap<String, UserStat> answMap;

    /** questHeap and answHeap records the top K user post question/answers. */
    private PriorityQueue<UserStat> quesHeap;
    private PriorityQueue<UserStat> answHeap;

    /** Map from UserId to DisplayName of the top K users. */
    Map<String, String> topQuesUsers;
    Map<String, String> topAnswUsers;

    public MyServerFault(int topK) {
        K = topK;
        quesMap = new HashMap<String, UserStat>();
        answMap = new HashMap<String, UserStat>();
        quesHeap = new PriorityQueue<UserStat>(K);
        answHeap = new PriorityQueue<UserStat>(K);
        topQuesUsers = new HashMap<String, String>(K);
        topAnswUsers = new HashMap<String, String>(K);
    }

    public void run() throws FileNotFoundException, IOException {
        /** Do statistics on posts data */
        statisPosts("posts-short.xml");

        /** Initiate top user map */
        initTopUserMap(quesHeap, topQuesUsers);
        initTopUserMap(answHeap, topAnswUsers);

        /** Find top user display names */
        findTopUserNamesFromFile("users-short.xml");

        /** Print top users */
        System.out.println("Top 10 users with the most questions:");
        printTopUsers(quesHeap, topQuesUsers);
        System.out.println();
        System.out.println();
        System.out.println("Top 10 users with the most answers:");
        printTopUsers(answHeap, topAnswUsers);
    }

    /**
     * Do statistics on posts data. If post type is question/answer, update the
     * statistic data.
     */
    void statisPosts(String filename) throws FileNotFoundException, IOException {
        BufferedReader b = new BufferedReader(new InputStreamReader(
                new FileInputStream(filename), Charset.forName("UTF-8")));
        String line;
        while ((line = b.readLine()) != null) {
            String postType = parseFieldFromLine(line, POST_TYPE_ID);
            if (postType.equals(POST_TYPE_QUES)) {
                String owernUserID = parseFieldFromLine(line, OWNER_USER_ID);
                updateUserStatic(owernUserID, quesHeap, quesMap);
            } else if (postType.equals(POST_TYPE_ANSW)) {
                String owernUserID = parseFieldFromLine(line, OWNER_USER_ID);
                updateUserStatic(owernUserID, answHeap, answMap);
            }
        }
        b.close();
    }

    /** Find top K users of their names from file based */
    public void findTopUserNamesFromFile(String filename) throws IOException {
        BufferedReader b = new BufferedReader(new InputStreamReader(
                new FileInputStream(filename), Charset.forName("UTF-8")));
        String line;
        while ((line = b.readLine()) != null) {
            String userId = parseFieldFromLine(line, USER_ID);
            if (topQuesUsers.containsKey(userId)) {
                String displayName = parseFieldFromLine(line, DISPLAY_NAME);
                topQuesUsers.put(userId, displayName);
            }
            if (topAnswUsers.containsKey(userId)) {
                String displayName = parseFieldFromLine(line, DISPLAY_NAME);
                topAnswUsers.put(userId, displayName);
            }
        }
        b.close();
    }

    /**
     * Update the statistics of a user. If user already in heap, remove and
     * re-add it to sift the heap. If user not in heap, check if heap is full.
     * If heap not full, add directly, if full, check if posts more then the
     * root user. If it is, poll the root user then add current user.
     */
    private void updateUserStatic(String ownerUserId,
            PriorityQueue<UserStat> heap, HashMap<String, UserStat> map) {
        UserStat userStat;
        if (map.containsKey(ownerUserId)) {
            userStat = map.get(ownerUserId);
            userStat.posts++;
        } else {
            userStat = new UserStat(ownerUserId, 1, false);
            map.put(ownerUserId, userStat);
        }

        if (userStat.amongTop) {
            heap.remove(userStat);
            heap.add(userStat);
        } else {
            if (heap.size() < K) {
                heap.add(userStat);
                userStat.amongTop = true;
            } else if (heap.peek() != null
                    && heap.peek().posts < userStat.posts) {
                heap.peek().amongTop = false;
                heap.poll();
                heap.add(userStat);
                userStat.amongTop = true;
            }
        }
    }

    /** Initiate top user map */
    public void initTopUserMap(PriorityQueue<UserStat> topUserHeap,
            Map<String, String> topUsers) {
        UserStat[] stats = new UserStat[topUserHeap.size()];
        topUserHeap.toArray(stats);
        for (UserStat userStat : stats)
            topUsers.put(userStat.userId, null);
    }

    /** TO BE REFINED */
    public String parseFieldFromLine(String line, String key) {
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

    /** Print top users with topUsers heap and information map */
    private void printTopUsers(PriorityQueue<UserStat> topUsers,
            Map<String, String> topUsersInfo) {
        List<UserStat> topUsersList = convMinHeapToSortedList(topUsers);
        for (UserStat userStat : topUsersList) {
            System.out.print(userStat.posts);
            System.out.print('\t');
            System.out.println(topUsersInfo.get(userStat.userId));
        }
    }

    /** Convert the minimum heap into a sorted list with reverse order */
    private List<UserStat> convMinHeapToSortedList(
            PriorityQueue<UserStat> topUsers) {
        LinkedList<UserStat> topUsersList = new LinkedList<UserStat>();
        while (!topUsers.isEmpty()) {
            topUsersList.addFirst(topUsers.poll());
        }
        return topUsersList;
    }

    public static void main(String[] args) throws FileNotFoundException,
            IOException {
        MyServerFault s = new MyServerFault(10);
        s.run();
    }

}
