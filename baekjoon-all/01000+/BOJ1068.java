/*
 * 백준 1068 : 트리
 * https://www.acmicpc.net/problem/1068
 * 난이도 : 골드 5
 * 풀이 날짜 : 2025-07-18
 * 간단 설명 : 특정 서브 트리를 제거했을 때 리프 노드를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.Queue;

public class BOJ1068 {
    // 입력을 위한 객체 선언
    static BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine()); // 노드의 개수
        int[] parInfo = new int[N]; // 부모의 정보를 담을 배열
        int[] checkChild = new int[N]; // 자식 노드를 가지고 있는 배열
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) { // 부모의 정보 저장
            parInfo[i] = Integer.parseInt(st.nextToken());
            if (parInfo[i] != -1) {
                checkChild[parInfo[i]]++; // 자식 노드를 가지고 있는 부모 노드 체크
            }
        }
        
        int cnt = 0; // 리프 노드의 개수
        for (int i = 0; i < N; i++) {
            // 리프 노드의 개수 세기
            if (checkChild[i] == 0) cnt++;
        }
        
        int removeNode = Integer.parseInt(br.readLine()); // 제거할 노드 번호
        // 제거할 노드가 루트 노드가 아닌 경우
        if (parInfo[removeNode] != -1) {
            int anotherChild = 0; // 또 다른 자식 노드 유무를 확인하기 위한 변수
            boolean checkLeaf = true; // 제거할 노드가 리프 노드인지 확인하기 위한 변수
            for (int i = 0; i < N; i++) {
                if (parInfo[i] == removeNode) checkLeaf = false;
                if (parInfo[i] == parInfo[removeNode]) anotherChild++;
            }
            
            // 루트 노드가 리프 노드가 되는 경우
            if (parInfo[parInfo[removeNode]] == -1 && anotherChild == 1) {
                cnt = 1;
            }
            // 루트 노드가 리프 노드가 안 되는 경우
            else {
                // 제거할 노드가 리프 노드면 리프 노드 개수 한 개 추가
                // 밑에 로직에서 1개가 제거될 예정이라 숫자 맞춰주기
                // 단, 부모 노드가 루트거나
                // 부모 노드가 자식 노드가 2개 이상이면 예외
                if (anotherChild < 2 && checkLeaf && parInfo[parInfo[removeNode]] != -1) cnt++;
                
                Queue<Integer> queue = new LinkedList<>(); // 리프 노드를 찾아 들어갈 큐 생성
                queue.add(removeNode); // 제거할 노드 번호 큐에 넣기
                
                while (queue.size() != 0) { // 리프 노드 찾기
                    int cur = queue.remove(); // 현재 노드
                    int ValidLeaf = 0; // 리프 노드인지 확인할 변수
                    for (int i = 0; i < N; i++) {
                        if (parInfo[i] == cur) queue.add(i); // 자식 노드 큐에 삽입
                        else ValidLeaf++; // 현재 노드의 자식 노드가 아니면 하나 증가
                    }
                    
                    // 현재 노드가 리프 노드면 리프 노드 개수 1개 제거
                    if (ValidLeaf == N) cnt--;
                }
            }
        }
        // 제거할 노드가 루트 노드인 경우
        else cnt = 0;
        
        // 버퍼 닫기
        br.close();
        
        // 결과값 출력하기
        System.out.print(cnt);
    } // main 종료
} // Main 종료