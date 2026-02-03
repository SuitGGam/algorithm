/*
 * 백준 16456번 : 하와와 대학생쨩 하와이로 가는 거시와요~
 * https://www.acmicpc.net/problem/16456
 * 난이도 : 실버 1
 * 풀이 날짜 : 2026-02-03
 * 간단 설명 : N개의 섬을 모두 보는 경우의 수를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ16456 {
	static final int MOD = 1_000_000_009; // MOD

	public static void main(String[] args) throws IOException {
		// 입력을 위한 객체 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine()); // 섬의 개수
		
		// 버퍼 닫기
		br.close();
		
		long[] dp = new long[N + 1]; // dp 배열
		dp[1] = 1; // 섬이 1개일 때
		if (N >= 2) dp[2] = 1; // 섬이 2개일 때
		if (N >= 3) dp[3] = 2; // 섬이 3개일 때
		for (int i = 4; i <= N; i++) dp[i] = (dp[i - 1] + dp[i - 3]) % MOD;
		
		// 결과값 출력하기
		System.out.print(dp[N]);
	} // main 종료
} // Main 종료
