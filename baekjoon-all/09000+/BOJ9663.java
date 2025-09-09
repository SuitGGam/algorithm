/*
 * 백준 9663번 : N-Queen
 * https://www.acmicpc.net/problem/9663
 * 난이도 : 골드 4
 * 풀이 날짜 : 2025-09-10
 * 간단 설명 : 체스판에서 퀸이 서로 공격하지 않게 놓을 수 있는 경우의 수를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ9663 {
    // 입력을 위한 객체 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    static int N; // 체스판의 크기
    static int[] chessBoard; // 체스판 열 방문 처리
    
    static int cnt; // 공격할 수 없는 경우의 수
    static void findVaild(int depth) { // 경우의 수를 찾는 함수
        // 달성 조건
        if (depth == N) {
            cnt++;
            return;
        }
        
        for (int i = 0; i < N; i++) {
            chessBoard[depth] = i;
            if (possibility(depth)) findVaild(depth + 1); // 가능성 확인 후 다음 행 찾기
        }
    } // findVaild 종료
    
    static boolean possibility(int col) {
        for (int i = 0; i < col; i++) {
            if (chessBoard[col] == chessBoard[i]) return false; // 같은 행에 존재하는 경우
            else if (Math.abs(col - i) == Math.abs(chessBoard[col] - chessBoard[i])) return false; // 대각선에 존재하는 경우
        }
        
        return true;
    } // possibility 종료
    
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine()); // 체스판의 크기
        chessBoard = new int[N];// 배열 크기 지정
        
        // 버퍼 닫기
        br.close();
        
        findVaild(0); // 경우의 수 찾기
        
        // 결과값 출력하기
        System.out.print(cnt);
    } // main 종료
} // Main 종료