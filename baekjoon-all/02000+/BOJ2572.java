/*
 * 백준 2572번 : 보드게임
 * https://www.acmicpc.net/problem/2572
 * 난이도 : 골드 3
 * 풀이 날짜 : 2026-01-19
 * 간단 설명 : 보드게임에서 얻을 수 있는 최고 점수를 구하는 문제
 */

/*
 * 완전 탐색 경우의 수 : K^N (10,000^1,000)
 * 이유 : 각 카드의 단계에서 현재 마을에서 연결된 도로 중 하나를 선택해야 함
 *
 * 알고리즘 : DP
 * 자료구조 : Array, ArrayList
 * 시간 복잡도 : O (N * (M + 2K))
 * 풀이 방법 : 1번 마을부터 시작함
 * 각 마을은 한 번만 올 수 있는 것이 아니라 계속 올 수 있음
 * 순환이 있을 수 있는 그래프 형태임
 * 그래서 DP를 선택함 (바텀업)
 * DP 테이블은 [카드의 수][마을의 수]로 선언
 * 각 카드의 순서마다 전체 마을을 돌면서
 * 각 마을에 연결된 도로들을 확인
 * 도로와 현재 카드가 일치하면 + 10점 갱신
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class BOJ2572 {
    static int N, M; // 카드의 수, 마을의 수
    static char[] card; // 카드 배열
    static ArrayList<Road>[] town; // 마을 그래프
    
    static class Road { // 길의 정보를 나타낸 class
        int edge; // 연결된 마을
        char color; // 길의 색깔
        
        Road(int edge, char color) {
            this.edge = edge;
            this.color = color;
        }
    } // Road 종료
    
    static int dynamic() { // 최대 점수를 구하기 위한 함수
        int[][] dp = new int[N + 1][M + 1]; // dp 배열
        for (int i = 0 ; i <= N; i++) {
            for (int j = 1; j <= M; j++) dp[i][j] = -1; // dp 배열 초기화
        }
        
        dp[0][1] = 0; // dp 시작점 초기화
        for (int i = 1 ; i<= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (dp[i - 1][j] == -1) continue; // 이전 마을에 없었다면 continue
                
                for (Road r : town[j]) {
                    int canMoveTown = r.edge; // 현재 마을에서 갈 수 있는 마을
                    int addedScore = r.color == card[i - 1] ? 10 : 0;
                    
                    dp[i][canMoveTown] = Math.max(dp[i][canMoveTown], dp[i - 1][j] + addedScore); // 점수 갱신
                }
            }
        }
        
        
        int maxScore = 0; // 최대 점수
        for (int j = 1; j <= M; j++) {
            maxScore = Math.max(maxScore, dp[N][j]); // 최대 점수 갱신
        }
        
        return maxScore; // 최대 점수 반환
    } // dijkstra 종료
    
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine()); // 카드의 수
        card = new char[N]; // 카드 배열
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) card[i] = st.nextToken().charAt(0); // 색깔 저장
        
        st = new StringTokenizer(br.readLine(), " ");
        M = Integer.parseInt(st.nextToken()); // 마을의 수
        int K = Integer.parseInt(st.nextToken()); // 길의 수
        town = new ArrayList[M + 1];
        for (int i = 1; i <= M; i++) town[i] = new ArrayList<>(); // 마을 생성
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int v = Integer.parseInt(st.nextToken()); // 연결된 마을 1
            int e = Integer.parseInt(st.nextToken()); // 연결된 마을 2
            char color = st.nextToken().charAt(0); // 길의 색깔
            
            // 도로 연결
            town[v].add(new Road(e, color));
            town[e].add(new Road(v, color));
        }
        
        // 버퍼 닫기
        br.close();
        
        int maxScore = dynamic(); // 최대 점수
        
        // 결과값 출력하기
        System.out.print(maxScore);
    } // main 종료
} // Main 종료