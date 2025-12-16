/*
 * 백준 3190번 : 뱀
 * https://www.acmicpc.net/problem/3190
 * 난이도 : 골드 4
 * 풀이 날짜 : 2025-12-17
 * 간단 설명 : Dummy 게임이 끝나는 시간을 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayDeque;

public class BOJ3190 {
    // 입력을 위한 객체 선언
    static BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    
    static class changeDir { // 방향 전환 정보를 담을 class
        int X; // 방향을 바꿀 시간
        char C; // 바꿀 방향
        
        changeDir(int X, char C) {
            this.X = X;
            this.C = C;
        }
    } // changeNode 종료
    
    static class movedNode { // 뱀이 지나간 곳의 정보를 담는 큐
        int x; // 뱀의 이동했던 x 좌표
        int y; // 뱀이 이동했던 y 좌표
        
        movedNode(int x, int y) {
            this.x = x;
            this.y = y;
        }
    } // movedNode 종료
    
    static int N; // 보드의 크기
    static boolean[][] board; // 보드 배열
    static int time = 0; // 경과된 게임 시간
    
    // 우하좌상
    static int dir = 0; // 뱀이 보고 있는 방향
    static int[] dx = { 0, 1,  0, -1 };
    static int[] dy = { 1, 0, -1,  0 };
    static void move(ArrayDeque<changeDir> changeQueue) throws IOException { // 뱀을 움직이는 함수
        int headX = 1; // 뱀의 머리 X 좌표
        int headY = 1; // 뱀의 머리 Y 좌표
        boolean[][] snakeBody = new boolean[N + 1][N + 1]; // 뱀의 몸 배열
        
        snakeBody[1][1] = true; // 뱀의 시작점
        ArrayDeque<movedNode> movedQueue = new ArrayDeque<>(); // 뱀이 지나간 곳의 정보를 담을 queue
        movedQueue.add(new movedNode(1, 1)); // 시작점 add
        
        while (true) {
            time++; // 시간 증가
            
            // 머리 좌표 늘리기
            headX += dx[dir];
            headY += dy[dir];
            
            if (headX <= 0 || headX > N || headY <= 0 || headY > N) return; // board를 벗어나면 종료
            if (snakeBody[headX][headY]) return; // 자신의 몸에 닿으면 종료
            
            snakeBody[headX][headY] = true; // 뱀의 길이 늘리기
            movedQueue.add(new movedNode(headX, headY)); // 늘어난 곳 add
            
            if (board[headX][headY]) board[headX][headY] = false; // 사과가 있다면 먹기
            else { // 사과가 없다면 꼬리 줄이기
                movedNode pastNode = movedQueue.poll(); // 뱀이 과거에 지나갔던 곳
                
                int tailX = pastNode.x; // 뱀의 꼬리 X 좌표
                int tailY = pastNode.y; // 뱀의 꼬리 Y 좌표
                
                snakeBody[tailX][tailY] = false; // 뱀의 길이 줄이기
            }
            
            // 동작이 전부 진행된 후 방향 변환
            while (!changeQueue.isEmpty()) {
                if (changeQueue.peek().X == time) {
                    changeDir info = changeQueue.poll();
                    
                    if (info.C == 'L') dir = (dir + 3) % 4; // 왼쪽으로 방향 전환
                    else dir = (dir + 1) % 4; // 오른쪽으로 방향 전환
                } else break;
            }
        }
    } // move 종료
    
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine()); // 보드의 크기
        int K = Integer.parseInt(br.readLine()); // 사과의 개수
        
        board = new boolean[N + 1][N + 1]; // 보드 배열 크기 지정
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken()); // 사과의 행 위치
            int y = Integer.parseInt(st.nextToken()); // 사과의 열 위치
            
            board[x][y] = true; // 사과 위치 입력
        }
        
        int L = Integer.parseInt(br.readLine()); // 뱀의 방향 변환 횟수
        ArrayDeque<changeDir> changeQueue = new ArrayDeque<>(); // 방향 변환 정보를 담을 queue
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int X = Integer.parseInt(st.nextToken());
            char C = st.nextToken().charAt(0);
            
            changeQueue.add(new changeDir(X, C)); // 방향 전환 정보 add
        }
        
        // 버퍼 닫기
        br.close();
        
        move(changeQueue); // 뱀 이동 시작
        
        // 결과값 출력하기
        System.out.print(time);
    } // main 종료
} // Main 종료