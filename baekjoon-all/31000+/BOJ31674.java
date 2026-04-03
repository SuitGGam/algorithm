/*
 * 백준 31674번 : 특별한 기술력
 * https://www.acmicpc.net/problem/31674
 * 난이도 : 실버 2
 * 풀이 날짜 : 2026-04-04
 * 간단 설명 : 요술 망치를 사용했을 때 얻을 수 있는 가장 큰 키를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class BOJ31674 {
    static final long MOD = 1_000_000_007; // 나머지 연산 수

    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()); // 학생의 수
        long[] height = new long[N]; // 키 배열

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) height[i] = Long.parseLong(st.nextToken()); // 키 저장

        // 버퍼 닫기
        br.close();

        Arrays.sort(height); // 키 정렬

        long maxHeight = 0; // 가장 큰 키
        for (int i = N - 1; i >= 0; i--) maxHeight = (maxHeight * 2 + height[i]) % MOD; // 망치 때리기

        // 결과값 출력하기
        System.out.print(maxHeight);
    } // main 종료
} // Main 종료