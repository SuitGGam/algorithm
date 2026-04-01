/*
 * 백준 2448번 : 별 찍기 - 11
 * https://www.acmicpc.net/problem/2448
 * 난이도 : 골드 4
 * 풀이 날짜 : 2026-04-01
 * 간단 설명 : N개의 줄에 규칙에 맞게 별을 찍는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ2448 {
    static char[][] star; // 별 배열

    static void draw(int x, int y, int N) { // 별을 그리는 함수
        // 종료 조건
        if (N == 3) {
            // 첫 번째 줄
            star[x][y] = '*';

            // 두 번째 줄
            star[x + 1][y - 1] = '*';
            star[x + 1][y + 1] = '*';

            // 세 번째 줄
            star[x + 2][y - 2] = '*';
            star[x + 2][y - 1] = '*';
            star[x + 2][y] = '*';
            star[x + 2][y + 1] = '*';
            star[x + 2][y + 2] = '*';

            return; // 종료
        }

        int nextN = N / 2; // 다음 N의 크기

        draw(x, y, nextN);
        draw(x + nextN, y - nextN, nextN);
        draw(x + nextN, y + nextN, nextN);
    } // draw 종료

    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()); // 출력할 줄의 수

        // 버퍼 닫기
        br.close();

        star = new char[N][N * 2 - 1]; // 별 배열
        for (int x = 0; x < N; x++) Arrays.fill(star[x], ' '); // 공백으로 초기화

        draw(0, N - 1, N); // 별 그리기

        StringBuilder sb = new StringBuilder(); // 결과값을 저장할 객체
        for (int x = 0; x < N; x++) { // 결과값 추가하기
            for (int y = 0; y < N * 2 - 1; y++) sb.append(star[x][y]);
            sb.append("\n"); // 개행 처리
        }

        // 결과값 출력하기
        System.out.print(sb);
    } // main 종료
} // Main 종료