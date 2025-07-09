/*
 * 백준 28420번 : 카더가든
 * https://www.acmicpc.net/problem/28420
 * 난이도 : 실버 1
 * 풀이 날짜 : 2025-06-04
 * 간단 설명 : 차와 캠핑카에 배치에 따른 최소 흐림(누적 합)을 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_28420 {
	// 입력을 위한 객체 선언
	static BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
    public static void main(String[] args) throws IOException {
    	st = new StringTokenizer(br.readLine(), " ");
    	N = Integer.parseInt(st.nextToken()); // 땅의 가로 크기
    	M = Integer.parseInt(st.nextToken()); // 땅의 세로 크기
    	
    	st = new StringTokenizer(br.readLine(), " ");
    	a = Integer.parseInt(st.nextToken()); // 차와 캠핑카의 너비
    	b = Integer.parseInt(st.nextToken()); // 차의 길이
    	c = Integer.parseInt(st.nextToken()); // 캠핑카의 길이
    	
    	earthRow = new int[N][M]; // 행 누적 합 땅의 크기 지정
    	earthCol = new int[N][M]; // 열 누적 합 땅의 크기 지정
    	for (int x = 0; x < N; x++) {
    		st = new StringTokenizer(br.readLine(), " ");
    		for (int y = 0; y < M; y++) {
    			int blur = Integer.parseInt(st.nextToken()); // 흐림 정도
    			earthRow[x][y] = blur;
    			earthCol[x][y] = blur;
    		}
    	}
    	
    	// 버퍼 닫기
    	br.close();
    	
    	// 행 누적 합 배열 만들기
    	for (int x = 0; x < N; x++) {
    		for (int y = 1; y < M; y++) {
    			earthRow[x][y] += earthRow[x][y - 1];
    		}
    	}
    	
    	// 열 누적 합 배열 만들기
    	for (int y = 0; y < M; y++) {
    		for (int x = 1; x < N; x++) {
    			earthCol[x][y] += earthCol[x - 1][y];
    		}
    	}
    	
    	// 최소 흐림 구하기
    	minBlur = Integer.MAX_VALUE; // 흐림 정도 초기화
    	findMinBlur();
    	
        // 결과값 출력하기
        System.out.print(minBlur);
    }
    
    static int N, M; // 땅의 크기
    static int a, b, c; // 차와 캠핑카의 너비, 차의 길이, 캠핑카의 길이
    static int[][] earthRow; // 행 누적 합 땅 배열
    static int[][] earthCol; // 열 누적 합 땅 배열
    
    // 최소 흐림 정도를 구하는 함수
    static int minBlur; // 최소 흐림 정도
    static void findMinBlur() {
    	// 차와 캠핑가 모양이 일자일 때
    	for (int x = 0; x < N - a + 1; x++) {
    		for (int y = b + c - 1; y < M; y++) {
    			int tmpSumBlur = 0; // 임시 흐림의 합
    			// 수평일 때는 너비만큼의 행의 합을 구해야 함
    			for (int repeat = 0; repeat < a; repeat++) {
    				// 땅을 벗어나지 않는 범위 내에서 해당 너비, 길이만큼의 합 구하기
    				if (y >= b + c) tmpSumBlur += earthRow[x + repeat][y] - earthRow[x + repeat][y - (b + c)];
    				else tmpSumBlur += earthRow[x + repeat][y];
    			}
    			
    			// 최소 흐림 정도 갱신하기
    			if (minBlur > tmpSumBlur) minBlur = tmpSumBlur;
    		}
    	}
    	
    	// 차와 캠핑가 모양이 수직이면서
    	// 캠핑카가 가로, 차가 세로로 놓여져 있을 때
    	for (int x = 0; x < N - (a + b) + 1; x++) {
    		for (int y = c + a - 1; y < M; y++) {
    			int tmpSumBlur = 0; // 임시 흐림의 합
    			// 수직일 때는 너비만큼의 행의 합과
    			// 너비만큼의 열의 합을 구해야 함
    			
    			// 1. 캠핑카에 대한 흐림 정도 구하기
    			for (int repeat = 0; repeat < a; repeat++) {
    				// 땅을 벗어나지 않는 범위 내에서 해당 너비, 길이만큼의 합 구하기
    				if (y >= c + a) tmpSumBlur += earthRow[x + repeat][y - a] - earthRow[x + repeat][y - (c + a)];
    				else tmpSumBlur += earthRow[x + repeat][y - a];
    			}
    			
    			// 2. 차에 대한 흐림 정도 구하기
    			for (int repeat = 0; repeat < a; repeat++) {
    				// 땅을 벗어나지 않는 범위 내에서 해당 너비, 길이만큼의 합 구하기
    				tmpSumBlur += earthCol[x + (a + b) - 1][y - repeat] - earthCol[x + a - 1][y - repeat];
    			}
    			
    			// 최소 흐림 정도 갱신하기
    			if (minBlur > tmpSumBlur) minBlur = tmpSumBlur;
    		}
    	}
    	
    	// 차와 캠핑가 모양이 수직이면서
    	// 차가 가로, 캠핑카가 세로로 놓여져 있을 때
    	for (int x = 0; x < N - (a + c) + 1; x++) {
    		for (int y = b + a - 1; y < M; y++) {
    			int tmpSumBlur = 0; // 임시 흐림의 합
    			// 수직일 때는 너비만큼의 행의 합과
    			// 너비만큼의 열의 합을 구해야 함
    			
    			// 1. 차에 대한 흐림 정도 구하기
    			for (int repeat = 0; repeat < a; repeat++) {
    				// 땅을 벗어나지 않는 범위 내에서 해당 너비, 길이만큼의 합 구하기
    				if (y >= b + a) tmpSumBlur += earthRow[x + repeat][y - a] - earthRow[x + repeat][y - (b + a)];
    				else tmpSumBlur += earthRow[x + repeat][y - a];
    			}
    			
    			// 2. 캠핑카에 대한 흐림 정도 구하기
    			for (int repeat = 0; repeat < a; repeat++) {
    				// 땅을 벗어나지 않는 범위 내에서 해당 너비, 길이만큼의 합 구하기
    				tmpSumBlur += earthCol[x + (a + c) - 1][y - repeat] - earthCol[x + a - 1][y - repeat];
    			}
    			
    			// 최소 흐림 정도 갱신하기
    			if (minBlur > tmpSumBlur) minBlur = tmpSumBlur;
    		}
    	}
    }
}