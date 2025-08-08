/*
 * 백준 1764번 : 듣보잡
 * https://www.acmicpc.net/problem/1764
 * 난이도 : 실버 4
 * 풀이 날짜 : 2025-08-08
 * 간단 설명 : 듣도 못한 사람과 보도 못한 사람 모두에 포함된 사람을 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.StringTokenizer;
import java.util.Set;

public class BOJ1764 {
    // 입력을 위한 객체 선언
    static BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken()); // 듣도 못한 사람의 수
        int M = Integer.parseInt(st.nextToken()); // 보도 못한 사람의 수

        // 빠른 탐색을 위한 HashSet
        Set<String> neverHeard = new HashSet<>(); // 듣도 못한 사람을 저장할 HashSet

        for (int i = 0; i < N; i++) {
            neverHeard.add(br.readLine()); // 듣도 못한 사람 저장
        }

        // 이름 사전순 정렬을 위한 TreeSet
        Set<String> completeUnknown = new TreeSet<>(); // 듣보잡을 저장할 TreeSet
        for (int i = 0; i < M; i++) {
            String neverSaw = br.readLine(); // 보도 못한 사람

            if (neverHeard.contains(neverSaw)) { // 듣보잡이면
                completeUnknown.add(neverSaw); // TreeSet에 추가
            }
        }

        // 버퍼 닫기
        br.close();

        StringBuilder sb = new StringBuilder(); // 결과값을 저장할 객체
        int cnt = completeUnknown.size(); // 듣보잡의 수
        for (String unknown : completeUnknown) {
            sb.append(unknown).append("\n"); // 듣보잡 명단 결과값에 추가하기
        }

        // 결과값 출력하기
        System.out.print(cnt + "\n" + sb);
    } // main 종료
} // Main 종료