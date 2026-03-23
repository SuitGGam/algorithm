/*
 * 백준 5875번 : 오타
 * https://www.acmicpc.net/problem/5875
 * 난이도 : 골드 2
 * 풀이 날짜 : 2026-03-24
 * 간단 설명 : 괄호를 최대 1개만 바꿔서 올바른 괄호쌍이 되는 경우의 수를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ5875 {
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String S = br.readLine(); // 괄호열

        // 버퍼 닫기
        br.close();

        int len = S.length(); // 괄호열의 길이
        int prefixSum = 0; // 괄호 누적 합
        for (int i = 0; i < len; i++) {
            if (S.charAt(i) == '(') prefixSum++; // 여는 괄호인 경우
            else prefixSum--; // 닫는 괄호인 경우
        }

        int cnt = 0; // 경우의 수
        if (prefixSum == 2) { // 여는 괄호가 2개 더 많은 경우
            int open = 0; // 여는 괄호의 수
            prefixSum = 0; // 괄호 누적 합 초기화
            for (int i = len - 1; i >= 0; i--) {
                if (S.charAt(i) == '(') {prefixSum++; open++;}// 여는 괄호인 경우
                else prefixSum--; // 닫는 괄호인 경우

                if (prefixSum == 1) {cnt = open; break;} // 처음으로 누적 합이 양수가 되는 곳까지의 여는 괄호의 수가 정답
            }
        } else if (prefixSum == -2) { // 닫는 괄호가 2개 더 많은 경우
            int close = 0; // 닫는 괄호의 수
            prefixSum = 0; // 괄호 누적 합 초기화
            for (int i = 0; i < len; i++) {
                if (S.charAt(i) == '(') prefixSum++; // 여는 괄호인 경우
                else {prefixSum--; close++;} // 닫는 괄호인 경우

                if (prefixSum == -1) {cnt = close; break;} // 처음으로 누적 합이 음수가 되는 곳까지의 닫힌 괄호의 수가 정답
            }
        }

        // 결과값 출력하기
        System.out.print(cnt);
    } // main 종료
} // Main 종료