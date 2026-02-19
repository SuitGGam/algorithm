/*
 * 백준 1990번 : 소수인팰린드롬
 * https://www.acmicpc.net/problem/1990
 * 난이도 : 골드 5
 * 풀이 날짜 : 2026-02-19
 * 간단 설명 : 소수면서 팰린드롬인 수를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1990 {
    static boolean[] composite; // 합성수 배열
    static StringBuilder sb = new StringBuilder(); // 결과값을 저장할 객체

    static void composite(int len) {
        composite = new boolean[len + 1]; // 합성수 배열 크기 지정

        for (int i = 2; i * i<= len; i++) {
            if (!composite[i]) {
                for (int j = i * i; j <= len; j += i) composite[j] = true; // 합성수 처리
            }
        }
    } // composite 종료

    static boolean isPalindrome(int num) { // 팰린드롬인지 확인하는 함수
        String n = String.valueOf(num); // 문자열로 변환
        int left = 0; // 왼쪽 포인터
        int right = n.length() - 1; // 오른쪽 포인터
        while (left <= right) {
            if (n.charAt(left++) != n.charAt(right--)) return false; // 팰린드롬 유효하지 않음
        }

        return true; // 팰린드롬 유효
    } // isPalindrome 종료

    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int a = Integer.parseInt(st.nextToken()); // 시작 범위
        int b = Integer.parseInt(st.nextToken()); // 끝 범위

        // 버퍼 닫기
        br.close();

        composite(b); // 합성수 찾기

        for (int i = a; i <= b; i++) {
            if (!composite[i] && isPalindrome(i)) sb.append(i).append("\n"); // 결과값 추가하기
        }
        sb.append(-1); // 마지막 결과값 추가하기

        // 결과값 출력하기
        System.out.print(sb);
    } // main 종료
} // Main 종료