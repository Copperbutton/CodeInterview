package interviewquestions.google;

public class GetShortestStringRepresentClient {
    public String[] getShortestRepresent(String[] strs) {
        if (strs == null)
            return null;
        int len = strs.length;
        ShortestStringRepresent sol = new ShortestStringRepresent();
        for (String str : strs)
            sol.put(str);
        String[] represent = new String[len];
        for (int i = 0; i < len; i++)
            represent[i] = sol.getShortestRepresent(strs[i]);
        return represent;
    }
}
