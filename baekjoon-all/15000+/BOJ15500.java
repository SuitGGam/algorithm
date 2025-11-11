/*
 * 백준 15500번 : 이상한 하노이 탑
 * https://www.acmicpc.net/problem/15500
 * 난이도 : 실버 2
 * 풀이 날짜 : 2025-11-12
 * 간단 설명 : 이상한 하노이의 탑을 원래 하노이의 탑처럼 만드는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class BOJ15500 {
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int N = Integer.parseInt(br.readLine()); // 원판의 개수
        ArrayList<Integer>[] tower = new ArrayList[3 + 1]; // 하노이의 탑 배열
        for (int i = 1; i <= 3; i++) tower[i] = new ArrayList<>(); // 배열 생성
        
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) tower[1].add(Integer.parseInt(st.nextToken()));
        
        // 버퍼 닫기
        br.close();
        
        int K = 0; // 원판을 옮긴 횟수
        StringBuilder sb = new StringBuilder(); // 결과값을 저장할 객체
        while (N > 0) {
            int location = 0; // 현재 옮긴 원판이 있는 타워 위치
            for (int i = 1; i <= 2; i++) {
                for (int circle : tower[i]) {
                    if (circle == N) { location = i; break; }
                }
            }
            
            int size = tower[location].size();
            for (int i = size - 1; i >= 0; i--) {
                if (tower[location].get(i) == N) {
                    tower[location].remove(i);
                    sb.append(location).append(" ").append(3).append("\n");
                    N--;
                    K++;
                    break;
                } else {
                    tower[location == 1 ? 2 : 1].add(tower[location].get(i));
                    tower[location].remove(i);
                    sb.append(location).append(" ").append(location == 1 ? 2 : 1).append("\n");
                    K++;
                }
            }
        }
        
        // 결과값 출력하기
        System.out.print(K + "\n" + sb);
    } // main 종료
} // Main 종료