/*
 * 리트코드 155번 : Min Stack
 * https://leetcode.com/problems/min-stack/description/
 * 난이도 : Medium
 * 풀이 날짜 : 2026-01-14
 * 간단 설명 : 스택 기본 기능과 최솟값을 구하는 메서드를 O(1)으로 구현하는 문제
 */

class MinStack {
    int[] stack; // stack 배열
    int[] min; // 최솟값 배열
    int top; // stack 배열 top
    
    public MinStack() {
        stack = new int[30001]; // stack 배열 생성
        min = new int[30001]; // 최솟값 배열 생성
        top = 0; // stack top 초기화
        min[0] = Integer.MAX_VALUE; // min 배열 초기값 초기화
    }
    
    public void push(int val) {
        stack[++top] = val; // val을 stack에 push
        min[top] = Math.min(val, min[top - 1]); // 최솟값 갱신
    }
    
    public void pop() {
        top--; // stack 감소
    }
    
    public int top() {
        return stack[top]; // top값 반환
    }
    
    public int getMin() {
        return min[top]; // 최솟값 반환
    }
}