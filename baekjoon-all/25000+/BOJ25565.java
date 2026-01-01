/*
 * 백준 25565번 : 딸기와 토마토
 * https://www.acmicpc.net/problem/25565
 * 난이도 : 골드 4
 * 풀이 날짜 : 2026-01-02
 * 간단 설명 : 딸기와 토마토가 같이 심어진 곳의 개수와 좌표를 구하는 문제
 */

/*
 * 완전 탐색 경우의 수 : N x M x K x 2
 * 모든 칸에서 K 만큼 다 순회해야 함
 * 시간 복잡도 : O (N)
 * 이유 : 씨앗을 심은 곳만 분석하면 됨
 * 알고리즘 : 구현
 * 자료구조 : Array, ArrayList
 * 풀이 방법 : 씨앗이 심어진 곳만 보면 됨
 * 씨앗이 몇 개가 같이 심어졌고 어디에 같이 심어졌는지가 중요
 * 씨앗이 심어진 개수와 K를 비교하면 몇 개가 같이 심어졌는지 알 수 있음
 * 그걸 바탕으로 3개의 분기로 나눔
 * 1. 모든 씨앗이 같이 심어진 경우 (ArrayList.size() == K)
 * 그러면 저장할 좌표를 모두 출력하면 됨
 * 2-1. 같이 심어진 씨앗의 개수가 1개인 경우 (딸기와 토마토가 교차하는 경우)
 * 이때는 씨앗을 심으면서 카운팅 한 행과 열의 개수를
 * K와 비교해서 겹치는 곳의 좌표가 같이 심어진 좌표
 * 2-2. 같이 심어진 씨앗의 개수가 1개인 경우 (딸기와 토마토가 같은 방향으로 심어진 경우)
 * 그럼 3번 분기와 같은 방식으로 처리
 * 반드시 위의 두 분기를 먼저 처리하고 3번 분기를 처리해야 함
 * 3. 같은 방향으로 심어진 경우 (딸기와 토마토가 같이 세로, 혹은 같이 가로로 심어진 경우)
 * 같은 방향으로 심어지면 첫 번째 심은 씨앗과 마지막으로 심은 씨앗의 행이나 열의 번호가 같음
 * 근데 이것부터 2번의 분기보다 먼저 넣으면 예제 1번과 같은 경우에 오답을 출력함
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class BOJ25565 {
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        int N = Integer.parseInt(st.nextToken()); // 텃밭의 세로 크기
        int M = Integer.parseInt(st.nextToken()); // 텃밭의 가로 크기
        int K = Integer.parseInt(st.nextToken()); // 연속한 칸의 크기
        
        ArrayList<int[]> field = new ArrayList<>(); // 씨앗이 심어진 곳의 좌표를 저장할 ArrayList
        int[] rowCnt = new int[N + 1]; // 행의 씨앗 개수를 세는 배열
        int[] colCnt = new int[M + 1]; // 열의 씨앗 개수를 세는 배열
        
        for (int x = 1; x <= N; x++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int y = 1; y <= M; y++) {
                int seed = Integer.parseInt(st.nextToken()); // 씨앗의 존재 유무
                if (seed == 1) {
                    field.add(new int[] { x, y }); // 씨앗이 심어진 곳 좌표 add
                    rowCnt[x]++; // 행 개수 증가
                    colCnt[y]++; // 열 개수 증가
                }
            }
        }
        
        // 버퍼 닫기
        br.close();
        
        int seedCnt = field.size() > K ? K - (field.size() - K) : field.size(); // 같이 씨앗이 심어진 곳의 개수
        StringBuilder togetherCoor = new StringBuilder(); // 씨앗이 같이 심어진 곳의 좌표를 저장할 객체
        togetherCoor.append(seedCnt).append("\n"); // 결과값 추가하기
        if (seedCnt == K) { // 모든 씨앗이 같이 심어진 경우
            // 결과값 추가하기
            for (int i = 0; i < K; i++) togetherCoor.append(field.get(i)[0]).append(" ").append(field.get(i)[1]).append("\n");
        } else if (seedCnt > 0) { // 씨앗의 일부가 같이 심어진 경우
            if (seedCnt == 1) { // 1개만 같이 심어진 경우
                boolean find = false;
                for (int[] pos : field) {
                    int x = pos[0]; // x 좌표
                    int y = pos[1]; // y 좌표
                    
                    if (rowCnt[x] == K && colCnt[y] == K) { // 교차하는 경우
                        togetherCoor.append(x).append(" ").append(y).append("\n"); // 결과값 추가하기
                        find = true;
                    }
                }
                
                if (!find) { // 같은 방향으로 1개만 같이 심어진 경우
                    int[] first = field.get(0); // 첫 번째 씨앗 좌표
                    int[] last = field.get(field.size() - 1); // 마지막 씨앗 좌표
                    
                    for (int i = field.size() - K; i < K; i++) togetherCoor.append(field.get(i)[0]).append(" ").append(field.get(i)[1]).append("\n");
                }
            } else { // 같은 방향으로 심어진 경우
                // 결과값 추가하기
                for (int i = field.size() - K; i < K; i++) togetherCoor.append(field.get(i)[0]).append(" ").append(field.get(i)[1]).append("\n");
            }
        }
        
        // 결과값 출력하기
        System.out.print(togetherCoor);
    } // main 종료
} // Main 종료