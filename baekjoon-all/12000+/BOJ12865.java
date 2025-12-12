/*
 * 백준 12865번 : 평범한 배낭
 * https://www.acmicpc.net/problem/12865
 * 난이도 : 골드 5
 * 풀이 날짜 : 2025-12-12
 * 간단 설명 : 무게 한도 내에서 배낭 안에 들어가는 물건의 최대 가치를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class BOJ12865 {
    static int N, K; // 물품의 수, 버틸 수 있는 무게
    static int[][] thing; // 물건 배열
    static int[][] dp; // dp 배열
    static int recur(int cur, int weight) { // 최대 가치를 구하는 함수
        if (weight > K) return 0; // 무게를 초과하면 종료
        
        if (cur == N) return 0; // 물건을 다 정했으면 종료
        
        if (dp[cur][weight] != -1) return dp[cur][weight]; // 이미 측정한 곳이면 종료
        
        int a = 0; // 현재 물건을 담는 경우
        if (weight + thing[cur][0] <= K) a = recur(cur + 1, weight + thing[cur][0]) + thing[cur][1];
        int b = recur(cur + 1, weight); // 현재 물건을 안 담는 경우
        
        return dp[cur][weight] = Math.max(a, b); // 최대 가치 return
    } // recur 종료
    
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        N = Integer.parseInt(st.nextToken()); // 물품의 수
        K = Integer.parseInt(st.nextToken()); // 버틸 수 있는 무게
        
        thing = new int[N][2]; // 물건 배열 크기 지정
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            thing[i][0] = Integer.parseInt(st.nextToken()); // 무게
            thing[i][1] = Integer.parseInt(st.nextToken()); // 가치
        }
        
        // 버퍼 닫기
        br.close();
        
        dp = new int[N][K + 1]; // dp 배열 크기 지정
        for (int i = 0; i < N; i++) Arrays.fill(dp[i], -1); // dp 배열 초기화
        
        // 결과값 출력하기
        System.out.print(recur(0, 0));
    } // main 종료
} // Main 종료