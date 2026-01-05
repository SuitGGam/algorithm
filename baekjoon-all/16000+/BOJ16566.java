/*
 * 백준 16566번 : 카드 게임
 * https://www.acmicpc.net/problem/16566
 * 난이도 : 골드 1
 * 풀이 날짜 : 2026-01-05
 * 간단 설명 : 철수를 이기려면 카드를 어떤 순서로 내야 하는지 구하는 문제
 */

/*
 * 완전 탐색 경우의 수 : M * K (4,000,000 x 10,000)
 * 이유 : M개의 카드를 K번 완전 탐색하면 그게 완전 탐색임
 *
 * 알고리즘 : 제곱근 분할
 * 자료구조 : Array
 * 시간 복잡도 : O (√N)
 * 풀이 방법 : 가지고 있는 카드를 √N의 범위로 나눔
 * 철수가 카드를 냈을 때 그 카드가 해당하는 범위부터 차례대로 탐색함
 * 그러면 완전 탐색에서 탐색하는 시간을 √M으로 줄일 수 있음
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ16566 {
    static final int MAX = 4_000_001; // N보다 큰 수
    
    static int N; // 전체 카드의 개수
    static int sqrt, group; // N의 제곱근, 그룹의 수
    static boolean[] own; // 민수가 소유한 카드 유무
    static int[][] rangeMin; // 그룹별 최솟값 배열
    
    static int select(int chulsu) { // 민수가 낼 카드를 고르는 함수
        int curGroup = chulsu / sqrt; // 철수가 낸 카드가 속한 그룹
        
        int minsu = -1; // 민수가 낼 카드 번호
        for (int i = curGroup; i < group; i++) {
            int curMin = rangeMin[i][0]; // 현재 그룹의 최솟값
            
            if (curMin == MAX) continue; // 현재 그룹의 최솟값이 없으면 continue
            
            if (curMin > chulsu) { // 현재 그룹의 최솟값이 철수가 낸 카드보다 큰 경우
                minsu = curMin; // 민수가 낼 카드 선택
                update(minsu); // 그룹 최솟값 갱신
                break;
            } else { // 현재 그룹의 최솟값이 철수가 낸 카드보다 작거나 같은 경우
                // 이 경우는 철수가 낸 카드보다 작거나 같은 경우라 update를 안 해도 됨
                int last = Math.min((curGroup + 1) * sqrt - 1, N); // 그룹의 마지막 범위
                for (int j = chulsu + 1; j <= last; j++) {
                    if (own[j] && j > chulsu) { // 그룹 내의 철수가 낸 카드보다 큰 숫자를 찾은 경우
                        minsu = j; // 민수가 낼 카드 선택
                        own[j] = false; // 카드 미소유 처리
                        break;
                    }
                }
                
                if (minsu != -1) break; // 적절한 값을 찾았으면 break
            }
        }
        
        return minsu;
    } // select 종료
    
    static void update(int card) { // 그룹의 최솟값을 갱신하는 함수
        own[card] = false; // 카드 미소유 처리
        
        int curGroup = card / sqrt; // 현재 그룹
        rangeMin[curGroup][0] = MAX; // 최솟값 초기화
        int last = Math.min((curGroup + 1) * sqrt - 1, N); // 그룹의 마지막 범위
        for (int i = card + 1; i <= last; i++) {
            if (own[i]) { rangeMin[curGroup][0] = i; break; } // 그룹 최솟값 갱신
        }
    } // update 종료
    
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        N = Integer.parseInt(st.nextToken()); // 전체 카드의 개수
        int M = Integer.parseInt(st.nextToken()); // 민수가 소유한 카드의 개수
        int K = Integer.parseInt(st.nextToken()); // 카드는 내는 횟수
        
        sqrt = (int) Math.sqrt(N); // N의 제곱근
        group = N / sqrt + 1; // 그룹의 수
        
        own = new boolean[N + 1]; // 민수가 소유한 카드 유무
        rangeMin = new int[group][1]; // 그룹별 최솟값 배열
        for (int[] min : rangeMin) min[0] = MAX; // 그룹 최솟값 초기화
        
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < M; i++) {
            int card = Integer.parseInt(st.nextToken()); // 카드 번호
            own[card] = true; // 카드 소유 처리
            int curGroup = card / sqrt; // 현재 카드의 소속 그룹
            if (rangeMin[curGroup][0] > card) rangeMin[curGroup][0] = card; // 그룹 최솟값 갱신
        }
        
        StringBuilder sb = new StringBuilder(); // 결과값을 저장할 객체
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < K; i++) {
            int chulsu = Integer.parseInt(st.nextToken()); // 철수가 낸 카드 번호
            int minsu  = select(chulsu); // 민수가 낼 카드 번호
            
            sb.append(minsu).append("\n"); // 결과값 추가하기
        }
        
        // 버퍼 닫기
        br.close();
        
        // 결과값 출력하기
        System.out.print(sb);
    } // main 종료
} // Main 종료