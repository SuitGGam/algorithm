/*
 * 백준 1932번 : 정수 삼각형
 * https://www.acmicpc.net/problem/1932
 * 난이도 : 실버 1
 * 풀이 날짜 : 2026-02-13
 * 간단 설명 : 합이 최대가 되는 경로에 있는 수의 합을 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1932 {
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine()); // 삼각형의 크기
        int[][] triangle = new int[n][n]; // 삼각형 배열
        for (int i = 0; i < n; i++) {
            st =  new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < i + 1; j++) triangle[i][j] = Integer.parseInt(st.nextToken()); // 정수 저장
        }

        // 버퍼 닫기
        br.close();

        int[][] dp = new int[n][n]; // dp 배열
        dp[0][0] = triangle[0][0]; // 초기값 세팅
        for (int i = 1; i < n; i++) { // 현재 층 선택
            for (int j = 0; j < i + 1; j++) { // 현재 숫자 선택
                dp[i][j] = Math.max(dp[i - 1][j] + triangle[i][j], dp[i - 1][j - 1 >= 0 ? j - 1 : j] + triangle[i][j]); // 최댓값 갱신
            }
        }

        int maxSum = 0; // 최대 합
        for (int i = 0; i < n; i++) maxSum = Math.max(maxSum, dp[n - 1][i]); // 최대 합 갱신

        // 결과값 출력하기
        System.out.print(maxSum);
    } // main 종료
} // Main 종료