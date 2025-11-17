/*
 * 백준 3896번 : 소수 사이 수열
 * https://www.acmicpc.net/problem/3896
 * 난이도 : 실버 1
 * 풀이 날짜 : 2025-11-18
 * 간단 설명 : 정수 k를 포함하는 소수 사이 수열의 길이를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ3896 {
    public static void main(String[] args) throws IOException {
        boolean[] prime = new boolean[1299709 + 1]; // 소수 배열
        for (int i = 2; i < prime.length; i++) { // 소수 배열 만들기
            if (!prime[i]) for (int j = i + i; j < prime.length; j += i) prime[j] = true;
        }
        
        // 입력을 위한 객체 선언
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스의 개수
        StringBuilder sb = new StringBuilder(); // 결과값을 저장할 객체
        for (int i = 0; i < T; i++) {
            int k = Integer.parseInt(br.readLine()); // 정수
            if (!prime[k]) { // 소수인 경우
                sb.append(0).append("\n"); // 결과값 추가하기
            } else { // 소수가 아닌 경우
                int left = k; // 왼쪽 소수
                while (prime[left]) left--;
                int right = k; // 오른쪽 소수
                while (prime[right]) right++;
                
                sb.append(right - left).append("\n"); // 결과값 추가하기
            }
        }
        
        // 버퍼 닫기
        br.close();
        
        // 결과값 출력하기
        System.out.print(sb);
    } // main 종료
} // Main 종료