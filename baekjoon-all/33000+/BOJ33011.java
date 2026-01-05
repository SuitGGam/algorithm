/*
 * 백준 33011번 : 홀수와 짝수 게임
 * https://www.acmicpc.net/problem/33011
 * 난이도 : 실버 3
 * 풀이 날짜 : 2026-01-03
 * 간단 설명 : 채완이와 희원이가 홀수와 짝수 게임을 할 때 승자를 구하는 문제
 */

/*
 * 완전 탐색 경우의 수 : T * 2^N (100 x 2^100)
 * 이유 : 선공과 후공이 각각 홀수 혹은 짝수를 가져가는 여러 경우의 수를 계산
 *
 * 알고리즘 : 그리디
 * 자료구조 : X
 * 시간 복잡도 : O (1)
 * 풀이 방법 : 그리디라고 하긴 좀 그렇지만 탐욕적인 방법에 가까운 느낌임
 * 카드는 무조건 1장 이상 들어오고 선공은 채완이가 시작함
 * 모든 카드를 짝수와 홀수로만 구분한다고 했을 때
 * 크게 두 가지로 구분 가능
 * 1. 홀수와 짝수가 같은 경우
 * 홀수와 짝수가 같은 경우는 채완이가 홀수를 가져가면 희원이는 짝수를 가져감
 * 그러면 먼저 가져간 사람이 더 먼저 뽑기 때문에 채완이가 먼저 뽑을 게 없어져서 패배
 *
 * 2. 홀수와 짝수가 다른 경우
 * 홀수와 짝수 중 많은 걸 채완이가 먼저 가져가면
 * 희원이는 많은 것의 개수가 짝수면 많은 걸 가져가야 이기고 아니면 짐
 * 즉, 채완이가 이기는 경우는 홀수와 짝수 중 많은 것의 개수가 홀수면서 둘이 같지 않아야 하는 경우만 있음
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ33011 {
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스의 개수
        StringBuilder sb = new StringBuilder(); // 결과값을 저장할 객체
        for (int tc = 0; tc < T; tc++) {
            int N = Integer.parseInt(br.readLine()); // 카드의 개수
            st = new StringTokenizer(br.readLine(), " ");
            
            int odd = 0; // 홀수 개수
            int even = 0; // 짝수 개수
            for (int i = 0; i < N; i++) {
                int num = Integer.parseInt(st.nextToken());
                if (num % 2 == 1) odd++;
                else even++;
            }
            
            int min = Math.min(odd, even); // 둘 중 작은 것
            int max = Math.max(odd, even); // 둘 중 큰 것
            
            String winner = "heeda0528"; // 승자
            if (max % 2 == 1 && min != max) winner = "amsminn";
            
            // 결과값 추가하기
            sb.append(winner).append("\n");
        }
        
        // 버퍼 닫기
        br.close();
        
        // 결과값 출력하기
        System.out.print(sb);
    } // main 종료
} // Main 종료