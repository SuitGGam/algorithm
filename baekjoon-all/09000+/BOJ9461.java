/*
 * 백준 9461번 : 파도반 수열
 * https://www.acmicpc.net/problem/9461
 * 난이도 : 실버 3
 * 풀이 날짜 : 2025-10-27
 * 간단 설명 : 파도반 수열을 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ9461 {
    public static void main(String[] args) throws IOException {
        long[] padovan = new long[100 + 1]; // 파도반 수열
        padovan[1] = padovan[2] =  1; // 기본값 세팅
        for (int i = 3; i <= 100; i++) padovan[i] = padovan[i - 2] + padovan[i - 3]; // 파도반 수열 만들기
        
        // 입력을 위한 객체 선언
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스의 개수
        StringBuilder sb = new StringBuilder(); // 결과값을 저장하기 위한 객체
        for (int tc = 0; tc < T; tc++) {
            int N = Integer.parseInt(br.readLine()); // 변의 길이
            sb.append(padovan[N]).append("\n"); // 결과값 추가하기
        }
        
        // 버퍼 닫기
        br.close();
        
        // 결과값 출력하기
        System.out.print(sb);
    } // main 종료
} // Main 종료