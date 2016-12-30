/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package humanwar;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.IOException;
import java.io.FileWriter;
import java.io.Writer;
import java.util.Scanner;
/**
 *
 * @author mahmouddawlatly
 */
public class Board extends JFrame implements ActionListener {
       //Human and Robot Objects
       private Robot r = new Robot();
       private Human h = new Human();
       
       //JPanel's placement
       private JPanel mp = new JPanel(new BorderLayout());
       private JPanel np = new JPanel();
       private JPanel sp = new JPanel(new GridLayout(2,5));
       private JPanel ep = new JPanel(new GridLayout(0,1));
       private JPanel wp = new JPanel(new GridLayout(0,1));
       private JPanel cp = new JPanel(new GridLayout(10,10));
       
       //Buttons on the board
       private JButton sd = new JButton(" Shoot Down ");
       private JButton su = new JButton(" Shoot Up ");
       private JButton confirm = new JButton(" Confirm ");
       private JButton md = new JButton("Move Down");
       private JButton mu = new JButton("Move Up");
       private JButton sl = new JButton(" Shoot Left ");
       private JButton sr = new JButton(" Shoot Right ");
       private JButton save = new JButton("");
       private JButton mr = new JButton(" Move Right ");
       private JButton ml = new JButton(" Move Left ");
       private JButton ret = new JButton("Retry");
       private JButton close = new JButton("Close");
       
       //human's number of moves
       private int counter = 0;
       
       //human's number of tries
       private int tries = 3;
       //variable for the turn
       private boolean turn = true;
       
       //to check win or loss conditions
       private boolean done = false;
       private boolean win = false;
       private boolean lose = false;
       private boolean confirmpress = true;
       
       //JLabels for the display
       private JLabel[][] board = new JLabel[10][10];
       private JLabel title = new JLabel("Welcome to Tank Wars");
       private JLabel[] humanMoves = new JLabel[18];//ms
       private JLabel[] robotMoves = new JLabel[18];//rs
       
       //robot's array of moves
       private int[] robotIter = new int[18];
       
       //Dialogs for win or lose conditions
       private JDialog d = new JDialog(this);
       private JDialog notfinished = new JDialog(this);
       private JDialog winDialog = new JDialog(this);
       private JDialog loseDialog = new JDialog(this);
       private JDialog killed = new JDialog(this);
       private JLabel retry = new JLabel("You lost, Would you like to try again? You have "+tries+" tries left." );
       private JLabel error = new JLabel("  Please complete the human's moves");
       private JLabel won = new JLabel("  You won.");
       
       //robot's iterator
       private Iterator iter = r.getIterator();
       private Font titleF = new Font("Sans Serif",Font.BOLD,30);
       
    public Board(){
       super("Human War");
       setLocation(250,300);
       setVisible(true);
       setSize(800,780);
       super.setResizable(true);
       title.setFont(titleF);
       h.setPosition(9, 9);
       r.setPosX(0);
       r.setPosY(0);
       //to display the human's list
       for(int i = 0 ; i < 18 ; i++, iter.hasNext()){
        robotMoves[i] = new JLabel();
        robotMoves[i].setText(i+1+".                        ");
        wp.add(robotMoves[i]);
       }
       //to display the robot's list
       for(int i = 0 ; i <18 ; i++){
        humanMoves[i] = new JLabel();  
        humanMoves[i].setText(i+1+".                        ");
        ep.add(humanMoves[i]);
       }
       

       //Settings for the dialogs
       d.setTitle("Error");
       d.setSize(300, 300);
       d.setLocation(500, 200);
       d.add(error);
       d.setVisible(false);
       d.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
       
       winDialog.setTitle("Congratulations");
       winDialog.setSize(300, 300);
       winDialog.setLocation(500, 200);
       winDialog.setVisible(false);
       winDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
       winDialog.add(won);
       
       loseDialog.setTitle("Unlucky");
       loseDialog.setSize(300, 300);
       loseDialog.setLocation(500, 200);
       loseDialog.setVisible(false);
       loseDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
       
       killed.setTitle("Killed");
       killed.setSize(300, 300);
       killed.setLocation(500, 200);
       killed.setVisible(false);
       killed.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);       
       
