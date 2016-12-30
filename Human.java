/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package humanwar;

/**
 *
 * @author mahmouddawlatly
 */
public class Human{
    private int move;
    private int tries;
    private int posX;
    private int posY;
    
    //Constructor
    Human(){
        move = 0;
        tries = 0;
        posX = 9;
        posY = 9;
    }
  
    public int getPosX(){
        return posX;
    }
    public int getPosY(){
        return posY;
    }
    public void setPosition(int x,int y){
        posX = x;
        posY = y;
        
    }
    
    public void moveUp(){
       if(posX < 10 && posY < 10 && posX >= 0 && posY >= 0)
       {
            posX = posX - 1;
       }
        
        
        
    }
    public void moveDown()
    {
        if(posX < 10 && posY < 10 && posX >= 0 && posY >= 0)
       {
            posX = posX + 1;
       }
        
    }
    public void moveRight()
    {
         if(posX < 10 && posY < 10 && posX >= 0 && posY >= 0)
       {
            posY = posY + 1;
       }
    }
    public void moveLeft()
    {
         if(posX < 10 && posY < 10 && posX >= 0 && posY >= 0)
       {
            posY = posY - 1;
       }
    }
}
