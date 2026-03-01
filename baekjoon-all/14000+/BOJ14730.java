/*
 * 백준 14730번 : 謎紛芥索紀 (Small)
 * https://www.acmicpc.net/problem/14730
 * 난이도 : 실버 5
 * 풀이 날짜 : 2026-03-01
 * 간단 설명 : 주어진 함수에 대해 f’(1)의 값을 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14730 {
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine()); // 항의 개수
        int ans = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), "  ");
            int C = Integer.parseInt(st.nextToken()); // 항의 계수
            int K = Integer.parseInt(st.nextToken()); // 항의 차수

            ans += C * K;
        }

        // 버퍼 닫기
        br.close();

        // 결과값 출력하기
        System.out.print(ans);
    } // main 종료
} // Main 종료