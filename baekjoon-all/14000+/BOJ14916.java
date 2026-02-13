/*
 * 백준 14916번 : 거스름돈
 * https://www.acmicpc.net/problem/14916
 * 난이도 : 실버 5
 * 풀이 날짜 : 2026-02-14
 * 간단 설명 : 최소 거스름돈 동전의 개수를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ14916 {
    static final int MAX = 100_000; // 최대 동전의 개수

    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine()); // 거스름돈 액수

        // 버퍼 닫기
        br.close();

        int minCoin = MAX; // 최소 동전의 개수 초기화
        for (int five = n % 2 == 0 ? 0 : 1; five <= MAX / 5; five += 2) { // 5원 동전의 개수
            for (int two = 0; two <= MAX / 2; two++) {
                if (five * 5 + two * 2 == n) minCoin = Math.min(minCoin, five + two); // 개수 갱신
            }
        }

        // 결과값 출력하기
        System.out.print(minCoin != MAX ? minCoin : -1);
    } // main 종료
} // Main 종료