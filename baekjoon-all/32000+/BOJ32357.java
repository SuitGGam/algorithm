/*
 * 백준 32357번 : 더블팰린드롬
 * https://www.acmicpc.net/problem/32357
 * 난이도 : 브론즈 1
 * 풀이 날짜 : 2026-03-13
 * 간단 설명 : 더블팰린드롬 현상을 일으키는 순서쌍의 개수를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ32357 {
    static boolean valid(String s) { // 단어가 팰린드롬인지 확인하는 함수
        int left = 0; // 왼쪽 포인터
        int right = s.length() - 1; // 오른쪽 포인터

        while (left <= right) {
            if (s.charAt(left) != s.charAt(right)) return false; // 팰린드롬이 아닌 경우

            // 다음 문자열 비교
            left++;
            right--;
        }

        return true; // 팰린드롬인 경우
    } // valid 종료

    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()); // 문자열의 수
        int cnt = 0; // 팰린드롬 문자의 개수
        for (int i = 0; i < N; i++) {
            String s = br.readLine(); // 문자열
            if (valid(s)) cnt++;
        }

        // 버퍼 닫기
        br.close();

        // 결과값 출력하기
        System.out.print(cnt * (cnt - 1));
    } // main 종료
} // Main 종료