/*
 * 백준 5567번 : 결혼식
 * https://www.acmicpc.net/problem/5567
 * 난이도 : 실버 2
 * 풀이 날짜 : 2025-09-01
 * 간단 설명 : 결혼식에 초대하는 동기의 수를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Stack;

public class BOJ5567 {
    static int[][] friends; // 친구 관계도 배열
    static boolean[] invitation; // 초대 여부 배열
    static Stack<Integer> stack = new Stack<>(); // 친구의 친구 정보를 담기 위한 스택
    
    public static void main(String[] args) throws IOException {
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine()); // 상근이의 동기 수
        int m = Integer.parseInt(br.readLine()); // 리스트의 길이
        friends = new int[n + 1][n + 1]; // 친구 관계도 배열 생성
        invitation = new boolean[n + 1]; // 초대 여부 배열 생성
        
        for (int i = 0; i < m; i++) { // 관계 설정 시작
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken()); // a 정점
            int b = Integer.parseInt(st.nextToken()); // b 정점
            friends[a][b] = 1; // a 관계도 추가
            friends[b][a] = 1; // b 관계도 추가
        } // 관계 설정 종료
        
        // 버퍼 닫기
        br.close();
        
        for (int i = 2; i <= n; i++) { // 상근이 친한 친구 찾기 시작
            if (friends[1][i] == 1)	invitation[i] = true;
        } // 상근이 친한 친구 찾기 종료
        
        for (int i = 2; i <= n; i++) { // 친한 친구의 친한 친구 찾기 시작
            if (invitation[i]) {
                for (int j = 2; j <= n; j++) {
                    if (friends[i][j] == 1) stack.push(j);
                }
            }
        } // 친한 친구의 친한 친구 찾기 종료
        
        while(!stack.empty()) { // 친한 친구의 친한 친구 초대장 주기 시작
            invitation[stack.pop()] = true;
        } // 친한 친구의 친한 친구 초대장 주기 종료
        
        // 초대장 개수 세기
        int count = 0; // 초대장 개수
        for (int i = 2; i <= n; i++) {
            if (invitation[i] == true) count++;
        }
        
        // 결과값 출력하기
        System.out.print(count);
    } // main 종료
} // class 종료