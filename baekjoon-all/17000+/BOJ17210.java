/*
 * 백준 17210번 : 문문문
 * https://www.acmicpc.net/problem/17210
 * 난이도 : 브론즈 3
 * 풀이 날짜 : 2026-02-18
 * 간단 설명 : 문을 여는 방법을 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ17210 {
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long N = Long.parseLong(br.readLine()); // 문의 개수
        int method = Integer.parseInt(br.readLine()); // 첫 번째 문을 여는 방법
        int opposite = method == 0 ? 1 : 0; // 반대 방법

        // 버퍼 닫기
        br.close();

        StringBuilder sb = new StringBuilder(); // 결과값을 저장할 객체
        if (N >= 6) sb.append("Love is open door"); // N이 6이상인 경우 열 수 없음
        else { // N이 5이하인 경우
            for (int i = 2; i <= N; i++) {
                if (i % 2 == 0) sb.append(opposite).append("\n"); // 짝수 문인 경우
                else sb.append(method).append("\n"); // 홀수 문인 경우
            }
        }

        // 결과값 출력하기
        System.out.print(sb);
    } // main 종료
} // Main 종료