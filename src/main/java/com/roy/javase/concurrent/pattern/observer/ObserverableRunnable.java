package com.roy.javase.concurrent.pattern.observer;

/**
 * Subject
 * @Author: Roy
 * @Date: 2019/3/13 11:17
 */
public abstract class ObserverableRunnable implements Runnable {

    protected final LifeCycleListener listener;

    public ObserverableRunnable(final LifeCycleListener listener){
        this.listener = listener;
    }

    protected void notifyChange(final RunnableEvent event){
        listener.onEvent(event);
    }

    public enum RunnableState{
        RUNNING,ERROR,DONE;
    }

    public static class RunnableEvent{

        private final RunnableState state;
        private final Thread thread;
        private final Throwable cause;


        public RunnableEvent(RunnableState state, Thread thread, Throwable cause) {
            this.state = state;
            this.thread = thread;
            this.cause = cause;
        }

        public RunnableState getState() {
            return state;
        }

        public Thread getThread() {
            return thread;
        }

        public Throwable getCause() {
            return cause;
        }
    }
}
