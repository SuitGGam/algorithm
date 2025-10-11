/*
 * 백준 14940번 : 쉬운 최단거리
 * https://www.acmicpc.net/problem/14940
 * 난이도 : 실버 1
 * 풀이 날짜 : 2025-10-12
 * 간단 설명 : 출발 지점부터 방문할 수 있는 모든 곳의 최단 거리를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayDeque;

public class BOJ14940 {
    // 입력을 위한 객체 선언
    static BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    
    static class Node {
        int x; // x좌표
        int y; // y좌표
        int distance; // 거리
        
        Node(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    } // Coordinate 종료
    
    static int n, m; // 지도의 세로 크기, 지도의 가로 크기
    static int[][] map; // 지도 배열
    static int[][] distance; // 최단 거리 배열
    static boolean[][] visited; // 방문 처리 배열
    
    // 상우하좌
    static int[] dx = {-1, 0, 1,  0};
    static int[] dy = { 0, 1, 0, -1};
    static ArrayDeque<Node> queue = new ArrayDeque<>(); // BFS를 위한 큐
    static void bfs() { // 최단 거리를 구하는 함수
        while (!queue.isEmpty()) {
            Node curNode = queue.poll(); // 현재 좌표
            
            for (int d = 0; d < 4; d++) {
                int nx = curNode.x + dx[d];
                int ny = curNode.y + dy[d];
                
                // 지도를 벗어나면 continue
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                
                if (!visited[nx][ny] && map[nx][ny] == 1) { // 방문하지 않은 갈 수 있는 땅인 경우
                    visited[nx][ny] = true; // 방문 처리
                    queue.add(new Node(nx, ny, curNode.distance + 1)); // 다음 좌표 큐에 add
                    distance[nx][ny] = curNode.distance + 1; // 거리 기록
                }
            }
        }
    } // bfs 종료
    
    public static void main(String[] args) throws IOException{
        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken()); // 지도의 세로 크기
        m = Integer.parseInt(st.nextToken()); // 지도의 가로 크기
        
        map = new int[n][m]; // 배열 크기 지정
        visited = new boolean[n][m]; // 배열 크기 지정
        for (int x = 0; x < n; x++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int y = 0; y < m; y++) {
                map[x][y] = Integer.parseInt(st.nextToken()); // 지도 정보 저장
                if (map[x][y] == 2) {
                    queue.add(new Node(x, y, 0)); // 시작점 큐에 add
                    visited[x][y] = true; // 방문 처리
                }
            }
        }
        
        // 버퍼 닫기
        br.close();
        
        distance = new int[n][m]; // 배열 크기 지정
        bfs(); // 최단 거리 구하기
        
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < m; y++) {
                if (map[x][y] == 1 && distance[x][y] == 0) distance[x][y] = -1; // 갈 수 없는 곳 표시
            }
        }
        
        StringBuilder sb = new StringBuilder(); // 결과값을 저장할 객체
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < m; y++) {
                sb.append(distance[x][y]).append(" "); // 결과값 추가하기
            }
            sb.append("\n"); // 개행 처리
        }
        
        // 결과값 출력하기
        System.out.print(sb);
    } // main 종료
} // Main 종료