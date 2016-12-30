/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package humanwar;
import java.util.Random;

/**
 *
 * @author mahmouddawlatly
 */
public class Robot implements Container{
    private int[] moves = {1,2,3,4,5,6,7,8};
    private int posX;
    private int posY;
    //variable for generating random moves
    private Random ry = new Random();
    
    //Constructor
    Robot(){
        posX = 0;
        posY = 0;
    }
    
    public void setPosX(int x){
        posX = x;
    }
    public void setPosY(int y){
        posY = y;
    }
  
    public int getPosX(){
        return posX;
    }
    public int getPosY(){
        return posY;
    }
    
    public void moveUp(){
       if(posX < 10 && posY < 10 && posX >= 0 && posY >= 0){
            posX = posX - 1;
       } 
    }
    public void moveDown(){
        if(posX < 10 && posY < 10 && posX >= 0 && posY >= 0){
            posX = posX + 1;
       }
        
    }
    public void moveRight(){
         if(posX < 10 && posY < 10 && posX >= 0 && posY >= 0){
            posY = posY + 1;
       }
    }
    public void moveLeft(){
         if(posX < 10 && posY < 10 && posX >= 0 && posY >= 0){
            posY = posY - 1;
       }
    }
    //Function that returns the iterator
   public Iterator getIterator() {
      return new MoveIterator();
   }
      //Concrete class that implements Iterator
      private class MoveIterator implements Iterator {

      private int counter;
      private int x; 
        
      //Checks for next move
      @Override
      public boolean hasNext() {
      
         if(counter < 18){
            return true;
         }
         return false;
      }
      
      //Go to next move
      @Override
      public Object next() {
      
         if(this.hasNext()){
            counter++;
            x = ry.nextInt(8) + 1;
            return x;
            
         }
         return null;
      }	
      
   }
}

