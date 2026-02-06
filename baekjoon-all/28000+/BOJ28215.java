/*
 * 백준 28215번 : 대피소
 * https://www.acmicpc.net/problem/28215
 * 난이도 : 실버 4
 * 풀이 날짜 : 2026-02-06
 * 간단 설명 : 집과 대피소 사이의 거리 최댓값 중 최솟값을 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ28215 {
    static final int MAX_DIS = 200_001; // 거리 초기화를 위한 변수
    
    static int N, K; // 집의 개수, 대피소의 개수
    static int minMaxDis; // 가장 긴 거리의 최솟값
    static int[][] home; // 집 좌표 배열
    static int[] selected; // 대피소를 설치하기 위해 선택한 집 배열
    
    static void comb(int depth, int start) { // 대피소를 설치할 집의 조합을 구하는 함수
        // 종료 조건
        if (depth == K) {
            calDis(); // 조합을 골랐으니 거리 구하기
            return; // 종료
        }
        
        for (int i = start; i < N; i++) {
            selected[depth] = i; // 집 선택
            comb(depth + 1, start + 1); // 다음 집 고르기
        }
    } // minMaxDis 종료
    
    static void calDis() { // 가장 긴 거리의 최솟값을 구하는 문제
        int[] dist = new int[N]; // 집과 대피소 간의 최소 거리
        for (int i = 0; i < N; i++) dist[i] = MAX_DIS; // 거리 초기화
        
        for (int i = 0; i < K; i++) { // 대피소 선택
            int curShelter = selected[i]; // 현재 선택된 대피소
            for (int j = 0; j < N; j++) { // 집 선택
                if (curShelter == j) continue; // 대피소인 집은 continue
                
                int gap = Math.abs(home[curShelter][0] - home[j][0]) + Math.abs(home[curShelter][1] - home[j][1]); // 집과 대피소 사이의 거리
                dist[j] = Math.min(dist[j], gap); // 거리 갱신
            }
        }
        
        for (int i = 0; i < K; i++) dist[selected[i]] = MAX_DIS; // 대피소였던 곳은 거리 초기화
        
        int minMax = 0;
        for (int i = 0; i < N; i++) {
            if (dist[i] == MAX_DIS) continue; // 대피소였던 곳은 continue
            
            if (minMax < dist[i]) minMax = dist[i]; // 대피소와의 최대 거리 갱신
        }
        
        minMaxDis = Math.min(minMaxDis, minMax); // 가장 긴 거리의 최솟값 갱신
    } // calDis 종료
    
	public static void main(String[] args) throws IOException {
		// 입력을 위한 객체 선언
		BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken()); // 집의 개수
        K = Integer.parseInt(st.nextToken()); // 대피소의 개수
        
        home = new int[N][2]; // 집 배열
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            home[i][0] = Integer.parseInt(st.nextToken()); // 집의 x 좌표
            home[i][1] = Integer.parseInt(st.nextToken()); // 집의 y 좌표
        }
        
		// 버퍼 닫기
		br.close();
        
        minMaxDis = MAX_DIS; // 가장 긴 거리의 최솟값 초기화
        selected = new int[K]; // 대피소를 설치하기 위해 선택한 집 배열
        comb(0, 0); // 가장 긴 거리의 최솟값 구하기
        
		// 결과값 출력하기
		System.out.print(minMaxDis);
	} // main 종료
} // Main 종료
