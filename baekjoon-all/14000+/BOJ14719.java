/*
 * 백준 14719번 : 빗물
 * https://www.acmicpc.net/problem/14719
 * 난이도 : 골드 5
 * 풀이 날짜 : 2025-12-01
 * 간단 설명 : 고인 빗물의 양을 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14719 {
    static int checkRainWater(int W, int[] world) { // 고인 빗물의 양을 확인하는 함수
        int[] maxLeft = new int[W]; // 왼쪽에서 가장 높은 블록 높이 배열
        maxLeft[0] = world[0]; // 첫 번째 값 넣기
        for (int i = 1; i < W; i++) maxLeft[i] = Math.max(maxLeft[i - 1], world[i]); // 최대 높이 갱신 저장
        
        int[] maxRight = new int[W]; // 오른쪽에서 가장 높은 블록 높이 배열
        maxRight[W - 1] = world[W - 1]; // 마지막 값 넣기
        for (int i = W - 2; i >= 0; i--) maxRight[i] = Math.max(maxRight[i + 1], world[i]); // 최대 높이 갱신 저장
        
        int rainWater = 0; // 빗물의 양
        for (int i = 0; i < W; i++) {
            int heightDiff = Math.min(maxLeft[i], maxRight[i]); // 물이 고일 수 있는 상한선
            rainWater += heightDiff - world[i]; // 빗물의 양 추가
        }
        
        return rainWater;
    } // checkRainWater 종료
    
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        int H = Integer.parseInt(st.nextToken()); // 세계의 세로 길이
        int W = Integer.parseInt(st.nextToken()); // 세계의 가로 길이
        
        int[] world = new int[W]; // 세계 배열
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < W; i++) world[i] = Integer.parseInt(st.nextToken()); // 블록의 높이 저장
        
        // 버퍼 닫기
        br.close();
        
        int rainWater = checkRainWater(W, world); // 빗물의 양
        
        // 결과값 출력하기
        System.out.print(rainWater);
    } // main 종료
} // Main 종료