/*
 * 백준 14397번 : 해변
 * https://www.acmicpc.net/problem/14397
 * 난이도 : 실버 4
 * 풀이 날짜 : 2026-02-05
 * 간단 설명 : 해변의 길이를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14397 {
    // 6방향 탐색을 위한 좌표
    static int[] dx     = {-1, -1,  0, 0,  1, 1}; // x
    static int[] oddDy  = { 0,  1, -1, 1,  0, 1}; // 홀수 줄(1, 3, 5 ...) y
    static int[] evenDy = {-1,  0, -1, 1, -1, 0}; // 짝수 줄(0, 2, 4 ...) y
    
	public static void main(String[] args) throws IOException {
		// 입력을 위한 객체 선언
		BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken()); // 지도의 세로 크기
        int M = Integer.parseInt(st.nextToken()); // 지도의 가로 크기
        
        char[][] map = new char[N][M]; // 지도 배열
        for (int x = 0; x < N; x++) {
            String info = br.readLine(); // 지도의 정보
            for (int y = 0; y < M; y++) map[x][y] = info.charAt(y); // 지도 정보 저장
        }
		
		// 버퍼 닫기
		br.close();
        
        int beachLength = 0; // 해변의 길이
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < M; y++) {
                if (map[x][y] != '#') continue; // 땅이 아니면 continue
                
                for (int d = 0; d < 6; d++) {
                    int nx = x + dx[d];
                    int ny = y + (x % 2 == 1 ? oddDy[d] : evenDy[d]);
                    
                    if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue; // 지도를 벗어나면 continue
                    
                    if (map[nx][ny] == '.') beachLength++; // 주변이 해변이면 길이 증가
                }
            }
        }
		
		// 결과값 출력하기
		System.out.print(beachLength);
	} // main 종료
} // Main 종료
