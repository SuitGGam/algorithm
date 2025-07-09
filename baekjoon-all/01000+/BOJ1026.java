/*
 * 백준 1026번 : 보물
 * https://www.acmicpc.net/problem/1026
 * 난이도 : 실버 4
 * 풀이 날짜 : 2025-06-20
 * 간단 설명 : 두 배열 중 하나의 배열만 순서를 재배정해서 두 배열의 최소 곱을 만드는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.PriorityQueue;
import java.util.Arrays;

public class BOJ_1026 {
	// 입력을 위한 객체 선언
	static BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine()); // 배열의 길이
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			A.offer(Integer.parseInt(st.nextToken())); // A 배열 추가
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		B = new int[N]; // B 배열 크기 지정
		for (int i = 0; i < N; i++) {
			B[i] = Integer.parseInt(st.nextToken()); // B 배열 저장
		}
		
		// 버퍼 닫기
		br.close();
		
		Arrays.sort(B); // B배열 비내림차순 정렬
		int minSum = 0; // 최소 합계
		for (int i = 0; i < N; i++) {
			// 제일 큰 A의 값과
			// 제일 작은 B의 값을 곱해서 더해주기
			minSum += B[i] * A.poll();
		}
		
		// 결과값 출력하기
		System.out.print(minSum);
	} // main 종료
	
	static int N; // 배열의 길이
	static PriorityQueue<Integer> A = new PriorityQueue<>((a, b) -> b - a); // 최댓값부터 꺼낼 우선순위 큐 A
	static int[] B; // B 배열
	
} // class 종료