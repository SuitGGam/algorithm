/*
 * 백준 11279번 : 최대 힙
 * https://www.acmicpc.net/problem/11279
 * 난이도 : 실버 2
 * 풀이 날짜 : 2025-06-21
 * 간단 설명 : 최대 힙 사용법을 익히는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BOJ_11279 {
	// 입력을 위한 객체 선언
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine()); // 연산의 개수
		StringBuilder sb = new StringBuilder(); // 결과값을 저장하는 객체
		for (int i = 0; i < N; i++) { // 연산 시작
			int x = Integer.parseInt(br.readLine()); // 주어지는 숫자
			// x가 0인 경우
			if (x == 0) {
				// 큐가 비어있는 경우
				if (maxHeap.size() == 0) sb.append(0).append("\n");
				// 큐가 안 비어있는 경우
				else sb.append(maxHeap.poll()).append("\n");
			}
			// x가 자연수인 경우
			else maxHeap.offer(x);
		} // 연산 종료
		
		// 버퍼 닫기
		br.close();
		
		// 결과값 출력하기
		System.out.print(sb);
	} // main 종료
	
	static int N; // 연산의 개수
	static PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a); // 최대 힙
	
} // class 종료