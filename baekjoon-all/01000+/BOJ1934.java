/*
 * 백준 1934번 : 최소공배수
 * https://www.acmicpc.net/problem/1934
 * 난이도 : 브론즈 1
 * 풀이 날짜 : 2025-09-09
 * 간단 설명 : 최대공약수를 이용해서 최소공배수를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1934 {
    // 입력을 위한 객체 선언
    static BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    
    static int gcd; // 최대공약수
    static void GCD(int A, int B) { // 최대공약수 구하는 함수
        // 종료 조건
        if (Math.min(A, B) == 0) {
            gcd = Math.max(A, B);
            return;
        }
        
        if (A >= B) GCD(B, A % B);
        else GCD(A, B % A);
    } // GCD
    
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스의 개수
        StringBuilder sb = new StringBuilder(); // 결과값을 저장할 객체
        for (int i = 0 ; i < T; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int A = Integer.parseInt(st.nextToken()); // 자연수 A
            int B = Integer.parseInt(st.nextToken()); // 자연수 B
            
            GCD(A, B); // 최대공약수 구하기
            int lcm = A * B / gcd; // 최소공배수 구하기
            
            // 결과값 추가하기
            sb.append(lcm).append("\n");
        }
        
        // 버퍼 닫기
        br.close();
        
        // 결과값 출력하기
        System.out.print(sb);
    } // main 종료
} // Main 종료