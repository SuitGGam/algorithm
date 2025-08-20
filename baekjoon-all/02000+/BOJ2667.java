/*
 * 백준 2667번 : 단지번호붙이기
 * https://www.acmicpc.net/problem/2667
 * 난이도 : 실버 1
 * 풀이 날짜 : 2025-08-21
 * 간단 설명 : 지도에서 아파트 단지의 수와 각 단지의 집의 수를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.PriorityQueue;

public class BOJ2667 {
    // 입력을 위한 객체 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    static int N; // 지도의 크기
    static char[][] map; // 지도 배열
    static int[] dx = {-1, 0, 1,  0}; // 상우하좌
    static int[] dy = { 0, 1, 0, -1}; // 상우하좌
    
    static int complexCnt = 0; // 단지 수
    static PriorityQueue<Integer> minHeap = new PriorityQueue<>(); // 단지 내 집의 수
    static void bfs() {
        Queue<Integer> xQueue = new ArrayDeque<>(); // x좌표
        Queue<Integer> yQueue = new ArrayDeque<>(); // y좌표
        
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                if (map[x][y] == '0') continue; // 집이 없으면 스킵
                
                int homeCnt = 0; // 단지 내 집의 수
                
                xQueue.add(x);
                yQueue.add(y);
                homeCnt++; // 집의 수 추가
                map[x][y] = '0';
                
                while (!xQueue.isEmpty()) {
                    int curX = xQueue.poll();
                    int curY = yQueue.poll();
                    
                    for (int d = 0; d < 4; d++) {
                        int nx = curX + dx[d];
                        int ny = curY + dy[d];
                        
                        if (nx < 0 || nx > N - 1 || ny < 0 || ny > N - 1) continue;
                        
                        if (map[nx][ny] == '1') {
                            xQueue.add(nx);
                            yQueue.add(ny);
                            homeCnt++; // 집의 수 추가
                            map[nx][ny] = '0';
                        }
                    }
                }
                minHeap.add(homeCnt);
                complexCnt++; // 단지 수 추가
            }
        }
    } // bfs 종료
    
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine()); // 지도의 크기
        
        map = new char[N][N]; // 지도 배열 생성
        for (int x = 0; x < N; x++) {
            String data = br.readLine(); // 지도의 자료
            for (int y = 0; y < N; y++) {
                map[x][y] = data.charAt(y); // 지도 정보 저장
            }
        }
        
        // 버퍼 닫기
        br.close();
        
        bfs();
        StringBuilder sb = new StringBuilder(); // 결과값을 저장하기 위한 객체
        sb.append(complexCnt).append("\n");
        while(!minHeap.isEmpty()) {
            sb.append(minHeap.poll()).append("\n");
        }
        
        // 결과값 출력하기ㅏ
        System.out.print(sb);
    } // main 종료
} // Main 종료