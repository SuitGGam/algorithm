/*
 * 백준 16918번 : 봄버맨
 * https://www.acmicpc.net/problem/16918
 * 난이도 : 실버 1
 * 풀이 날짜 : 2025-10-02
 * 간단 설명 : N초 후에 격자판의 상태가 어떤지 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ16918 {
    // 입력을 위한 객체 선언
    static BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    
    // 상우하좌
    static int[] dx = {-1, 0, 1,  0};
    static int[] dy = { 0, 1, 0, -1};
    
    static void printGrid(char[][] grid, StringBuilder sb) { // 결과값을 저장하는 함수
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[x].length; y++) {
                sb.append(grid[x][y]); // 결과값 추가하기
            }
            sb.append("\n"); // 개행 처리
        }
    } // printGrid 종료
    
    static char[][] boom(char[][] grid, int R, int C) { // 폭탄이 폭발하는 함수
        char[][] next = new char[R][C]; // 다음 상태 배열
        
        for (int x = 0; x < R; x++) {
            for (int y = 0; y < C; y++) {
                next[x][y] = 'O'; // 폭탄으로 채우기
            }
        }
        
        for (int x = 0; x < R; x++) {
            for (int y = 0; y < C; y++) {
                if (grid[x][y] == 'O') { // 폭탄이었던 위치라면
                    next[x][y] = '.'; // 현재 칸을 빈 칸으로 갱신
                    for (int d = 0; d < 4; d++) {
                        int nx = x + dx[d];
                        int ny = y + dy[d];
                        
                        // 격자판을 안 벗어났다면 현재 칸을 빈 칸으로 갱신
                        if (nx >= 0 && nx < R && ny >= 0 && ny < C) next[nx][ny] = '.';
                    }
                }
            }
        }
        
        return next; // 격자판 최종 상태 return
    } // boom 종료
    
    public static void main(String[] args) throws IOException{
        st = new StringTokenizer(br.readLine(), " ");
        int R = Integer.parseInt(st.nextToken()); // 격자판의 세로 길이
        int C = Integer.parseInt(st.nextToken()); // 격자판의 가로 길이
        int N = Integer.parseInt(st.nextToken()); // 대기 시간
        
        char[][] grid = new char[R][C]; // 격자판 배열
        for (int x = 0; x < R; x++) {
            String state = br.readLine(); // 격자판의 상태
            for (int y = 0; y < C; y++) {
                grid[x][y] = state.charAt(y); // 격자판 상태 저장
            }
        }
        
        // 버퍼 닫기
        br.close();
        
        StringBuilder sb =  new StringBuilder(); // 결과값을 저장할 객체
        if (N == 1) { // N이 1인 경우
            // 입력값 그대로 나옴
            printGrid(grid, sb); // 결과값 추가하기
        } else if (N % 2 == 0) { // N이 짝수인 경우
            // 폭탄이 꽉찬 형태로 나옴
            for (int x = 0; x < R; x++) {
                for (int y = 0; y < C; y++) {
                    sb.append('O'); // 결과값 추가하기
                }
                sb.append("\n"); // 개행 처리
            }
        } else { // N이 3이상의 홀수인 경우
            // 두 가지 패턴만 존재
            char[][] state3 = boom(grid, R, C); // 첫 폭발
            char[][] state5 = boom(state3, R, C); // 두 번째 폭발
            
            if (N % 4 == 3) printGrid(state3, sb);
            else printGrid(state5, sb);
        }
        
        // 결과값 출력하기
        System.out.print(sb);
    } // main 종료
} // Main 종료