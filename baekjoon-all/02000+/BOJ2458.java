/*
 * 백준 2458번 : 키 순서
 * https://www.acmicpc.net/problem/2458
 * 난이도 : 골드 4
 * 풀이 날짜 : 2025-08-25
 * 간단 설명 : 키를 확실히 아는 학생이 몇 명인지 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.ArrayList;

public class BOJ2458 {
    // 입력을 위한 객체 선언
    static BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    
    static int N; // 학생의 수
    static ArrayList<Integer>[] shorter; // 나보다 키가 작은 사람의 목록
    static ArrayList<Integer>[] taller;  // 나보다 키가 큰 사람 목록
    
    static int cnt; // 키 순서를 확실히 알 수 있는 학생의 수
    static boolean[] check; // 순서를 확실히 알 수 있는지 확인하는 배열
    static int rootStudent; // dfs 시작 숫자
    static void dfs(int curStudent) {
        shorterThanMe(curStudent); // 나보다 작은 사람 탐색
        tallerThanMe(curStudent);  // 나보다 큰 사람 탐색
    } // dfs 종료
    
    static void shorterThanMe(int curStudent) { // 나보다 작은 사람을 탐색하는 함수
        if (curStudent != rootStudent && check[curStudent]) return; // 중복 방문 방지
        
        check[curStudent] = true;
        
        for (int i = 0; i < shorter[curStudent].size(); i++){
            int nxtStudent = shorter[curStudent].get(i);
            
            shorterThanMe(nxtStudent);
        }
    } // shorterThanMe 종료
    
    static void tallerThanMe(int curStudent) { // 나보다 큰 사람을 탐색하는 함
        if (curStudent != rootStudent && check[curStudent]) return; // 중복 방문 방지
        
        check[curStudent] = true;
        
        for (int i = 0; i < taller[curStudent].size(); i++){
            int nxtStudent = taller[curStudent].get(i);
            
            tallerThanMe(nxtStudent);
        }
    } // tallerThanMe 종료
    
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken()); // 학생의 수
        int M = Integer.parseInt(st.nextToken()); // 키를 비교한 횟수
        
        shorter = new ArrayList[N + 1]; // 키가 작은 사람의 목록 크기 지정
        taller  = new ArrayList[N + 1]; // 키가 큰 사람의 목록 크기 지정
        for (int i = 1; i <= N; i++) {
            shorter[i] = new ArrayList<>(); // 나보다 작은 사람 목록 배열 생성
            taller[i]  = new ArrayList<>(); // 나보다 큰 사람 목록 배열 생성
        }
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken()); // 더 큰 학생
            int b = Integer.parseInt(st.nextToken()); // 더 작은 학생
            
            shorter[a].add(b); // 나보다 작은 사람 기록
            taller[b].add(a);  // 나보다 큰 사람 기록
        }
        
        // 버퍼 닫기
        br.close();
        
        cnt = 0; // 키를 확실히 알 수 있는 학생의 수 초기화
        check = new boolean[N + 1]; // 순서를 확실히 알 수 있는지 확인하는 배열 크기 지정
        for (int i = 1; i <= N; i++) {
            rootStudent = i;
            dfs(rootStudent); // 탐색 시작
            
            int cntTrue = 0; // 순서를 아는 학생의 수
            for (int j = 1; j <= N; j++) {
                if (check[j]) cntTrue++;
            }
            
            if (cntTrue == N) cnt++; // 모두 확인이 가능하면 확실히 아는 학생 한 명 추가
            
            Arrays.fill(check, false); // check 배열 초기화
        }
        
        // 결과값 출력하기
        System.out.print(cnt);
    } // main 종료
} // Main 종료