       notfinished.setLayout(new FlowLayout());
       notfinished.setTitle("You lost");
       notfinished.setSize(400, 300);
       notfinished.setLocation(500, 200);
       notfinished.add(retry);
       notfinished.add(ret);
       notfinished.add(close);
       notfinished.setVisible(false);
       notfinished.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
       //Until here
       
       //Adding components
       cp.setPreferredSize(new Dimension(350,350));
       sp.add(sd);
       sp.add(su);
       sp.add(confirm);
       sp.add(mu);
       sp.add(md);
       sp.add(sl);
       sp.add(sr);
       sp.add(save);
       sp.add(mr);
       sp.add(ml);
       np.add(title);
       
       mp.add(np,BorderLayout.NORTH);
       mp.add(sp,BorderLayout.SOUTH);
       mp.add(ep,BorderLayout.EAST);
       mp.add(wp,BorderLayout.WEST);
       mp.add(cp,BorderLayout.CENTER);
       super.add(mp);
       //Until here
       
       //Laying the board and the inital positions of the players
       for(int i = 0; i < 10;i++){
           for(int j = 0; j < 10; j++){
               if(i == 9 && j == 9){
                 board[i][j] = new JLabel(new ImageIcon("tank1L.png")); 
                 board[i][j].setSize(30,30);
                 cp.add(board[i][j]);
                 
               }else if(i == 0 && j == 0){
                 board[i][j] = new JLabel(new ImageIcon("tank2R.png")); 
                 board[i][j].setSize(30,30);
                 cp.add(board[i][j]);
               }else{
                 board[i][j] = new JLabel(new ImageIcon("Ali5.png"));
                 board[i][j].setSize(30,30);
                 cp.add(board[i][j]);
               }
           }
       }
       //Until here
       
