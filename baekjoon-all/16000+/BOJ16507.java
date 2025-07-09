/*
 * 백준 16507번 : 어두운 건 무서워
 * https://www.acmicpc.net/problem/16507
 * 난이도 : 실버 1
 * 풀이 날짜 : 2025-06-02
 * 간단 설명 : 주어지는 범위에 대한 평균 밝기를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_16507 {
	// 입력을 위한 객체 선언
	static BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
    public static void main(String[] args) throws IOException {
    	st = new StringTokenizer(br.readLine(), " ");
    	R = Integer.parseInt(st.nextToken()); // 사진의 세로 크기
    	C = Integer.parseInt(st.nextToken()); // 사진의 가로 크기
    	Q = Integer.parseInt(st.nextToken()); // 밝기 평균을 알아볼 개수
    	
    	photo = new int[R + 1][C + 1]; // 사진의 크기
    	for (int x = 1; x <= R; x++) { // 사진 밝기 입력 시작
    		st = new StringTokenizer(br.readLine(), " ");
    		for (int y = 1; y <= C; y++) {
    			photo[x][y] = Integer.parseInt(st.nextToken());
    		}
    	} // 사진 밝기 입력 종료
    	
    	// 행 누적 합 만들기
    	for (int x = 1; x <= R; x++) {
    		for (int y = 2; y <= C; y++) {
    			photo[x][y] += photo[x][y - 1];
    		}
    	}
    	
    	// 열 누적 합 만들기
    	for (int y = 1; y <= C; y++) {
    		for (int x = 2; x <= R; x++) {
    			photo[x][y] += photo[x - 1][y];
    		}
    	}
    	
    	// 쿼리 받기
    	for (int i = 0; i < Q; i++) {
    		st = new StringTokenizer(br.readLine(), " ");
    		int startR1 = Integer.parseInt(st.nextToken()); // 평균을 구할 R 시작점
    		int startC1 = Integer.parseInt(st.nextToken()); // 평균을 구할 C 시작점
    		int endR2 = Integer.parseInt(st.nextToken()); // 평균을 구할 R 종료점
    		int endC2 = Integer.parseInt(st.nextToken()); // 평균을 구할 C 종료점
    		findAvg(startR1, startC1, endR2, endC2);
    	}
    	
    	// 버퍼 닫기
    	br.close();
    	
        // 결과값 출력하기
        System.out.print(sb);
    }
    
    static int R, C, Q; // 사진의 세로 크기, 사진의 가로 크기, 밝기 평균을 알아볼 개수
    static int[][] photo; // 사진의 밝기를 담을 배열
    
    // 해당 구간의 밝기 평균을 구하는 함수
    static StringBuilder sb = new StringBuilder(); // 결과값을 저장할 객체
    static void findAvg(int startR1, int startC1, int endR2, int endC2) {
    	// 평균 밝기 구하기
    	// 평균을 구할 R, C의 종료점이 밝기의 총합
    	// 하지만 포함되지 않는 범위인 네모 바로 바깥의 행과 열의 누적 합을 빼주기
    	// 여기서 평균을 구할 R, C의 시작점보다 가로 1, 세로 1씩 작은 부분이 두 번 빼짐
    	// 그래서 한 번 더해줌
    	// 그리고 가로와 세로의 시작점과 끝의 차이 + 1만큼을 곱해서
    	// sum을 나눠주면 됨
    	int sum = photo[endR2][endC2] - photo[endR2][startC1 - 1] - photo[startR1 - 1][endC2] + photo[startR1 - 1][startC1 - 1];
    	int avg = sum / ((endR2 - startR1 + 1) * (endC2 - startC1 + 1));
    	
    	// 결과값 추가하기
    	sb.append(avg).append("\n");
    }
}