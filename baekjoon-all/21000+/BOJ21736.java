/*
 * 백준 21736번 : 헌내기는 친구가 필요해
 * https://www.acmicpc.net/problem/21736
 * 난이도 : 실버 2
 * 풀이 날짜 : 2025-10-16
 * 간단 설명 : 도연이가 캠퍼스에서 만날 수 있는 사람 수를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayDeque;

public class BOJ21736 {
    // 입력을 위한 객체 선언
    static BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    
    static int N, M; // 캠퍼스의 세로 길이, 캠퍼스의 가로 길이
    static char[][] campus; // 캠퍼스 정보 배열
    static boolean[][] visited; // 방문 처리 배열
    
    static class Node { // 좌표 class
        int x; // x좌표
        int y; // y좌표
        
        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    } // Node 종료
    
    // 상하좌우
    static int[] dx = {-1, 1,  0, 0};
    static int[] dy = { 0, 0, -1, 1};
    static ArrayDeque<Node> queue = new ArrayDeque<>(); // 좌표를 담을 큐
    static int bfs() { // bfs 함수
        int person = 0; // 만난 사람의 수
        while (!queue.isEmpty()) {
            Node curNode = queue.poll(); // 현재 좌표
            
            for (int d = 0; d < 4; d++) {
                int nx = curNode.x + dx[d];
                int ny = curNode.y + dy[d];
                
                if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue; // 캠퍼스를 벗어나면 continue
                
                if (!visited[nx][ny] && campus[nx][ny] != 'X') { // 방문하지 않은 곳이면서 벽이 아닌 경우
                    visited[nx][ny] = true; // 방문 처리
                    queue.add(new Node(nx, ny)); // 새로운 좌표 add
                    if (campus[nx][ny] == 'P') person++; // 사람을 만난 경우
                }
            }
        }
        
        return person;
    } // bfs 종료
    
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken()); // 캠퍼스의 세로 길이
        M = Integer.parseInt(st.nextToken()); // 캠퍼스의 가로 길이
        
        campus = new char[N][M]; // 배열 크기 지정
        visited = new boolean[N][M]; // 배열 크기 지정
        for (int x = 0; x < N; x++) {
            String info = br.readLine(); // 캠퍼스의 정보
            for (int y = 0; y < M; y++) {
                campus[x][y] = info.charAt(y); // 캠퍼스 정보 저장
                if (campus[x][y] == 'I') {
                    queue.add(new Node(x, y)); // 도연이 좌표 add
                    visited[x][y] = true; // 방문 처리
                }
            }
        }
        
        // 버퍼 닫기
        br.close();
        
        int person = bfs();
        
        // 결과값 출력하기
        System.out.print(person == 0 ? "TT" : person);
    } // main 종료
} // Main 종료