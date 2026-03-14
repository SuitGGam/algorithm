/*
 * 백준 9465번 : 스티커
 * https://www.acmicpc.net/problem/9465
 * 난이도 : 실버 1
 * 풀이 날짜 : 2026-03-14
 * 간단 설명 : 두 변을 공유하지 않는 스티커 점수의 최댓값을 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class BOJ9465 {
    static int n; // 배열의 크기
    static int[][] sticker; // 스티커 배열
    static int[][] dp; // dp 배열

    static int getMax(int row, int col) { // 최대 점수를 구하는 함수
        if (col == n) return 0; // 스티커를 전부 선택했으면 종료

        if (dp[row][col] != - 1) return dp[row][col]; // 이미 계산된 곳이면 반환

        int cur = 0; // 현재 점수
        if (row == 0) { // 위쪽 스티커를 선택한 경우
            cur = sticker[row][col] + Math.max(getMax(1, col + 1), getMax(2, col + 1));
        } else if (row == 1) { // 아래쪽 스티커를 선택한 경우
            cur = sticker[row][col] + Math.max(getMax(0, col + 1), getMax(2, col + 1));
        } else{ // 스티커를 선택하지 않는 경우
            cur = Math.max(getMax(0, col + 1), Math.max(getMax(1, col + 1), getMax(2, col + 1)));
        }

        return dp[row][col] = cur; // 점수 반환
    } // getMax 종료

    static void reset() { // dp 배열을 초기화하는 함수
        for (int i = 0; i < 3; i++) Arrays.fill(dp[i], -1); // dp 배열 초기화
    } // reset 종료

    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine()); // 테스트 케이스의 개수
        StringBuilder sb = new StringBuilder(); // 결과값을 저장할 객체
        for (int tc = 0; tc < T; tc++) {
            n = Integer.parseInt(br.readLine()); // 배열의 크기

            sticker = new int[2][n]; // 스티커 배열
            for (int i = 0; i < 2; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < n; j++) sticker[i][j] = Integer.parseInt(st.nextToken()); // 스티커 점수 입력
            }

            dp = new int[3][n]; // dp 배열
            reset(); // dp 배열 초기화

            int maxScore = Math.max(getMax(0, 0), Math.max(getMax(1, 0), getMax(2, 0))); // 최대 점수

            sb.append(maxScore).append("\n"); // 결과값 추가하기
        }

        // 버퍼 닫기
        br.close();

        // 결과값 출력하기
        System.out.print(sb);
    } // main 종료
} // Main 종료