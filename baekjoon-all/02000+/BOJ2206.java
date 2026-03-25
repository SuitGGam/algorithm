/*
 * 백준 2206번 : 벽 부수고 이동하기
 * https://www.acmicpc.net/problem/2206
 * 난이도 : 골드 3
 * 풀이 날짜 : 2026-03-26
 * 간단 설명 : 최대 벽을 1개 부술 수 있을 때의 최단 거리를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.PriorityQueue;
import java.util.Arrays;

public class BOJ2206 {
    static final int INF = 1_000_001; // 거리 초기화를 위한 큰 값

    static class Node { // 맵의 정보를 담을 함수
        int x;
        int y;
        int dist;
        boolean broken;

        Node(int x, int y, int dist, boolean broken) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.broken = broken;
        }
    }

    static int N, M; // 맵의 세로 크기, 맵의 가로 크기
    static char[][] map; // 맵 배열
    static int[][][] dist; // 거리 배열

    // 상우하좌
    static int[] dx = {-1, 0, 1,  0};
    static int[] dy = { 0, 1, 0, -1};

    static void dijkstra() { // 벽을 부수고 이동하는 함수
        PriorityQueue<Node> queue = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.dist, o2.dist)); // 각 맵을 담을 PriorityQueue

        dist[map[0][0] == '0' ? 0 : 1][0][0] = 1; // 시작점 거리 초기화
        queue.add(new Node(0, 0, 1, map[0][0] == '0' ? false : true)); // 시작점 add

        while (!queue.isEmpty()) {
            Node cur = queue.poll(); // 현재 노드

            if (cur.x == N - 1 && cur.y == M - 1) continue; // 도착 지점이면 continue

            if (dist[cur.broken ? 1 : 0][cur.x][cur.y] < cur.dist) continue; // 기존 거리보다 현재 거리가 더 길면 continue

            for (int d = 0; d < 4; d++) {
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue; // 맵을 벗어나면 continue

                if (map[nx][ny] == '1') { // 벽인 경우
                    if (!cur.broken && dist[1][nx][ny] > cur.dist + 1) { // 아직 벽을 안 부셨고 거리가 더 짧다면
                        dist[1][nx][ny] = cur.dist + 1; // 벽을 부수고 이동한 거리 갱신
                        queue.add(new Node(nx, ny, cur.dist + 1, true)); // 다음 지점 add
                    }
                } else { // 벽이 아닌 경우
                    int idx = cur.broken ? 1 : 0; // 벽을 부셨는지의 상태
                    if (dist[idx][nx][ny] > cur.dist + 1) { // 거리가 더 짧다면
                        dist[idx][nx][ny] = cur.dist + 1; // 거리 갱신
                        queue.add(new Node(nx, ny, cur.dist + 1, cur.broken)); // 다음 지점 add
                    }
                }
            }
        }
    } // dijkstra 종료

    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken()); // 맵의 세로 크기
        M = Integer.parseInt(st.nextToken()); // 맵의 가로 크기

        map = new char[N][M]; // 맵 배열
        for (int x = 0; x < N; x++) {
            String info = br.readLine(); // 맵의 정보
            for (int y = 0; y < M; y++) map[x][y] = info.charAt(y); // 맵 정보 저장
        }

        // 버퍼 닫기
        br.close();

        dist = new int[2][N][M]; // 거리 배열
        for (int i = 0; i < 2; i++) {
            for (int x = 0; x < N; x++) Arrays.fill(dist[i][x], INF);
        }
        dijkstra(); // 벽 부수고 이동하기

        // 결과값 출력하기
        System.out.print(Math.min(dist[0][N - 1][M - 1], dist[1][N - 1][M - 1]) == INF ? -1 : Math.min(dist[0][N - 1][M - 1], dist[1][N - 1][M - 1]));
    } // main 종료
} // Main 종료