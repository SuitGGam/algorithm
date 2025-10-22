/*
 * 백준 26162번 : 인공 원소
 * https://www.acmicpc.net/problem/26162
 * 난이도 : 실버 5
 * 풀이 날짜 : 2025-10-23
 * 간단 설명 : 두 소수의 합으로 만들 수 있는 원소인지 판별하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ26162 {
    // 입력을 위한 객체 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    static boolean[] prime; // 소수 배열
    static boolean checkValid(int num) { // 인공 합성 가능 여부를 판단하는 함수
        for (int i = 2; i < prime.length - 1; i++) {
            for (int j = i; j < prime.length; j++) {
                if (!prime[i] && !prime[j]) {
                    if (i + j == num) return true;
                }
            }
        }
        
        return false;
    } // checkValid 종료
    
    public static void main(String[] args) throws IOException {
        prime = new boolean[118 + 1]; // 배열 크기 지정
        for (int i = 2; i < prime.length; i++) {
            if (!prime[i]) { // 소수인 경우
                for (int j = i * 2; j < prime.length; j += i) prime[j] = true; // 소수가 아닌 수 제거
            }
        }
        
        int N = Integer.parseInt(br.readLine()); // 원자 번호의 개수
        StringBuilder sb = new StringBuilder(); // 결과값을 저장할 객체
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine()); // 원자 번호
            sb.append(checkValid(num) ? "Yes" : "No").append("\n"); // 결과값 추가하기
        }
        
        // 버퍼 닫기
        br.close();
        
        // 결과값 출력하기
        System.out.print(sb);
    } // main 종료
} // Main 종료