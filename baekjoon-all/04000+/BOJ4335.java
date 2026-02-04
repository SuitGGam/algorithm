/*
 * 백준 4335번 : 숫자 맞추기
 * https://www.acmicpc.net/problem/4335
 * 난이도 : 실버 5
 * 풀이 날짜 : 2026-02-04
 * 간단 설명 : 숫자 게임에서 스탠이 거짓말을 하는지 안 하는지 판별하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ4335 {
	public static void main(String[] args) throws IOException {
		// 입력을 위한 객체 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder sb = new StringBuilder(); // 결과값을 저장할 객체
		int low = 1; // 최소 범위
		int high = 10; // 최대 범위
		String valid = "Stan may be honest"; // 스탠의 거짓말 여부
		while (true) {
			int num = Integer.parseInt(br.readLine()); // 올리가 외친 수
			if (num == 0) break; // 종료 조건
			
			String stan = br.readLine(); // 스탠의 답변
			if (stan.equals("right on")) { // 정답이라고 한 경우
				if (num < low || num > high || low > high) valid = "Stan is dishonest"; // 거짓말을 한 경우
				sb.append(valid).append("\n"); // 결과값 추가하기
				
				// 세팅 초기화
				low = 1;
				high = 10;
				valid = "Stan may be honest";
			} else if (stan.equals("too low")) low = Math.max(low, num + 1); // 최소 범위 갱신
			else high = Math.min(high, num - 1); // 최대 범위 갱신
		}
		
		// 버퍼 닫기
		br.close();
		
		// 결과값 출력하기
		System.out.print(sb);
	} // main 종료
} // Main 종료
