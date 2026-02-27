/*
 * 백준 9251번 : LCS
 * https://www.acmicpc.net/problem/9251
 * 난이도 : 골드 4
 * 풀이 날짜 : 2026-02-28
 * 간단 설명 : 가장 긴 최장 공통 부분 수열의 길이를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ9251 {
    static char[] s1, s2; // 문자열 1 배열, 문자열 2 배열
    static int s1Len, s2Len; // 문자열 1의 길이, 문자열 2의 길이
    static int[][] dp; // dp 배열

    static int lcs(int s1Idx, int s2Idx) { // 최장 공통 부분 수열의 길이를 구하는 함수
        // 종료 조건 - 끝에 도달하면 종료
        if (s1Idx == s1Len || s2Idx == s2Len) return 0;

        // 이미 계산된 곳이면 계산된 값 반환
        if (dp[s1Idx][s2Idx] != -1) return dp[s1Idx][s2Idx];

        // 두 문자열의 문자가 같으면 길이 1 증가
        if (s1[s1Idx] == s2[s2Idx]) dp[s1Idx][s2Idx] = 1 + lcs(s1Idx + 1, s2Idx + 1);
            // 두 문자열의 문자가 다른 경우 다음 문자로 이동
        else dp[s1Idx][s2Idx] = Math.max(lcs(s1Idx + 1, s2Idx), lcs(s1Idx, s2Idx + 1));

        return dp[s1Idx][s2Idx]; // 계산된 값 반환
    } // lcs 종료

    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        s1 = br.readLine().toCharArray(); // 문자열 1 배열
        s2 = br.readLine().toCharArray(); // 문자열 2 배열

        // 버퍼 닫기
        br.close();

        s1Len = s1.length; // 문자열 1의 길이
        s2Len = s2.length; // 문자열 2의 길이
        dp = new int[s1Len][s2Len]; // dp 배열
        for (int i = 0; i < s1Len; i++) {
            for (int j = 0; j < s2Len; j++) dp[i][j] = -1; // dp 배열 초기화
        }

        int maxLen = lcs(0, 0); // 최장 길이

        // 결과값 출력하기
        System.out.print(maxLen);
    } // main 종료
} // Main 종료