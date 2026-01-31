/*
 * 백준 2512번 : 예산
 * https://www.acmicpc.net/problem/2512
 * 난이도 : 실버 2
 * 풀이 날짜 : 2026-01-31
 * 간단 설명 : 배정 가능한 예산의 최댓값을 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.HashMap;

public class BOJ2512 {
	public static void main(String[] args) throws IOException {
		// 입력을 위한 객체 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine()); // 지방의 수
		
		int sum = 0; // 요청 예산의 총합
		int max = 0; // 최대 요청 예산
		HashMap<Integer, Integer> map = new HashMap<>(); // 요청 예산의 개수를 저장할 HashMap
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			int budget = Integer.parseInt(st.nextToken()); // 요청 예산
			sum += budget; // 요청 예산 총합 증가
			max = Math.max(max, budget); // 최대 요청 예산 갱신
			map.put(budget, map.getOrDefault(budget, 0) + 1); // 요청 예산 put
		}
		
		int M = Integer.parseInt(br.readLine()); // 총 예산
		
		// 버퍼 닫기
		br.close();
		
		while (sum > M) {
			int curMax = map.remove(max); // 현재 최대 요청 예산
			sum -= curMax; // 최대 요청 예산의 개수만큼 예산 깎기
			max--; // 최대 예산 감소
			map.put(max, map.getOrDefault(max, 0) + curMax); // 요청 예산 갱신
		}
		
		// 결과값 출력하기
		System.out.print(max);
	} // main 종료
} // Main 종료
