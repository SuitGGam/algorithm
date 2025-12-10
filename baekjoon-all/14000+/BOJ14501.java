/*
 * 백준 14501번 : 퇴사
 * https://www.acmicpc.net/problem/14501
 * 난이도 : 실버 3
 * 풀이 날짜 : 2025-12-11
 * 간단 설명 : 남은 일 수 중에 얻을 수 있는 최대 이익을 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14501 {
    static int N;
    static int maxProfit; // 최대 이익
    static int[][] counsel; // 상담 정보 배열
    static void recur(int cur, int score) { // 최대 이익을 구하는 함수
        if (cur > N) return; // 일자를 넘어가면 종료
        
        if (cur == N) { // 종료 조건
            maxProfit = Math.max(score, maxProfit); // 최대 이익 갱신
            return; // 종료
        }
        
        recur(cur + counsel[cur][0], score + counsel[cur][1]); // 현재 일자 상담을 진행하는 경우
        recur(cur + 1, score); // 현재 일자 상담을 진행 안 하는 경우
    } // recur 종료
    
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine()); // 남은 일 수
        counsel = new int[N][2]; // 배열 크기 지정
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            counsel[i][0] = Integer.parseInt(st.nextToken()); // 상담 기간
            counsel[i][1] = Integer.parseInt(st.nextToken()); // 상담 비용
        }
        
        // 버퍼 닫기
        br.close();
        
        maxProfit = 0; // 최대 이익 초기화
        recur(0, 0);
        
        // 결과값 출력하기
        System.out.print(maxProfit);
    } // main 종료
} // Main 종료