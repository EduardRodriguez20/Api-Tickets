package com.edan.api_tickets.queue;

import lombok.Getter;

import java.util.LinkedList;
import java.util.Queue;

public class RequestQueue extends Thread{
    @Getter
    private static final RequestQueue instance = new RequestQueue();
    private final Queue<Request> queue;
    private RequestQueue() {
        queue = new LinkedList<>();
    }

    public synchronized void addQueue(Request request) {
        queue.add(request);
    }
    public synchronized Request pollQueue() {
        return queue.poll();
    }

    @Override
    public void run() {
        while (true){
            processRequest();
            System.out.println("xd");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void processRequest(){
        while (!queue.isEmpty()){
            Request request = queue.poll();
            if (request!= null){

            }
        }
    }




}
