/*
 * 백준 1987번 : 알파벳
 * https://www.acmicpc.net/problem/1987
 * 난이도 : 골드 4
 * 풀이 날짜 : 2026-03-03
 * 간단 설명 : R행 C열의 보드에서 같은 알파벳을 두 번 이상 지나지 않고 최대로 갈 수 있는 칸 수를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1987 {
    static int R, C; // 세로 길이, 가로 길이
    static int maxSpace; // 최대로 움직일 수 있는 칸 수
    static char[][] board; // 보드 배열
    static boolean[] visited; // 알파벳 방문 처리 배열

    // 상우하좌
    static int[] dr = {-1, 0, 1,  0};
    static int[] dc = { 0, 1, 0, -1};

    static void dfs(int r, int c, int cnt) { // 말이 갈 수 있는 최대 칸 수를 구하는 함수
        maxSpace = Math.max(maxSpace, cnt); // 최대로 움직일 수 있는 칸 수 갱신

        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            // 보드를 벗어났거나 이미 지나간 문자면 continue
            if (nr < 0 || nr >= R || nc < 0 || nc >= C || visited[board[nr][nc] - 'A']) continue;

            visited[board[nr][nc] - 'A'] = true; // 알파벳 방문 처리
            dfs(nr, nc, cnt + 1); // 다음 장소 이동
            visited[board[nr][nc] - 'A'] = false; // 알파벳 방문 처리 해제
        }
    } // dfs 종료

    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        R = Integer.parseInt(st.nextToken()); // 세로 크기
        C = Integer.parseInt(st.nextToken()); // 가로 크기

        board = new char[R][C]; // 보드 배열
        for (int r = 0; r < R; r++) {
            String alphabet = br.readLine(); // 알파벳
            for (int c = 0; c < C; c++) board[r][c] = alphabet.charAt(c); // 문자 저장
        }

        // 버퍼 닫기
        br.close();

        visited = new boolean[26]; // 알파벳 방문 처리 배열
        visited[board[0][0] - 'A'] = true; // 시작점 알파벳 방문 처리
        dfs(0, 0, 1); // 지날 수 있는 최대 칸 수

        // 결과값 출력하기
        System.out.print(maxSpace);
    } // main 종료
} // Main 종료