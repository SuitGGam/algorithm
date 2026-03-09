/*
 * 백준 27968번 : 사사의 사차원 사탕 봉지
 * https://www.acmicpc.net/problem/27968
 * 난이도 : 실버 2
 * 풀이 날짜 : 2026-03-09
 * 간단 설명 : 아이에게 사탕을 주기 위해 사탕을 몇 번 꺼내야 하는지 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ27968 {
    static int M; // 사탕을 꺼낼 수 있는 최대 횟수
    static long[] takeOut; // 꺼내는 사탕의 수 배열

    static int binarySearch(long wantCandy) { // 사탕을 꺼내는 횟수를 구하는 문제
        int cnt = M; // 꺼내는 횟수
        if (wantCandy > takeOut[M - 1]) return cnt + 1; // 다 꺼낼 수 없으면 종료

        int left = 0; // 왼쪽 시작점
        int right = M - 1; // 오른쪽 시작점
        while (left <= right) {
            int mid = (left + right) / 2; // 가운데

            if (wantCandy <= takeOut[mid]) { // 뽑으려는 개수보다 많거나 같으면 오른쪽 포인터 감소
                cnt = mid; // 횟수 갱신
                right = mid - 1;
            } else left = mid + 1; // 뽑으려는 개수보다 부족하면 왼쪽 포인터 증가
        }

        return cnt + 1; // 꺼내는 횟수 반환
    } // binarySearch 종료

    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken()); // 아이의 수
        M = Integer.parseInt(st.nextToken()); // 사탕을 꺼낼 수 있는 최대 횟수

        st = new StringTokenizer(br.readLine(), " ");
        takeOut = new long[M]; // 꺼내는 사탕의 수 배열
        for (int i = 0; i < M; i++) takeOut[i] = Long.parseLong(st.nextToken()); // 꺼내는 사탕의 수 저장

        long[] want = new long[N]; // 받고 싶은 사탕의 수 배열
        for (int i = 0; i < N; i++) want[i] = Long.parseLong(br.readLine()); // 받고 싶은 사탕의 수 저장

        // 버퍼 닫기
        br.close();

        for (int i = 1; i < M; i++) takeOut[i] += takeOut[i - 1]; // 꺼내는 사탕의 수 누적 합 변환

        StringBuilder sb = new StringBuilder(); // 결과값을 저장할 객체
        for (int i = 0; i < N; i++) {
            int cnt = binarySearch(want[i]);

            sb.append(cnt <= M ? cnt : "Go away!").append("\n"); // 결과값 추가하기
        }

        // 결과값 출력하기
        System.out.print(sb);
    } // main 종료
} // Main 종료