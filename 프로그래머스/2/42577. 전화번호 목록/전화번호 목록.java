import java.util.*;

class Solution {
    public boolean solution(String[] phoneBook) {
        Set<String> phoneNums = new HashSet<>();
        for (String phoneNum : phoneBook) {
            phoneNums.add(phoneNum);
        }
        for (String phoneNum : phoneNums) {
            for (int i = 1; i < phoneNum.length(); i++) {
                if (phoneNums.contains(phoneNum.substring(0, i))) {
                    return false;
                }
            }
        }
        return true;
    }
}