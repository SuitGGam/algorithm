/*
 * 백준 32206번 : 아보와 킨텍스
 * https://www.acmicpc.net/problem/32206
 * 난이도 : 실버 1
 * 풀이 날짜 : 2025-11-19
 * 간단 설명 : 문자열의 앞이나 뒤, 혹은 문자 사이에 영문 소문자 하나를 추가할 때 나올 수 있는 서로 다른 문자열의 개수
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ32206 {
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine()); // 문자열의 길이
        br.readLine(); // 문자열 S
        
        // 버퍼 닫기
        br.close();
        
        // 결과값 출력하기
        System.out.print((N + 1) * 26 - N);
    } // main 종료
} // Main 종료