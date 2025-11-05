/*
 * 백준 2447번 : 별 찍기 - 10
 * https://www.acmicpc.net/problem/2447
 * 난이도 : 골드 5
 * 풀이 날짜 : 2025-10-09
 * 간단 설명 : 재귀적인 별 찍기를 하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2447 {
    // 입력을 위한 객체 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    static char[][] star; // 별 배열
    static void star(int x, int y, int size, boolean blank) { // 별을 찍는 함수
        // 종료 조건이 먼저 오면 안 됨
        // 1칸이 공백일 수도 있음
        if (blank) { // 공백 칸인 경우
            for (int i = x; i < x + size; i++) {
                for (int j = y; j < y + size; j++) {
                    star[i][j] = ' '; // 공백 채우기
                }
            }
            
            return;
        }
        
        // 종료 조건
        if (size == 1) {
            star[x][y] = '*'; // 별 채우기
            return;
        }
        
        int divideSize = size / 3; // 3등분 한 크기
        int count = 0; // 별을 찍은 개수
        for (int i = x; i < x + size; i+= divideSize) {
            for (int j = y; j < y + size; j += divideSize) {
                count++; // 별 하나 찍음
                if (count == 5) star(i, j, divideSize, true); // 공백인 경우
                else star(i, j, divideSize, false); // 공백이 아닌 경우
            }
        }
    } // star 종료
    
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine()); // 줄의 개수
        
        // 버퍼 닫기
        br.close();
        
        star = new char[N][N]; // 배열 크기 지정
        star(0, 0, N, false); // 별 찍기
        
        // 결과값 추가하기
        StringBuilder sb = new StringBuilder();
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                sb.append(star[x][y]);
            }
            sb.append("\n"); // 개행 처리
        }
        
        // 결과값 출력하기
        System.out.print(sb);
    } // main 종료
} // Main 종료