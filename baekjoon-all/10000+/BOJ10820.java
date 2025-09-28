/*
 * 백준 10820번 : 문자열 분석
 * https://www.acmicpc.net/problem/10820
 * 난이도 : 브론즈 2
 * 풀이 날짜 : 2025-09-28
 * 간단 설명 : 문자열의 소문자, 대문자, 숫자, 공백의 개수를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ10820 {
    // 입력을 위한 객체 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException{
        StringBuilder sb = new StringBuilder(); // 결과값을 저장할 객체
        String str; // 문자열
        while((str = br.readLine()) != null) {
            int lower  = 0; // 소문자의 개수
            int upper  = 0; // 대문자의 개수
            int number = 0; // 숫자의 개수
            int blank  = 0; // 공백의 개수

            for (int i = 0; i < str.length(); i++) {
                char ch = str.charAt(i); // 현재 문자
                if (ch >= 97 && ch <= 122) lower++; // 소문자인 경우
                else if (ch >= 65 && ch <= 90) upper++; // 대문자인 경우
                else if (ch >= 48 && ch <= 57) number++; // 숫자인 경우
                else if (ch == 32) blank++; // 공백인 경우
            }

            // 결과값 저장하기
            sb.append(lower).append(" ").append(upper).append(" ").append(number).append(" ").append(blank).append("\n");
        }

        // 버퍼 닫기
        br.close();

        // 결과값 출력하기
        System.out.print(sb);
    } // main 종료
} // Main 종료