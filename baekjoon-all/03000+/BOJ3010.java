/*
 * 백준 3010번 : 페그
 * https://www.acmicpc.net/problem/3010
 * 난이도 : 실버 3
 * 풀이 날짜 : 2026-02-26
 * 간단 설명 : 칩이 다른 칩을 점프해서 점프한 칩을 제거할 수 있는 경우의 수를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ3010 {
    // 인근 칩 탐색 - 상우하좌
    static int[] dxChip = {-1, 0, 1,  0};
    static int[] dyChip = { 0, 1, 0, -1};

    // 칩 너머 빈 공간 탐색 - 상우하좌
    static int[] dxBlank = {-2, 0, 2,  0};
    static int[] dyBlank = { 0, 2, 0, -2};

    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[][] board = new char[7][7];
        for (int x = 0; x < 7; x++) {
            String info = br.readLine(); // 페그 상태
            for (int y = 0; y < 7; y++) board[x][y] = info.charAt(y); // 페그 상태 저장
        }

        // 버퍼 닫기
        br.close();

        int cnt = 0; // 칩을 움직일 수 있는 올바른 방법의 수
        for (int x = 0; x < 7; x++) {
            for (int y = 0; y < 7; y++) {
                if (board[x][y] != 'o') continue; // 칩이 아니면 continue

                for (int d = 0; d < 4; d++) {
                    int nx = x + dxChip[d];
                    int ny = y + dyChip[d];

                    // 페그를 벗어나거나 칩이 아니면 continue
                    if (nx < 0 || nx >= 7 || ny < 0 || ny >= 7 || board[nx][ny] != 'o') continue;

                    int nx2 = x + dxBlank[d];
                    int ny2 = y + dyBlank[d];

                    // 페그를 벗어나거나 빈 공간이 아니면 continue
                    if (nx2 < 0 || nx2 >= 7 || ny2 < 0 || ny2 >= 7) continue;

                    if (board[nx2][ny2] == '.') cnt++; // 칩 이동 가능
                }
            }
        }

        // 결과값 출력하기
        System.out.print(cnt);
    } // main 종료
} // Main 종료