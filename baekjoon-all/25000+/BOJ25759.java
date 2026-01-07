/*
 * 백준 25759번 : 들판 건너가기
 * https://www.acmicpc.net/problem/25759
 * 난이도 : 골드 4
 * 풀이 날짜 : 2026-01-04
 * 간단 설명 : 꽃다발의 최대 아름다움이 나오는 경우를 구하는 문제
 */

/*
 * 완전 탐색 경우의 수 : 2^N (2^100,000)
 * 이유 : 1 ~ 2, 1 ~ 3, 1 ~ 4, •••, N - 2 ~ N, N - 1 ~ N
 * 이런 식으로 모든 꽃에 대해 선택을 한다/안 한다를 다 확인해 봐야 함
 *
 * 알고리즘 : DP
 * 자료구조 : Array
 * 시간 복잡도 : O (N * A)
 * 풀이 방법 : 완전 탐색으로 하기엔 오래 걸리지만
 * DP를 활용하면 각 경우에 대해서 얻을 수 있는 최댓값을 구할 수 있음
 * 꽃의 아름다움 범위가 1 ~ 100으로 작으니 각 꽃에 갈 때마다
 * 이전에 가능했던 모든 경우에 대해 탐색하면서 가장 큰 경우를 갱신하면 됨
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class BOJ25759 {
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int N = Integer.parseInt(br.readLine()); // 꽃의 개수
        int[] flower = new int[N]; // 꽃의 아름다움 배열
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            flower[i] = Integer.parseInt(st.nextToken()); // 꽃의 아름다움 저장
        }
        
        // 버퍼 닫기
        br.close();
        
        int[] dp = new int[100 + 1]; // dp 배열
        Arrays.fill(dp, -1); // dp 배열 초기화
        dp[flower[0]] = 0; // 첫 번째 꽃 처리 (무조건 따야 함)
        
        for (int i = 1; i < N; i++) {
            int cur = flower[i]; // 현재 꽃의 아름다움
            
            for (int prv = 1; prv <= 100; prv++) { // 이전에 선택했던 꽃의 아름다움 확인
                if (dp[prv] == -1) continue; // 고른 적이 없으면 continue
                
                int gap = Math.abs(prv - cur); // 현재 꽃과 이전 꽃의 아름다움 차이
                int score = gap * gap; // 현재 꽃을 골랐을 때의 아름다움
                dp[cur] = Math.max(dp[cur], dp[prv] + score); // 최대 아름다움 갱신
            }
        }
        
        int maxBeauty = 0; // 최대 아름다움
        for (int score : dp) maxBeauty = Math.max(maxBeauty, score);
        
        // 결과값 출력하기
        System.out.print(maxBeauty);
    } // main 종료
} // Main 종료