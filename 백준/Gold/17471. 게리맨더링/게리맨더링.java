
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Main {
	
	static boolean[] isSelected;
	static int areaCnt;
	static int[] populations;
	static List<List<Integer>> adjList;
	static int result = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		areaCnt = Integer.parseInt(br.readLine());
		isSelected = new boolean[areaCnt];
		populations = new int[areaCnt];
		String[] input = br.readLine().split(" ");
		for (int i = 0; i < areaCnt; i++) {
			populations[i] = Integer.parseInt(input[i]);
		}
		adjList = new ArrayList<>();
		for (int i = 0; i < areaCnt; i++) {
			adjList.add(new ArrayList<>());
			input = br.readLine().split(" ");
			for (int j = 1; j < input.length; j++) {
				adjList.get(i).add(Integer.parseInt(input[j]) - 1);
			}
		}
		br.close();

		if (isValid()) {
			int difference = computeDifference();
			if (difference < result) {
				result = difference;
			}
		}
		backTrack(-1);
		System.out.print((result == Integer.MAX_VALUE) ? -1 : result);
	}
	
	static void backTrack(int depth) {
		if (depth == areaCnt / 2) {
			return;
		}
		
		for (int i = depth + 1; i < areaCnt; i++) {
			isSelected[i] = true;
			if (isValid()) {
				int difference = computeDifference();
				if (difference < result) {
					result = difference;
				}
			}
			backTrack(i);
			isSelected[i] = false;
		}
	}
	
	static boolean isValid() {
		int selectedArea = -1;
		for (int i = 0; i < areaCnt; i++) {
			if (isSelected[i]) {
				selectedArea = i;
				break;
			}
		}
		int notSelectedArea = -1;
		for (int i = 0; i < areaCnt; i++) {
			if (!isSelected[i]) {
				notSelectedArea = i;
				break;
			}
		}
		
		int isVisited = 0;
		if (selectedArea != -1) {
			isVisited |= bfs(selectedArea, true);
		}
		if (notSelectedArea != -1) {
			isVisited |= bfs(notSelectedArea, false);
		}
		return ((1 << areaCnt) - 1) == isVisited;
	}
	
	static int bfs(int source, boolean isSelected) {
		int isVisited = 0;
		Queue<Integer> queue = new ArrayDeque<>();
		queue.add(source);
		isVisited |= 1 << source;
		
		while (!queue.isEmpty()) {
			int currentArea = queue.remove();
			for (int adjNode : adjList.get(currentArea)) {
				if (((1 << adjNode) & isVisited) > 0 || Main.isSelected[adjNode] != isSelected) {
					continue;
				}
				queue.add(adjNode);
				isVisited |= 1 << adjNode;
			}
		}
		return isVisited;
	}
	
	static int computeDifference() {
		int selectedSum = 0;
		int notSelectedSum = 0;
		for (int i = 0; i < areaCnt; i++) {
			if (isSelected[i]) {
				selectedSum += populations[i];
			} else {
				notSelectedSum += populations[i];
			}
		}
		return Math.abs(selectedSum - notSelectedSum);
	}

}
