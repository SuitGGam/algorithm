/*
 * 백준 27885번 : 가희와 열리지 않는 건널목
 * https://www.acmicpc.net/problem/27885
 * 난이도 : 브론즈 1
 * 풀이 날짜 : 2026-02-19
 * 간단 설명 : 하루 동안 건널목의 차단기가 몇 초 동안 올라가는지 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class BOJ27885 {
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int c = Integer.parseInt(st.nextToken()); // 상행 열차 수
        int h = Integer.parseInt(st.nextToken()); // 하행 열차 수

        int[] time = new int[c + h]; // 열차 시간 배열
        for (int i = 0; i < c + h; i++) {
            st = new StringTokenizer(br.readLine(), ":");
            int hour = Integer.parseInt(st.nextToken()); // 시 저장
            int min = Integer.parseInt(st.nextToken()); // 분 저장
            int second = Integer.parseInt(st.nextToken()); // 초 저장

            time[i] = hour * 3600 + min * 60 + second; // 초로 변경
        }

        // 버퍼 닫기
        br.close();

        Arrays.sort(time); // 시간 정렬
        int block = 0; // 차단기가 내려간 시간
        int prv = time[0]; // 마지막으로 차단기가 올라간 시각
        for (int i = 0; i < c + h; i++) {
            int depart = time[i] + 40; // 열차가 빠져나가는 시각

            if (time[i] < prv) block += depart - prv; // 구간이 겹치면 겹치는 만큼 차단기가 더 내려감
            else block += 40; // 구간이 안 겹치면 40초만 차단기가 내려감

            prv = depart; // 마지막 시각 갱신
        }

        // 결과값 출력하기
        System.out.print(86400 - block);
    } // main 종료
} // Main 종료