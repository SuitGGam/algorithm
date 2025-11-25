/*
 * 백준 10026번 : 적록색약
 * https://www.acmicpc.net/problem/10026
 * 난이도 : 골드 5
 * 풀이 날짜 : 2025-11-25
 * 간단 설명 : 그냥 봤을 때의 영역 개수와 적록색약이 봤을 때의 영역 개수를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class BOJ10026 {
    // 상우하좌
    static int[] dx = {-1, 0, 1,  0};
    static int[] dy = { 0, 1, 0, -1};
    
    static int bfs(int N, char[][] grid) { // 일반 탐색 함수
        boolean[][] visited = new boolean[N][N]; // 방문 처리 배열
        int cnt = 0; // 영역 개수
        
        ArrayDeque<int[]> queue = new ArrayDeque<>(); // 큐
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                if (visited[x][y]) continue; // 방문한 곳이면 continue
                
                cnt++; // 영역 개수 증가
                char curColor = grid[x][y];
                visited[x][y] = true; // 방문 처리
                queue.add(new int[] { x, y }); // 그리드 정보 add
                while (!queue.isEmpty()) {
                    int[] curGrid = queue.poll(); // 현재 그리드 정보
                    
                    for (int d = 0; d < 4; d++) {
                        int nx = curGrid[0] + dx[d];
                        int ny = curGrid[1] + dy[d];
                        
                        if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue; // 그리드를 벗어나면 continue
                        if (visited[nx][ny] || grid[nx][ny] != curColor) continue; // 방문했거나 색깔이 다르면 continue
                        
                        visited[nx][ny] = true; // 방문 처리
                        queue.add(new int[] { nx, ny }); // 같은 색 그리드 정보 add
                    }
                }
            }
        }
        
        return cnt; // 영역 개수 return
    } // bfs 종료
    
    static int rgBfs(int N, char[][] grid) { // 적록색약 탐색 함수
        boolean[][] visited = new boolean[N][N]; // 방문 처리 배열
        int cnt = 0; // 영역 개수
        
        ArrayDeque<int[]> queue = new ArrayDeque<>(); // 큐
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                if (visited[x][y]) continue; // 방문한 곳이면 continue
                
                cnt++; // 영역 개수 증가
                char curColor = grid[x][y];
                visited[x][y] = true; // 방문 처리
                queue.add(new int[] { x, y }); // 그리드 정보 add
                while (!queue.isEmpty()) {
                    int[] curGrid = queue.poll(); // 현재 그리드 정보
                    
                    for (int d = 0; d < 4; d++) {
                        int nx = curGrid[0] + dx[d];
                        int ny = curGrid[1] + dy[d];
                        
                        if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue; // 그리드를 벗어나면 continue
                        
                        if (curColor == 'B') { // 파랑 영역인 경우
                            if (visited[nx][ny] || grid[nx][ny] != curColor) continue; // 방문했거나 색깔이 다르면 continue
                        } else { // 적록 영역인 경우
                            if (visited[nx][ny] || grid[nx][ny] == 'B') continue; // 방문했거나 파랑 그리드가 아니면 continue
                        }
                        
                        visited[nx][ny] = true; // 방문 처리
                        queue.add(new int[] { nx, ny }); // 같은 색 그리드 정보 add
                    }
                }
            }
        }
        
        return cnt; // 영역 개수 return
    } // rgBfs 종료
    
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine()); // 그리드의 크기
        char[][] grid = new char[N][N]; // 그리드 배열
        for (int x = 0; x < N; x++) {
            String color = br.readLine(); // 그림 색깔
            for (int y = 0; y < N; y++) {
                grid[x][y] = color.charAt(y); // 색깔 저장
            }
        }
        
        // 버퍼 닫기
        br.close();
        
        int allCnt = bfs(N, grid); // 일반 탐색
        int rgCnt = rgBfs(N, grid); // 적록색약 탐색
        
        // 결과값 출력하기
        System.out.print(allCnt + " " + rgCnt);
    } // main 종료
} // Main 종료
