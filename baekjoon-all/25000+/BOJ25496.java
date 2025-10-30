/*
 * 백준 25496번 : 장신구 명장 임스
 * https://www.acmicpc.net/problem/25496
 * 난이도 : 실버 5
 * 풀이 날짜 : 2025-10-31
 * 간단 설명 : 제작 가능한 장신구의 최대 개수를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class BOJ25496 {
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        int P = Integer.parseInt(st.nextToken()); // 현재 쌓인 피로도
        int N = Integer.parseInt(st.nextToken()); // 만들 수 있는 장신구의 개수
        
        int[] fatigue = new int[N]; // 피로도 배열
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) fatigue[i] = Integer.parseInt(st.nextToken()); // 피로도 저장
        
        // 버퍼 닫기
        br.close();
        
        Arrays.sort(fatigue); // 피로도 비내림차순 정렬
        int maxAccessory = 0; // 최대 제작 가능한 장신구의 개수
        for (int i = 0; i < N; i++) {
            if (P < 200) {
                P += fatigue[i]; // 피로도 증가
                maxAccessory++;  // 장신구 제작
            } else break;
        }
        
        // 결과값 출력하기
        System.out.print(maxAccessory);
    } // main 종료
} // Main 종료