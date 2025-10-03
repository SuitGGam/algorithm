/*
 * 백준 6593번 : 상범 빌딩
 * https://www.acmicpc.net/problem/6593
 * 난이도 : 골드 5
 * 풀이 날짜 : 2025-10-04
 * 간단 설명 : 상범 빌딩을 탈출하는데 걸리는 최소 시간을 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayDeque;

public class BOJ6593 {
    // 입력을 위한 객체 선언
    static BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    
    static Node escapeNode; // 탈출 지점 좌표
    static class Node {
        int z, x, y, time; // 층, 행, 열의 좌표, 걸린 시간
        
        Node(int z, int x, int y, int time) { // 좌표와 시간
            this.z = z;
            this.x = x;
            this.y = y;
            this.time = time;
        }
        
        Node(int z, int x, int y) { // 좌표
            this.z = z;
            this.x = x;
            this.y = y;
        }
    } // Node 종료
    
    // 전우후좌상하
    static int[] dx = {-1, 0, 1,  0,  0, 0};
    static int[] dy = { 0, 1, 0, -1,  0, 0};
    static int[] dz = { 0, 0, 0,  0, -1, 1};
    
    static ArrayDeque<Node> queue = new ArrayDeque<>(); // 좌표를 담을 큐
    static int escape(char[][][] building, int L, int R, int C) { // 빌딩을 탈출하는 BFS 함수
        int[][][] time = new int[L][R][C]; // 시간을 기록할 배열
        
        while (!queue.isEmpty()) {
            Node curNode = queue.poll(); // 현재 노드
            
            for (int d = 0; d < 6; d++) {
                int nz = curNode.z + dz[d];
                int nx = curNode.x + dx[d];
                int ny = curNode.y + dy[d];
                
                if (nz >= 0 && nz < L && nx >= 0 && nx < R && ny >= 0 && ny < C && building[nz][nx][ny] != '#' & time[nz][nx][ny] == 0) {
                    time[nz][nx][ny] = time[curNode.z][curNode.x][curNode.y] + 1; // 누적 시간 갱신
                    queue.add(new Node(nz, nx, ny, time[nz][nx][ny])); // queue에 다음 Node add
                }
            }
        }
        
        return time[escapeNode.z][escapeNode.x][escapeNode.y]; // 결과값 리턴
    } // escape 종료
    
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder(); // 결과값을 추가할 객체
        while (true) {
            st = new StringTokenizer(br.readLine(), " ");
            int L = Integer.parseInt(st.nextToken()); // 상범 빌딩의 층 수
            int R = Integer.parseInt(st.nextToken()); // 한 층의 행의 수
            int C = Integer.parseInt(st.nextToken()); // 한 층의 열의 수
            
            // 종료 조건
            if (L == 0) break;
            
            char[][][] building = new char[L][R][C]; // 상범 빌딩 구조 배열
            for (int i = 0; i < L; i++) {
                for (int j = 0; j < R; j++) {
                    String info = br.readLine(); // 상범 빌딩의 정보
                    for (int k = 0; k < C; k++) {
                        building[i][j][k] = info.charAt(k); // 상범 빌딩 정보 저장
                        if (building[i][j][k] == 'S') queue.add(new Node(i, j, k, 0)); // 시작점 queue에 add
                        if (building[i][j][k] == 'E') escapeNode = new Node(i, j, k); // 탈출 좌표 등록
                    }
                }
                br.readLine(); // 빈 줄 처리
            }
            
            int minMinute = escape(building, L, R, C); // 탈출 시작
            
            // 결과값 추가하기
            if (minMinute != 0) sb.append("Escaped in ").append(minMinute).append(" minute(s).\n");
            else sb.append("Trapped!\n");
        }
        
        // 버퍼 닫기
        br.close();
        
        // 결과값 출력하기
        System.out.print(sb);
    } // main 종료
} // Main 종료