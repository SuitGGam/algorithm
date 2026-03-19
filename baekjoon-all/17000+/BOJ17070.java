/*
 * 백준 17070번 : 파이프 옮기기 1
 * https://www.acmicpc.net/problem/17070
 * 난이도 : 골드 5
 * 풀이 날짜 : 2026-03-20
 * 간단 설명 : 출발지에서 도착지까지 가는 방법의 수를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayDeque;

public class BOJ17070 {
    static class Pipe { // 파이브의 정보를 담을 class
        int r; // 행 좌표
        int c; // 열 좌표
        int s; // 현재 모양 - 가로(0), 세로(1), 대각선(2)

        Pipe(int r, int c, int s) {
            this.r = r;
            this.c = c;
            this.s = s;
        }
    } // Node 종료

    static int N; // 집의 크기
    static int[][] home; // 집 배열
    static int cnt; // 이동시키는 방법의 수

    // 가로, 세로, 대각선 이동
    static int[] dr = {0, 1, 1};
    static int[] dc = {1, 0, 1};

    static void dfs() { // 
        ArrayDeque<Pipe> stack = new ArrayDeque<>(); // 파이브 정보를 담기 위한 Stack
        stack.push(new Pipe(0, 1, 0)); // 시작점 push

        while (!stack.isEmpty()) {
            Pipe cur = stack.pop(); // 현재 파이브 정보

            if (cur.r == N - 1 && cur.c == N - 1) {cnt++; continue;} // 도착점에 도착한 경우

            for (int d = 0; d < 3; d++) {
                if (cur.s == 0 && d == 1) continue; // 가로 모양이면 세로 이동 불가능
                if (cur.s == 1 && d == 0) continue; // 세로 모양이면 가로 이동 불가능

                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];

                if (nr >= N || nc >= N || home[nr][nc] == 1) continue; // 집을 벗어나거나 벽을 만나면 continue

                if (d == 2) { // 대각선 이동인 경우
                    if (home[nr - 1][nc] == 1 || home[nr][nc - 1] == 1) continue; // 다른 칸이 막혀있으면 continue
                }

                stack.push(new Pipe(nr, nc, d)); // 다음 지점 push
            }
        }
    } // dfs 종료

    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine()); // 집의 크기
        home = new int[N][N]; // 집 배열
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int c = 0; c < N; c++) home[r][c] = Integer.parseInt(st.nextToken()); // 집 정보 저장
        }

        // 버퍼 닫기
        br.close();

        cnt = 0; // 이동시키는 방법의 수
        dfs(); // 경로 탐색하기

        // 결과값 출력하기
        System.out.print(cnt);
    } // main 종료
} // Main 종료