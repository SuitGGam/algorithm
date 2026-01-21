/*
 * 백준 6068번 : 시간 관리하기
 * https://www.acmicpc.net/problem/6068
 * 난이도 : 골드 5
 * 풀이 날짜 : 2026-01-20
 * 간단 설명 : 존이 일을 완료할 수 있는 가장 늦은 시간을 구하는 문제
 */

/*
 * 완전 탐색 경우의 수 : S (1,000,000)
 * 이유 : 끝내야 하는 시간의 범위 내에서 모두 탐색하면 됨
 *
 * 알고리즘 : 그리디
 * 자료구조 : Array
 * 시간 복잡도 : O (S * N)
 * 풀이 방법 : 끝나는 시간을 먼저 끝나는 시간 순으로 정렬시킴
 * 시작 시간인 0초부터 마지막 끝나는 시간까지 T를 더하면서 계산
 * T가 끝나는 시간인 S를 넘어버리면 해당 시간부터 이후 시간까지는 불가능한 시간임
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class BOJ6068 {
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int N = Integer.parseInt(br.readLine()); // 일의 개수
        int[][] work = new int[N][2]; // 일 배열
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            work[i][0] = Integer.parseInt(st.nextToken()); // 필요한 시간
            work[i][1] = Integer.parseInt(st.nextToken()); // 끝내야 하는 시간
        }
        
        // 버퍼 닫기
        br.close();
        
        Arrays.sort(work, (a, b) -> {
            if (a[1] == b[1]) return Integer.compare(a[0], b[0]);
            return Integer.compare(a[1], b[1]);
        });
        
        int lateWakeUp = -1; // 가장 늦게 일어나도 되는 시간
        for (int i = 0; i < work[N - 1][1]; i++) {
            int curTime = i; // 현재 시간
            boolean valid = true;
            for (int j = 0; j < N; j++) {
                curTime += work[j][0];
                if (curTime > work[j][1]) { valid = false; break; }
            }
            
            if (valid) lateWakeUp = i; // 늦게 일어나도 되는 시간 갱신
            else break;
        }
        
        // 결과값 출력하기
        System.out.print(lateWakeUp);
    } // main 종료
} // Main 종료