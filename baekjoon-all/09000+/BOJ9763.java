/*
 * 백준 9763번 : 마을의 친밀도
 * https://www.acmicpc.net/problem/9763
 * 난이도 : 골드 5
 * 풀이 날짜 : 2026-01-19
 * 간단 설명 : N개의 마을 중에 거리가 가장 가까운 세 마을의 친밀도를 구하는 문제
 */

/*
 * 완전 탐색 경우의 수 : N^3 (10,000^3)
 * 이유 : 각 마을에 대하여 모든 조합을 검사해봐야 함
 *
 * 알고리즘 : 브루트 포스
 * 자료구조 : Array
 * 시간 복잡도 : O (N^2)
 * 풀이 방법 : 완전 탐색을 하면 연산 횟수가 1조가 되기 때문에 시간 초과가 남
 * 하지만 우리가 2개의 최솟값(첫 번째로 가까운 거리, 두 번째로 가까운 거리)을 가지고 거리를 구하면 O(N^2)로 구할 수 있음
 * 하나의 마을을 기점으로 가장 가까운 거리를 계속 갱신하는데
 * 갱신이 될 때 기존의 가장 가까운 거리로 두 번째로 가까운 거리를 갱신하면 됨
 * 이렇게 되면 O(N^2)에 해결 가능
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ9763 {
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int N = Integer.parseInt(br.readLine()); // 마을의 수
        int[][] town = new int[N][3]; // 마을 좌표
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            town[i][0] = Integer.parseInt(st.nextToken()); // 마을의 x 좌표
            town[i][1] = Integer.parseInt(st.nextToken()); // 마을의 y 좌표
            town[i][2] = Integer.parseInt(st.nextToken()); // 마을의 z 좌표
        }
        
        // 버퍼 닫기
        br.close();
        
        int minIntimacy = 12001; // 최저 친밀도
        for (int i = 0; i < N; i++) {
            int min1 = 6001; // 첫 번째로 가까운 거리
            int min2 = 6001; // 두 번째로 가까운 거리
            for (int j = 0; j < N; j++) {
                if (i == j) continue; // 같은 마을이면 continue
                
                // 현재 거리
                int dist = Math.abs(town[i][0] - town[j][0]) +
                        Math.abs(town[i][1] - town[j][1]) +
                        Math.abs(town[i][2] - town[j][2]);
                
                if (min1 > dist) {
                    min2 = min1; // 두 번째로 가까운 거리 갱신
                    min1 = dist; // 첫 번째로 가까운 거리 갱신
                } else if (min2 > dist) {
                    min2 = dist; // 두 번째로 가까운 거리 갱신
                }
            }
            
            minIntimacy = Math.min(minIntimacy, min1 + min2); // 최저 친밀도 갱신
        }
        
        // 결과값 출력하기
        System.out.print(minIntimacy);
    } // main 종료
} // Main 종료