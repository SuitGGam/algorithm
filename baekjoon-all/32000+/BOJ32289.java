/*
 * 백준 32289번 : Max-Queen
 * https://www.acmicpc.net/problem/32289
 * 난이도 : 실버 5
 * 풀이 날짜 : 2026-03-01
 * 간단 설명 : N행 M열로 이루어진 체스판에서 퀸이 서로 다른 공격하는 쌍의 최대 개수를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ32289 {
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        long n = Long.parseLong(st.nextToken()); // 행의 개수
        long m = Long.parseLong(st.nextToken()); // 열의 개수

        // 버퍼 닫기
        br.close();

        long ans = (n - 1) * (4 * m - 3) + m - 1; // 쌍의 개수 최댓값

        // 결과값 출력하기
        System.out.print(ans);
    } // main 종료
} // Main 종료