/*
 * 백준 2075번 : N번째 큰 수
 * https://www.acmicpc.net/problem/2075
 * 난이도 : 실버 3
 * 풀이 날짜 : 2025-06-21
 * 간단 설명 : 최대 힙을 활용하여 N번째 큰 수를 찾는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.PriorityQueue;

public class BOJ2075 {
	// 입력을 위한 객체 선언
	static BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine()); // 연산의 개수
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				maxHeap.offer(Integer.parseInt(st.nextToken())); // 숫자 추가
			}
		}
		
		// 버퍼 닫기
		br.close();
		
		for (int i = 0; i < N - 1; i++) {
			// N - 1번째까지 큰 숫자 제거
			maxHeap.poll();
		}
		
		int NthNum = maxHeap.poll(); // N번째 큰 숫자
		
		// 결과값 출력하기
		System.out.print(NthNum);
	} // main 종료
	
	static int N; // 표의 크기
	static PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a); // 최대 힙
	
} // class 종료