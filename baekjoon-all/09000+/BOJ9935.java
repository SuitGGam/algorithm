/*
 * 백준 9935번 : 문자열 폭발
 * https://www.acmicpc.net/problem/9935
 * 난이도 : 골드 4
 * 풀이 날짜 : 2026-03-27
 * 간단 설명 : 문자열에서 폭발을 일으킨 문자열을 제외한 남은 문자열을 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ9935 {
    static String S, explosion; // 문자열, 폭발 문자열
    static StringBuilder sb = new StringBuilder(); // 결과값을 저장할 객체

    static void explode() { // 문자열을 폭발시키는 함수

        int len = S.length(); // 문자열의 길이
        int eLen = explosion.length(); // 폭발 문자열의 길이
        for (int i = 0; i < len; i++) {
            sb.append(S.charAt(i)); // 현재 문자 추가
            int sLen = sb.length(); // 현재 StringBuilder의 길이

            if (sb.charAt(sLen - 1) == explosion.charAt(eLen - 1) && sLen >= eLen) { // 현재 문자와 폭발 문자열의 마지막 번째 문자가 똑같은 경우
                boolean valid = true; // 유효성 검증

                for (int j = 0; j < eLen; j++) { // 문자 대조
                    if (sb.charAt(sLen - eLen + j) != explosion.charAt(j)) {valid = false; break;}
                }

                if (valid) sb.delete(sLen - eLen, sLen); // 검증 완료했으니 폭발시키기
            }
        }

        if (sb.length() == 0) sb.append("FRULA"); // 남아있는 문자가 없는 경우
    } // explode 종료

    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        S = br.readLine(); // 문자열
        explosion = br.readLine(); // 폭발 문자열

        // 버퍼 닫기
        br.close();

        explode(); // 문자열 폭발시키기

        // 결과값 출력하기
        System.out.print(sb);
    } // main 종료
} // Main 종료