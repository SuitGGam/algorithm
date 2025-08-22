/*
 * 백준 21318번 : 피아노 체조
 * https://www.acmicpc.net/problem/21318
 * 난이도 : 실버 1
 * 풀이 날짜 : 2025-08-22
 * 간단 설명 : x번부터 y번까지의 악보를 순서대로 연주할 때 몇 개의 악보에서 실수하는지 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ21318 {
    // 입력을 위한 객체 선언
    static BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine()); // 악보의 개수
        // 악보의 난이도가 낮아지는 곳을 확인할 배열
        // 질문의 범위가 1부터 들어오니 배열의 크기 + 1
        int[] mistake = new int[N + 1];
        st = new StringTokenizer(br.readLine(), " ");
        int prevLevel = Integer.parseInt(st.nextToken()); // 이전 난이도
        int prefixMinus = 0; // 누적 실수 값
        for (int i = 2; i <= N; i++) {
            int nowLevel = Integer.parseInt(st.nextToken()); // 현재 난이도
            // 현재 난이도와 이전 난이도를 비교했을 때
            // 현재 난이도의 값이 더 작다면 누적 실수 값 -1
            if (prevLevel > nowLevel) mistake[i] = 1;
            // 이전 난이도 최신화
            prevLevel = nowLevel;
        }
        
        //
        for (int i = 2; i <= N; i++) {
            if (mistake[i] == 0) mistake[i] = mistake[i - 1];
            else if (mistake[i] == 1) mistake[i] = mistake[i - 1] + 1;
        }
        
        StringBuilder sb = new StringBuilder(); // 결과값을 저장할 객체
        int Q = Integer.parseInt(br.readLine()); // 질문의 개수
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken()); // 시작 구간
            int end = Integer.parseInt(st.nextToken()); // 종료 구간
            
            // 결과값 추가하기
            // mistake[end] - mistake[start]
            // 종료 구간의 값에서 시작 구간까지의 값을 빼주면
            // 실수 개수 확인 가능
            // 시작 구간은 앞값과의 비교값이니 포함해서 빼야 함
            // 시작, 종료 구간이 다른 경우만 계산
            // 같으면 0개
            if (start != end) sb.append(mistake[end] - mistake[start]).append("\n");
            else sb.append(0).append("\n");
            
        }
        
        // 버퍼 닫기
        br.close();
        
        // 결과값 출력하기
        System.out.print(sb);
    }
}