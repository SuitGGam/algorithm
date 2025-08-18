/*
 * 백준 2178번 : 미로 탐색
 * https://www.acmicpc.net/problem/2178
 * 난이도 : 실버 1
 * 풀이 날짜 : 2025-08-18
 * 간단 설명 : 최단 거리로 미로를 탐색하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;

public class BOJ2178 {
    static BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken()); // 세로
        M = Integer.parseInt(st.nextToken()); // 가로
        maze = new char[N][M]; // 미로 크기 지정
        for (int x = 0; x < N; x++) { // 미로 정보 입력 시작
            String mazeInfo = br.readLine(); // 미로 정보
            for (int y = 0; y < M; y++) {
                maze[x][y] = mazeInfo.charAt(y);
            }
        } // 미로 정보 입력 종료
        
        // 버퍼 닫기
        br.close();
        
        distance = new int[N][M]; // 최단 거리 배열 크기 지정
        visited = new boolean[N][M]; // 방문 배열 크기 지정
        bfs(0, 0); // 탐색 시작
        
        // 결과값 출력하기
        System.out.print(distance[N - 1][M - 1]);
    }
    
    static int[] dx = {-1, 0, 1,  0}; // 상우하좌
    static int[] dy = { 0, 1, 0, -1}; // 상우하좌
    
    static int N, M; // 미로 크기
    static char[][] maze; // 미로 배열
    static boolean[][] visited; // 방문 확인 배열
    static int[][] distance; // 최단 거리 배열
    
    // 탐색 함수
    static void bfs(int startX, int startY) {
        Queue<int[]> queue = new LinkedList<>(); // x좌표 저장하는 큐
        
        queue.offer(new int[] {startX, startY}); // 시작 좌표 저장
        visited[startX][startY] = true; // 시작 좌표 방문 처리
        distance[startX][startY] = 1; // 시작 칸도 이동 칸 수에 포함
        
        while (!queue.isEmpty()) { // 탐색 시작
            int[] now = queue.poll(); // 좌표 꺼내기
            int x = now[0]; // x좌표
            int y = now[1]; // y좌표
            
            for (int i = 0; i < 4; i++) { // 상우하좌 탐색 시작
                int r = x + dx[i]; // 탐색할 x좌표
                int c = y + dy[i]; // 탐색할 y좌표
                
                // 미로 범위 밖은 안 가기
                if (r < 0 || r == N || c < 0 || c == M) continue;
                
                if (!visited[r][c] && maze[r][c] == '1') {
                    // 방문 안 한 곳이면서
                    // 갈 수 있는 곳
                    visited[r][c] = true; // 방문 처리
                    distance[r][c] = distance[x][y] + 1; // 이동한 칸 수 증가
                    queue.offer(new int[] {r, c}); // 좌표 저장
                    
                    // 도착 지점이면 종료
                    if (r == N - 1 && c == M - 1) {
                        return;
                    }
                }
            } // 상우하좌 탐색 종료
        } // 탐색 종료
    }
}