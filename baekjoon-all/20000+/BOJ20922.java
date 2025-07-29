/*
 * 백준 20922번 : 겹치는 건 싫어
 * https://www.acmicpc.net/problem/20922
 * 난이도 : 실버 1
 * 풀이 날짜 : 2025-07-30
 * 간단 설명 : K 조건을 만족하는 최장 연속 부분 수열의 길이를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ20922 {
    // 입력을 위한 객체 선언
    static BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken()); // 수열의 길이
        int K = Integer.parseInt(st.nextToken()); // 최대 중복 개수

        st = new StringTokenizer(br.readLine(), " ");
        int[] sequence = new int[N]; // 수열을 담을 배열
        for (int i = 0 ; i < N; i++) {
            sequence[i] = Integer.parseInt(st.nextToken()); // 수열 저장
        }

        // 버퍼 닫기
        br.close();

        int left  =  0; // 왼쪽 포인터
        int right = -1; // 오른쪽 포인터
        int maxLength = 0; // 최장 길이
        int tmpLength = right - left + 1; // 임시 길이
        int[] counting = new int[100000 + 1];  // 수의 개수를 저장할 카운팅 배열
        while (right < N - 1) {
            counting[sequence[++right]]++; // 오른쪽 포인터 한 칸 이동

            while (counting[sequence[right]] > K) { // K 조건을 만족하지 못 하는 경우
                counting[sequence[left++]]--; // 왼쪽 포인터 한 칸 이동
            }

            tmpLength = right - left + 1; // 임시 길이
            if (maxLength < tmpLength) maxLength = tmpLength; // 최대 길이 갱신
        }

        // 결과값 출력하기
        System.out.print(maxLength);
    } // main 종료
} // Main 종료