/*
 * 백준 28426번 : 더하기와 나누기
 * https://www.acmicpc.net/problem/28426
 * 난이도 : 골드 5
 * 풀이 날짜 : 2025-08-15
 * 간단 설명 : 1 ~ N 이하의 모든 정수 i에 대하여 Ai가 A1 + A2 + ... AN의 약수가 되는 정수 i는 정확히 1개가 되는 수열 구하기
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ28426 {
    // 입력을 위한 객체 선언
    static BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
    
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine()); // 수열의 길이
        
        // 버퍼 닫기
        br.close();
        
        // 수열 찾기
        findSequence(N);
        
        // 결과값 출력하기
        System.out.print(sb);
    }
    
    static StringBuilder sb = new StringBuilder(); // 결과값을 저장하기 위한 객체
    static void findSequence(int N) {
        long sum = 0; // 수열의 합
        int[] sequence = new int[N]; // 수열을 담을 배열
        // 수열의 합과 배열 만들기
        for (int i = 0; i < N; i++) {
            sum += i + 2;
            sequence[i] = i + 2;
        }
        
        // 수열 조정하기
        int divisorCnt = 0;
        while (divisorCnt != 1) {
            divisorCnt = 0; // 약수 개수 초기화
            
            // 약수 개수 확인하기
            for (int i = 0; i < N; i++) {
                if (sum % sequence[i] == 0) divisorCnt++;
            }
            
            // 약수의 개수가 1개가 아니면
            // 수열의 맨 마지막 값, 수열의 합 1씩 더해주기
            if (divisorCnt != 1) {
                sequence[N - 1]++;
                sum++;
            }
        }
        
        // 결과값 추가하기
        for (int i = 0; i < N; i++) {
            sb.append(sequence[i]).append(" ");
        }
    }
}