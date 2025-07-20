/*
 * 백준 17144번 : 미세먼지 안녕!
 * https://www.acmicpc.net/problem/17144
 * 난이도 : 골드 4
 * 풀이 날짜 : 2025-07-20
 * 간단 설명 : 미세먼지 확산, 공기청정기 작동을 T번 진행한 후 미세먼지 양의 총합을 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ17144 {
    // 입력을 위한 객체 선언
    static BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    
    static int R, C, T;  // 행의 길이, 열의 길이, 초의 길이
    static int cleanIdx; // 공기청정기 위치
    static int[][] room; // 방의 정보를 담을 배열
    static int[][] tmpRoom; // 변화된 방의 정보를 담을 배열
    
    static int[] dx = {0, 1,  0, -1}; // 우하좌상
    static int[] dy = {1, 0, -1,  0}; // 우하좌상
    
    // 미세먼지 확산 함수
    static void spread() {
        // 방 전체 탐색
        // 임시 배열을 이용해서 값을 저장하면
        // 확산의 영향이 중복되는 걸 방지 가능
        for (int x = 0; x < R; x++) {
            for (int y = 0; y < C; y++) {
                if (room[x][y] == -1) continue; // 공기청정기 continue
                
                if (room[x][y] > 0) { // 미세먼지가 있을 시 확산
                    for (int d = 0; d < 4; d++) { // 4방향 확산
                        int nx = x + dx[d]; // 행 방향 설정
                        int ny = y + dy[d]; // 열 방향 설정
                        
                        // 방을 벗어나거나 공기청정기를 만나면 continue
                        if (nx < 0 || nx >= R || ny < 0 || ny >= C || tmpRoom[nx][ny] == -1) continue;
                        
                        tmpRoom[nx][ny] += room[x][y] / 5; // 미세먼지 확산
                        tmpRoom[x][y]   -= room[x][y] / 5; // 확산한만큼 감소
                    }
                }
            }
        }
    } // spread 종료
    
    // 공기청정기 작동 함수
    static void clean() {
        // 작동 방향과 같은 방향으로 밀어내면 tmp를 N번 사용
        // 하지만 반대 방향으로 진행하면서 뒤에서 당겨오면 tmp 사용 X
        
        // 위 칸 공기청정기 작동
        for (int x = cleanIdx - 1; x > 0; x--) tmpRoom[x][0] = tmpRoom[x - 1][0];        // 오른쪽 -> 왼쪽 방향 순환
        for (int y = 0; y < C - 1; y++) tmpRoom[0][y] = tmpRoom[0][y + 1];               // 오른쪽 -> 왼쪽 방향 순환
        for (int x = 0; x < cleanIdx; x++) tmpRoom[x][C - 1] = tmpRoom[x + 1][C - 1];    // 아래 -> 위 방향 순환
        for (int y = C - 1; y > 1; y--) tmpRoom[cleanIdx][y] = tmpRoom[cleanIdx][y - 1]; // 왼쪽 -> 오른쪽 방향 순환
        tmpRoom[cleanIdx][1] = 0; // 바람 나온 곳 정화
        
        // 아래 칸 공기청정기 작동
        for (int x = cleanIdx + 2; x < R - 1; x++) tmpRoom[x][0] = tmpRoom[x + 1][0];            // 아래 -> 위 방향 순환
        for (int y = 0; y < C - 1; y++) tmpRoom[R - 1][y] = tmpRoom[R - 1][y + 1];               // 오른쪽 -> 왼쪽 방향 순환
        for (int x = R - 1; x > cleanIdx + 1; x--) tmpRoom[x][C - 1] = tmpRoom[x - 1][C - 1];    // 위 -> 아래 방향 순환
        for (int y = C - 1; y > 1; y--) tmpRoom[cleanIdx + 1][y] = tmpRoom[cleanIdx + 1][y - 1]; // 왼쪽 -> 오른쪽 방향 순환
        tmpRoom[cleanIdx + 1][1] = 0; // 바람 나온 곳 정화
    } // clean 종료
    
    // 임시 배열 -> 원본 배열 복사 함수
    static void copy() {
        for (int x = 0; x < R; x++) {
            for (int y = 0; y < C; y++) {
                room[x][y] = tmpRoom[x][y]; // 미세먼지 양 최신화
            }
        }
    } // copy 종료
    
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken()); // 행의 길이
        C = Integer.parseInt(st.nextToken()); // 열의 길이
        T = Integer.parseInt(st.nextToken()); // 초의 길이
        
        room = new int[R][C]; // 방 정보를 담을 배열의 크기 지정
        tmpRoom = new int[R][C]; // 변화된 방의 정보를 담을 배열 크기 지정
        for (int x = 0; x < R; x++) { // 방 정보 입력
            st = new StringTokenizer(br.readLine(), " ");
            for (int y = 0; y < C; y++) {
                int fineDust = Integer.parseInt(st.nextToken());;
                room[x][y] = fineDust;
                tmpRoom[x][y] = fineDust;
            }
        }
        
        // 버퍼 닫기
        br.close();
        
        // 공기청정기 위치 찾기
        // 공기청정기 위 칸 녀석만 찾기
        for (int x = 0; x < R; x++) {
            if (room[x][0] == -1) {
                cleanIdx = x; // 공기청정기 위 칸 위치 저장
                break;
            }
        }
        
        // 미세먼지 확산, 공기청정기 작동하기
        for (int i = 0; i < T; i++) {
            spread(); // 미세먼지 확산
            clean();  // 공기청정기 작동
            copy();   // 임시 배열 -> 원본 배열 복사
        }
        
        // 남은 미세먼지 합 구하기
        int sumFineDust = 0; // 남은 미세먼지 총합
        for (int x = 0; x < R; x++) {
            for (int y = 0; y < C; y++) {
                if (room[x][y] == -1) continue; // 공기청정기 continue
                
                sumFineDust += room[x][y]; // 미세먼지 양 더하기
            }
        }
        
        // 결과값 출력하기
        System.out.print(sumFineDust);
    } // main 종료
} // Main 종료