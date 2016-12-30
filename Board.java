/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package humanwar;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 *
 * @author mahmouddawlatly
 */
public class Board extends JFrame implements ActionListener {
       JPanel mp = new JPanel(new BorderLayout());
       JPanel np = new JPanel();
       JPanel sp = new JPanel();
       JPanel ep = new JPanel(new GridLayout(2,4));
       JPanel wp = new JPanel(new GridLayout(2,4));
       JPanel cp = new JPanel(new GridLayout(10,10));
       
       JButton b1 = new JButton("aaa");
       JButton b2 = new JButton("button1");
       JButton b3 = new JButton("button1");
       JButton b4 = new JButton("button1");
       
       JLabel[][] board = new JLabel[10][10];
       JLabel title = new JLabel("Welcome to Human Wars");

       Font titleF = new Font("Sans Serif",Font.BOLD,30);
       
    public Board(){
       super("Human War");
       setVisible(true);
       setSize(1100,1100);
       
       title.setFont(titleF);
       

       b1.addActionListener(this);
       wp.add(b1);
       wp.add(b2);
       wp.add(b3);
       ep.add(b4);
       np.add(title);
       ep.add(b3);

       mp.add(np,BorderLayout.NORTH);
       mp.add(sp,BorderLayout.SOUTH);
       mp.add(ep,BorderLayout.EAST);
       mp.add(wp,BorderLayout.WEST);
       mp.add(cp,BorderLayout.CENTER);
       super.add(mp);
       
       
       //JLabel[][] boar = new JLabel[10][10];
       for(int i = 0; i < 10;i++){
           for(int j = 0; j < 10; j++){
                //board[i][j].setLayout(new GridLayout(10,10));
                //board[i][j].add((new JLabel(new ImageIcon("square.png"))));
                //  JLabel m = new JLabel("HI!!!");
                // m.setSize(200,200);
               
                 board[i][j] = new JLabel(new ImageIcon("rsz_rsz_white1.png"));
                 board[i][j].setSize(30,30);
                 cp.add(board[i][j]);
               
           }
       }
       
       /*for(int i = 0 ; i < 9 ; i++)
       {
       Button ne = new Button("1");
       //JLabel ne = new JLabel("1");
       cp.setLayout(new GridLayout(3,3));
       cp.add(ne);
      
       }
       for(int i = 0 ; i < 50 ; i++)
       {
       JLabel ne = new JLabel("1");
       
       sp.add(ne);
       }*/
       
       
    }
    @Override
    public void actionPerformed(ActionEvent evt){
        /*if (evt.getSource() == b1){
            board[9][9].setIcon(new ImageIcon("addition2.png"));
            b5.setText("eee");
            
        }*/
    }
    
}
