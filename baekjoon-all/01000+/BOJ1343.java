/*
 * 백준 1343번 : 폴리오미노
 * https://www.acmicpc.net/problem/1343
 * 난이도 : 실버 5
 * 풀이 날짜 : 2025-11-07
 * 간단 설명 : 폴리오미노로 보드판을 덮는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1343 {
    static int x; // 연속된 X의 개수
    static StringBuilder sb = new StringBuilder(); // 결과값을 추가할 객체
    static void addPolyomino() { // 폴리오미노를 추가하는 함수
        if (x == 0) return; // x가 0이면 return
        
        for (int j = 0; j < x / 4; j++) sb.append("AAAA");
        x %= 4;
        for (int j = 0; j < x / 2; j++) sb.append("BB");
        x = 0; // X 초기화
    } // addPolyomino 종료
    
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String board = br.readLine(); // 보드판
        
        // 버퍼 닫기
        br.close();
        
        x = 0; // 연속된 X의 개수 초기화
        for (int i = 0; i < board.length(); i++) {
            if (board.charAt(i) == 'X') x++;
            else if (x % 2 != 0) { x = -1; break; }
            else if (x % 2 == 0) {
                addPolyomino();
                sb.append(".");
            }
        }
        
        // 마지막 검사
        if (x % 2 != 0) x = -1;
        else if (x % 2 == 0) addPolyomino();
        
        // 결과값 출력하기
        System.out.print(x != -1 ? sb : -1);
    } // main 종료
} // Main 종료