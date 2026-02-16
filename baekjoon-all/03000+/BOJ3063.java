/*
 * 백준 3063번 : 게시판
 * https://www.acmicpc.net/problem/3063
 * 난이도 : 실버 4
 * 풀이 날짜 : 2026-02-16
 * 간단 설명 : 첫 번째 포스터 위에 두 번째 포스터가 붙여졌을 때 첫 번째 포스터의 보이는 부분의 넓이를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ3063 {
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine()); // 테스트 케이스의 개수
        StringBuilder sb = new StringBuilder(); // 결과값을 저장할 객체
        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            int x3 = Integer.parseInt(st.nextToken());
            int y3 = Integer.parseInt(st.nextToken());
            int x4 = Integer.parseInt(st.nextToken());
            int y4 = Integer.parseInt(st.nextToken());

            int minX = Math.max(x1, x3);
            int minY = Math.max(y1, y3);
            int maxX = Math.min(x2, x4);
            int maxY = Math.min(y2, y4);

            int firstPoster = (x2 - x1) * (y2 - y1); // 처음 붙인 포스터의 넓이

            int coveredWidth = Math.max(0, maxX - minX); // 가리는 부분의 가로 길이
            int coveredHeight = Math.max(0, maxY - minY); // 가리는 부분의 세로 길이
            int coveredArea = coveredWidth * coveredHeight; // 가리는 부분의 넓이

            int visibleArea = firstPoster - coveredArea; // 처음 붙인 포스터의 보이는 부분의 넓이
            sb.append(visibleArea).append("\n"); // 결과값 추가하기
        }

        // 버퍼 닫기
        br.close();

        // 결과값 출력하기
        System.out.print(sb);
    } // main 종료
} // Main 종료