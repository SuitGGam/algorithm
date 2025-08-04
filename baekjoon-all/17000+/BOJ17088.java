/*
 * 백준 17088번 : 트럭
 * https://www.acmicpc.net/problem/17088
 * 난이도 : 골드 5
 * 풀이 날짜 : 2025-06-16
 * 간단 설명 : 조합을 통해 완전 탐색을 최적화하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ17088 {
	// 입력을 위한 객체 선언
	static BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine()); // 수열의 크기
		
		sequence = new int[N]; // 수열을 담을 배열
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			sequence[i] = Integer.parseInt(st.nextToken()); // 수열 값 저장
		}
		
		// 버퍼 닫기
		br.close();
		
		// 첫 두 원소의 조합에 따른 공차를 정해놓고
		// 뒤에 오는 원소들이 변경 범위 내(-1 ~ 1)에 들어오는지 확인
		minCal = 100001; // 연산 횟수 초기화
		for (int d1 = -1; d1 <= 1; d1++) {
			// 배열의 크기가 2 이상인 경우
			if (N > 1) {
				for (int d2 = -1; d2 <= 1; d2++) {
					int first  = sequence[0] + d1; // 첫 번째 원소 조합
					int second = sequence[1] + d2; // 두 번째 원소 조합
					int diff = second - first;
					tmpPrev = second; // 변경된 이전 값 갱신
					checkDiff(d1, d2, diff); // 공차 확인
				}
			}
		}
		
		// 성립 불가능한 경우
		if (minCal == 100001) minCal = -1;
		// 배열의 크기가 1인 경우
		if (N == 1) minCal = 0;
		
		// 결과값 출력하기
		System.out.print(minCal);
	} // main 종료
	
	static int N, minCal; // 수열의 크기, 최소 연산 횟수
	static int tmpPrev; // 변경된 이전 값
	static int[] sequence; // 수열을 담을 배열
	
	// 공차를 확인하는 함수
	static void checkDiff(int d1, int d2, int diff) {
		int tmpCal = Math.abs(d1) + Math.abs(d2); // 임시 연산 횟수
		for (int i = 2; i < N; i++) {
			int expected = tmpPrev + diff; // 공차가 가능한 범위
			// 성립 가능한 범위가 아닌 경우 종료
			if (Math.abs(sequence[i] - expected) > 1) return;
			// 성립 가능하면 차이만큼 연산 횟수 증가
			// 차이가 없으면 연산 횟수 증가 안 해도 됨
			tmpCal += Math.abs(sequence[i] - expected);
			// tmpPrev 갱신
			tmpPrev = expected;
		}
		
		// 최소 연산 횟수 갱신
		if (minCal > tmpCal) minCal = tmpCal;
		return;
	} // checkDiff 종료
} // class 종료