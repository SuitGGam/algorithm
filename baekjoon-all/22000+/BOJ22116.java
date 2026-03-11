/*
 * 백준 22116번 : 창영이와 퇴근
 * https://www.acmicpc.net/problem/22116
 * 난이도 : 골드 4
 * 풀이 날짜 : 2026-03-11
 * 간단 설명 : 시작점에서 도착점까지 가는 최대 경사의 최솟값을 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.PriorityQueue;

public class BOJ22116 {
    static class Node { // 좌표 class
        int r; // r 좌표
        int c; // c 좌표
        int maxSlope; // 최대 경사

        Node(int r, int c, int maxSlope) {
            this.r = r;
            this.c = c;
            this.maxSlope = maxSlope;
        }
    } // Node 종료

    static final int MAX = Integer.MAX_VALUE; // 경사 최댓값

    static int N; // 격자의 크기
    static int[][] grid; // 격자 배열

    // 상우하좌
    static int[] dr = {-1, 0, 1,  0};
    static int[] dc = { 0, 1, 0, -1};

    static int dijkstra(int rStart, int cStart, int rEnd, int cEnd) { // 최대 경사의 최솟값을 구하는 함수
        int[][] dist = new int[N][N]; // 경사 배열
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) dist[r][c] = MAX; // 누적 경사 초기화
        }

        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) ->
                Integer.compare(o1.maxSlope, o2.maxSlope)); // 다음 좌표와 경사를 저장할 pq

        pq.add(new Node(rStart, cStart, 0)); // 시작점 add
        dist[rStart][cStart] = 0; // 시작점 경사 초기화
        while (!pq.isEmpty()) {
            Node cur = pq.poll(); // 현재 위치

            if (cur.r == rEnd && cur.c == cEnd) return cur.maxSlope; // 도착 지점이면 종료

            if (dist[cur.r][cur.c] < cur.maxSlope) continue; // 현재 경사보다 과거 경사가 작으면 continue

            for (int d = 0; d < 4; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];

                if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue; // 격자를 벗어나면 continue

                int curSlope = Math.abs(grid[cur.r][cur.c] - grid[nr][nc]); // 경사
                int nxtMaxSlope = Math.max(cur.maxSlope, curSlope); // 경사 최댓값

                if (dist[nr][nc] > nxtMaxSlope) { // 기존 기록된 경사보다 작으면
                    dist[nr][nc] = nxtMaxSlope; // 경사 갱신

                    pq.add(new Node(nr, nc, dist[nr][nc])); // 다음 좌표 add
                }
            }
        }

        return dist[rEnd][cEnd]; // 최대 경사의 최솟값 반환
    } // dijkstra 종료

    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine()); // 격자의 크기
        grid = new int[N][N]; // 격자 배열
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int c = 0; c < N; c++) grid[r][c] = Integer.parseInt(st.nextToken()); // 높이 저장
        }

        // 버퍼 닫기
        br.close();

        int minOfMax = dijkstra(0, 0, N - 1, N - 1); // 최대 경사의 최솟값

        // 결과값 출력하기
        System.out.print(minOfMax);
    } // main 종료
} // Main 종료