/*
 * 백준 17478번 : 재귀함수가 뭔가요?
 * https://www.acmicpc.net/problem/17478
 * 난이도 : 실버 5
 * 풀이 날짜 : 2025-06-06
 * 간단 설명 : 재귀 함수를 바탕으로 규칙을 찾아 출력하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ17478 {
	// 입력을 위한 객체 선언
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine()); // 재귀 횟수
		
		// 버퍼 닫기
		br.close();
		
		sb.append(start).append("\n"); // 시작 문구
		whatIsRecursion(0); // 재귀 시작
		
		// 결과값 출력하기
		System.out.print(sb);
	} // main 종료
	
	static int N; // 재귀 횟수
	// 추가할 문자들
	static String start = "어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.";
	static String dash = "____";
	static String whatRecursion = "\"재귀함수가 뭔가요?\"";
	static String listen = "\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.";
	static String person = "마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.";
	static String right = "그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"";
	static String self = "\"재귀함수는 자기 자신을 호출하는 함수라네\"";
	static String answer = "라고 답변하였지.";
	
	static StringBuilder sb = new StringBuilder(); // 결과값을 저장할 객체
	static void whatIsRecursion(int depth) {
		// 종료 조건
		if (depth > N) return;
		
		// 결과값 추가하기
		for (int i = 0; i < depth; i++) sb.append(dash);
		sb.append(whatRecursion).append("\n");
		if (depth < N) {
			for (int i = 0; i < depth; i++) sb.append(dash);
			sb.append(listen).append("\n");
			for (int i = 0; i < depth; i++) sb.append(dash);
			sb.append(person).append("\n");
			for (int i = 0; i < depth; i++) sb.append(dash);
			sb.append(right).append("\n");
		} else {
			for (int i = 0; i < depth; i++) sb.append(dash);
			sb.append(self).append("\n");
		}
		
		whatIsRecursion(depth + 1);
		for (int i = 0; i < depth; i++) sb.append(dash);
		sb.append(answer).append("\n");
	} // whatIsRecursion 함수 종료
} // class 종료