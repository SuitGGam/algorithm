/*
 * 백준 3088번 : 화분 부수기
 * https://www.acmicpc.net/problem/3088
 * 난이도 : 실버 2
 * 풀이 날짜 : 2026-03-08
 * 간단 설명 : 최소로 직접 파괴해야 하는 화분의 개수를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ3088 {
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine()); // 화분의 개수
        boolean[] broken = new boolean[1000000 + 1]; // 숫자 배열
        int cnt = 0; // 최소 직접 파괴 개수
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken()); // 첫 번째 숫자
            int b = Integer.parseInt(st.nextToken()); // 두 번째 숫자
            int c = Integer.parseInt(st.nextToken()); // 세 번째 숫자

            if (!broken[a] && !broken[b] && !broken[c]) cnt++; // 다 깨진 적이 없는 숫자면 파괴 횟수 증가

            // 파괴 처리
            broken[a] = true;
            broken[b] = true;
            broken[c] = true;
        }

        // 버퍼 닫기
        br.close();

        // 결과값 출력하기
        System.out.print(cnt);
    } // main 종료
} // Main 종료