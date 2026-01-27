/*
 * 백준 20950번 : 미술가 미미
 * https://www.acmicpc.net/problem/20950
 * 난이도 : 실버 2
 * 풀이 날짜 : 2026-01-27
 * 간단 설명 : 곰두리색과 문두리색의 최소 차이를 구하는 문제
 */

/*
 * 완전 탐색 경우의 수 : NC2 + NC3 + ••• NC7
 * 이유 : 최소 2개의 조합부터 최대 7개의 조합까지 모든 조합을 구해야 함
 *
 * 알고리즘 : 백트래킹 (조합)
 * 자료구조 : Array
 * 시간 복잡도 : O (N^7)
 * 풀이 방법 : NC2 + NC3 + ••• NC7
 * 위의 조합을 다 구해봐도 경우의 수가 크지 않음
 * 고로 직접 다 구해서 완전 탐색
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ20950 {
    static int N, minGap; // 물감의 개수, 곰두리색과 문두리색의 차이
    static int[][] paint; // 물감 배열
    
    static void comb(int r, int depth, int start, int sumR, int sumG, int sumB) { // 물감을 조합하는 함수
        // 종료 조건
        if (r == depth) {
            int avgR = sumR / r; // 혼합된 R
            int avgG = sumG / r; // 혼합된 G
            int avgB = sumB / r; // 혼합된 B
            int gap = Math.abs(paint[0][0] - avgR) + Math.abs(paint[0][1] - avgG) + Math.abs(paint[0][2] - avgB); // 색의 차이
            if (minGap > gap) minGap = gap; // 차이 갱신
            return; // 종료
        }
        
        for (int i = start; i <= N; i++) { // 물감 고르기
            comb(r, depth + 1, i + 1, sumR + paint[i][0], sumG + paint[i][1], sumB + paint[i][2]); // 다음 물감 고르기
        }
    } // comb 종료
    
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine()); // 물감의 개수
        paint = new int[N + 1][3]; // 물감 배열
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            paint[i][0] = Integer.parseInt(st.nextToken()); // R 저장
            paint[i][1] = Integer.parseInt(st.nextToken()); // G 저장
            paint[i][2] = Integer.parseInt(st.nextToken()); // B 저장
        }
        
        st = new StringTokenizer(br.readLine(), " ");
        paint[0][0] = Integer.parseInt(st.nextToken()); // 곰두리색 R 저장
        paint[0][1] = Integer.parseInt(st.nextToken()); // 곰두리색 G 저장
        paint[0][2] = Integer.parseInt(st.nextToken()); // 곰두리색 B 저장
        
        // 버퍼 닫기
        br.close();
        
        minGap = 800; // 곰두리색과 문두리색의 차이 초기화
        for (int i = 2; i <= Math.min(N, 7); i++) {
            comb(i, 0, 1, 0, 0, 0); // 조합 만들기
        }
        
        // 결과값 출력하기
        System.out.print(minGap);
    } // main 종료
} // Main 종료