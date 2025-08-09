/*
 * 백준 1463번 : 1로 만들기
 * https://www.acmicpc.net/problem/1463
 * 난이도 : 실버 3
 * 풀이 날짜 : 2025-08-09
 * 간단 설명 : 정수 N을 1로 만드는 연산을 최솟값을 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1463 {
    // 입력을 위한 객체 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int makeOne(int N, int cnt) { // 1로 만드는 함수
        // 종료 조건
        if (N <= 1) return cnt;

        return Math.min(makeOne(N / 2, cnt + 1 + (N % 2)), makeOne(N / 3, cnt + 1 + (N % 3)));
    } // makeOne 종료

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine()); // 정수 N

        // 버퍼 닫기
        br.close();

        int cnt = makeOne(N, 0); // 연산을 하는 횟수 초기화

        // 결과값 출력하기
        System.out.print(cnt);
    } // main 종료
} // Main 종료