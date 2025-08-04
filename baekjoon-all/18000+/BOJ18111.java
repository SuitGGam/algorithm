/*
 * 백준 18111번 : 마인크래프트
 * https://www.acmicpc.net/problem/18111
 * 난이도 : 실버 2
 * 풀이 날짜 : 2025-06-21
 * 간단 설명 : 여러 땅을 같은 높이로 맞추는 데 드는 최소 시간을 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ18111 {
	// 입력을 위한 객체 선언
	static BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 땅의 넓이
		M = Integer.parseInt(st.nextToken()); // 땅의 높이
		B = Integer.parseInt(st.nextToken()); // 인벤토리에 있는 블록의 개수
		
		earth = new int[N][M]; // 땅의 크기 지정
		minHeight = Integer.MAX_VALUE; // 제일 낮은 땅의 높이 초기화
		maxHeight = Integer.MIN_VALUE; // 제일 높은 땅의 높이 초기화
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				earth[i][j] = Integer.parseInt(st.nextToken()); // 땅의 높이 설정
				// 제일 낮은 땅의 높이 갱신
				if (earth[i][j] < minHeight) minHeight = earth[i][j];
				// 제일 높은 땅의 높이 갱신
				if (earth[i][j] > maxHeight) maxHeight = earth[i][j];
			}
		}
		
		// 버퍼 닫기
		br.close();
		
		minTime = Integer.MAX_VALUE; // 최소 시간 초기화
		earthHeight = Integer.MIN_VALUE; // 맞춰진 땅의 높이 초기화
		startMinecraft(); // 땅 높이 맞추기
		
		// 결과값 출력하기
		System.out.print(minTime + " " + earthHeight);
	} // main 종료
	
	static int N, M, B; // 땅의 넓이, 땅의 길이, 인벤토리에 있는 블록의 개수
	static int minHeight, maxHeight; // 제일 낮은 땅의 높이, 제일 높은 땅의 높이
	static int minTime, earthHeight; // 최소 시간, 맞춰진 땅의 높이
	static int[][] earth; // 땅의 높이를 담을 배열
	
	// 땅의 높이를 맞추는 함수
	static void startMinecraft() {
		// 제일 낮은 땅의 높이부터
		// 제일 높은 땅의 높이까지 완전 탐색
		// 제일 낮거나 높은 곳은 굳이 변경할 필요 없음
work:	for (int height = minHeight; height <= maxHeight; height++) {
			int tmpTime = 0; // 임시 작업 시간
			int tmpBlock = B; // 임시 인벤토리의 블록 개수
			// 깎는 작업 먼저 진행해야 쌓는 작업에 무리 없음
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					// 맞추려는 높이보다
					// 현재 땅의 높이가 높거나 같은 경우
					if (height <= earth[i][j]) {
						tmpTime += (earth[i][j] - height) * 2; // 작업 시간 추가
						tmpBlock += earth[i][j] - height; // 인벤토리에 블록 개수 추가
					}
				}
			}
			
			// 쌓는 작업 시작
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					// 맞추려는 높이보다
					// 현재 땅의 높이가 낮은 경우
					// 쌓을 수 있는 충분한 블록이 있는 경우
					if (height > earth[i][j]) {
						if (tmpBlock >= height - earth[i][j]) {
							tmpTime += height - earth[i][j]; // 작업 시간 추가
							tmpBlock -= height - earth[i][j]; // 인벤토리에 블록 개수 감소
						}
						// 쌓을 수 있는 충분한 블록이 없는 경우
						else continue work;
					}
				}
			}
			
			// 최소 시간일 경우
			// 시간, 높이 갱신
			if (minTime > tmpTime) {
				minTime = tmpTime;
				earthHeight = height;
			}
			// 최소 동일 시간일 경우
			// 최고 높이 갱신
			else if (minTime == tmpTime) {
				if (earthHeight < height) earthHeight = height;
			}
		}
	} // startMinecraft 종료
	
} // class 종료