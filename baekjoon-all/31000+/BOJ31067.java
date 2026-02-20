/*
 * 백준 31067번 : 다오의 경주 대회
 * https://www.acmicpc.net/problem/31067
 * 난이도 : 브론즈 1
 * 풀이 날짜 : 2026-02-20
 * 간단 설명 : 트랙의 거리를 K만큼 늘릴 수 있을 때 트랙의 길이가 오름차순이 될 수 있게 하는 최소 늘림 횟수를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ31067 {
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 트랙의 수
        int K = Integer.parseInt(st.nextToken()); // 늘릴 수 있는 길이

        int[] len = new int[N]; // 트랙의 길이 배열
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) len[i] = Integer.parseInt(st.nextToken()); // 길이 저장

        // 버퍼 닫기
        br.close();

        int minExtend = 0; // 길이를 늘리는 최소 횟수
        boolean valid = true; // 트랙의 유효성
        for (int i = 1; i < N; i++) {
            if (len[i] <= len[i - 1]) { // 이전 트랙보다 거리가 짧거나 같은 경우
                if (len[i] + K > len[i - 1]) {len[i] += K; minExtend++;} // 거리를 늘려서 더 길어진다면 늘려주기
                else {valid = false; break;} // 거리를 늘려도 더 길어지지 않는다면 방법 X
            }
        }

        // 결과값 출력하기
        System.out.print(valid == true ? minExtend : -1);
    } // main 종료
} // Main 종료