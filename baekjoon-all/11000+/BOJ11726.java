/*
 * 백준 11726번 : 2×n 타일링
 * https://www.acmicpc.net/problem/11726
 * 난이도 : 실버 3
 * 풀이 날짜 : 2025-10-19
 * 간단 설명 : 2×n 크기의 직사각형을 1×2, 2×1 타일로 채우는 방법의 수를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ11726 {
    // 입력을 위한 객체 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    static final int MOD = 10_007; // 모듈러
    
    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine()); // 직사각형의 가로 길이
        
        // 버퍼 닫기
        br.close();
        
        int[] tile = new int[1000 + 1]; // 타일 dp
        tile[1] = 1;
        tile[2] = 2;
        for (int i = 3; i <= n; i++) {
            tile[i] = (tile[i - 1] % MOD + tile[i - 2] % MOD) % MOD;
        }
        
        // 결과값 출력하기
        System.out.print(tile[n]);
    } // main 종료
} // Main 종료