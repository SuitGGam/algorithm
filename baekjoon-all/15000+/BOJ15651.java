/*
 * 백준 15651번 : N과 M (3)
 * https://www.acmicpc.net/problem/15651
 * 난이도 : 실버 3
 * 풀이 날짜 : 2025-08-29
 * 간단 설명 : 중복순열을 만드는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ15651 {
	// 입력을 위한 객체 선언
	static BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int N, M; // 자연수의 개수, 고를 숫자의 개수
	static int[] selected; // 고른 숫자를 담을 배열
	static boolean[] visited; // 방문 처리를 할 배열
	
	static StringBuilder sb = new StringBuilder(); // 결과값을 담을 객체
	static void permutationWithRepetition(int start, int depth) { // 중복순열을 만드는 함수
		// 종료 조건
		if (depth > M) {
			for (int i = 1; i <= M; i++) sb.append(selected[i]).append(" "); // 결과값 추가하기
			sb.append("\n"); // 개행 처리
			return; // 종료
		}
		
		for (int i = start; i <= N; i++) { // 중복순열 만들기
			selected[depth] = i; // 숫자 고르기
			permutationWithRepetition(1, depth + 1); // 다음 숫자 고르기
		}
	} // permutationWithRepetition 종료
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 자연수의 개수
		M = Integer.parseInt(st.nextToken()); // 고를 숫자의 개수
		
		// 버퍼 닫기
		br.close();
		
		selected = new int[M + 1]; // 배열 크기 지정
		visited  = new boolean[N + 1]; // 배열 크기 지정
		permutationWithRepetition(1, 1); // 중복순열 만들기
		
		// 결과값 출력하기
		System.out.print(sb);
	} // main 종료
} // Main 종료