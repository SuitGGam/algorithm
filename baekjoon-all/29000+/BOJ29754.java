/*
 * 백준 29754번 : 세상에는 많은 유투버가 있고, 그중에서 버츄얼 유튜버도 존재한다
 * https://www.acmicpc.net/problem/29754
 * 난이도 : 실버 1
 * 풀이 날짜 : 2026-02-14
 * 간단 설명 : 진짜 버츄얼 유튜버를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class BOJ29754 {
    static int M; // 방송을 마지막으로 본 날짜
    static TreeMap<String, int[][]> map; // 방송 정보를 담을 TreeMap

    static void register(String name) { // 초기 방송 정보를 등록하는 함수
        map.put(name, new int[(M - 1) / 7 + 1][2]); // 초기 등록
    } // register

    static void update(String name, int day, String start, String end) { // 방송 정보를 업데이트하는 함수
        int hour = Integer.parseInt(end.substring(0, 2)) - Integer.parseInt(start.substring(0, 2)); // 시간
        int min = Integer.parseInt(end.substring(3)) - Integer.parseInt(start.substring(3)); // 분

        if (min < 0) {hour--; min *= -1;}

        int[][] info = map.get(name); // 방송 정보 가져오기
        int week = (day - 1) / 7; // 해당 주간
        info[week][0]++; // 해당 주간 방송 날짜 증가
        info[week][1] += hour * 60 + min; // 분 증가
    } // update 종료

    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken()); // 방송의 수
        M = Integer.parseInt(st.nextToken()); // 방송을 마지막으로 본 날짜

        map = new TreeMap<>(); // 방송 정보를 담을 TreeMap

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            String name = st.nextToken(); // 버츄얼 유튜버의 이름
            int day = Integer.parseInt(st.nextToken()); // 방송 날짜
            String start = st.nextToken(); // 방송 시작 시간
            String end = st.nextToken(); // 방송 종료 시간

            if (!map.containsKey(name)) register(name); // 초기 등록

            update(name, day, start, end); // 방송 정보 등록
        }

        // 버퍼 닫기
        br.close();

        StringBuilder sb = new StringBuilder(); // 결과값을 저장할 객체
        boolean empty = true;
        for (Map.Entry<String, int[][]> entry : map.entrySet()) {
            String name = entry.getKey(); // 이름
            int[][] info = entry.getValue(); // 방송 정보

            boolean real = true;
            for (int i = 0 ; i < (M - 1) / 7 + 1; i++) { // 방송 조건을 충족시키면 이름 추가
                if (info[i][0] < 5 || info[i][1] < 3600) {real = false; break;}
            }

            if (real) {
                sb.append(name).append("\n");
                empty = false; // 비어있지 않음
            }
        }

        // 결과값 출력하기
        System.out.print(empty ? -1 : sb);
    } // main 종료
} // Main 종료