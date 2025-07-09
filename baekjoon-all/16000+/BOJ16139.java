/*
 * 백준 16139번 : 인간-컴퓨터 상호작용
 * https://www.acmicpc.net/problem/16139
 * 난이도 : 실버 1
 * 풀이 날짜 : 2025-06-03
 * 간단 설명 : 주어지는 범위에 대한 알파벳이 나온 횟수를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_16139 {
	// 입력을 위한 객체 선언
	static BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
    public static void main(String[] args) throws IOException {
    	S = br.readLine(); // 문자열
    	alphabet = new int[S.length()][26]; // 알파벳의 횟수를 저장하는 배열 크기 지정
    	for (int i = 0; i < S.length(); i++) { // 알파벳 횟수 구하기 시작
    		alphabet[i][S.charAt(i) - 'a']++;
    	} // 알파벳 횟수 구하기 종료
    	
    	for (int i = 1; i < S.length(); i++) { // 알파벳 횟수 누적 합 변환 시작
    		for (int j = 0; j < 26; j++) {
    			// 횟수가 0이면 이전 값 가져오기
    			if (alphabet[i][j] == 0) alphabet[i][j] = alphabet[i - 1][j];
    			// 횟수가 1이면 누적 합으로 바꿔주기
    			else alphabet[i][j] += alphabet[i - 1][j];
    		}
    	} // 알파벳 횟수 누적 합 변환 종료
    	
    	int q = Integer.parseInt(br.readLine()); // 질문의 수
    	for (int i = 0; i < q; i++) {
    		st = new StringTokenizer(br.readLine());
    		char c = st.nextToken().charAt(0); // 찾을 알파벳 수
    		int start = Integer.parseInt(st.nextToken()); // 시작 구간
    		int end = Integer.parseInt(st.nextToken()); // 종료 구간
    		findAlphabet(c, start, end);
    	}
    	
    	// 버퍼 닫기
    	br.close();
    	
        // 결과값 출력하기
        System.out.print(sb);
    }
    
    static String S; // 문자열
    static int[][] alphabet; // 알파벳의 횟수를 저장하는 배열
    
    // 구간의 문자가 나온 횟수를 구하는 함수
    static StringBuilder sb = new StringBuilder(); // 결과값을 저장할 객체
    static void findAlphabet(char c, int start, int end) {
    	int count = 0; // 구간에 알파벳이 나온 횟수
    	if (start != 0) count = alphabet[end][c - 'a'] - alphabet[start - 1][c - 'a'];
    	else count = alphabet[end][c - 'a'];
    	
    	// 결과값 추가하기
    	sb.append(count).append("\n");
    }
}