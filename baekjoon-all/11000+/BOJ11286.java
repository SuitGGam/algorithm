/*
 * 백준 11286번 : 절댓값 힙
 * https://www.acmicpc.net/problem/11286
 * 난이도 : 실버 1
 * 풀이 날짜 : 2025-06-21
 * 간단 설명 : 절댓값 최소 힙 사용법을 익히는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BOJ11286 {
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
				if (absolMinHeap.size() == 0) sb.append(0).append("\n");
				// 큐가 안 비어있는 경우
				else sb.append(absolMinHeap.poll()).append("\n");
			}
			// x가 0이 아닌 경우
			else absolMinHeap.offer(x);
		} // 연산 종료
		
		// 버퍼 닫기
		br.close();
		
		// 결과값 출력하기
		System.out.print(sb);
	} // main 종료
	
	static int N; // 연산의 개수
	static PriorityQueue<Integer> absolMinHeap = new PriorityQueue<>(
		(a, b) -> {
			int absA = Math.abs(a); // 절댓값 A
			int absB = Math.abs(b); // 절댓값 B
			// 절댓값이 작은 것 리턴
			if (absA != absB) return absA - absB;
			// 절댓값이 같으면 실제 값이 더 작은 것 리턴
			return a - b;
		}	
	); // 절댓값 최소 힙
	
} // class 종료