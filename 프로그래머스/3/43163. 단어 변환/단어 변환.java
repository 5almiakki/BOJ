import java.util.*;

class Solution {
    
    public int solution(String begin, String target, String[] words) {
        Set<String> visitedWords = new HashSet<>();
        Map<String, List<String>> adjWordList = new HashMap<>();
        adjWordList.put(begin, new ArrayList<>());
        for (int i = 0; i < words.length; i++) {
            if (isTransformable(begin, words[i])) {
                adjWordList.get(begin).add(words[i]);
            }
            adjWordList.put(words[i], new ArrayList<>());
        }
        for (int i = 0; i < words.length - 1; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if (isTransformable(words[i], words[j])) {
                    adjWordList.get(words[i]).add(words[j]);
                    adjWordList.get(words[j]).add(words[i]);
                }
            }
        }
        Queue<WordCnt> queue = new ArrayDeque<>();
        queue.add(new WordCnt(begin, 0));
        visitedWords.add(begin);
        while (!queue.isEmpty()) {
            WordCnt currentWordCnt = queue.remove();
            if (currentWordCnt.word.equals(target)) {
                return currentWordCnt.cnt;
            }
            for (String adjWord : adjWordList.get(currentWordCnt.word)) {
                if (visitedWords.contains(adjWord)) {
                    continue;
                }
                WordCnt newWordCnt = new WordCnt(adjWord, currentWordCnt.cnt + 1);
                queue.add(newWordCnt);
                visitedWords.add(newWordCnt.word);
            }
        }
        return 0;
    }
    
    boolean isTransformable(String word, String targetWord) {
        int differentCharCnt = 0;
        char[] cs1 = word.toCharArray();
        char[] cs2 = targetWord.toCharArray();
        for (int i = 0; i < cs1.length; i++) {
            if (cs1[i] != cs2[i]) {
                differentCharCnt++;
            }
            if (differentCharCnt >= 2) {
                return false;
            }
        }
        return differentCharCnt == 1;
    }
    
    class WordCnt {
        
        String word;
        int cnt;
        
        WordCnt(String word, int cnt) {
            this.word = word;
            this.cnt = cnt;
        }
        
    }
    
}