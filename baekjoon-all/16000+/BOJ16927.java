/*
 * 백준 16927번 : 배열 돌리기 2
 * https://www.acmicpc.net/problem/16927
 * 난이도 : 골드 5
 * 풀이 날짜 : 2026-03-10
 * 간단 설명 : 배열을 반시계 방향으로 R번 회전시킨 결과를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ16927 {
    static int N, M; // 배열의 세로 크기, 배열의 가로 크기
    static int[][] arr; // 회전시킬 배열

    static void rotate(int R) { // 배열을 회전시키는 함수
        int layer = Math.min(N, M) / 2; // 테두리 반절의 길이

        for (int i = 0; i < layer; i++) {
            int xStart = i, xEnd = N - 1 - i; // 행 시작점, 끝점
            int yStart = i, yEnd = M - 1 - i; // 열 시작점, 끝점

            int height = xEnd - xStart + 1; // 세로 길이
            int width = yEnd - yStart + 1; // 가로 길이
            int perimeter = (height + width - 2) * 2;

            int validR = R % perimeter; // 유효한 회전 횟수

            for (int j = 0; j < validR; j++) {
                int tmp = arr[xStart][yStart]; // 시작점 보관

                // 1. 상단 행 왼쪽으로 밀기 (시작점 빈 칸 채우기)
                for (int y = yStart; y < yEnd; y++) arr[xStart][y] = arr[xStart][y + 1];

                // 2. 우측 열 위쪽으로 밀기 (상단 행 빈 칸 채우기)
                for (int x = xStart; x < xEnd; x++) arr[x][yEnd] = arr[x + 1][yEnd];

                // 3. 하단 행 오른쪽으로 밀기 (우측 열 빈 칸 채우기)
                for (int y = yEnd; y > yStart; y--) arr[xEnd][y] = arr[xEnd][y - 1];

                // 4. 좌측 열 아래쪽으로 밀기 (하단 행 빈 칸 채우기)
                for (int x = xEnd; x > xStart; x--) arr[x][yStart] = arr[x - 1][yStart];

                arr[xStart + 1][yStart] = tmp; // 시작점 옮기기
            }
        }
    } // rotate 종료

    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken()); // 배열의 세로 크기
        M = Integer.parseInt(st.nextToken()); // 배열의 가로 크기
        int R = Integer.parseInt(st.nextToken()); // 회전의 수

        arr = new int[N][M]; // 회전시킬 배열
        for (int x = 0; x < N; x++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int y = 0; y < M; y++) arr[x][y] = Integer.parseInt(st.nextToken()); // 원소 저장
        }

        // 버퍼 닫기
        br.close();

        rotate(R); // 배열 회전시키기

        StringBuilder sb = new StringBuilder(); // 결과값을 저장할 객체
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < M; y++) sb.append(arr[x][y]).append(" "); // 결과값 추가하기
            sb.append("\n"); // 개행 처리
        }

        // 결과값 출력하기
        System.out.print(sb);
    } // main 종료
} // Main 종료