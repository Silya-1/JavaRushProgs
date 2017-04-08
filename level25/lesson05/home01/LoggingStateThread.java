package com.javarush.test.level25.lesson05.home01;

/**
 * Created by Силя on 21.07.2016.
 */
public class LoggingStateThread extends Thread
{
    private Thread thread;
    public LoggingStateThread(Thread target)
    {
        setDaemon(true);
        thread = target;
        //System.out.println(target.getState());
    }

    public void run()
    {
        State state = thread.getState();
        System.out.println(state);
        while (state != State.TERMINATED)
        {
            if (state != thread.getState())
            {
                state = thread.getState();
                System.out.println(state);
            }
        }
    }
}
