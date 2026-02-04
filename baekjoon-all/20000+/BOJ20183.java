/*
 * 백준 20183번 : 골목 대장 호석 - 효율성 2
 * https://www.acmicpc.net/problem/20183
 * 난이도 : 골드 1
 * 풀이 날짜 : 2026-02-04
 * 간단 설명 : 주어진 돈을 가지고 골목을 이동했을 때 수금액의 최댓값이 최소값인 경우를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class BOJ20183 {
	static class Node { // 
		int to; // 연결된 교차로
		long cost; // 수금액
		
		Node(int to, long cost) {
			this.to = to;
			this.cost = cost;
		}
	} // Node 종료
	
	static int N; // 교차로의 개수
	static int A, B; // 시작 교차로, 도착 교차로
	static long C; // 가진 돈
	static ArrayList<Node>[] cross; // 교차로 배열
	static final long INIT = 1_000_000_000; // 수금액 초기화 값
	
	static boolean dijkstra(int limit) { // 제한된 수금액으로 A에서 B로 갈 수 있는지 구하는 함수
		long[] money = new long[N + 1]; // 누적 수금액 배열
		for (int i = 1; i <= N; i++) money[i] = Long.MAX_VALUE; // 누적 수금액 초기화
		money[A] = 0; // 시작 교차로 누적 수금액 초기화
		
		PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Long.compare(o1.cost, o2.cost)); // Node를 담을 pq
		pq.add(new Node(A, 0L)); // 시작 교차로 add
		
		while (!pq.isEmpty()) {
			Node cur = pq.poll(); // 현재 교차로
			
			if (cur.cost > money[cur.to]) continue; // 누적 수금액이 기존의 수금액보다 크면 continue
			
			if (cur.to == B) return true; // 도착 교차로에 도착했으면 유효 반환
			
			for (Node nxt : cross[cur.to]) {
				if (nxt.cost > limit) continue; // 제한된 금액을 넘으면 continue
				
				long nxtCost = cur.cost + nxt.cost; // 다음 누적 금액
				
				if (nxtCost <= C && nxtCost < money[nxt.to]) {
					money[nxt.to] = nxtCost; // 누적 수금액 갱신
					pq.add(new Node(nxt.to, nxtCost)); // 다음 교차로 add
				}
			}
		}
		
		return money[B] <= C; // 유효 여부 반환
	} // dijkstra 종료
	
	public static void main(String[] args) throws IOException {
		// 입력을 위한 객체 선언
		BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken()); // 교차로의 개수
		int M = Integer.parseInt(st.nextToken()); // 골목의 개수
		A = Integer.parseInt(st.nextToken()); // 시작 교차로
		B = Integer.parseInt(st.nextToken()); // 도착 교차로
		C = Long.parseLong(st.nextToken()); // 가진 돈
		
		cross = new ArrayList[N + 1]; // 교차로 배열
		for (int i = 1; i <= N; i++) cross[i] = new ArrayList<>(); // 교차로 새엇ㅇ
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int u = Integer.parseInt(st.nextToken()); // 연결된 교차로 1
			int v = Integer.parseInt(st.nextToken()); // 연결된 교차로 2
			long cost = Long.parseLong(st.nextToken()); // 수금액
			
			// 교차로 연결하기
			cross[v].add(new Node(u, cost));
			cross[u].add(new Node(v, cost));
		}
		
		// 버퍼 닫기
		br.close();
		
		int minOfMax = -1; // 수금액 최댓값의 최솟값
		int low = 1; // 최소 금액
		int high = (int) INIT; // 최대 금액
		while (low <= high) {
			int mid = (low + high) / 2; // 현재 제한 값
			if (dijkstra(mid)) {
				minOfMax = mid; // 수금액 갱신
				high = mid - 1; // 상한치 감소
			} else low = mid + 1; // 하한치 증가
		}
		
		// 결과값 출력하기
		System.out.print(minOfMax);
	} // main 종료
} // Main 종료
