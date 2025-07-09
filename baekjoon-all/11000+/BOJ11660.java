/*
 * 백준 11660번 : 구간 합 구하기 5
 * https://www.acmicpc.net/problem/11660
 * 난이도 : 실버 1
 * 풀이 날짜 : 2025-06-02
 * 간단 설명 : 주어지는 범위에 대한 합을 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11660 {
	// 입력을 위한 객체 선언
	static BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
    public static void main(String[] args) throws IOException {
    	st = new StringTokenizer(br.readLine(), " ");
    	N = Integer.parseInt(st.nextToken()); // 표의 크기
    	M = Integer.parseInt(st.nextToken()); // 합을 구하는 횟수
    	
    	graph = new int[N + 1][N + 1]; // 표의 크기 설정
    	for (int x = 1; x <= N; x++) { // 표의 값 입력 시작
    		st = new StringTokenizer(br.readLine(), " ");
    		for (int y = 1; y <= N; y++) {
    			graph[x][y] = Integer.parseInt(st.nextToken());
    		}
    	} // 표의 값 입력 종료
    	
    	// 행 누적 합 만들기
    	for (int x = 1; x <= N; x++) {
    		for (int y = 2; y <= N; y++) {
    			graph[x][y] += graph[x][y - 1];
    		}
    	}
    	
    	// 열 누적 합 만들기
    	for (int y = 1; y <= N; y++) {
    		for (int x = 2; x <= N; x++) {
    			graph[x][y] += graph[x - 1][y];
    		}
    	}
    	
    	// 구간의 합 구하기
    	for (int i = 0; i < M; i++) {
    		st = new StringTokenizer(br.readLine(), " ");
    		int startX = Integer.parseInt(st.nextToken()); // 합을 구할 X 시작점
    		int startY = Integer.parseInt(st.nextToken()); // 합을 구할 Y 시작점
    		int endX = Integer.parseInt(st.nextToken()); // 합을 구할 X 종료점
    		int endY = Integer.parseInt(st.nextToken()); // 합을 구할 Y 종료점
    		findSum(startX, startY, endX, endY);
    	}
    	
    	// 버퍼 닫기
    	br.close();
    	
        // 결과값 출력하기
        System.out.print(sb);
    }
    
    static int N, M; // 표의 크기, 합을 구해야 하는 횟수
    static int[][] graph; // 크기를 담을 표
    
    // 구간의 합을 구하는 함수
    static StringBuilder sb = new StringBuilder(); // 결과값을 저장할 객체
    static void findSum(int startX, int startY, int endX, int endY) {
    	// endX, endY에 대한 구간의 총합 구하기
    	// 하지만 포함되지 않는 범위인 네모 바로 바깥의 행과 열의 누적 합을 빼주기
    	// 여기서 합을 구할 X, Y의 시작점보다 가로 1, 세로 1씩 작은 부분이 두 번 빼짐
    	// 그래서 한 번 더해줌
    	int sum = graph[endX][endY] - graph[endX][startY - 1] - graph[startX - 1][endY] + graph[startX - 1][startY - 1];
    	
    	// 결과값 추가하기
    	sb.append(sum).append("\n");
    }
}