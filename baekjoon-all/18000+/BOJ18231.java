/*
 * 백준 18231번 : 파괴된 도시
 * https://www.acmicpc.net/problem/18231
 * 난이도 : 골드 5
 * 풀이 날짜 : 2025-12-27
 * 간단 설명 : 지도와 같은 모양처럼 될 수 있는 폭탄이 떨어진 도시 번호를 구하는 문제
 */

/*
 * 최대 경우의 수 : 2000 + 200,000 (N과 2M의 최대치의 합)
 * 시간 복잡도 : O (N + M)
 * 이유 : N개의 도시를 탐색하면서 각 도시에 연결된 간선을 모두 탐색함
 * 하나의 간선이 2개의 도시를 연결 중이니 총 2M번 간선을 확인함
 * 알고리즘 : 완전 탐색
 * 자료구조 : ArrayList
 * 풀이 방법 : 폭탄이 떨어진 도시라면 인근 도시가 다 파괴돼있어야 함
 * 그래서 모든 도시(혹은 파괴된 도시)의 주변 도시를 확인해서 전부 파괴됐는지 확인
 * 전부 파괴됐다면 해당 도시 번호들을 모아서 각 번호마다 주변 도시 탐색 진행
 * 그래서 파괴된 도시의 번호와 일치한다면 지도와 같음
 * 불일치한다면 지도와 다름
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class BOJ18231 {
    static int N; // 도시의 개수, 도로의 개수, 파괴된 도시의 개수
    static ArrayList<Integer>[] city; // 도시 그래프
    static boolean[] destroyedCity; // 파괴된 도시 배열
    static ArrayList<Integer> bombFellCity = new ArrayList<>(); // 폭탄이 떨어진 도시의 번호
    
    static void findBombFellCity() { // 폭탄이 떨어진 도시를 탐색하는 함수
        for (int i = 1; i <= N; i++) {
            if (!destroyedCity[i]) continue; // 파괴가 안 된 도시인 경우 continue
            
            boolean allDestroyed = true; // 모든 주변 도시 파괴 여부
            for (int v : city[i]) { // 주변 도시 탐색
                if (!destroyedCity[v]) { // 주변 도시가 모두 파괴가 안 된 경우
                    allDestroyed = false;
                    break;
                }
            }
            
            if (allDestroyed) bombFellCity.add(i); // 주변 도시가 모두 파괴된 경우
        }
    } // findBombFellCity 종료
    
    static boolean valid() { // 폭탄이 떨어진 도시로 지도가 유효한지 확인하는 함수
        boolean[] checkCity = new boolean[N + 1]; // 파괴된 도시를 확인하기 위한 배열
        for (int v : bombFellCity) {
            checkCity[v] = true; // 파괴 확인 처리
            for (int i = 0; i < city[v].size(); i++) {
                int curCity = city[v].get(i);
                checkCity[curCity] = true; // 파괴 확인 처리
            }
        }
        
        for (int i = 1; i <= N; i++) {
            if (destroyedCity[i] != checkCity[i]) return false; // 유효하지 않은 지도
        }
        
        return true;
    } // valid 종료
    
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        N = Integer.parseInt(st.nextToken()); // 도시의 개수
        int M = Integer.parseInt(st.nextToken()); // 도로의 개수
        
        city = new ArrayList[N + 1]; // 도시 그래프
        for (int i = 1; i <= N; i++) city[i] = new ArrayList<>(); // 도시 생성
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int U = Integer.parseInt(st.nextToken()); // 도시 U
            int V = Integer.parseInt(st.nextToken()); // 도시 V
            
            // 도로 연결
            city[U].add(V);
            city[V].add(U);
        }
        
        int K = Integer.parseInt(br.readLine()); // 파괴된 도시의 개수
        destroyedCity = new boolean[N + 1]; // 도시 파괴 여부 확인 배열
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < K; i++) {
            int destroy = Integer.parseInt(st.nextToken()); // 파괴된 도시 번호
            destroyedCity[destroy] = true; // 파괴 처리
        }
        
        // 버퍼 닫기
        br.close();
        
        findBombFellCity(); // 폭탄이 떨어진 도시 탐색
        
        // 결과값 출력하기
        if (!valid()) System.out.print(-1); // 지도와 같은 모양이 안 나오는 경우
        else { // 지도와 같은 모양이 나오는 경우
            StringBuilder sb = new StringBuilder(); // 폭탄이 떨어진 도시 목록을 저장할 객체
            for(int i = 0; i < bombFellCity.size(); i++) {
                sb.append(bombFellCity.get(i)).append(" "); // 폭탄이 떨어진 도시 번호 추가하기
            }
            
            System.out.print(bombFellCity.size() + "\n" + sb);
        }
    } // main 종료
} // Main 종료