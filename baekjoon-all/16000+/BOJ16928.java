/*
 * 백준 16928번 : 뱀과 사다리 게임
 * https://www.acmicpc.net/problem/16928
 * 난이도 : 골드 5
 * 풀이 날짜 : 2025-11-26
 * 간단 설명 : 게임을 끝내는데 주사위를 최소 몇 번 던져야 하는지 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.ArrayDeque;

public class BOJ16928 {
    static int gameBfs(ArrayList<Integer>[] board) { // 게임을 진행할 함수
        int[] dice = new int[100 + 1]; // 거리 배열
        Arrays.fill(dice, Integer.MAX_VALUE); // 거리 배열 초기화
        
        boolean[] visited = new boolean[100 + 1]; // 방문 처리 배열
        ArrayDeque<int[]> queue = new ArrayDeque<>(); // bfs에 사용할 큐
        dice[1] = 0; // 시작점 주사위 횟수 초기화
        queue.add(new int[] { 1, dice[1] }); // 시작점 큐에 add
        visited[1] = true; // 시작점 방문 처리
        while (!queue.isEmpty()) {
            int[] curLoc = queue.poll(); // 현재 위치
            
            for (int d = 1; d <= 6; d++) {
                int nd = curLoc[0] + d;
                
                if (nd > 100 || visited[nd]) continue; // 게임판을 벗어나거나 방문했던 곳이면 continue
                
                if (board[nd].size() > 0) { // 해당 칸에 사다리나 뱀이 있는 경우
                    visited[nd] = true; // 방문 처리
                    visited[board[nd].get(0)] = true; // 연결된 곳 방문 처리
                    queue.add(new int[] { board[nd].get(0), curLoc[1] + 1 }); // 다음 위치 큐에 add
                    if (dice[nd] > curLoc[1] + 1) dice[nd] = curLoc[1] + 1; // 주사위 횟수 갱신
                    if (dice[board[nd].get(0)] > curLoc[1] + 1) dice[board[nd].get(0)] = curLoc[1] + 1; // 주사위 횟수 갱신
                } else { // 해당 칸에 사다리와 뱀이 없는 경우
                    visited[nd] = true; // 방문 처리
                    queue.add(new int[] { nd, curLoc[1] + 1 }); // 다음 위치 큐에 add
                    if (dice[nd] > curLoc[1] + 1) dice[nd] = curLoc[1] + 1; // 주사위 횟수 갱신
                }
            }
        }
        
        return dice[100];
    } // gameBfs 종료
    
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        ArrayList<Integer>[] board = new ArrayList[100 + 1]; // 게임판 배열
        for (int i = 1; i <= 100; i++) board[i] = new ArrayList<>(); // 칸 배열 생성
        
        int N = Integer.parseInt(st.nextToken()); // 사다리의 수
        int M = Integer.parseInt(st.nextToken()); // 뱀의 수
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken()); // 도착한 칸
            int y = Integer.parseInt(st.nextToken()); // 이동할 칸
            
            board[x].add(y); // 사다리 연결
        }
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int u = Integer.parseInt(st.nextToken()); // 도착한 칸
            int v = Integer.parseInt(st.nextToken()); // 이동할 칸
            
            board[u].add(v); // 뱀 연결
        }
        
        // 버퍼 닫기
        br.close();
        
        int minDice = gameBfs(board); // 최소 주사위 횟수
        
        // 결과값 출력하기
        System.out.print(minDice);
    } // main 종료
} // Main 종료
