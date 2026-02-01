/*
 * 백준 16510번 : Predictable Queue
 * https://www.acmicpc.net/problem/16510
 * 난이도 : 실버 1
 * 풀이 날짜 : 2026-02-02
 * 간단 설명 : 주어지는 시간 안에 몇 개의 일을 처리할 수 있는지 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ16510 {
	static int N; // 일의 개수
	static int[] prefixSum; // 일의 시간 누적 합 배열

	static int binarySearch(int T) { // 처리할 수 있는 일의 개수를 구하는 함수
		int handle = 0; // 처리할 수 있는 일의 개수
		
		int start = 1; // 시작 구간
		int end = N; // 종료 구간
		while (start <= end) {
			int mid = (start + end) / 2; // 가운데
			if (prefixSum[mid] <= T) {
				handle = mid; // 처리할 수 있는 일의 개수 갱신
				start = mid + 1; // 시작 구간 증가
			} else end = mid - 1; // 종료 구간 감소
		}
		
		return handle; // 처리할 수 있는 일의 개수 반환
	} // binarySearch 종료

	public static void main(String[] args) throws IOException {
		// 입력을 위한 객체 선언
		BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken()); // 일의 개수
		int M = Integer.parseInt(st.nextToken()); // 몇 개의 일을 처리할 수 있는지 알아볼 개수
		
		prefixSum = new int[N + 1]; // 일의 시간 누적 합 배열
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; i++) {
			int time = Integer.parseInt(st.nextToken()); // 일의 시간 저장
			prefixSum[i] = time + prefixSum[i - 1]; // 시간 누적 합 만들기
		}
		
		StringBuilder sb = new StringBuilder(); // 결과값을 저장할 객체
		for (int i = 0; i < M; i++) {
			int T = Integer.parseInt(br.readLine()); // 일을 할 수 있는 시간
			sb.append(binarySearch(T)).append("\n"); // 결과값 추가하기
		}
		
		// 버퍼 닫기
		br.close();
		
		// 결과값 출력하기
		System.out.print(sb);
	} // main 종료
} // Main 종료
