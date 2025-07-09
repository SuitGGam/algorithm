/*
 * 백준 28449번 : 누가 이길까
 * https://www.acmicpc.net/problem/28449
 * 난이도 : 골드 5
 * 풀이 날짜 : 2025-07-04
 * 간단 설명 : HI팀 N명과 ARC팀 M명이 서로 모두 겨뤘을 때 전적을 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class BOJ_28449 {
	// 입력을 위한 객체 선언
	static BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int N, M;  // HI팀 인원 수, ARC팀 인원 수
	static long HIWin, ARCWin, draw; // HI팀 승리 수, ARC팀 승리 수, 무승부 수
	static int[] HI;  // HI팀 실력을 담을 배열
	static int[] ARC; // AR팀 실력을 담을 배열
	
	// 대결하는 함수
	static void fight() {
		Arrays.sort(HI);  //  HI팀 비내림차순 정렬
		Arrays.sort(ARC); // ARC팀 비내림차순 정렬
		HIWin = ARCWin = draw = 0; // 결투 횟수 초기화
		
		// HI팀을 기준으로 승부
		// 각 팀원이 ARC팀 전원과의 무승부 시작점과 끝점 찾기
		// 무승부 앞으로는 HI팀 승리, 무승부 뒤로는 ARC팀 승리
		for (int i = 0; i < N; i++) {
			int start = 0;   // ARC팀 시작점
			int end = M - 1; // ARC팀 끝점
			int answer = -1; // HI팀원이 이길 수 있는 기준점, 무승부가 없는 경우를 위한 척도
			boolean checkDraw = false; // 무승부 유무
			
			// 이분 탐색
			while (start <= end) {
				int mid = (start + end) / 2; // 비교 기준점
				
				// HI팀이 실력이 더 좋은 경우
				if (HI[i] > ARC[mid]) {
					answer = mid; // 기준점 갱신
					start = mid + 1;
				}
				// ARC팀이 실력이 더 좋거나 같은 경우
				else {
					if (HI[i] == ARC[mid]) checkDraw = true;
					end = mid - 1;
				}
			}
			
			int startDraw = M; // 무승부 시작점
			int endDraw = -1;  // 무승부 끝점
			// 무승부가 있는 경우 시작점과 끝점 찾기
			if (checkDraw) {
				// 무승부 시작점 찾기
				// 이분 탐색
				start = 0;   // 시작점 초기화
				end = M - 1; // 끝점 초기화
				while (start <= end) {
					int mid = (start + end) / 2; // 비교 기준점
					
					// HI팀이 실력이 더 좋은 경우
					if (HI[i] > ARC[mid]) start = mid + 1;
					// ARC팀이 실력이 더 좋은 경우
					else if (HI[i] < ARC[mid]) end = mid - 1;
					// 무승부인 경우
					else {
						startDraw = mid;
						end = mid - 1;
					}
				}
				
				// 무승부 끝점 찾기
				// 이분 탐색
				start = 0;   // 시작점 초기화
				end = M - 1; // 끝점 초기화
				while (start <= end) {
					int mid = (start + end) / 2; // 비교 기준점
					
					// HI팀이 실력이 더 좋은 경우
					if (HI[i] > ARC[mid]) start = mid + 1;
					// ARC팀이 실력이 더 좋은 경우
					else if (HI[i] < ARC[mid]) end = mid - 1;
					// 무승부인 경우
					else {
						endDraw = mid;
						start = mid + 1;
					}
				}
			}
			
			HIWin  += answer + 1; // HI팀 승리 횟수
			// 무승부가 있는 경우
			if (checkDraw) draw   += endDraw - startDraw + 1; // 무승부 횟수
		}
		
		ARCWin = ((long) N * M) - HIWin - draw; // ARC팀 승리 횟수
	}
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // HI팀 인원 수
		M = Integer.parseInt(st.nextToken()); // ARC팀 인원 수
		
		HI = new int[N]; // HI팀 실력을 담을 배열 크기 지정
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			HI[i] = Integer.parseInt(st.nextToken()); // HI팀 실력 저장
		}
		
		ARC = new int[M]; // HI팀 실력을 담을 배열 크기 지정
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < M; i++) {
			ARC[i] = Integer.parseInt(st.nextToken()); // ARC팀 실력 저장
		}
		
		// 버퍼 닫기
		br.close();
		
		fight(); // 대결 시작
		
		// 결과값 출력하기
		System.out.print(HIWin + " " + ARCWin + " " + draw);
	} // main 종료
} // class 종료