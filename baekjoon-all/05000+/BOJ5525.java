/*
 * 백준 5525번 : IOIOI
 * https://www.acmicpc.net/problem/5525
 * 난이도 : 실버 1
 * 풀이 날짜 : 2025-11-08
 * 간단 설명 : 연속된 IOI의 개수가 몇 개인지 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ5525 {
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine()); // 문자열 P의 N
        int M = Integer.parseInt(br.readLine()); // S의 길이
        String S = br.readLine(); // 문자열
        
        // 버퍼 닫기
        br.close();
        
        int cnt = 0; // 연속된 IOI의 개수
        int result = 0; // P가 포함된 개수
        for (int i = 1; i < M - 1; ) {
            if (S.charAt(i - 1) == 'I' && S.charAt(i) == 'O' && S.charAt(i + 1) == 'I') {
                cnt++; // IOI 발견
                if (cnt == N) { // N번 연속 발견되면 조건 만족
                    result++; // 결과 증가
                    cnt--; // IOI 연속 개수 감소
                }
                
                i += 2; // 2칸 이동
            } else { // IOI 발견 X
                cnt = 0; // IOI 연속 개수 초기화
                i++; // 1칸 이동
            }
        }
        
        // 결과값 출력하기
        System.out.print(result);
    } // main 종료
} // Main 종료