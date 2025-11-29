/*
 * 백준 14503번 : 로봇 청소기
 * https://www.acmicpc.net/problem/14503
 * 난이도 : 골드 5
 * 풀이 날짜 : 2025-11-30
 * 간단 설명 : 로봇 청소기가 작동이 멈추기 전까지 청소한 칸의 개수를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14503 {
    static int N, M; // 방의 세로 크기, 방의 가로 크기
    static int[][] room; // 방 배열
    static int d; // 로봇 청소기의 방향
    
    // 북동남서
    static int[] dx = { -1, 0, 1,  0 };
    static int[] dy = {  0, 1, 0, -1 };
    static int work(int r, int c) { // 로봇 청소기를 작동하는 함수
        int cleanCnt = 0; // 청소하는 칸의 개수
        while (true) {
            if (room[r][c] == 0) { cleanRoom(r, c); cleanCnt++; } // 현재 칸이 청소가 안 된 경우, 현재 칸 청소
            
            boolean checkNotCleanRoom = false; // 청소되지 않은 주변 칸을 확인하는 변수
            for (int i = 0; i < 4; i++) {
                int nx = r + dx[i];
                int ny = c+ dy[i];
                
                if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue; // 방을 벗어나면 continue
                
                if (room[nx][ny] == 0) checkNotCleanRoom = true; // 청소되지 않은 칸을 발견한 경우
            }
            
            if (!checkNotCleanRoom) { // 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 없는 경우
                int nx = r + dx[(d + 2) % 4];
                int ny = c + dy[(d + 2) % 4];
                
                if (room[nx][ny] == 1 || nx < 0 || nx >= N || ny < 0 || ny >= M) break; // 후진이 불가능한 경우 break
                else { r = nx; c = ny; } // 후진이 가능한 경우 후진
            } else { // 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 있는 경우
                d = (d + 3) % 4; // 반시계 방향으로 90도 회전
                
                int nx = r + dx[d];
                int ny = c + dy[d];
                if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue; // 방을 벗어나면 continue
                
                if (room[nx][ny] == 0) { r = nx; c = ny; } // 앞쪽 칸이 청소되지 않은 빈 칸이면 전진
            }
        }
        
        return cleanCnt; // 청소한 칸의 개수 반환
    } // work 종료
    
    static void cleanRoom(int x, int y) { // 현재 칸을 청소하는 함수
        room[x][y] = 2; // 현재 칸 청소
    } // cleanRoom 종료
    
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        N = Integer.parseInt(st.nextToken()); // 방의 세로 크기
        M = Integer.parseInt(st.nextToken()); // 방의 가로 크기
        
        st = new StringTokenizer(br.readLine(), " ");
        int r = Integer.parseInt(st.nextToken()); // 로봇 청소기의 시작 세로 위치
        int c = Integer.parseInt(st.nextToken()); // 로봇 청소기의 시작 가로 위치
        d = Integer.parseInt(st.nextToken()); // 로봇 청소기의 방향
        
        room = new int[N][M]; // 배열 크기 지정
        for (int x = 0; x < N; x++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int y = 0; y < M; y++) {
                room[x][y] = Integer.parseInt(st.nextToken()); // 방 정보 저장
            }
        }
        
        // 버퍼 닫기
        br.close();
        
        int cleanRoom = work(r, c);
        
        // 결과값 출력하기
        System.out.print(cleanRoom);
    } // main 종료
} // Main 종료
