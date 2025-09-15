/*
 * 백준 32931번 : 에라토스테네스의 체
 * https://www.acmicpc.net/problem/32931
 * 난이도 : 실버 1
 * 풀이 날짜 : 2025-09-16
 * 간단 설명 : 자석이 이동하면서 구할 수 있는 최대 합을 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ32931 {
    // 입력을 위한 객체 선언
    static BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine()); // 격자의 열의 개수
        int[][] grid = new int[2][N]; // 격자 배열
        
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) grid[0][i] = Integer.parseInt(st.nextToken()); // 첫 번째 행 수 저장하기
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) grid[1][i] = Integer.parseInt(st.nextToken()); // 두 번째 행 수 저장하기
        
        // 버퍼 닫기
        br.close();
        
        long[][] dp = new long[2][N]; // dp 배열
        
        // 첫 열 초기화
        dp[0][0] = grid[0][0] + Math.max(0, grid[1][0]);
        dp[1][0] = grid[0][0] + grid[1][0];
        
        for (int i = 1; i < N; i++) {
            for (int my = 0; my < 2; my++) {
                int other = (my + 1) % 2; // 남은 행
                
                dp[my][i] = dp[my][i - 1] + grid[my][i] + Math.max(0, grid[other][i]);
                dp[my][i] = Math.max(dp[my][i], dp[other][i - 1] + grid[my][i] + grid[other][i]);
            }
        }
        
        // 결과값 출력하기
        System.out.print(dp[1][N - 1]);
    } // main 종료
} // Main 종료