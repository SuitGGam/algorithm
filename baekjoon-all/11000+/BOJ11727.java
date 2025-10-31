/*
 * 백준 11727번 : 2×n 타일링 2
 * https://www.acmicpc.net/problem/11727
 * 난이도 : 실버 3
 * 풀이 날짜 : 2025-11-01
 * 간단 설명 : 2×n 크기의 직사각형을 1×2, 2×1, 2×2 타일로 채우는 방법의 수를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ11727 {
    static final int MOD = 10_007;
    
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine()); // 직사각형의 가로 길이
        
        // 버퍼 닫기
        br.close();
        
        int[] dp = new int[1000 + 1]; // 방법의 수 배열
        dp[1] = 1;
        dp[2] = 3;
        for (int i = 3; i <= 1000; i++) dp[i] = (dp[i - 1] + dp[i - 2] * 2) % MOD;
        
        // 결과값 출력하기
        System.out.print(dp[n]);
    } // main 종료
} // Main 종료