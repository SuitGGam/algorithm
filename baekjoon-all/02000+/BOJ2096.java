/*
 * 백준 2096번 : 내려가기
 * https://www.acmicpc.net/problem/2096
 * 난이도 : 골드 5
 * 풀이 날짜 : 2026-03-13
 * 간단 설명 : 내려가기 게임에서 얻을 수 있는 최소 점수와 최대 점수를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class BOJ2096 {
    static int N; // 줄의 수
    static int[][] game; // 게임 숫자 배열
    static int[][] minDp; // 최소 점수 dp 배열
    static int[][] maxDp; // 최대 점수 dp 배열

    static int getMin(int row, int col) { // 최소 점수를 구하는 함수
        if (row == N - 1) return game[row][col]; // 마지막 행에 도달하면 값 반환

        if (minDp[row][col] != -1) return minDp[row][col]; // 이미 계산된 곳이면 반환

        int cur = 0; // 현재 고를 숫자
        if (col == 0) { // 왼쪽 칸인 경우
            cur = Math.min(getMin(row + 1, 0), getMin(row + 1, 1));
        } else if (col == 1) { // 가운데 칸인 경우
            cur = Math.min(getMin(row + 1, 0), Math.min(getMin(row + 1, 1), getMin(row + 1, 2)));
        } else { // 오른쪽 칸인 경우
            cur = Math.min(getMin(row + 1, 1), getMin(row + 1, 2));
        }

        return minDp[row][col] = game[row][col] + cur; // 더한 값 반환
    } // getMin 종료

    static int getMax(int row, int col) { // 최대 점수를 구하는 함수
        if (row == N - 1) return game[row][col]; // 마지막 행에 도달하면 값 반환

        if (maxDp[row][col] != -1) return maxDp[row][col]; // 이미 계산된 곳이면 반환

        int cur = 0; // 현재 고를 숫자
        if (col == 0) { // 왼쪽 칸인 경우
            cur = Math.max(getMax(row + 1, 0), getMax(row + 1, 1));
        } else if (col == 1) { // 가운데 칸인 경우
            cur = Math.max(getMax(row + 1, 0), Math.max(getMax(row + 1, 1), getMax(row + 1, 2)));
        } else { // 오른쪽 칸인 경우
            cur = Math.max(getMax(row + 1, 1), getMax(row + 1, 2));
        }

        return maxDp[row][col] = game[row][col] + cur; // 더한 값 반환
    } // getMax 종료

    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine()); // 줄의 수
        game = new int[N][3]; // 게임 숫자 배열
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            game[i][0] = Integer.parseInt(st.nextToken()); // 첫 번째 수
            game[i][1] = Integer.parseInt(st.nextToken()); // 두 번째 수
            game[i][2] = Integer.parseInt(st.nextToken()); // 세 번째 수
        }

        // 버퍼 닫기
        br.close();

        minDp = new int[N][3]; // 최소 점수 dp 배열
        for (int i = 0; i < N; i++) Arrays.fill(minDp[i], -1); // 최소 점수 dp 배열 초기화
        int minScore = Math.min(getMin(0, 0), Math.min(getMin(0, 1), getMin(0, 2)));

        maxDp = new int[N][3]; // 최소 점수 dp 배열
        for (int i = 0; i < N; i++) Arrays.fill(maxDp[i], -1); // 최소 점수 dp 배열 초기화
        int maxScore = Math.max(getMax(0, 0), Math.max(getMax(0, 1), getMax(0, 2)));

        // 결과값 출력하기
        System.out.print(maxScore + " " + minScore);
    } // main 종료
} // Main 종료