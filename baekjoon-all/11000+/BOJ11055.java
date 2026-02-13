/*
 * 백준 11055번 : 가장 큰 증가하는 부분 수열
 * https://www.acmicpc.net/problem/11055
 * 난이도 : 실버 2
 * 풀이 날짜 : 2026-02-13
 * 간단 설명 : 증가하는 부분 수열 중 합이 제일 큰 값을 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11055 {
    static int N; // 수열의 크기
    static int[] A; // 수열 배열
    static int maxSum; // 최대 합
    static int[] dp; // dp 배열

    static int calSum(int idx) { // 합이 가장 큰 증가하는 부분 수열의 합을 구하는 함수
        // 종료 조건
        if (dp[idx] != 0) return dp[idx]; // 이미 길이를 거쳐간 곳이면 해당 지점으로부터 합이 이미 구해졌음

        dp[idx] = A[idx]; // 시작 기준 최고 합은 자기 자신

        for (int nxt = idx + 1; nxt < N; nxt++) {
            if (A[idx] < A[nxt]) dp[idx] = Math.max(dp[idx], A[idx] + calSum(nxt)); // 갱신
        }

        return dp[idx]; // 현재 합 반환
    } // calSum 종료

    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine()); // 수열의 크기
        A = new int[N]; // 수열 배열
        StringTokenizer st = new StringTokenizer(br.readLine() ," ");
        for (int i = 0; i < N; i++) A[i] = Integer.parseInt(st.nextToken()); // 수열 값 저장

        // 버퍼 닫기
        br.close();

        dp = new int[N]; // dp 배열
        maxSum = 0; // 최장 길이 초기화
        for (int i = 0; i < N; i++) {
            maxSum = Math.max(maxSum, calSum(i)); // 합이 가장 큰 증가하는 부분 수열 합 구하기
        }

        // 결과값 출력하기
        System.out.print(maxSum);
    } // main 종료
} // Main 종료