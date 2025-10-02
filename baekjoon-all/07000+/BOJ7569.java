/*
 * 백준 7569번 : 토마토
 * https://www.acmicpc.net/problem/7569
 * 난이도 : 골드 5
 * 풀이 날짜 : 2025-10-03
 * 간단 설명 : 토마토가 다 익는데 필요한 최소 일 수를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayDeque;

public class BOJ7569 {
    // 입력을 위한 객체 선언
    static BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    
    static ArrayDeque<Node> queue = new ArrayDeque<>(); // 익은 토마토를 담을 큐
    static class Node {
        int x; // 토마토의 세로 좌표
        int y; // 토마토의 가로 좌표
        int z; // 토마토의 높이 좌표
        
        Node(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    } // Node 종료
    
    static int minDays, unripe; // 최소 일, 안 익은 토마토 개수
    // 전우후좌상하
    static int[] dx = {-1, 0, 1,  0,  0, 0};
    static int[] dy = { 0, 1, 0, -1,  0, 0};
    static int[] dz = { 0, 0, 0,  0, -1, 1};
    static void ripe(int[][][] tomato, int H, int N, int M) { // 토마토를 익히는 함수
        boolean valid; // 토마토가 다 익었는지 확인하는 변수
        while (!queue.isEmpty()) {
            
            if (unripe == 0) return; // 토마토가 다 익은 경우
            else { // 토마토를 더 익힐 수 있는 경우
                int size = queue.size(); // 현재 큐 크기
                for (int i = 0; i < size; i++) { // 현재 큐만큼만 반복
                    Node node = queue.poll(); // 좌표 꺼내기
                    for (int d = 0; d < 6; d++) {
                        int nx = node.x + dx[d];
                        int ny = node.y + dy[d];
                        int nz = node.z + dz[d];
                        
                        // 농장을 벗어나지 않는 범위 내에서 작동
                        if (nx >= 0 && nx < N && ny >= 0 && ny < M && nz >= 0 && nz < H) {
                            if (tomato[nz][nx][ny] == 0) { // 안 익은 토마토인 경우
                                tomato[nz][nx][ny] = 1; // 토마토 익음 처리
                                unripe--; // 안 익은 토마토 개수 감소
                                queue.add(new Node(nx, ny, nz)); // 토마토 좌표 추가
                            }
                        }
                    }
                }
                
                minDays++; // 최소 일 하루 증가
            }
        }
        
        if (unripe > 0) minDays = -1; // 토마토가 다 익을 수 없는 경우
    } // ripe 종료
    
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        int[][][] tomato = new int[H][N][M]; // 토마토 농장 배열
        
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int k = 0; k < M; k++) {
                    int condition = Integer.parseInt(st.nextToken()); // 토마토의 상태
                    tomato[i][j][k] = condition; // 토마토 상태 저장
                    if (condition == 1) queue.add(new Node(j, k, i)); // 익은 토마토면 좌표 저장
                    else if (condition == 0) unripe++; // 안 익은 토마토 개수 추가
                }
            }
        }
        
        // 버퍼 닫기
        br.close();
        
        minDays = 0; // 최소 일 초기화
        ripe(tomato, H, N, M); // 토마토 익히기
        
        // 결과값 출력하기
        System.out.print(minDays);
    } // main 종료
} // Main 종료