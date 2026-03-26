/*
 * 백준 11054번 : 가장 긴 바이토닉 부분 수열
 * https://www.acmicpc.net/problem/11054
 * 난이도 : 골드 4
 * 풀이 날짜 : 2026-03-26
 * 간단 설명 : 가장 긴 바이토닉 부분 수열 길이를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class BOJ11054 {
    static int N; // 수열의 크기
    static int[] A; // A 배열
    static int[] lisDp; // 가장 긴 증가하는 부분 수열 길이 Dp 배열
    static int[] ldsDp; // 가장 긴 감소하는 부분 수열 길이 Dp 배열

    static int lis(int idx) { // 가장 긴 증가하는 부분 수열 길이를 구하는 함수
        if (lisDp[idx] != -1) return lisDp[idx]; // 이미 계산된 곳이면 종료

        lisDp[idx] = 1; // 최소 길이는 자기 자신

        for (int prv = 0; prv < idx; prv++) { // 다음 위치부터 탐색
            if (A[prv] < A[idx]) lisDp[idx] = Math.max(lisDp[idx], lis(prv) + 1); // 길이 갱신
        }

        return lisDp[idx];
    } // lis 종료

    static int lds(int idx) { // 가장 긴 감소하는 부분 수열 길이를 구하는 함수
        if (ldsDp[idx] != -1) return ldsDp[idx]; // 이미 계산된 곳이면 종료

        ldsDp[idx] = 1; // 최소 길이는 자기 자신

        for (int nxt = idx + 1; nxt < N; nxt++) { // 다음 위치부터 탐색
            if (A[idx] > A[nxt]) ldsDp[idx] = Math.max(ldsDp[idx], lds(nxt) + 1); // 길이 갱신
        }

        return ldsDp[idx];
    } // lds 종료

    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine()); // 수열의 크기
        A = new int[N]; // 수열 A
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) A[i] = Integer.parseInt(st.nextToken()); // 원소 저장

        // 버퍼 닫기
        br.close();

        lisDp = new int[N]; // 가장 긴 증가하는 부분 수열 길이
        Arrays.fill(lisDp, -1); // lisDp 배열 초기화
        int maxLis = 0; // 가장 긴 증가하는 부분 수열 길이
        for (int i = 0; i < N; i++) maxLis = Math.max(maxLis, lis(i)); // 가장 긴 증가하는 부분 수열 길이 구하기

        ldsDp = new int[N]; // 가장 긴 감소하는 부분 수열 길이
        Arrays.fill(ldsDp, -1); // ldsDp 배열 초기화
        int maxLds = 0; // 가장 긴 감소하는 부분 수열 길이
        for (int i = 0; i < N; i++) maxLds = Math.max(maxLds, lds(i)); // 가장 긴 감소하는 부분 수열 길이 구하기

        int maxLen = 0; // 가장 긴 바이토닉 부분 수열 길이
        for (int i = 0; i < N; i++) maxLen = Math.max(maxLen, lisDp[i] + ldsDp[i] - 1); // 길이 갱신

        // 결과값 출력하기
        System.out.print(maxLen);
    } // main 종료
} // Main 종료