/*
 * 백준 12873번 : 기념품
 * https://www.acmicpc.net/problem/12873
 * 난이도 : 실버 3
 * 풀이 날짜 : 2025-06-10
 * 간단 설명 : 원형 모양의 링을 돌면서 한 명씩 제거하고 마지막에 남는 사람을 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ_12873 {
	// 입력을 위한 객체 선언
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine()); // 캠프 참가자의 수
		
		// 버퍼 닫기
		br.close();
		
		for (int i = 0; i < N; i++) {
			// 캠프 참가자 배열에 추가
			camp.add(i + 1);
		}
		
		int t = 1; // 단계
		int lastIdx = 0; // 현재 위치
		
		// 빙글빙글 제거 시작
		while (camp.size() > 1) {
			long t3 = (long) t * t * t - 1; // 이동할 횟수
			lastIdx = (lastIdx + (int) (t3 % camp.size())) % camp.size(); // 이동할 위치
			camp.remove(lastIdx); // 이동한 위치의 사람 제거
			t++; // 단계 증가
		}
		
		// 결과값 출력하기
		int winner = camp.get(0); // 기념품을 받는 사람 번호
		System.out.print(winner);
	} // main 종료
	
	static int N; // 캠프 참가자의 수
	static ArrayList<Integer> camp = new ArrayList<>(); // 캠프 참가자
} // class 종료