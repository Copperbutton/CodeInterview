package interviewquestions.other;

import java.io.*;
import java.util.*;

/**
 * Code interview questions from Yelp.
 * Time: 17/9/2014
 * Loc: CMU, PIT
 */

public class FindTopKRecords {
    private class ItemVisit implements Comparable<ItemVisit> {
        private String item;
        private int visit;

        public ItemVisit(String item, int visit) {
            this.item = item;
            this.visit = visit;
        }

        public int compareTo(ItemVisit item) {
            if (item == null)
                return 1;
            return item.visit - this.visit;
        }
    }
    
    private List<String> readLogs(String filename) throws IOException {
        List<String> logList = new ArrayList<String> ();
        FileReader reader = new FileReader(filename);
        BufferedReader buff = new BufferedReader(reader);
        String logLine = buff.readLine();
        while (logLine != null) {
            logList.add(logLine);
            logLine = buff.readLine();
        }
        buff.close();
        return logList;
    }

    public List<String> findTopKRecords(List<String> logList, int K) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        PriorityQueue<ItemVisit> queue = new PriorityQueue<ItemVisit>();
        List<String> topRecords = new ArrayList<String>();
        for (String log : logList) {
            String[] tokens = log.split("\t");
            // If tokens.length < N
            // log.add("Error: log format error" + log);
            // continue;
            String item = fetchItemFromSearchURL(tokens[3]);
            int count = 1;
            if (map.containsKey(item)) {
                count = map.get(item) + 1;
            }
            map.put(item, count);
        }

        for (String item : map.keySet()) {
            queue.offer(new ItemVisit(item, map.get(item)));
        }

        for (int i = 0; i < K; i++) {
            topRecords.add(queue.poll().item);
        }
        return topRecords;
    }

    private String fetchItemFromSearchURL(String URL) {
        //To be implemented
        return new String();
    }
}
