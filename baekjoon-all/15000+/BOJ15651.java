/*
 * 백준 15651번 : N과 M (3)
 * https://www.acmicpc.net/problem/15651
 * 난이도 : 실버 3
 * 풀이 날짜 : 2025-06-05
 * 간단 설명 : 중복 순열을 만드는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15651 {
	// 입력을 위한 객체 선언
	static BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 자연수 N
		M = Integer.parseInt(st.nextToken()); // N개 중에 고를 개수
		
		selected = new int[M]; // 순열 배열 크기 지정
		
		// 중복 순열 찾기
		perm(0);
		
		// 버퍼 닫기
		br.close();
		
		// 결과값 출력하기
		System.out.print(sb);
	} // main 종료
	
	static int N, M; // 자연수 N, 고를 숫자의 개수 M개
	static int[] selected; // 고른 숫자를 담을 배열
	
	// 조합 찾는 함수
	static StringBuilder sb = new StringBuilder();
	static void perm(int depth) {
		// 종료 조건
		if (depth == M) {
			// 결과값 추가하기
			for (int i = 0; i < M; i++) {
				sb.append(selected[i]).append(" ");
			}
			sb.append("\n"); // 개행 처리
			return;
		}
		
		// 조합 고르기
		for (int i = 1; i <= N; i++) {
			selected[depth] = i; // 순열 고르기
			perm(depth + 1); // 다음 중복 순열 고르기
		}
	}
} // class 종료