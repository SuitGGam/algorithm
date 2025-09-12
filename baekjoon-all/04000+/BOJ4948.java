/*
 * 백준 4948번 : 베르트랑 공준
 * https://www.acmicpc.net/problem/4948
 * 난이도 : 실버 2
 * 풀이 날짜 : 2025-09-13
 * 간단 설명 : n보다 크고 2n보다 작거나 같은 소수의 개수를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ4948 {
    // 입력을 위한 객체 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    static boolean[] checkPrime; // 소수 판별 배열
    static void findPrimeNumber() {
        checkPrime = new boolean[246912 + 1]; // 배열 크기 지정
        
        for (int i = 2; i <= 246912; i++) {
            if (checkPrime[i]) continue; // 소수가 아니면 continue
            else for (int j = i * 2; j <= 246912; j += i) checkPrime[j] = true; // 소수의 배수는 모두 true 처리
        }
    } // findPrimeNumber 종료
    
    static int[] countPrime; // 소수 개수 함수
    static void countPrimeNumber() {
        countPrime = new int[246912 + 1];
        
        // 소수인 숫자 확인하기
        for (int i = 2; i <= 246912; i++) if (!checkPrime[i]) countPrime[i]++;
        
        // 소수 누적합 만들기 : 배열 조회용
        for (int i = 2; i <= 246912; i++) countPrime[i] += countPrime[i - 1];
    } // countPrimeNumber 종료
    
    public static void main(String[] args) throws IOException {
        findPrimeNumber();  // n의 최대 범위에 대한 소수 찾기
        countPrimeNumber(); // 구간별 소수의 개수 파악하기
        
        StringBuilder sb = new StringBuilder(); // 결과값을 저장할 객체
        while (true) {
            int n = Integer.parseInt(br.readLine()); // 자연수 n
            
            // 종료 조건
            if (n == 0) break;
            
            // 결과값 추가하기
            sb.append(countPrime[2 * n] - countPrime[n]).append("\n");
        }
        
        // 버퍼 닫기
        br.close();
        
        // 결과값 출력하기
        System.out.print(sb);
    } // main 종료
} // Main 종료