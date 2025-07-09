/*
 * 백준 13423번 : Three Dots
 * https://www.acmicpc.net/problem/13423
 * 난이도 : 실버 2
 * 풀이 날짜 : 2025-06-27
 * 간단 설명 : N개의 점 중에서 세 개의 점을 골라, 서로의 차가 같게 하는 경우의 수를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class BOJ_13423 {
	// 입력을 위한 객체 선언
	static BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int N; // 점의 개수
	static int arr[]; // 점 배열
	
	// 경우의 수 찾는 함수
	static int findCase() {
		Arrays.sort(arr); // 점 비오름차순 정렬
		
		int cnt = 0; // 가능한 경우의 수
		for (int i = 0; i < N - 1; i++) { // 첫 번째 점 선택
			for (int j = i + 1; j < N; j++) { // 두 번째 점 선택
				// 점 두 개를 정해놓고
				// 두 번째 점 - 첫 번째 점의 값을
				// 두 번째 점에 더해준 다음
				// 그것과 같은 값을 이진 탐색
				// 만족하는 값을 찾으면 경우의 수 1개 추가
				if (binarySearch(i, j)) cnt++;
			}
		}
		
		// 경우의 수 반환
		return cnt;
	}
	
	static boolean binarySearch(int i, int j) {
		int findNumber = arr[j] + (arr[j] - arr[i]); // 찾으려는 값
		
		int start = 0; // 시작점
		int end = N - 1; // 끝점
		boolean checkFind = false; // 원하는 값을 찾았는지 여부
		
		while (start <= end) {
			int mid = (start + end) / 2; // 중간점
			
			// 원하는 값을 찾으면 true 리턴
			if (arr[mid] == findNumber) return true;
			// 원하는 값보다 작으면
			else if (arr[mid] < findNumber) start = mid + 1;
			// 원하는 값보다 크면
			else end = mid - 1;
		}
		
		// 원하는 값을 못 찾으면
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스의 개수
		StringBuilder sb = new StringBuilder(); // 결과값을 저장하기 위한 객체
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine()); // 점의 개수
			arr = new int[N]; // 점 배열 크기 지정
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken()); // 점의 위치 저장
			}
			
			int cases = findCase(); // 세 점으로 가능한 경우의 수
			sb.append(cases).append("\n"); // 결과값 추가하기
		}
		
		// 버퍼 닫기
		br.close();
		
		// 결과값 출력하기
		System.out.print(sb);
	} // main 종료
} // class 종료