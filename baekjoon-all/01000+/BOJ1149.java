/*
 * 백준 1149번 : RGB거리
 * https://www.acmicpc.net/problem/1149
 * 난이도 : 실버 1
 * 풀이 날짜 : 2025-12-12
 * 간단 설명 : 집을 칠하는데 드는 최소 비용을 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1149 {
    static int N; // 집의 개수
    static int[][] color; // 색깔 배열
    static int[][] dp; // dp 배열
    static int recur(int cur, int prv) { // 최소 비용을 구하는 함수
        if (cur == N) return 0; // 다 골랐으면 return
        
        if (prv != 3 && dp[cur][prv] != Integer.MAX_VALUE) return dp[cur][prv]; // 이미 골랐으면 return
        
        int minCost = Integer.MAX_VALUE; // 최소 비용 초기화
        for (int i = 0; i < 3; i++) {
            if (i == prv) continue; // 이전에 골랐던 거면 continue
            
            minCost = Math.min(minCost, recur(cur + 1,  i) + color[cur][i]); // 다른 색깔 고르면서 최소 비용 갱신
        }
        
        return dp[cur][prv] = minCost; // 최소 비용 return
    } // recur 종료
    
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine()); // 남은 근무 일수
        color = new int[N][3]; // 배열 크기 지정
        for (int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine(), " ");
            color[i][0] = Integer.parseInt(st.nextToken()); // 빨강 비용
            color[i][1] = Integer.parseInt(st.nextToken()); // 초록 비용
            color[i][2] = Integer.parseInt(st.nextToken()); // 파랑 비용
        }
        
        // 버퍼 닫기
        br.close();
        
        dp = new int[N][4]; // 배열 크기 지정
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 3; j++) {
                dp[i][j] = Integer.MAX_VALUE; // dp 배열 초기화
            }
        }
        
        // 결과값 출력하기
        System.out.print(recur(0, 3));
    } // main 종료
} // Main 종료