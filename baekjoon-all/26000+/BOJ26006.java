/*
 * 백준 26006번 : K-Queen
 * https://www.acmicpc.net/problem/26006
 * 난이도 : 실버 2
 * 풀이 날짜 : 2026-01-12
 * 간단 설명 : 킹과 퀸의 위치가 주어졌을 때 어떤 상태인지 구하는 문제
 */

/*
 * 완전 탐색 경우의 수 : K * (2 * N + √N) (100,000 * (2 * 10^9 + 10^9))
 * 이유 : 퀸의 좌표가 들어올 때마다 행, 열, 대각선을 모두 탐색해야 함
 *
 * 알고리즘 : 구현
 * 자료구조 : Array
 * 시간 복잡도 : O (K)
 * 풀이 방법 : 우리가 알고 싶은 건 오직 킹과 인근 8방향이 공격당하는지임
 * 총 9칸에 대해서만 공격의 대상이 되는지만 파악하면 됨
 * 퀸은 가로, 세로, 대각선을 모두 공격함
 * 그럼 퀸의 좌표가 들어왔을 때 킹과 인근 8방향 중
 * 같은 행, 열, 대각선이 있는지만 확인하면 됨
 * 그리고 조건에 맞게 어떤 상태인지 구하면 됨
 *
 * 여기서 주의해야 할 건 두 가지임
 * 1. 킹의 인근 8방향 중 체스판 밖이 있는 경우를 체크할 것
 * 2. 대각선을 어떻게 처리할 것인지
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ26006 {
    static int N; // 체스판의 크기
    static int R, C; // 백색 킹의 행, 열 위치
    
    // 킹 위치 + 인근 8방향
    // 00, 01, 02, 10, 11, 12, 20, 21, 22
    static int[] dr = {-1, -1, -1, 0, 0, 0, 1, 1, 1};
    static int[] dc = {-1, 0, 1, -1, 0, 1, -1, 0, 1};
    static boolean[] kingNear = new boolean[9]; // 킹과 인근 8방향 배열
    
    static void checkNear(int qr, int qc) { // 퀸이 킹과 인근 8방향을 공격 가능한지 확인하는 함수
        for (int i = 0; i < 9; i++) {
            int nr = R + dr[i];
            int nc = C + dc[i];
            
            // 공격 가능한 곳이면 true로 변경
            if (qr == nr || qc == nc || Math.abs(qr - nr) == Math.abs(qc - nc)) kingNear[i] = true;
        }
    } // checkNear 종료
    
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        N = Integer.parseInt(st.nextToken()); // 체스판의 크기
        int K = Integer.parseInt(st.nextToken()); // 흑색 퀸의 수
        
        st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken()); // 백색 킹의 행 좌표
        C = Integer.parseInt(st.nextToken()); // 백색 킹의 열 좌표
        
        for (int i = 0; i < 9; i++) { // 체스판이 범위 밖인지 확인
            int nr = R + dr[i];
            int nc = C + dc[i];
            
            if (nr < 1 || nr > N || nc < 1 || nc > N) kingNear[i] = true; // 체스판의 범위 밖이면 true로 변경
        }
        
        for (int i = 0; i < K; i++) {
            st =  new StringTokenizer(br.readLine(), " ");
            int qr = Integer.parseInt(st.nextToken()); // 흑색 퀸의 행 좌표
            int qc = Integer.parseInt(st.nextToken()); // 흑색 퀸의 열 좌표
            
            checkNear(qr, qc); // 킹과 인근 8방향 확인
        }
        
        // 버퍼 닫기
        br.close();
        
        boolean kingIsAttacked = kingNear[4]; // 킹의 공격 당하는지 여부
        boolean canMove = false; // 킹이 인근 8방향으로 움직일 수 있는지 여부
        for (int i = 0; i < 9; i++) {
            if (i == 4) continue; // 킹의 위치면 continue
            
            if (!kingNear[i]) { canMove = true; break; } // 킹이 움직일 수 있으면 true로 변경
        }
        
        String condition = "NONE"; // 아무 것도 아닌 상태
        if (kingIsAttacked && canMove) condition = "CHECK"; // 체크인 상태
        else if (kingIsAttacked && !canMove) condition = "CHECKMATE"; // 체크메이트인 상태
        else if (!kingIsAttacked && !canMove) condition = "STALEMATE"; // 스테일메이트인 상태
        
        // 결과값 출력하기
        System.out.print(condition);
    } // main 종료
} // Main 종료