/*
 * 백준 1012번 : 유기농 배추
 * https://www.acmicpc.net/problem/1012
 * 난이도 : 실버 2
 * 풀이 날짜 : 2025-08-31
 * 간단 설명 : 배추 밭에 필요한 배추흰지렁이의 최소 마리 수를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1012 {
    static int M, N, K; // 가로 길이, 세로 길이, 배추 개수
    static int[][] cabField; // 배추 밭
    static boolean[][] visited; // 배추 밭 방문 표시
    static int earthWorm; // 지렁이 마리 수
    static Queue<Integer> queueX = new LinkedList<>(); // x 좌표 큐
    static Queue<Integer> queueY = new LinkedList<>(); // y 좌표 큐
    static int[] dx = {-1, 0, 1,  0}; // x 상우하좌
    static int[] dy = { 0, 1, 0, -1}; // y 상우하좌
    static StringBuilder sb = new StringBuilder(); // 결과값을 저장할 객체
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스의 개수
        for (int tc = 1; tc <= T; tc++) { // 테스트 케이스 반복 시작
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            M = Integer.parseInt(st.nextToken()); // 배추밭의 가로 길이
            N = Integer.parseInt(st.nextToken()); // 배추밭의 세로 길이
            K = Integer.parseInt(st.nextToken()); // 배추가 심어져 있는 위치의 개수
            
            cabField = new int[M][N]; // 배추 밭 생성
            visited = new boolean[M][N]; // 배추 밭 방문표 생성
            
            for (int i = 0; i < K; i++) { // 배추 심기 시작
                st = new StringTokenizer(br.readLine(), " ");
                int x = Integer.parseInt(st.nextToken()); // 가로 좌표
                int y = Integer.parseInt(st.nextToken()); // 세로 좌표
                cabField[x][y] = 1;
            } // 배추 심기 종료
            
            earthWorm = 0; // 지렁이 마리 수 초기화
            for (int x = 0; x < M; x++) { // 배추 밭 완전 탐색 시작
                for (int y = 0; y < N; y++) {
                    if (!visited[x][y] && cabField[x][y] == 1) { // 방문하지 않은 곳이면 지렁이 이동 시작
                        bfs(x, y);
                    } // 지렁이 이동 종료
                }
            } // 배추 밭 완전 탐색 종료
            
            // 결과값 추가하기
            sb.append(earthWorm).append("\n");
        } // 테스트 케이스 반복 종료
        
        // 버퍼 닫기
        br.close();
        
        // 결과값 출력하기
        System.out.print(sb);
    } // main 종료
    
    // 배추 밭 BFS
    static void bfs(int x, int y) {
        earthWorm++; // 지렁이 한 마리 추가(구역 하나)
        visited[x][y] = true; // 방문 처리
        queueX.add(x);
        queueY.add(y);
        
        while (!queueX.isEmpty() && !queueY.isEmpty()) {
            int curX = queueX.poll();
            int curY = queueY.poll();
            
            for (int i = 0; i < 4; i++) { // 상우하좌 탐색 시작
                curX += dx[i];
                curY += dy[i];
                
                // 배열 밖이면 스킵
                if (curX < 0 || curX >= M || curY < 0 || curY >= N) {
                    curX -= dx[i]; // 자리 원복 1
                    curY -= dy[i]; // 자리 원복 2
                    continue;
                }
                
                if (!visited[curX][curY] && cabField[curX][curY] == 1) {
                    visited[curX][curY] = true;
                    queueX.add(curX);
                    queueY.add(curY);
                }
                curX -= dx[i]; // 자리 원복 1
                curY -= dy[i]; // 자리 원복 2
            } // 상우하좌 탐색 종료
        }
    }
} // class 종료