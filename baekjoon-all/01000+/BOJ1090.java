/*
 * 백준 1090번 : 체커
 * https://www.acmicpc.net/problem/1090
 * 난이도 : 플래티넘 4
 * 풀이 날짜 : 2025-11-14
 * 간단 설명 : k개의 체커를 가장 적은 이동 횟수로 한 곳으로 모으는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class BOJ1090 {
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int N = Integer.parseInt(br.readLine()); // 체커의 수
        
        int[] xBoard = new int[N]; // x좌표 배열
        int[] yBoard = new int[N]; // y좌표 배열
        int[][] board = new int[N][2]; // 좌표 배열
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken()); // x좌표
            int y = Integer.parseInt(st.nextToken()); // y좌표
            
            xBoard[i] = x; // x좌표 입력
            yBoard[i] = y; // y좌표 입력
            board[i][0] = x; // x좌표 입력
            board[i][1] = y; // y좌표 입력
        }
        
        // 버퍼 닫기
        br.close();
        
        int[] move = new int[N + 1]; // 이동 횟수 배열
        Arrays.fill(move, Integer.MAX_VALUE); // 이동 횟수 초기화
        
        for (int x : xBoard) {
            for (int y : yBoard) {
                int[] dist = new int[N]; // 거리 배열
                for (int i = 0; i < N; i++) dist[i] = Math.abs(board[i][0] - x) + Math.abs(board[i][1] - y);
                
                Arrays.sort(dist); // 거리 배열 비내림차순 정렬
                
                int sum = 0; // 이동해야 하는 거리 누적 합
                for (int i = 1; i <= N; i++) {
                    sum += dist[i - 1]; // 거리 누적 합
                    move[i] = Math.min(move[i], sum); // 최소 이동 거리 갱신
                }
            }
        }
        
        StringBuilder sb = new StringBuilder(); // 결과값을 저장할 객체
        for (int i = 1; i <= N; i++) sb.append(move[i]).append(" ");
        
        // 결과값 출력하기
        System.out.print(sb);
    } // main 종료
} // Main 종료