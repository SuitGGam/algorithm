/*
 * 백준 31937번 : 로그프레소 마에스트로
 * https://www.acmicpc.net/problem/31937
 * 난이도 : 실버 2
 * 풀이 날짜 : 2026-02-06
 * 간단 설명 : 최초로 감염된 컴퓨터를 찾는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class BOJ31937 {
    static int N, M; // 컴퓨터의 개수, 로그의 개수
    static boolean[] infected; // 컴퓨터 감염 여부 배열
    static int[][] log; // 파일 전송 로그 배열

    static boolean valid(int curPC) { // 최초 감염 PC인지 확인하는 함수
        boolean[] checkInfected = new boolean[N + 1]; // 현재 감염 컴퓨터 목록
        checkInfected[curPC] = true; // 최초 감염 컴퓨터

        for (int[] pc : log) {
            int transmit = pc[1]; // 파일을 전송하는 컴퓨터
            int receive  = pc[2]; // 파일을 받는 컴퓨터

            if (checkInfected[transmit]) checkInfected[receive] = true; // 바이러스 전파
        }

        for (int i = 1; i <= N; i++) {
            if (infected[i] != checkInfected[i]) return false; // 유효하지 않음
        }

        return true; // 유효함
    } // valid 종료
    
	public static void main(String[] args) throws IOException {
		// 입력을 위한 객체 선언
		BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken()); // 컴퓨터의 개수
        M = Integer.parseInt(st.nextToken()); // 로그의 개수
        int K = Integer.parseInt(st.nextToken()); // 감염된 컴퓨터의 개수

        int[] infectedPC = new int[K]; // 감염된 컴퓨터 배열
        infected = new boolean[N + 1]; // 컴퓨터 감염 여부 배열
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < K; i++) {
            int curInfected = Integer.parseInt(st.nextToken()); // 감염된 컴퓨터 번호
            infectedPC[i] = curInfected; // 감염된 컴퓨터 명단 저장
            infected[curInfected] = true; // 감염 처리
        }
        
        log = new int[M][3]; // 파일 전송 로그 배열
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int t = Integer.parseInt(st.nextToken()); // 감염된 시각
            int a = Integer.parseInt(st.nextToken()); // 파일을 전송하는 컴퓨터
            int b = Integer.parseInt(st.nextToken()); // 파일을 받는 컴퓨터
            
            // 전송 기록 저장
            log[i][0] = t;
            log[i][1] = a;
            log[i][2] = b;
        }
        
		// 버퍼 닫기
		br.close();

        Arrays.sort(log, (o1, o2) -> Integer.compare(o1[0], o2[0])); // 로그를 시간 순으로 정렬
        
        int firstInfected = 0; // 처음 감염된 컴퓨터
        for (int curInfected : infectedPC) {
            if (valid(curInfected)) {firstInfected = curInfected; break;}
        }
        
		// 결과값 출력하기
		System.out.print(firstInfected);
	} // main 종료
} // Main 종료
