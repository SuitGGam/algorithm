/*
 * 백준 2960번 : 에라토스테네스의 체
 * https://www.acmicpc.net/problem/2960
 * 난이도 : 실버 4
 * 풀이 날짜 : 2025-09-15
 * 간단 설명 : 에라토스테네스의 체에서 K번째 지워진 수를 찾는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2960 {
    // 입력을 위한 객체 선언
    static BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken()); // 자연수 N
        int K = Integer.parseInt(st.nextToken()); // 지워지는 수의 개수
        
        // 버퍼 닫기
        br.close();
        
        int result = 0; // K번째 지워진 수
        int removeCnt = 0; // 지워진 숫자의 개수
        boolean[] checkRemove = new boolean[N + 1]; // 수의 지움 여부를 확인하는 배열
        for (int i = 2; i <= N; i++) {
            if (checkRemove[i]) continue; // 지워진 숫자면 continue
            else {
                for (int j = i; j <= N; j += i) { // 소수인 경우
                    if (checkRemove[j]) continue; // 지워진 숫자면 continue
                    else {
                        checkRemove[j] = true; // 숫자 지움 처리
                        if (K == ++removeCnt) result = j; // K번째 지워진 수
                    }
                }
            }
        }
        
        // 결과값 출력하기
        System.out.print(result);
    } // main 종료
} // Main 종료