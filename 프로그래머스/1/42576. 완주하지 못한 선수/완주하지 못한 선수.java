import java.util.*;

class Solution {
    
    public String solution(String[] participant, String[] completion) {
        Map<String, Integer> participants = new HashMap<>();
        for (String p : participant) {
            participants.put(p, participants.getOrDefault(p, 0) + 1);
        }
        Map<String, Integer> completions = new HashMap<>();
        for (String c : completion) {
            completions.put(c, completions.getOrDefault(c, 0) + 1);
        }
        for (Map.Entry entry : participants.entrySet()) {
            if (!entry.getValue().equals(completions.get(entry.getKey()))) {
                return (String) entry.getKey();
            }
        }
        return null;
    }
}