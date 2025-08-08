/*
 * 백준 11399번 : ATM
 * https://www.acmicpc.net/problem/11399
 * 난이도 : 실버 4
 * 풀이 날짜 : 2025-08-08
 * 간단 설명 : N명의 사람이 돈을 인출하는데 걸리는 최소 시간을 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class BOJ11399 {
    // 입력을 위한 객체 선언
    static BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine()); // 사람의 수

        st = new StringTokenizer(br.readLine(), " ");
        int[] takeOut = new int[N]; // 인출하는데 필요한 시간을 저장할 배열
        for (int i = 0; i < N; i++) {
            takeOut[i] = Integer.parseInt(st.nextToken()); // 인출하는데 필요한 시간 저장
        }

        // 버퍼 닫기
        br.close();

        Arrays.sort(takeOut); // 시간 비 내림차순 정렬하기

        int minTime = takeOut[0]; // 최소 시간
        for (int i = 1; i < N; i++) {
            // 누적합으로 바꾸면서 누적합의 합 구하기
            takeOut[i] += takeOut[i - 1]; // 누적합 구하기
            minTime += takeOut[i]; // 최소 시간 더하기
        }

        // 결과값 출력하기
        System.out.print(minTime);
    } // main 종료
} // Main 종료