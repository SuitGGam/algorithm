/*
 * 백준 1715번 : 카드 정렬하기
 * https://www.acmicpc.net/problem/1715
 * 난이도 : 골드 4
 * 풀이 날짜 : 2025-06-21
 * 간단 설명 : 가장 적은 카드 묶음 2개를 합치면서 최소 비교 횟수를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BOJ_1715 {
	// 입력을 위한 객체 선언
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine()); // 묶음의 개수
		for (int i = 0; i < N; i++) {
			minHeap.offer(Integer.parseInt(br.readLine())); // 묶음 추가
		}
		
		// 버퍼 닫기
		br.close();
		
		int minCompare = 0; // 최소 비교 횟수
		while (minHeap.size() > 1) {
			// 카드 묶음이 하나만 남으면 종료
			// 현재 누적 카드 묶음 수 + 다음 카드 묶음 수
			// 합쳐진 카드 뭉치도 하나의 묶음으로 봐야 함
			int cardsA = minHeap.poll(); // 합치려는 카드 묶음 A
			int cardsB = minHeap.poll(); // 합치려는 카드 묶음 B
			int cardsSum = cardsA + cardsB; // A와 B의 합친 카드 묶음
			minCompare += cardsSum; // 최소 비교 횟수 갱신
			minHeap.offer(cardsSum); // 카드 묶음 다시 넣기
		}
		
		// 결과값 출력하기
		System.out.print(minCompare);
	} // main 종료
	
	static int N; // 묶음의 개수
	static PriorityQueue<Integer> minHeap = new PriorityQueue<>(); // 최소 힙
	
} // class 종료