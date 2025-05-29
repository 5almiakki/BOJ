import java.util.*;

class Solution {
    
    public int solution(int[] nums) {
        Set<Integer> kinds = new HashSet<>();
        for (int num : nums) {
            kinds.add(num);
        }
        return Math.min(nums.length / 2, kinds.size());
    }
}