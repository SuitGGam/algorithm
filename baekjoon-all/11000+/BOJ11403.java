/*
 * 백준 11403번 : 경로 찾기
 * https://www.acmicpc.net/problem/11403
 * 난이도 : 실버 1
 * 풀이 날짜 : 2025-10-13
 * 간단 설명 : 정점 a에서 정점 b로 가는 경로가 있는지 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11403 {
    // 입력을 위한 객체 선언
    static BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    
    static int N; // 정점의 개수
    static int[][] graph; // 인접 행렬 배열
    
    static void fw() { // 플로이드 워셜 함수
        for (int k = 0; k < N; k++) { // 중간 정점
            for (int i = 0; i < N; i++) { // 시작 정점
                for (int j = 0; j < N; j++) { // 도착 정점
                    // 경유지를 통해 연결돼 있다면 갈 수 있음
                    if (graph[i][k] == 1 && graph[k][j] == 1) graph[i][j] = 1;
                }
            }
        }
    } // fw 종료
    
    public static void main(String[] args) throws IOException{
        N = Integer.parseInt(br.readLine()); // 정점의 개수
        graph = new int[N][N]; // 배열 크기 지정
        for (int x = 0; x < N; x++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int y = 0; y < N; y++) {
                graph[x][y] = Integer.parseInt(st.nextToken()); // 간선 정보 저장
            }
        }
        
        // 버퍼 닫기
        br.close();
        
        fw(); // 플로이드 워셜
        
        StringBuilder sb = new StringBuilder(); // 결과값을 저장할 객체
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                sb.append(graph[x][y]).append(" "); // 결과값 추가하기
            }
            sb.append("\n"); // 개행 처리
        }
        
        // 결과값 출력하기
        System.out.print(sb);
    } // main 종료
} // Main 종료