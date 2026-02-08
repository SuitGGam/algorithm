/*
 * 백준 17136번 : 색종이 붙이기
 * https://www.acmicpc.net/problem/17136
 * 난이도 : 골드 2
 * 풀이 날짜 : 2026-02-09
 * 간단 설명 : 종이 위의 덮을 곳을 색종이 몇 장으로 덮는지 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ17136 {
    static final int SIZE = 10; // 종이의 크기
    
    static int needConfetti; // 필요한 색종이의 개수
    static int[][] paper; // 종이 배열
    static int[] paperCnt = {0, 5, 5, 5, 5, 5}; // 각 크기별 색종이의 갯수
    
    static void dfs(int x, int y, int cnt) { // 색종이 붙일 곳을 탐색하는 함수
        // 종료 조건
        if (x == SIZE) { // 종이를 전부 탐색한 경우
            needConfetti = Math.min(needConfetti, cnt); // 필요한 색종이의 수 갱신
            return; // 종료
        }
        
        if (cnt >= needConfetti) return; // 현재 필요한 색종이의 개수가 이전과 같거나 크면 종료
        
        
        if (y == 10) { // 열을 다 탐색하면 다음 행으로 이동
            dfs(x + 1, 0, cnt);
            return;
        }
        
        if (paper[x][y] == 1) { // 색종이를 덮어야 하는 곳인 경우
            for (int size = 5; size > 0; size--) {
                if (!(paperCnt[size] > 0 && canAttach(x, y, size))) continue; // 색종이를 못 붙이는 경우 continue
                
                attach(x, y, size, 0); // 색종이 붙이기
                paperCnt[size]--; // 색종이 사용
                
                dfs(x, y + 1, cnt + 1); // 다음 칸 이동
                
                attach(x, y, size, 1); // 색종이 떼기
                paperCnt[size]++; // 색종이 복구
            }
        } else dfs(x, y + 1, cnt); // 색종이를 안 덮어도 되는 경우 옆 칸으로 이동
    } // dfs 종료
    
    static boolean canAttach(int startX, int startY, int size) { // 색종이를 붙일 수 있는지 확인하는 함수
        for (int x = startX; x < startX + size; x++) {
            for (int y = startY; y < startY + size; y++) {
                if (x >= SIZE || y >= SIZE || paper[x][y] == 0) return false; // 종이를 벗어나거나 안 덮어도 되는 곳이면 붙일 수 없음
            }
        }
        
        return true; // 색종이를 붙일 수 있음
    } // canAttach 종료
    
    static void attach(int startX, int startY, int size, int state) { // 색종이를 붙이거나 떼는 함수
        for (int x = startX; x < startX + size; x++) {
            for (int y = startY; y < startY + size; y++) paper[x][y] = state;
        }
    } // attach 종료
    
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        paper = new int[SIZE][SIZE]; // 종이 배열
        for (int x = 0; x < SIZE; x++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int y = 0; y < SIZE; y++) paper[x][y] = Integer.parseInt(st.nextToken()); // 종이 정보 저장
        }
        
        // 버퍼 닫기
        br.close();
        
        needConfetti = Integer.MAX_VALUE; // 필요한 색종이의 개수 초기화
        dfs(0, 0, 0); // 색종이 붙이기
        
        // 결과값 출력하기
        System.out.print(needConfetti == Integer.MAX_VALUE ? -1 : needConfetti);
    } // main 종료
} // Main 종료