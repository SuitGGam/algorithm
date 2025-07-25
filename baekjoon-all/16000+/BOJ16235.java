/*
 * 백준 16235번 : 나무 재테크
 * https://www.acmicpc.net/problem/16235
 * 난이도 : 골드 3
 * 풀이 날짜 : 2025-07-25
 * 간단 설명 : 나무 재테크를 K년 했을 때 몇 개의 나무가 있는지 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayDeque;

public class BOJ16235 {
    // 입력을 위한 객체 선언
    static BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    
    static int N; // 땅의 크기
    
    static int[][] land; // 나무를 심을 땅
    static int[][] nourishment; // 겨울마다 추가할 양분을 가진 땅
    
    static class Tree {
        int r; // r 좌표
        int c; // c 좌표
        int age; // 나무의 나이
        
        // 나무 생성
        Tree(int r, int c, int age) {
            this.r = r;
            this.c = c;
            this.age = age;
        }
    }
    
    static ArrayDeque<Tree> aliveTree; // 살아있는 나무를 담을 덱
    static ArrayDeque<Tree> deadTree;  // 죽은 나무를 담을 덱
    static ArrayDeque<Tree> fallTree;  // 가을에 생긴 나무를 담을 임시덱
    
    // 봄
    static void spring() {
        // 기존 나무의 개수를 미리 저장해야
        // 새로 생긴 나무가 기존의 덱에 들어가도 영향 X
        int aliveTreeSize = aliveTree.size(); // 살아있는 나무의 개수
        
        // 가을에 생긴 신생 나무 먼저 양분 주기
        int fallTreeSize  = fallTree.size();  // 가을에 생긴 나무 개수
        for (int i = 0; i < fallTreeSize; i++) {
            Tree tree = fallTree.poll();
            
            if (tree.age <= land[tree.r][tree.c]) { // 나무가 나이를 먹을 수 있는 경우
                land[tree.r][tree.c] -= tree.age; // 양분 감소
                tree.age++; // 나무의 나이 증가
                aliveTree.add(tree); // 살아있는 나무 덱에 넣기
            } else { // 나무가 나이를 못 먹고 죽는 경우
                deadTree.add(tree); // 죽은 나무 덱에 넣기
            }
        }
        
        for (int i = 0; i < aliveTreeSize; i++) {
            Tree tree = aliveTree.poll();
            
            if (tree.age <= land[tree.r][tree.c]) { // 나무가 나이를 먹을 수 있는 경우
                land[tree.r][tree.c] -= tree.age; // 양분 감소
                tree.age++; // 나무의 나이 증가
                aliveTree.add(tree); // 살아있는 나무 덱에 넣기
            } else { // 나무가 나이를 못 먹고 죽는 경우
                deadTree.add(tree); // 죽은 나무 덱에 넣기
            }
        }
    }
    
    // 여름
    static void summer() {
        int size = deadTree.size(); // 죽은 나무의 개수
        for (int i = 0; i < size; i++) {
            Tree tree = deadTree.poll();
            
            land[tree.r][tree.c] += tree.age / 2; // 땅에 죽은 나무의 양분 추가
        }
    }
    
    // 가을
    // 처음에 주어지는 나무는 모두 다른 칸에 주어짐(각 칸 최대 1개)
    // 가을에 새로 생성되는 나무는 나이가 1이니 따로 집합을 만들고 봄에 양분을 먼저 주면 됨
    static int[] dr = {-1, -1, -1, 0, 1, 1, 1, 0}; // 번식 r 좌표 - 왼쪽대각위부터 시계 방향
    static int[] dc = {-1, 0, 1, 1, 1, 0, -1, -1}; // 번식 c 좌표 - 왼쪽대각위부터 시계 방향
    static void fall() {
        int size = aliveTree.size(); // 살아있는 나무의 개수
        for (int i = 0; i < size; i++) {
            Tree tree = aliveTree.poll();
            
            // 나무의 나이가 5의 배수인 경우
            // 인접한 칸에 나이가 1인 나무 생성
            if (tree.age % 5 == 0) {
                for (int d = 0; d < 8; d++) {
                    int nr = tree.r + dr[d];
                    int nc = tree.c + dc[d];
                    
                    // 땅의 범위를 벗어나면 continue
                    if (nr == 0 || nc == 0 || nr > N || nc > N) continue;
                    
                    // 임시 나무 저장소에 저장
                    // aliveTree에 저장하면 이 과정에서 겹치는 현상 발생
                    fallTree.add(new Tree(nr, nc, 1)); // 나무 생성
                }
            }
            
            aliveTree.add(tree); // 다시 살아있는 나무 덱에 넣기
        }
    }
    
    // 겨울
    static void winter() {
        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= N; c++) {
                land[r][c] += nourishment[r][c]; // 땅에 양분 추가
            }
        }
    }
    
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken()); // 땅의 크기
        int M = Integer.parseInt(st.nextToken()); // 심은 나무의 수
        int K = Integer.parseInt(st.nextToken()); // 재테크할 시간
        
        land = new int[N + 1][N + 1]; // 땅의 크기 지정
        nourishment = new int[N + 1][N + 1]; // 땅의 크기 지정
        
        for (int r = 1; r <= N; r++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int c = 1; c <= N; c++) {
                land[r][c] = 5; // 땅이 처음 가지고 있는 양분
                nourishment[r][c] = Integer.parseInt(st.nextToken()); // 겨울마다 추가할 양분
            }
        }
        
        aliveTree = new ArrayDeque<>(); // 살아있는 나무를 담을 덱 생성
        deadTree = new ArrayDeque<>();  // 죽은 나무를 담을 덱 생성
        fallTree = new ArrayDeque<>();  // 죽은 나무를 담을 덱 생성
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int r = Integer.parseInt(st.nextToken()); // r 좌표
            int c = Integer.parseInt(st.nextToken()); // c 좌표
            int age = Integer.parseInt(st.nextToken()); // 나무의 나이
            aliveTree.add(new Tree(r, c, age)); // 살아있는 나무 덱에 추가
        }
        
        // 버퍼 닫기
        br.close();
        
        // 재테크 하기
        for (int i = 0; i < K; i++) {
            spring();
            summer();
            fall();
            winter();
        }
        
        // 살아있는 나무의 수
        int numOfTrees = aliveTree.size() + fallTree.size();
        
        // 결과값 출력하기
        System.out.print(numOfTrees);
    } // main 종료
} // Main 종료