/*
 * 백준 11053번 : 가장 긴 증가하는 부분 수열
 * https://www.acmicpc.net/problem/11053
 * 난이도 : 실버 2
 * 풀이 날짜 : 2026-02-11
 * 간단 설명 : 가장 긴 증가하는 부분 수열의 길이를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class BOJ11053 {
    static int N; // 수열의 크기
    static int[] A; // A 수열 배열
    static int[] dp; // dp 배열

    static int calLen(int idx) { // 최장 길이를 구하는 함수
        // 종료 조건
        if (dp[idx] != -1) return dp[idx]; // 이미 계산된 곳이면 현재 위치 길이 반환

        dp[idx] = 1; // 최소 길이는 자기 자신

        for (int nxt = idx + 1; nxt < N; nxt++) { // 다음 위치부터 탐색
            if (A[idx] < A[nxt]) dp[idx] = Math.max(dp[idx], calLen(nxt) + 1); // 기존의 값과 다음 원소부터 시작하는 길이 비교 후 갱신
        }

        return dp[idx]; // 현재 길이 반환
    } // calLen 종료
    
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine()); // 수열의 크기
        A = new int[N]; // A 수열 배열
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) A[i] = Integer.parseInt(st.nextToken()); // 수열 저장

        // 버퍼 닫기
        br.close();

        dp = new int[N]; // dp 배열
        Arrays.fill(dp, -1); // dp 배열 초기화


        int maxLen = 0; // 최장 길이
        for (int i = 0; i < N; i++) {
            maxLen = Math.max(maxLen, calLen(i)); // 최장 길이 갱신
        }
        
        // 결과값 출력하기
        System.out.print(maxLen);
    } // main 종료
} // Main 종료
