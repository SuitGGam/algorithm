/*
 * 백준 25416번 : 빠른 숫자 탐색
 * https://www.acmicpc.net/problem/25416
 * 난이도 : 실버 2
 * 풀이 날짜 : 2026-03-29
 * 간단 설명 : 1이 적혀 있는 칸으로 가기 위한 최소 이동 횟수를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayDeque;

public class BOJ25416 {
    static class Node { // 좌표와 거리 정보를 가진 class
        int r;
        int c;
        int dist;

        Node(int r, int c, int dist) {
            this.r = r;
            this.c = c;
            this.dist = dist;
        }
    } // Node 종료

    // 상우하좌
    static int[] dr = {-1, 0, 1,  0};
    static int[] dc = { 0, 1, 0, -1};

    static int targetR, targetC; // 목표 지점의 R, C 좌표
    static int[][] board; // 보드 배열
    static boolean[][] visited; // 방문 처리 배열

    static int bfs(int startR, int startC) { // 최소 이동 횟수를 구하는 함수
        ArrayDeque<Node> queue = new ArrayDeque<>(); // 좌표 정보를 담을 Queue
        queue.add(new Node(startR, startC, 0)); // 시작점 add
        visited[startR][startC] = true; // 시작점 방문 처리

        int move = 0; // 최소 이동 횟수
        boolean valid = false; // 이동 유효성

        while (!queue.isEmpty()) {
            Node cur = queue.poll(); // 현재 좌표

            if (cur.r == targetR && cur.c == targetC) { // 목표 지점에 도달한 경우
                move = cur.dist;
                valid = true;
                break;
            }

            for (int d = 0; d < 4; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];

                if (nr < 0 || nr >= 5 || nc < 0 || nc >= 5 || board[nr][nc] == -1) continue; // 보드를 벗어나거나 이동이 불가능하면 continue

                if (!visited[nr][nc]) {
                    queue.add(new Node(nr, nc, cur.dist + 1)); // 다음 지점 add
                    visited[nr][nc] = true; // 다음 지점 방문 처리
                }
            }
        }

        // 결과값 반환
        if (valid) return move;
        else return -1;
    } // bfs 종료

    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        board = new int[5][5]; // 보드 배열
        for (int r = 0; r < 5; r++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int c = 0; c < 5; c++) {
                board[r][c] = Integer.parseInt(st.nextToken()); // 보드 정보 저장

                if (board[r][c] == 1) { // 목표 지점 좌표 저장
                    targetR = r;
                    targetC = c;
                }
            }
        }

        st = new StringTokenizer(br.readLine(), " ");
        int startR = Integer.parseInt(st.nextToken()); // 시작 지점 R 좌표
        int startC = Integer.parseInt(st.nextToken()); // 시작 지점 C 좌표

        // 버퍼 닫기
        br.close();

        visited = new boolean[5][5]; // 방문 처리 배열
        int minMove = bfs(startR, startC);

        // 결과값 출력하기
        System.out.print(minMove);
    } // main 종료
} // Main 종료