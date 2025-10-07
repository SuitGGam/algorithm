/*
 * 백준 4963번 : 섬의 개수
 * https://www.acmicpc.net/problem/4963
 * 난이도 : 실버 2
 * 풀이 날짜 : 2025-10-08
 * 간단 설명 : 섬의 개수를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayDeque;

public class BOJ4963 {
    // 입력을 위한 객체 선언
    static BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    
    static class Coordinate {
        int x; // x좌표
        int y; // y좌표
        
        Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    } // Coordinate 종료
    
    // 상, 상우, 우, 하우, 하, 하좌, 좌, 상좌
    static int[] dx = {-1, -1, 0, 1, 1,  1,  0, -1};
    static int[] dy = { 0,  1, 1, 1, 0, -1, -1, -1};
    
    static StringBuilder sb = new StringBuilder(); // 결과값을 저장할 객체
    static void countLand(int[][] map, boolean[][] visited, int h, int w) { // 섬의 개수를 세는 함수
        ArrayDeque<Coordinate> queue = new ArrayDeque<>(); // 좌표를 저장할 큐
        
        int cnt = 0; // 섬의 개수
        for (int x = 0; x < h; x++) {
            for (int y = 0; y < w; y++) {
                if (!visited[x][y] && map[x][y] == 1) { // 방문하지 않은 섬인 경우
                    queue.add(new Coordinate(x, y)); // 시작 좌표 큐에 추가
                    visited[x][y] = true; // 방문 처리
                    cnt++; // 섬 개수 추가
                    
                    while (!queue.isEmpty()) {
                        Coordinate c = queue.poll(); // 현재 좌표
                        
                        for (int d = 0; d < 8; d++) {
                            int nx = c.x + dx[d];
                            int ny = c.y + dy[d];
                            
                            if (nx >= 0 && nx < h && ny >= 0 && ny < w) { // 지도를 벗어나지 않으면
                                if (!visited[nx][ny]) { // 방문을 안 한 곳이면
                                    visited[nx][ny] = true; // 방문 처리
                                    if (map[nx][ny] == 1) queue.add(new Coordinate(nx, ny)); // 큐에 좌표 추가
                                }
                            }
                        }
                    }
                }
            }
        }
        // 결과값 추가하기
        sb.append(cnt).append("\n");
    } // countLand 종료
    
    public static void main(String[] args) throws IOException {
        while (true) {
            st = new StringTokenizer(br.readLine(), " ");
            int w = Integer.parseInt(st.nextToken()); // 지도의 너비
            int h = Integer.parseInt(st.nextToken()); // 지도의 높이
            
            // 종료 조건
            if (w == 0 && h == 0) break;
            
            int[][] map = new int[h][w]; // 지도 배열
            boolean[][] visited = new boolean[h][w]; // 방문 처리 배열
            for (int x = 0; x < h; x++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int y = 0; y < w; y++) map[x][y] = Integer.parseInt(st.nextToken()); // 지도 정보 저장
            }
            
            countLand(map, visited, h, w); // 섬 개수 세기
        }
        
        // 버퍼 닫기
        br.close();
        
        // 결과값 출력하기
        System.out.print(sb);
    } // main 종료
} // Main 종료