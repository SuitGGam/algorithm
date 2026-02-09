/*
 * 백준 5212번 : 지구 온난화
 * https://www.acmicpc.net/problem/5212
 * 난이도 : 실버 2
 * 풀이 날짜 : 2026-02-10
 * 간단 설명 : 50년 후의 지도 상태를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ5212 {
    static int R, C; // 지도의 세로 크기, 지도의 가로 크기
    static char[][] map; // 지도 배열
    
    // 인근 바다 탐색을 위한 4방향 좌표 : 상우하좌
    static int[] dx = {-1, 0, 1,  0};
    static int[] dy = { 0, 1, 0, -1};
    
    static StringBuilder removeIsland() { // 바다 인근의 섬을 제거하는 함수
        char[][] tmp = new char[R][C]; // 임시 지도 배열
        // 50년 후 지도의 범위
        int minR = R;
        int maxR = -1;
        int minC = C;
        int maxC = -1;
        
        for (int x = 0; x < R; x++) { // 50년 후 지도 만들기
            for (int y = 0; y < C; y++) {
                int oceanCnt = 0; // 인근 바다의 수
                
                for (int d = 0; d < 4; d++) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];
                    
                    // 주변이 바다인 경우
                    if (nx < 0 || nx >= R || ny < 0 || ny >= C) oceanCnt++;
                    else if (map[nx][ny] == '.') oceanCnt++;
                }
                
                if (oceanCnt >= 3) tmp[x][y] = '.'; // 바다에 잠기는 경우
                else tmp[x][y] = map[x][y]; // 바다에 안 잠기는 경우
                
                if (tmp[x][y] == 'X') { // 50년 후 지도 범위 구하기
                    minR = Math.min(minR, x);
                    maxR = Math.max(maxR, x);
                    minC = Math.min(minC, y);
                    maxC = Math.max(maxC, y);
                }
            }
        }
        
        StringBuilder sb = new StringBuilder(); // 50년 후 지도를 저장할 객체
        for (int x = minR; x <= maxR; x++) {
            for (int y = minC; y <= maxC; y++) sb.append(tmp[x][y]); // 지도 정보 추가하기
            sb.append("\n"); // 개행 처리
        }
        
        return sb; // 50년 후 지도 반환
    } // removeIsland 종료
    
	public static void main(String[] args) throws IOException {
		// 입력을 위한 객체 선언
		BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		R = Integer.parseInt(st.nextToken()); // 지도의 세로 크기
        C = Integer.parseInt(st.nextToken()); // 지도의 가로 크기
        
        map = new char[R][C]; // 지도 배열
        for (int x = 0; x < R; x++) {
            String info = br.readLine(); // 지도의 정보
            for (int y = 0; y < C; y++) map[x][y] = info.charAt(y); // 지도 정보 저장
        }
		
		// 버퍼 닫기
		br.close();
        
        StringBuilder sb = removeIsland(); // 결과값을 저장할 객체
		
		// 결과값 출력하기
		System.out.print(sb);
	} // main 종료
} // Main 종료
