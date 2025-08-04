/*
 * 백준 14713번 : 앵무새
 * https://www.acmicpc.net/problem/14713
 * 난이도 : 실버 2
 * 풀이 날짜 : 2025-06-12
 * 간단 설명 : 큐를 이용해 문장의 유효성을 파악하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Queue;
import java.util.ArrayDeque;

public class BOJ14713 {
	// 입력을 위한 객체 선언
	static BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine()); // 앵무새의 수
		for (int i = 0; i <= N; i++) { // 큐 생성 및 단어 저장 시작
			wordQueue.add(new ArrayDeque<>()); // 큐 생성
			st = new StringTokenizer(br.readLine(), " ");
			while (st.hasMoreTokens()) {
				wordQueue.get(i).offer(st.nextToken()); // 단어 저장
			}
		} // 큐 생성 및 단어 저장 종료
		
		// 버퍼 닫기
		br.close();
		
		String possible = "Possible"; // 문장의 가능 여부
		boolean valid = checkValid(); // 유효성 확인
		if (!valid) possible = "Impossible"; // 유효하지 않으면 Impossible
		
		// 결과값 출력하기
		System.out.print(possible);
	} // main 종료
	
	static int N; // 앵무새의 수
	static ArrayList<Queue<String>> wordQueue = new ArrayList<>(); // 단어를 저장할 큐 배열
	
	// 문장이 유효한지 확인하는 함수
	static boolean checkValid() {
		while (wordQueue.get(N).size() > 0) {
			boolean check = false; // poll이 됐는지 확인하는 boolean
			for (int i = 0; i < N; i++) {
				// 큐가 남아있는 경우만 확인
				if (wordQueue.get(i).size() > 0) {
					// 순서가 유효한 상태에서 단어가 같으면 poll
					if (wordQueue.get(i).peek().equals(wordQueue.get(N).peek())) {
						wordQueue.get(i).poll(); // 문장 Si
						wordQueue.get(N).poll(); // 문장 L
						check = true;
						break;
					}
				}
			}
			
			// 유효하지 않은 경우
			if (!check) return false;
		}
		
		// 단어가 남아있는 경우
		// 앵무새는 모든 문장을 끝까지 말하고 돌아감
		// 즉, 문장이 되더라도 남아있는 단어가 있다면
		// 유효하지 않은 것
		for (int i = 0; i < N; i++) {
			if (wordQueue.get(i).size() != 0) return false;
		}
		
		// 유효한 경우
		return true;
	} // checkValid 종료
	
} // class 종료