/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package td5;

import com.sun.jmx.snmp.tasks.Task;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 * @author ANA
 */
public class Td5 {
    
    public class TaskQueue {
    private static final int MAX_TASKS = 1000;

    private BlockingQueue<Task> queue 
        = new LinkedBlockingQueue<Task>(MAX_TASKS);

    public void putTask(Task r) throws InterruptedException { 
        queue.put(r);
    }

    public Task getTask() throws InterruptedException { 
        return queue.take();
    }
}
    
 
public class PlayerMatcher {
    private PlayerSource players;

    public PlayerMatcher(PlayerSource players) { 
        this.players = players; 
    }

    public void matchPlayers() throws InterruptedException { 
        Player playerOne = null, playerTwo;
         try {
             while (true) {
                 playerOne = playerTwo = null;
                 // Wait for two players to arrive and start a new game
                 playerOne = players.waitForPlayer(); // could throw IE
                 playerTwo = players.waitForPlayer(); // could throw IE
                 startNewGame(playerOne, playerTwo);
             }
         }
         catch (InterruptedException e) {  
             // If we got one player and were interrupted, put that player back
             if (playerOne != null) {
                 players.addFirst(playerOne);
             }
             // Then propagate the exception
             throw e;
         }
    }

        private void startNewGame(Player playerOne, Player playerTwo) {
            throw new UnsupportedOperationException("Not yet implemented");
        }
    }
   

}
