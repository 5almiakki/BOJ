import java.util.*;

class Solution {

    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        int answerIdx = 0;
        for (int[] command : commands) {
            int[] subarray = Arrays.copyOfRange(array, command[0] - 1, command[1]);
            Arrays.sort(subarray);
            answer[answerIdx] = subarray[command[2] - 1];
            answerIdx++;
        }
        return answer;
    }
}