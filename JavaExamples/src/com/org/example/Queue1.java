package com.org.example;

public class Queue1 {
    
  private int front;
  private int rear;
  private int maxSize;
  private int arr[];
    
  Queue1(int maxSize) {
      this.front = 0;
      this.rear = -1;
      this.maxSize = maxSize;
      this.arr = new int[this.maxSize];
  }
    
  public boolean isFull() {
      if (rear == maxSize - 1) {
          return true;
      }
      return false;
  }
          
  public boolean enqueue(int data) {
      if (isFull()) {
          return false;
      } else {
          arr[++rear] = data;
          return true;
      }
  }

  public void display() {
      if(isEmpty())
          System.out.println("Queue is empty!");
      else {
          for (int index = front; index <= rear; index++) {
              System.out.println(arr[index]);
          }
      }
  }
          
  public boolean isEmpty() {
      if (front > rear)
          return true;
      return false;
  }
          
  public int dequeue() {
      if (isEmpty()) {
          return Integer.MIN_VALUE;
      } else {
          int data = arr[this.front];
          arr[front++] = Integer.MIN_VALUE;
          return data;
      }
  }

  public int getMaxSize() {
      return maxSize;
  }
}


class Tester {

  public static void main(String[] args) {
          
      Queue queue = new Queue(7);
      queue.enqueue(13983);
      queue.enqueue(10080);
      queue.enqueue(7113);
      queue.enqueue(2520);
      queue.enqueue(2500);

      Queue outputQueue = findEvenlyDivisibleNumbers(queue);
          
      System.out.println("Evenly divisible numbers");
      outputQueue.display();

  }
    
  public static Queue findEvenlyDivisibleNumbers(Queue queue) {
      //Implement your code here and change the return value accordingly
      return null;
  }
}

