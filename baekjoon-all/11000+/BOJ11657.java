/*
 * 백준 11657번 : 타임머신
 * https://www.acmicpc.net/problem/11657
 * 난이도 : 골드 4
 * 풀이 날짜 : 2026-01-30
 * 간단 설명 : 1번 도시에서 다른 도시들까지 가는 최단 시간을 구하는 문제
 */

/*
 * 완전 탐색 경우의 수 : N! (500!)
 * 이유 : 모든 경로에 대해서 탐색해야 함
 *
 * 알고리즘 : 벨만포드
 * 자료구조 : ArrayList (그래프)
 * 시간 복잡도 : O (NM)
 * 풀이 방법 : 가중치에 음수가 포함된 그래프임
 * 그래서 벨만포드 사용
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class BOJ11657 {
    static final long INF = Long.MAX_VALUE; // 이동 시간 초기값
    
    static class City { // 도시 class
        int start, end; // 시작 도시, 도착 도시
        long time; // 이동 시간
        
        City(int start, int end, long time) {
            this.start = start;
            this.end = end;
            this.time = time;
        }
    } // City 종료
    
    static StringBuilder bellmanFord(int N, ArrayList<City> city, int start) { // 나머지 도시로 가는 가장 빠른 시간을 구하는 함수
        StringBuilder sb = new StringBuilder(); // 결과값을 저장할 객체
        
        long[] moveTime = new long[N + 1]; // 이동 시간 배열
        for (int i = 1; i <= N; i++) moveTime[i] = INF; // 이동 시간 초기화
        moveTime[start] = 0; // 시작 도시 시간 초기화
        
        // 나머지 도시 간선 확인
        for (int i = 2; i <= N; i++) {
            for (City curCity : city) {
                if (moveTime[curCity.start] != INF && moveTime[curCity.start] + curCity.time < moveTime[curCity.end]) {
                    moveTime[curCity.end] = moveTime[curCity.start] + curCity.time; // 시간 갱신
                }
            }
        }
        
        // 음수 사이클 확인
        for (City curCity : city) {
            if (moveTime[curCity.start] != INF && moveTime[curCity.start] + curCity.time < moveTime[curCity.end]) {
                sb.append(-1); // 음수 사이클 존재
                return sb; // 결과값 반환
            }
        }
        
        // 결과값 추가하기
        for (int i = 2; i <= N; i++) {
            if (moveTime[i] != INF) sb.append(moveTime[i]).append("\n");
            else sb.append(-1).append("\n");
        }
        
        return sb; // 결과값 반환
    } // bellmanFord 종료
    
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        int N = Integer.parseInt(st.nextToken()); // 도시의 개수
        int M = Integer.parseInt(st.nextToken()); // 버스 노선의 개수
        
        ArrayList<City> city = new ArrayList<>(); // 도시 그래프 배열
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken()); // 시작 도시
            int end = Integer.parseInt(st.nextToken()); // 도착 도시
            long time = Integer.parseInt(st.nextToken()); // 이동 시간
            
            city.add(new City(start, end, time)); // 버스 정보 추가
        }
        
        // 버퍼 닫기
        br.close();
        
        StringBuilder sb = bellmanFord(N, city, 1); // 각 도시로 이동하는 시간 결과값을 저장할 객체
        
        // 결과값 출력하기
        System.out.print(sb);
    } // main 종료
} // Main 종료