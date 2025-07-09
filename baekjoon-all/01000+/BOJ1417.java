/*
 * 백준 1417번 : 국회의원 선거
 * https://www.acmicpc.net/problem/1417
 * 난이도 : 실버 5
 * 풀이 날짜 : 2025-06-21
 * 간단 설명 : 다솜이가 가장 많은 득표를 받을 수 있게 매수하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BOJ_1417 {
	// 입력을 위한 객체 선언
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine()); // 후보의 수
		int dasom = Integer.parseInt(br.readLine()); // 다솜의 표 수
		for (int i = 0; i < N - 1; i++) {
			int otherCandidate = Integer.parseInt(br.readLine()); // 다른 후보 표 수
			// 다솜이가 받은 표보다 같거나 많은 표만 추가
			if (dasom <= otherCandidate) maxHeap.offer(otherCandidate);
		}
		
		// 버퍼 닫기
		br.close();
		
		int minBuyVote = 0; // 매수해야 하는 최소 투표 수
		while (true) {
			// 다솜이가 가장 많은 득표를 혼자 가지고 있으면 종료
			if (maxHeap.size() == 0 || maxHeap.peek() < dasom) break;
			// 다솜이를 제외한 가장 많은 득표자가
			// 다솜이보다 표가 많거나 같은 경우 
			else {
				int otherVote = maxHeap.poll(); // 최다 득표자의 득표 수
				// 한 표 매수
				dasom++;
				maxHeap.offer(--otherVote);
				minBuyVote++;
			}
		}
		
		// 결과값 출력하기
		System.out.print(minBuyVote);
	} // main 종료
	
	static int N; // 후보의 수
	static PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a); // 최대 힙
	
} // class 종료