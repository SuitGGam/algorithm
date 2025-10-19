/*
 * 백준 18870번 : 좌표 압축
 * https://www.acmicpc.net/problem/18870
 * 난이도 : 실버 2
 * 풀이 날짜 : 2025-10-20
 * 간단 설명 : 수직선 위의 좌표를 압축한 결과를 출력하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.HashMap;

public class BOJ18870 {
    // 입력을 위한 객체 선언
    static BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine()); // 좌표의 개수
        int[] original = new int[N]; // 원본 배열
        int[] sorted   = new int[N]; // 정렬된 배열
        
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            original[i] = sorted[i] = Integer.parseInt(st.nextToken()); // 좌표 저장
        }
        
        // 버퍼 닫기
        br.close();
        
        Arrays.sort(sorted); // 배열 정렬
        HashMap<Integer, Integer> rankMap = new HashMap<>(); // rank를 매길 HashMap
        int rank = 0; // 순서
        for (int v : sorted) {
            if (!rankMap.containsKey(v)) { // 순위가 안 매겨진 원소인 경우
                rankMap.put(v, rank); // 순위 저장
                rank++; // 순위 상승
            }
        }
        
        StringBuilder sb = new StringBuilder(); // 결과값을 저장할 객체
        for (int key : original) {
            int ranking = rankMap.get(key); // 순위 가져오기
            sb.append(ranking).append(" "); // 결과값 추가하기
        }
        
        // 결과값 출력하기
        System.out.print(sb);
    } // main 종료
} // Main 종료