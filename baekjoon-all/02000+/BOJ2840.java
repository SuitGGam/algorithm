/*
 * 백준 2840번 : 행운의 바퀴
 * https://www.acmicpc.net/problem/2840
 * 난이도 : 실버 4
 * 풀이 날짜 : 2025-06-09
 * 간단 설명 : 원형 자료구조의 알파벳을 순서에 맞게 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.LinkedList;

public class BOJ2840 {
	// 입력을 위한 객체 선언
	static BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 바퀴 칸의 수
		K = Integer.parseInt(st.nextToken()); // 바퀴를 돌리는 횟수
		
		// 글자를 저장할 바퀴 공간 만들기
		for (int i = 0 ; i < N; i++) {
			wheel.add('?'); // 처음엔 모르는 상태
		}
		
		lastIdx = 0; // 돌림판 첫 위치 초기화
		boolean tmpValid = true; // 현재 입력의 유효 여부
		boolean valid = true; // 행운의 바퀴가 유효 여부
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int S = Integer.parseInt(st.nextToken()); // 글자가 바뀐 횟수
			char c = st.nextToken().charAt(0); // 회전이 멈췄을 때 가리키는 글자
			tmpValid = recordWheel(S, c); // 바퀴에 글자 기록하기
			if (!tmpValid) valid = false; // 현재의 입력이 유효하지 않다면 행운의 바퀴 X
		}
		
		// 버퍼 닫기
		br.close();
		
		StringBuilder sb = new StringBuilder(); // 바퀴에 적힌 글자를 저장하기 위한 객체
		// 행운의 바퀴가 있는 경우
		if (valid) {
			for (int i = 0; i < N; i++) {
				sb.append(wheel.get(lastIdx));
				lastIdx--;
				if (lastIdx < 0) lastIdx += N;
			}
		}
		// 행운의 바퀴가 없는 경우
		else {
			sb.append("!");
		}
		
		// 결과값 출력하기
		System.out.print(sb);
	} // main 종료
	
	static int N, K; // 바퀴 칸의 수, 바퀴를 돌리는 횟수
	static LinkedList<Character> wheel = new LinkedList<>(); // 글자를 저장할 바퀴
	static int[] countAlphabet = new int[26]; // 알파벳 개수를 저장할 배열
	
	// 바퀴에 적힌 문자를 찾기 위해 글자를 기록하는 함수
	static int lastIdx; // 회전이 멈춘 인덱스
	static boolean recordWheel(int S, char c) {
		lastIdx = (lastIdx + S) % N; // 마지막 인덱스 갱신
		// 글자가 안 적혀있는 칸이면 글자 적어주기
		if (wheel.get(lastIdx) == '?') {
			wheel.set(lastIdx, c);
			
			// 같은 문자 방지를 위한 기록
			if (countAlphabet[c - 'A'] == 0) countAlphabet[c - 'A']++;
			// 바퀴에 같은 문자가 2개 이상인 경우
			else return false;
			return true;
		}
		// 글자가 적혀있는데 같은 경우
		else if (wheel.get(lastIdx) == c) {
			return true;
		}
		// 글자가 적혀있는데 다른 경우
		return false;
	}
} // class 종료