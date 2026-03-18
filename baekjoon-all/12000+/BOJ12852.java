/*
 * 백준 12852번 : 1로 만들기 2
 * https://www.acmicpc.net/problem/12852
 * 난이도 : 골드 5
 * 풀이 날짜 : 2026-03-19
 * 간단 설명 : 정수 X를 1로 만드는 최소 연산 횟수와 방법을 출력하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ12852 {
    static int[] dp; // dp 배열
    static int[] route; // 경로 배열

    static int makeOne(int N) { // 숫자를 1로 만드는 함수
        if (N == 1) return 0; // 숫자가 1이 되면 종료

        if (dp[N] != -1) return dp[N]; // 이미 계산된 경우면 반환

        int cur = makeOne(N - 1) + 1; // 1을 뺀 경우
        route[N] = N - 1; // 경로 저장

        if (N % 2 == 0) { // 2로 나눈 경우
            int tmp = makeOne(N / 2) + 1; // 임시 연산 횟수

            if (tmp < cur) { // 연산 횟수가 더 적은 경우
                cur = tmp; // 연산 횟수 갱신
                route[N] = N / 2; // 경로 갱신
            }
        }

        if (N % 3 == 0) { // 3으로 나눈 경우
            int tmp = makeOne(N / 3) + 1; // 임시 연산 횟수

            if (tmp < cur) { // 연산 횟수가 더 적은 경우
                cur = tmp; // 연산 횟수 갱신
                route[N] = N / 3; // 경로 갱신
            }
        }

        return dp[N] = cur; // 최소 연산 횟수 저장 후 갱신
    } // makeOne

    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()); // 자연수

        // 버퍼 닫기
        br.close();

        dp = new int [N + 1]; // dp 배열
        route = new int[N + 1]; // 경로 배열
        Arrays.fill(dp, -1); //dp 배열 초기화

        dp[1] = 0; // 1은 연산 0번
        int minCal = makeOne(N); // 1 만들기

        StringBuilder sb = new StringBuilder(); // 경로를 저장할 객체
        while (N > 0) {
            sb.append(N).append(" "); // 경로 추가하기
            N = route[N]; // 다음 경로로 이동
        }

        // 결과값 출력하기
        System.out.print(minCal + "\n" + sb);
    } // main 종료
} // Main 종료