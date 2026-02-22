/*
 * 백준 8394번 : 악수
 * https://www.acmicpc.net/problem/8394
 * 난이도 : 실버 3
 * 풀이 날짜 : 2026-02-22
 * 간단 설명 : 자신의 오른쪽이나 왼쪽 사람과 악수를 하거나 둘 다 안 할 경우 총 악수 경우의 수를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ8394 {
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine()); // 사람의 수

        // 버퍼 닫기
        br.close();

        int[] dp = new int[n + 1]; // dp 배열
        dp[0] = 0;
        dp[1] = 1;
        if (n >= 2) dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % 10;
        }

        // 결과값 출력하기
        System.out.print(dp[n]);
    } // main 종료
} // Main 종료