        //Adding action listeners
        close.addActionListener(this);
        ret.addActionListener(this);
        sd.addActionListener(this);
        su.addActionListener(this);
        mr.addActionListener(this);
        md.addActionListener(this);
        mu.addActionListener(this);
        sl.addActionListener(this);
        sr.addActionListener(this);
        ml.addActionListener(this);
        save.addActionListener(this);
        confirm.addActionListener(this);
    }

       @Override
    public void actionPerformed(ActionEvent evt){
      //retry button which resets the board to its inital position
      if(evt.getSource() == ret){
          for(int i = 0; i< 10;i++){
              for(int j = 0; j < 10;j++){
                  if(i == 0 && j==0){
                      board[i][j].setIcon(new ImageIcon("tank2R.png"));
                  }else if(i == 9 && j == 9){
                      board[i][j].setIcon(new ImageIcon("tank1L.png"));
                  }else{
                      board[i][j].setIcon(new ImageIcon("Ali5.png"));
                  }
              }
          }
          for(int i = 0; i < 18; i++){
              humanMoves[i].setText(i+1+".                        ");
          }
          h.setPosition(9, 9);
          r.setPosX(0);
          r.setPosY(0);
          confirmpress = false;
          done = false;
          win = false;
          lose = false;
          counter = 0;
          tries--;
          retry.setText("You lost, Would you like to try again? You have "+tries+" tries left." );
          if(tries == -1){
              System.exit(0);
          }
          notfinished.setVisible(false);
      }
      //close button which exits the game
      if(evt.getSource() == close){
          System.exit(0);
      }
      //Move Up button which prints the command in the human's list of moves
      if(evt.getSource() == mu) 
        {
           if(counter == 18){
               return;
           }else{
               humanMoves[counter].setText(counter+1+". "+ mu.getActionCommand());
               counter++;
           }
        
        }
      //Shoot Down button which prints the command in the human's list of moves
      if(evt.getSource() == sd) 
        {
           if(counter == 18){
               return;
           }else{
               humanMoves[counter].setText(counter+1+". "+ sd.getActionCommand());
               counter++;
           }
        
        }
      //Shoot Up button which prints the command in the human's list of moves
      if(evt.getSource() == su) 
        {
           if(counter == 18){
               return;
           }else{
               humanMoves[counter].setText(counter+1+". "+ su.getActionCommand());
               counter++;
           }
        
        }
      //Move Right button which prints the command in the human's list of moves
      if(evt.getSource() == mr) 
        {
           if(counter == 18){
               return;
           }else{
               humanMoves[counter].setText(counter+1+". "+ mr.getActionCommand());
               counter++;
           }
        
        }
      //Move Down button which prints the command in the human's list of moves
      if(evt.getSource() == md) 
        {
           if(counter == 18){
               return;
           }else{
               humanMoves[counter].setText(counter+1+". "+ md.getActionCommand());
               counter++;
           }
        
        }
      //Shoot Left button which prints the command in the human's list of moves
      if(evt.getSource() == sl) 
      {
           if(counter == 18){
               return;
           }else{
               humanMoves[counter].setText(counter+1+". "+ sl.getActionCommand());
               counter++;
           }
        
        }
      //Shoot Right button which prints the command in the human's list of moves
      if(evt.getSource() == sr) 
        {
           if(counter == 18){
               return;
           }else{
               humanMoves[counter].setText(counter+1+". "+ sr.getActionCommand());
               counter++;
           }
        
        }
      //Move Left button which prints the command in the human's list of moves
      if(evt.getSource() == ml) 
        {
           if(counter == 18){
               return;
           }else{
               humanMoves[counter].setText(counter+1+". "+ ml.getActionCommand());
               counter++;
           }
        
        }
      // Confirm button which executes the moves
      if(evt.getSource() == confirm) {
          if(counter == 18 && confirmpress){
            for(int i = 0 ; i < 18 ; i++, iter.hasNext()){
             int move = (int) iter.next();
             if(move == 1){
             robotMoves[i].setText(i+1+".Move Up");
             robotIter[i] = 1;
             }
             if(move == 2){
             robotMoves[i].setText(i+1+".Move Down");
             robotIter[i] = 2;
             }
             if(move == 3){
             robotMoves[i].setText(i+1+".Move Right");
             robotIter[i] = 3;
             }
             if(move == 4){
             robotMoves[i].setText(i+1+".Move Left");
             robotIter[i] = 4;
             }
             if(move == 5){
             robotMoves[i].setText(i+1+".Shoot Up");
             robotIter[i] = 5;
             }
             if(move == 6){
             robotMoves[i].setText(i+1+".Shoot Down");
             robotIter[i] = 6;
             }
             if(move == 7){
             robotMoves[i].setText(i+1+".Shoot Right");
             robotIter[i] = 7;
             }
             if(move == 8){
             robotMoves[i].setText(i+1+".Shoot Left");
             robotIter[i] = 8;
             }
             confirmpress = false;
            }
            
          }//if confirm button has been pressed after completion of moves, do nothing and execute the game
          else if (confirmpress == false){
          }//The human's moves haven't been completed
          else{
              d.setVisible(true);
          }
          
          for(int i=0,j=0;i<=18 && j<18 && counter == 18 && !done;i++,j++){
              //human's turn
              if(turn == true){
                  //Move up
                  if(humanMoves[i].getText().equals(Integer.toString(i+1)+". "+ mu.getActionCommand()) ){
                      if(h.getPosX() < 10 && h.getPosY() < 10 && h.getPosX() >= 0 && h.getPosY() >= 0){
                          
                        h.moveUp();

                        if(h.getPosX() == -1 || h.getPosX() == 0 ){
                          h.setPosition(0, h.getPosY());
                          board[h.getPosX()][h.getPosY()].setIcon(new ImageIcon("tank1U.png"));
                          board[h.getPosX()+1][h.getPosY()].setIcon(new ImageIcon("Ali5.png"));
                        }//checks for crash condition
                        if(board[h.getPosX()][h.getPosY()] == board[r.getPosX()][r.getPosY()]){
                            done = true;
                            lose = true;
                        }//until here
                        else{
                            board[h.getPosX()][h.getPosY()].setIcon(new ImageIcon("tank1U.png"));
                            board[h.getPosX()+1][h.getPosY()].setIcon(new ImageIcon("Ali5.png"));
                        }
                    }
                      turn = false;//change to robot's turn
                    } //Move down
                  else if(humanMoves[i].getText().equals(Integer.toString(i+1)+". "+ md.getActionCommand()) ){

                        if(h.getPosX() < 10 && h.getPosY() < 10 && h.getPosX() >= 0 && h.getPosY() >= 0){
                        h.moveDown();

                        if(h.getPosX() == 10 || h.getPosX() == 9){
                            h.setPosition(9, h.getPosY());
                            board[h.getPosX()][h.getPosY()].setIcon(new ImageIcon("tank1D.png"));
                            board[h.getPosX()-1][h.getPosY()].setIcon(new ImageIcon("Ali5.png"));
                        }//Checks for crash condition
                        if(board[h.getPosX()][h.getPosY()] == board[r.getPosX()][r.getPosY()]){
                            done = true;
                            lose = true;
                        }//Until here
                        else{
                            board[h.getPosX()][h.getPosY()].setIcon(new ImageIcon("tank1D.png"));
                            board[h.getPosX()-1][h.getPosY()].setIcon(new ImageIcon("Ali5.png"));
                            
                        }
                        }
                        turn = false;
                    }
                  
                  else if(humanMoves[i].getText().equals(Integer.toString(i+1)+". "+ mr.getActionCommand()) ){
                       //Move right  
                      if(h.getPosX() < 10 && h.getPosY() < 10 && h.getPosX() >= 0 && h.getPosY() >= 0){
                      h.moveRight();
                      
                       if(h.getPosY() == 10  || h.getPosY() == 9){
                           h.setPosition(h.getPosX(), 9);
                           board[h.getPosX()][h.getPosY()].setIcon(new ImageIcon("tank1R.png"));
                           board[h.getPosX()][h.getPosY()-1].setIcon(new ImageIcon("Ali5.png"));
                       }//Checks for crash conditon
                       if(board[h.getPosX()][h.getPosY()] == board[r.getPosX()][r.getPosY()]){
                          done = true;
                          lose = true;
                      }//Until here
                       else{
                        board[h.getPosX()][h.getPosY()].setIcon(new ImageIcon("tank1R.png"));
                        if(h.getPosX() != 9 &&  h.getPosY() != 9 ){
                          board[h.getPosX()][h.getPosY()-1].setIcon(new ImageIcon("Ali5.png"));
                        }

                      }

                      }
                      turn = false;
                    }
                      else if(humanMoves[i].getText().equals(Integer.toString(i+1)+". "+ ml.getActionCommand()) ){
                        //Move left 
                      if(h.getPosX() < 10 && h.getPosY() < 10 && h.getPosX() >= 0 && h.getPosY() >= 0){
                      h.moveLeft();
                      
                       if(h.getPosY() == -1 || h.getPosY() == 0){
                           h.setPosition(h.getPosX(), 0);
                           board[h.getPosX()][h.getPosY()].setIcon(new ImageIcon("tank1L.png"));
                           board[h.getPosX()][h.getPosY()+1].setIcon(new ImageIcon("Ali5.png"));
                       }//check for crash conditions
                       if(board[h.getPosX()][h.getPosY()] == board[r.getPosX()][r.getPosY()]){
                            done = true;
                            lose = true;
                        }//Until here
                       else{
                            board[h.getPosX()][h.getPosY()].setIcon(new ImageIcon("tank1L.png"));
                            board[h.getPosX()][h.getPosY()+1].setIcon(new ImageIcon("Ali5.png"));

                        }
                      }
                      turn = false;
                    }
                  else if(humanMoves[i].getText().equals(Integer.toString(i+1)+". "+ sl.getActionCommand()) ){
                        //Shoot left 
                      if(h.getPosX() < 10 && h.getPosY() < 10 && h.getPosX() >= 0 && h.getPosY() >= 0){
                      board[h.getPosX()][h.getPosY()].setIcon(new ImageIcon("tank1SL.png"));
                        if(h.getPosY() < 1){
                            
                        }//checks for win condition
                        else if(board[h.getPosX()][h.getPosY()-1] == board[r.getPosX()][r.getPosY()]){
                            done = true;
                            win = true;
                        }//Until here
                      }
                      turn = false;
                    }
                      else if(humanMoves[i].getText().equals(Integer.toString(i+1)+". "+ sr.getActionCommand()) ){
                         //Shoot right
                      if(h.getPosX() < 10 && h.getPosY() < 10 && h.getPosX() >= 0 && h.getPosY() >= 0){
                          
                        board[h.getPosX()][h.getPosY()].setIcon(new ImageIcon("tank1SR.png"));
                        if(h.getPosY() > 8){
                            
                        }//Check for win condition
                        else if(board[h.getPosX()][h.getPosY()+1] == board[r.getPosX()][r.getPosY()]){
                            done = true;
                            win = true;
                        }//Until here
                        }
                      turn = false;
                    }
                  else if(humanMoves[i].getText().equals(Integer.toString(i+1)+". "+ su.getActionCommand()) ){
                         //Shoot Up
                      if(h.getPosX() < 10 && h.getPosY() < 10 && h.getPosX() >= 0 && h.getPosY() >= 0){
                        board[h.getPosX()][h.getPosY()].setIcon(new ImageIcon("tank1SU.png"));
                        if(h.getPosX() < 1){
                            
                        }//Check for win condition
                        else if(board[h.getPosX()-1][h.getPosY()] == board[r.getPosX()][r.getPosY()]){
                            done = true;
                            win = true;
                        }//Until here
                      }
                      turn = false;
                    }
                  else if(humanMoves[i].getText().equals(Integer.toString(i+1)+". "+ sd.getActionCommand()) ){
                      //Shoot Down   
                      if(h.getPosX() < 10 && h.getPosY() < 10 && h.getPosX() >= 0 && h.getPosY() >= 0){
                        board[h.getPosX()][h.getPosY()].setIcon(new ImageIcon("tank1SD.png"));
                        if(h.getPosX() > 8){
                            
                        }//Check for win condiition
                        else if(board[h.getPosX()+1][h.getPosY()] == board[r.getPosX()][r.getPosY()]){
                            done = true;
                            win = true;
                        }//Until here
                      }
                      turn = false;
                    }
                  j--;
              }else{
                  //Robot's turn
                  
                  //Move Up
                if(robotIter[j] == 1){
                    if(r.getPosX() < 10 && r.getPosY() < 10 && r.getPosX() >= 0 && r.getPosY() >= 0){

                      r.moveUp();

                      if(r.getPosX() == -1 ){
                        r.setPosX(0);
                        r.setPosY(r.getPosY());
                     }
                      if(board[r.getPosX()][r.getPosY()] == board[h.getPosX()][h.getPosY()]){
                          done = true;
                          lose = true;
                      }else{
                          board[r.getPosX()][r.getPosY()].setIcon(new ImageIcon("tank2U.png"));
                          board[r.getPosX()+1][r.getPosY()].setIcon(new ImageIcon("Ali5.png"));
                      }
                      
                      
                      }
                    turn = true;
                }
                //Move Down
                if(robotIter[j] == 2){
                    if(r.getPosX() < 10 && r.getPosY() < 10 && r.getPosX() >= 0 && r.getPosY() >= 0){
                    r.moveDown();

                    if(r.getPosX() == 10 ){
                      r.setPosX(9);
                      r.setPosY(r.getPosY());
                    }

                    if(board[r.getPosX()][r.getPosY()] == board[h.getPosX()][h.getPosY()]){
                        done = true;
                        lose = true;
                    }else{
                        board[r.getPosX()][r.getPosY()].setIcon(new ImageIcon("tank2D.png"));
                        board[r.getPosX()-1][r.getPosY()].setIcon(new ImageIcon("Ali5.png"));
                    }
                    }
                    turn = true;
                }
                //Move Right
                if(robotIter[j] == 3){
                    if(r.getPosX() < 10 && r.getPosY() < 10 && r.getPosX() >= 0 && r.getPosY() >= 0){
                    r.moveRight();

                     if(r.getPosY() == 10 ){
                         r.setPosX(r.getPosX());
                         r.setPosY(9);
                     }
                     
                    if(board[r.getPosX()][r.getPosY()] == board[h.getPosX()][h.getPosY()]){
                        done = true;
                        lose = true;
                    }else{
                        board[r.getPosX()][r.getPosY()].setIcon(new ImageIcon("tank2R.png"));
                        if(r.getPosX() != 9 &&  r.getPosY() != 9 ){
                            board[r.getPosX()][r.getPosY()-1].setIcon(new ImageIcon("Ali5.png"));
                        }
                    }
                    }
                    turn = true;
                }
                //Move Left
                if(robotIter[j] == 4){
                    if(r.getPosX() < 10 && r.getPosY() < 10 && r.getPosX() >= 0 && r.getPosY() >= 0){
                    r.moveLeft();

                     if(r.getPosY() == -1 ){
                         r.setPosX(r.getPosX());
                         r.setPosY(0);
                     }
                     
                    if(board[r.getPosX()][r.getPosY()] == board[h.getPosX()][h.getPosY()]){
                        done = true;
                        lose = true;
                    }else{
                        board[r.getPosX()][r.getPosY()].setIcon(new ImageIcon("tank2L.png"));
                        board[r.getPosX()][r.getPosY()+1].setIcon(new ImageIcon("Ali5.png"));
                    } 
                    }
                    turn = true;
                }//Shoot Up
                if(robotIter[j] == 5){
                    if(r.getPosX() < 10 && r.getPosY() < 10 && r.getPosX() >= 0 && r.getPosY() >= 0){
                      board[r.getPosX()][r.getPosY()].setIcon(new ImageIcon("tank2SU.png"));
                        if(r.getPosX() < 1){
                            
                        }else if(board[r.getPosX()-1][r.getPosY()] == board[h.getPosX()][h.getPosY()]){
                            done = true;
                            lose = true;
                        }
                      }
                    turn = true;
                }//Shoot Down
                if(robotIter[j] == 6){
                    if(r.getPosX() < 10 && r.getPosY() < 10 && r.getPosX() >= 0 && r.getPosY() >= 0){
                      board[r.getPosX()][r.getPosY()].setIcon(new ImageIcon("tank2SD.png"));
                        if(r.getPosX() > 8){
                            
                        }else if(board[r.getPosX()+1][r.getPosY()] == board[h.getPosX()][h.getPosY()]){
                            done = true;
                            lose = true;
                        }
                      }
                    turn = true;
                }//Shoot Right
                if(robotIter[j] == 7){
                    if(r.getPosX() < 10 && r.getPosY() < 10 && r.getPosX() >= 0 && r.getPosY() >= 0){
                      board[r.getPosX()][r.getPosY()].setIcon(new ImageIcon("tank2SR.png"));
                        if(r.getPosY() > 8){

                        }else if(board[r.getPosX()][r.getPosY()+1] == board[h.getPosX()][h.getPosY()]){
                        done = true;
                        lose = true;
                    }
                      }
                    turn = true;
                }//Shoot Left
                if(robotIter[j] == 8){
                    if(r.getPosX() < 10 && r.getPosY() < 10 && r.getPosX() >= 0 && r.getPosY() >= 0){
                    board[r.getPosX()][r.getPosY()].setIcon(new ImageIcon("tank2SL.png"));
                    if(r.getPosY() < 1){

                    }else if(board[r.getPosX()][r.getPosY()-1] == board[h.getPosX()][h.getPosY()]){
                        done = true;
                        lose = true;
                    }
                    }
                    turn = true;
                }
                i--; 
              }
          }
          if(!done && !confirmpress){
              notfinished.setVisible(true);
          }else if(done && win){
              winDialog.setVisible(true);
          }else if(done && lose){
              killed.setVisible(true);
          }else if(done){
              notfinished.setVisible(true);
          }

        }
      
    }
    
}
