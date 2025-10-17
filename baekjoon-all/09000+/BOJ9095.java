/*
 * 백준 9095번 : 1, 2, 3 더하기
 * https://www.acmicpc.net/problem/9095
 * 난이도 : 실버 3
 * 풀이 날짜 : 2025-10-18
 * 간단 설명 : 정수 n을 1, 2, 3의 합으로 나타내는 방법의 수를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 1 : { {1} } - 1개
 * 2 : { {1+1}, {2} } - 2개
 * 3 : { {1+1+1}, {2+1, 1+2}, {3} } - 4개
 * 4 : { {1+1+1+1}, {2+1+1, 1+2+1, 1+1+2, 2+2}, {3+1, 1+3} } - 7개
 * 5 : { {1+1+1+1+1}, {2+1+1+1, 1+2+1+1, 1+1+2+1, 1+1+1+2, 2+2+1, 2+1+2, 1+2+2}, {3+1+1, 1+3+1, 1+1+3, 3+2, 2+3} } - 13개
 * n >= 4 : dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3]
 */

public class BOJ9095 {
    // 입력을 위한 객체 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    public static void main(String[] args) throws IOException {
        int[] dp = new int[10 + 1]; // dp 배열
        dp[1] = 1; // { {1} }
        dp[2] = 2; // { {1+1}, {2} }
        dp[3] = 4; // { {1+1+1}, {2+1, 1+2}, {3} }
        for (int i = 4; i < 11; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3]; // dp값 구하기
        }
        
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스의 개수
        StringBuilder sb = new StringBuilder(); // 결과값을 저장할 객체
        for (int i = 0; i < T; i++) {
            int n = Integer.parseInt(br.readLine()); // 정수
            sb.append(dp[n]).append("\n"); // 결과값 추가하기
        }
        
        // 버퍼 닫기
        br.close();
        
        // 결과값 출력하기
        System.out.print(sb);
    } // main 종료
} // Main 종료