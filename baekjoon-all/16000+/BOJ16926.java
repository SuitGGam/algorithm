/*
 * 백준 16926번 : 배열 돌리기 1
 * https://www.acmicpc.net/problem/16926
 * 난이도 : 골드 5
 * 풀이 날짜 : 2026-03-08
 * 간단 설명 : 배열을 반시계 방향으로 R번 회전시켰을 때 결과를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ16926 {
    static int N, M; // 배열의 세로 크기, 배열의 가로 크기
    static int[][] arr; // 회전 시킬 배열

    static void rotate(int xStart, int xEnd, int yStart, int yEnd) { // 배열을 회전시키는 함수
        // 종료 조건 - 회전 시킬 곳이 없을 때
        if (xStart >= xEnd || yStart >= yEnd) return;

        int tmp1 = arr[xStart][yStart]; // 왼쪽 상단 임시 저장
        for (int y = yStart; y < yEnd; y++) arr[xStart][y] = arr[xStart][y + 1]; // 위쪽 행
        int tmp2 = arr[xEnd][yStart]; // 왼쪽 하단 임시 저장
        for (int x = xEnd; x > xStart + 1; x--) arr[x][yStart] = arr[x - 1][yStart]; // 왼쪽 열
        arr[xStart + 1][yStart] = tmp1; // 왼쪽 상단 값 옮기기
        tmp1 = arr[xEnd][yEnd]; // 우측 하단 임시 저장
        for (int y = yEnd; y > yStart + 1; y--) arr[xEnd][y] = arr[xEnd][y - 1]; // 아래쪽 행
        arr[xEnd][yStart + 1] = tmp2; // 왼쪽 하단 값 옮기기
        for (int x = xStart; x < xEnd - 1; x++) arr[x][yEnd] = arr[x + 1][yEnd]; // 오른쪽 열
        arr[xEnd - 1][yEnd] = tmp1; // 우측 하단값 옮기기

        rotate(xStart + 1, xEnd - 1, yStart + 1, yEnd - 1); // 안쪽 회전 호출
    } // rotate 종료

    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken()); // 배열의 세로 크기
        M = Integer.parseInt(st.nextToken()); // 배열의 가로 크기
        int R = Integer.parseInt(st.nextToken()); // 회전 횟수

        arr = new int[N][M]; // 회전 시킬 배열
        for (int x = 0; x < N; x++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int y = 0; y < M; y++) arr[x][y] = Integer.parseInt(st.nextToken()); // 원소 입력
        }

        // 버퍼 닫기
        br.close();

        for (int i = 0; i < R; i++) rotate(0, N - 1, 0, M - 1); // 배열 회전

        StringBuilder sb = new StringBuilder(); // 결과값을 저장할 객체
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < M; y++) sb.append(arr[x][y]).append(" "); // 결과값 추가하기
            sb.append("\n"); // 개행 처리
        }

        // 결과값 출력하기
        System.out.print(sb);
    } // main 종료
} // Main 종료