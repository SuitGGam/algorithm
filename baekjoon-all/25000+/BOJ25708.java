/*
 * 백준 25708번 : 만남의 광장
 * https://www.acmicpc.net/problem/25708
 * 난이도 : 실버 1
 * 풀이 날짜 : 2025-06-04
 * 간단 설명 : 두 개의 행, 두 개의 열을 골라서 나올 수 있는 합과
 * 두 개의 행과 두 개의 열 사이에 만들어진 직사각형의 개수의 합의 최댓값을 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_25708 {
	// 입력을 위한 객체 선언
	static BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
    public static void main(String[] args) throws IOException {
    	st = new StringTokenizer(br.readLine(), " ");
    	N = Integer.parseInt(st.nextToken()); // 가로 크기
    	M = Integer.parseInt(st.nextToken()); // 세로 크기
    	square = new int[N][M]; // 만남의 광장 크기 지정
    	squareRow = new int[N][M]; // 만남의 광장(행) 크기 지정
    	squareCol = new int[N][M]; // 만남의 광장(열) 크기 지정
    	
    	for (int x = 0; x < N; x++) {
    		st = new StringTokenizer(br.readLine(), " ");
    		for (int y = 0; y < M; y++) {
    			int beauty = Integer.parseInt(st.nextToken()); // 광장의 아름다움
    			square[x][y] = beauty; // 원본 배열
    			squareRow[x][y] = beauty; // 행 누적 합 배열
    			squareCol[x][y] = beauty; // 열 누적 합 배열
    		}
    	}
    	
    	// 버퍼 닫기
    	br.close();
    	
    	// 행 누적 합 배열 만들기
    	for (int x = 0; x < N; x++) {
    		for (int y = 1; y < M; y++) {
    			squareRow[x][y] += squareRow[x][y - 1];
    		}
    	}
    	
    	// 열 누적 합 배열 만들기
    	for (int y = 0; y < M; y++) {
    		for (int x = 1; x < N; x++) {
    			squareCol[x][y] += squareCol[x - 1][y];
    		}
    	}
    	
    	// 광장의 최대 아름다움 구하기
    	maxBeauty = Integer.MIN_VALUE; // 광장의 최대 아름다움 초기화
    	findMaxBeauty();
    	
        // 결과값 출력하기
        System.out.print(maxBeauty);
    }
    
    static int N, M; // 만남의 광장 크기
    static int[][] square; // 만남의 광장 원본 배열
    static int[][] squareRow; // 만남의 광장 행 누적 합 배열
    static int[][] squareCol; // 만남의 광장 열 누적 합 배열
    
    // 최대 광장의 아름다움을 구하는 함수
    static int maxBeauty; // 광장의 최대 아름다움
    static void findMaxBeauty() {
    	for (int x1 = 0; x1 < N - 1; x1++) {
    		for (int x2 = x1 + 1; x2 < N; x2++) {
    			for (int y1 = 0; y1 < M - 1; y1++) {
    				for (int y2 = y1 + 1; y2 < M; y2++) {
    					// 현재 고른 광장의 최대 아름다움
    					// 겹치는 부분 네 곳은 두 번씩 더해짐
    					// 한 번씩 빼줘야 함
    					// 길에 둘러쌓인 녹지의 칸 개수 더해야 함
    					int tmpSum = squareRow[x1][M - 1] + squareRow[x2][M - 1] + squareCol[N - 1][y1] + squareCol[N - 1][y2];
    					tmpSum -= square[x1][y1] + square[x1][y2] + square[x2][y1] + square[x2][y2];
    					tmpSum += (x2 - x1 - 1) * (y2 - y1 - 1);
    					
    					// 광장의 최대 아름다움 갱신하기
    					if (maxBeauty < tmpSum) maxBeauty = tmpSum;
    				}
    			}
    		}
    	}
    }
}