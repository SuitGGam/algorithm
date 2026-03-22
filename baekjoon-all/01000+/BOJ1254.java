/*
 * 백준 1254번 : 팰린드롬 만들기
 * https://www.acmicpc.net/problem/1254
 * 난이도 : 실버 2
 * 풀이 날짜 : 2026-03-23
 * 간단 설명 : 문자열 뒤에 문자가 추가가 가능할 때 만들 수 있는 가장 짧은 팰린들모 문자열 길이를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1254 {
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String S = br.readLine(); // 문자열 S

        // 버퍼 닫기
        br.close();

        int len   = S.length(); // 문자열 S의 길이
        int max   = 1; // 문자열 S의 부분 팰린드롬 최대 길이
        for (int l = 0; l < len - 1; l++) {
            int left = l; // 왼쪽 포인터
            int right = l; // 오른쪽 포인터
            int tmpMax = 1; // 임시 부분 팰린들모 최대 길이
            while (left >= 0 && right < len) {
                if (S.charAt(left) == S.charAt(right)) { // 두 포인터의 문자가 같은 경우
                    tmpMax = Math.max(tmpMax, right - left + 1); // 임시 부분 팰린드롬 길이 갱신
                    if (right == len - 1) max = Math.max(max, tmpMax); // 부분 팰린드롬 최대 길이 갱신

                    left--; // 왼쪽 포인터 감소
                    right++; // 오른쪽 포인터 증가
                } else break; // 두 포인터의 문자가 다른 경우
            }

            left = l; // 왼쪽 포인터
            right = l + 1; // 오른쪽 포인터
            tmpMax = 1; // 임시 부분 팰린들모 최대 길이
            while (left >= 0 && right < len) {
                if (S.charAt(left) == S.charAt(right)) { // 두 포인터의 문자가 같은 경우
                    tmpMax = Math.max(tmpMax, right - left + 1); // 임시 부분 팰린드롬 길이 갱신
                    if (right == len - 1) max = Math.max(max, tmpMax); // 부분 팰린드롬 최대 길이 갱신

                    left--; // 왼쪽 포인터 감소
                    right++; // 오른쪽 포인터 증가
                } else break; // 두 포인터의 문자가 다른 경우
            }
        }

        // 결과값 출력하기
        System.out.print(max == len ? len : len + (len - max));
    } // main 종료
} // Main 종료