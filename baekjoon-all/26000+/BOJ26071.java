/*
 * 백준 26071번 : 오락실에 간 총총이
 * https://www.acmicpc.net/problem/26071
 * 난이도 : 실버 2
 * 풀이 날짜 : 2026-01-10
 * 간단 설명 : 화면에서 총총이를 한 곳으로 모으기 위해 최소 몇 번의 버튼을 눌러야 하는지 구하는 문제
 */

/*
 * 완전 탐색 경우의 수 : N^2 * (N - 1) * 4 (1,500^2 x 1,499 x 4)
 * 이유 : 곰곰이가 움직일 때 모두 같은 방향으로 움직이기 때문에
 * 화면의 테두리가 아닌 곳에서는 뭉칠 수가 없음
 * 즉, 화면의 테두리에서만 뭉칠 수 있음
 * 그래서 화면 테두리에 대한 모든 G에 대해 완전 탐색 진행
 *
 * 알고리즘 : 그리디(?)
 * 자료구조 : X
 * 시간 복잡도 : O (N^2)
 * 풀이 방법 : 완전 탐색 경우에서 말했듯 곰곰이는 화면의 테두리에서만 모일 수 있음
 * 좀 더 자세히 보면 모두 같은 방향으로 움직이기 때문에 정확히는 꼭짓점 네 곳에서만 모일 수 있음
 * 하지만 예외가 두 가지가 존재함
 * 곰곰이가 한 마리일 때 (움직일 필요 없음)
 * 곰곰이가 한 줄(행 또는  열)에 모여 있을 때 (꼭짓점이 아닌 테두리에서 모일 수 있음)
 * 이에 대한 분기를 나누고 곰곰이가 존재하는 행과 열에 대한 최댓값, 최솟값을 구해야 함
 * 각 꼭짓점이 가지는 수는 N - 1, 0임
 * 이거에 대해서 행과 열의 최댓값, 최솟값의 차이를 계산해야 함
 * 각 꼭짓점에서 가장 멀리(행 + 열) 있는 곰곰이의 움직임만큼 움직인 게 최소 움직임임
 *
 * 시간 복잡도가 O (N^2)인 이유는 매번 행과 열의 최댓값, 최솟값에 대한 갱신을 하기 때문임
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ26071 {
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine()); // 화면의 크기
        int minX = N, minY = N; // 곰곰이가 위치한 최대 X, Y 좌표
        int maxX = -1, maxY = -1; // 곰곰이가 위치한 최소 X, Y 좌표
        int gomCnt = 0; // 곰곰이의 수
        for (int x = 0; x < N; x++) {
            String line = br.readLine(); // 화면의 정보
            for (int y = 0; y < N; y++) {
                if (line.charAt(y) == 'G') {
                    minX = Math.min(minX, x); // 최소 X 갱신
                    minY = Math.min(minY, y); // 최소 Y 갱신
                    maxX = Math.max(maxX, x); // 최대 X 갱신
                    maxY = Math.max(maxY, y); // 최대 Y 갱신
                    gomCnt++; // 곰곰이의 수 증가
                }
            }
        }
        
        // 버퍼 닫기
        br.close();
        
        int minButton = 0; // 최소 버튼 횟수
        if (gomCnt != 1) {
            int verticalDist = 0; // 세로 이동
            if (minX != maxX) verticalDist = Math.min(maxX, N - 1 - minX); // 곰곰이가 여러 행에 걸쳐 있는 경우
            
            int horizontalDist = 0; // 가로 이동
            if (minY != maxY) horizontalDist = Math.min(maxY, N - 1 - minY); // 곰곰이가 여러 열에 걸쳐 있는 경우
            
            minButton = verticalDist + horizontalDist; // 최소 버튼 횟수 갱신
        }
        
        // 결과값 출력하기
        System.out.print(minButton);
    } // main 종료
} // Main 종료