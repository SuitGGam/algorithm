/*
 * 백준 1620번 : 나는야 포켓몬 마스터 이다솜
 * https://www.acmicpc.net/problem/1620
 * 난이도 : 실버 4
 * 풀이 날짜 : 2025-08-08
 * 간단 설명 : 다솜이가 원하는 포켓몬을 찾을 수 있게 도와주는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class BOJ1620 {
    // 입력을 위한 객체 선언
    static BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken()); // 포켓몬의 수
        int M = Integer.parseInt(st.nextToken()); // 맞춰야 하는 문제 수

        TreeMap<Integer, String> numMap = new TreeMap<>(); // 번호가 키값인 트리맵
        TreeMap<String, Integer> nameMap = new TreeMap<>(); // 이름을 키값인 트리맵
        for (int i = 1; i <= N; i++) {
            String name = br.readLine(); // 포켓몬의 이름
            numMap.put(i, name);  // 키 : 번호, 값 : 이름
            nameMap.put(name, i); // 키 : 이름, 값 : 번호
        }

        StringBuilder sb = new StringBuilder(); // 결과값을 저장할 객체
        for (int i = 0; i < M; i++) {
            String find = br.readLine(); // 찾아야 하는 포켓몬

            if (find.charAt(0) < 58) { // 주어진 값이 숫자인 경우
                sb.append(numMap.get(Integer.parseInt(find))).append("\n");
            } else { // 주어진 값이 문자인 경우
                sb.append(nameMap.get(find)).append("\n");
            }
        }

        // 버퍼 닫기
        br.close();

        // 결과값 출력하기
        System.out.print(sb);
    } // main 종료
} // Main 종료