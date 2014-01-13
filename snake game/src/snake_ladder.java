/*
 * SnakeAndLadder.java
 *
 * Created on February 25, 2007, 12:46 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

//package myOwnPackage;

/**
 *
 * @author mayank
 */


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.Random;
import java.util.*;

import java.io.*;
import java.lang.Integer;

/* Snake_and_Ladder.java requires no other files. */
public class snake_ladder implements ActionListener{
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from thes
     * event-dispatching thread.
     */
    
    private Random generator;
    private JLabel[] blocks;
    private int[] ladstart,ladend,snakestart,snakeend;   
 	private int steps,position1=1,position2=1,position3=1,count=0,xcount=0,noofsnakes,noofladders,num,boardsize,numplayers,totalcount=0;
    private JFrame frame, configFrame;
    private JLabel label2,label3,player1,player2,player3,nump;
    private JTextField textfield1,textfield2,textfield3,textfield4;
    private JButton ok;
    String[] names;
 
  
   
   private void configureboard()throws IOException
   {
   	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
   	String ans;
   	int i;
   	System.out.println("ENTER THE NUMBER OF PLAYERS");
   	ans= br.readLine();
   	Integer numtemp = Integer.valueOf(ans);
   	numplayers = numtemp.intValue();
   	names = new String[numplayers];
    for(i=0;i<numplayers;i++)
    {
    
    System.out.println("ENTER THE NAME OF PLAYER "+(i+1));
    names[i]=br.readLine();
   }
	System.out.println("Do You want to configure the board?(y/n) ");
   	 ans= br.readLine();
   	if(ans.compareToIgnoreCase("y")==0)
   	{
   	  	System.out.println("Enter the size of the board (5-12)"); 
   		ans= br.readLine();
   	 numtemp = Integer.valueOf(ans);
   		num = numtemp.intValue();
   	    boardsize=num*num;
   		System.out.println("Do You want to configure the position of snakes and ladder?(y/n) ");
  	    ans= br.readLine();
   	   	if(ans.compareToIgnoreCase("y")==0) 	
   			{
   		  		i=0;
   		 		System.out.println("Enter the no of snakes/ladders");
   		 		ans= br.readLine();
   		 		numtemp = Integer.valueOf(ans);
   		 		noofladders = noofsnakes = numtemp.intValue();
          		initialize_snake_ladder();
    		}
   	    else
   	    	initialize_standard_snake_ladder();
   	 }
   	else
   		initialize_standard_board();
   	}	
     	
void initialize_array()
  {
  	snakestart=new int[noofsnakes];
    snakeend=new int[noofsnakes];
    ladstart=new int[noofladders];
    ladend=new int[noofladders];
     initialize(ladstart);
     initialize(ladend);
     initialize(snakestart);
     initialize(snakeend);
  }
 
void initialize_snake_ladder()throws IOException
   {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
   		String positemp;
   		int posi,i;
   		Integer numtemp;
   		initialize_array();
   		for(i=0;i<noofsnakes;i++)
   		{
   	 		System.out.println("\nENTER START POSTION OF SNAKE NUMBER "+(i+1));
   	    	positemp= br.readLine();
   		 	numtemp = Integer.valueOf(positemp);
   		 	posi = numtemp.intValue();
   	 		snakestart[i]=posi;
            System.out.println("\nENTER END POSTION OF SNAKE NUMBER "+(i+1));
   	  		positemp= br.readLine();
   		 	numtemp = Integer.valueOf(positemp);
   		 	posi = numtemp.intValue();
   	 		snakeend[i]=posi;
    	} 
    	
    	for(i=0;i<noofladders;i++)
   		{
   	 		System.out.println("\nENTER START POSTION OF LADDER NUMBER "+(i+1));
   	    	positemp= br.readLine();
   		 	numtemp = Integer.valueOf(positemp);
   		 	posi = numtemp.intValue();
   	 		ladstart[i]=posi;
      
        	System.out.println("\nENTER END POSTION OF LADDER NUMBER "+(i+1));
   	    	positemp= br.readLine();
   		 	numtemp = Integer.valueOf(positemp);
   		 	posi = numtemp.intValue();
   	 		ladend[i]=posi;	
    	}       	
   }
   
  
  
   void initialize_standard_snake_ladder()
   	{
   		noofsnakes = noofladders =num;
   		initialize_array();
   		set_snake_ladder();
	 
   	}
  
  
   void set_snake_ladder()
   {
  	 	int temp,i=0;
   		generator = new Random();
    	for(i=0;i<num;i++)
    	{	
      		temp=random_number(num,boardsize-2,generator);
      		while(contains(snakestart,temp)||contains(snakeend,temp)||contains(ladstart,temp)||contains(ladend,temp))
      		{
             temp=random_number(num,boardsize-2,generator);
            }
        snakestart[i]=temp; 
        temp=random_number(0,snakestart[i]-1,generator);
        while(contains(snakestart,temp)||contains(snakeend,temp)||contains(ladstart,temp)||contains(ladend,temp))
        {
       		temp=random_number(0,snakestart[i]-1,generator);
      	}
     	snakeend[i]=temp; 
     	temp=random_number(1,boardsize-num-1,generator);
      	while(contains(snakestart,temp)||contains(snakeend,temp)||contains(ladstart,temp)||contains(ladend,temp))
      	{
       		temp=random_number(1,boardsize-num-1,generator);
      	}
     	ladstart[i]=temp; 
        temp=random_number(ladstart[i],boardsize-2,generator);
        while(contains(snakestart,temp)||contains(snakeend,temp)||contains(ladstart,temp)||contains(ladend,temp))
     	 {
             	temp=random_number(ladstart[i],boardsize-2,generator);
      	 }
      	ladend[i]=temp;
        }  
}
   
   void initialize_standard_board()
   {
   	num=8;
   	boardsize=num*num;
   	initialize_standard_snake_ladder();
   	
   }
   
   
  
    private  void createAndShowGUI()throws IOException,InterruptedException {
  
      int j=0,i=0,x=0,labelsize=0;
      String a;
      configureboard();
      //Make sure we have nice window decorations.
      JFrame.setDefaultLookAndFeelDecorated(true);
      //Create and set up the window.
      frame = new JFrame("Snake_and_Ladder");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     // for(j=1;j<26;j++)
      blocks=new JLabel[144];
      i=1;
      a=String.valueOf(i);
        JPanel pane_frame = new JPanel();
       	pane_frame.setLayout(new BoxLayout(pane_frame, BoxLayout.PAGE_AXIS));
		pane_frame.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
    	
    	JPanel pane = new JPanel();
      	JPanel pane_dice = new JPanel();	
           
      //preparation of the dice panel
      pane_dice.setLayout(new GridLayout(0,3,10,10));
      pane_dice.setBackground(Color.BLACK	);	
      JButton dice = new JButton("PRESS DICE");
	  dice.setBackground(Color.white);
	  dice.setForeground(Color.black);
	 // dice.setForeground(new java.awt.Color(204, 0, 204));
      dice.setBorder(new javax.swing.border.BevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255,51,51), new java.awt.Color(255, 51, 51), new java.awt.Color(255, 0, 204), new java.awt.Color(255, 51, 255)));
      dice.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
      dice.setPreferredSize(new java.awt.Dimension(90, 40));
 	
     // JLabel label1 = new JLabel();
      label2 = new JLabel();
     // label1.setBackground(Color.BLACK);
      pane_dice.add(dice);
    //  pane_dice.add(label1);
     // pane_dice.add(null);
  	  label2.setOpaque(true);
	  label2.setForeground(Color.black);
	  label2.setBackground(Color.white);
	  label2.setPreferredSize(new Dimension(90,40));
      label2.setBorder(new javax.swing.border.BevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255,51,51), new java.awt.Color(255, 51, 51), new java.awt.Color(255, 0, 204), new java.awt.Color(255, 51, 255)));
      label2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
       pane_dice.add(label2);
      //abel2.setAlignment();
     // label2.setText("AMARS ");
      label3 = new JLabel();
      label3.setOpaque(true);
	  label3.setForeground(Color.black);
	  label3.setBackground(Color.white);
	  label3.setPreferredSize(new Dimension(90,40));
      label3.setBorder(new javax.swing.border.BevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255,51,51), new java.awt.Color(255, 51, 51), new java.awt.Color(255, 0, 204), new java.awt.Color(255, 51, 255)));
      label3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
      label3.setText("YOU GOT _ ");
      pane_dice.add(label3);
      //preparation of the game panel2
        pane.setLayout(new GridLayout(0,num,2,2));
    	pane.setBackground(Color.BLACK);

	
	 ImageIcon iconladder1 = createImageIcon("E:\\New folder\\SE\\Assignment-2\\Snake_And_Ladder\\images\\LADDER1.GIF",
                                 "a pretty but meaningless splat");
    
	 ImageIcon iconsnake = createImageIcon("E:\\New folder\\SE\\Assignment-2\\Snake_And_Ladder\\images\\snake.gif",
                                 "a pretty but meaningless splat");
	
     ImageIcon iconsnake1 = createImageIcon("E:\\New folder\\SE\\Assignment-2\\Snake_And_Ladder\\images\\snake2.gif",
                                 "a pretty but meaningless splat");
                                 
     ImageIcon iconladder2 = createImageIcon("E:\\New folder\\SE\\Assignment-2\\Snake_And_Ladder\\images\\ladder1.gif",
                                 "a pretty but meaningless splat");
 	
	 ImageIcon iconladder3 = createImageIcon("E:\\New folder\\SE\\Assignment-2\\Snake_And_Ladder\\images\\ladder1.gif",
                                 "a pretty but meaningless splat");
    
	 ImageIcon iconladder4 = createImageIcon("E:\\New folder\\SE\\Assignment-2\\Snake_And_Ladder\\3images\\ladder1.gif",
                                 "a pretty but meaningless splat");
   	
     int k=24,p=0,y=0;	
       
	 if(num==12)
	    labelsize=45;
	 if(num==11)
	    labelsize=50;
     if(num<=10) 
	    labelsize=60;
	    
     for(j=0;j<boardsize;j++)
	    {
	       if(contains(snakestart,j+1)&&j!=0)//setting up the snake
	        {
	        	p=boardsize-j-1;
	        	if(x%2==0)
	           	blocks[p] = new JLabel(a,iconsnake,JLabel.CENTER);
	           	else
	           	blocks[p] = new JLabel(a,iconsnake1,JLabel.CENTER);
	           	blocks[p].setOpaque(true);
	        	blocks[p].setBackground(Color.white);
		    	blocks[p].setPreferredSize(new Dimension(labelsize,labelsize));
		    	x++;
		   }
		   else//setting up the ladder
		 	{
		 	  if(contains(ladstart,j+1)&&j!=0)
		   		{
		   			p=boardsize-j-1;
	    	    	if(y%4==0)
	    				blocks[p] = new JLabel(a, iconladder1, JLabel.CENTER);
					if(y%4==1)
	    				blocks[p] = new JLabel(a, iconladder2, JLabel.CENTER);
					if(y%4==2)
	    				blocks[p] = new JLabel(a, iconladder3, JLabel.CENTER);
					if(y%4==3)
	    				blocks[p] = new JLabel(a, iconladder4, JLabel.CENTER);
					blocks[p].setOpaque(true);
	    		   	blocks[p].setBackground(Color.white);
	    			blocks[p].setPreferredSize(new Dimension(labelsize,labelsize));
	    			y++;
		  		 }
		   	  else
		   		{
		   			p=boardsize-j-1;
		   			blocks[p] = new JLabel(a,null, JLabel.CENTER);
					blocks[p].setOpaque(true);
	       			blocks[p].setBackground(Color.white);
	    			blocks[p].setPreferredSize(new Dimension(labelsize,labelsize));
		   		}
		   }
		   
		   i++;
		   a=String.valueOf(i);
	     }	
    
    for(i=0;i<boardsize;i++)
      pane.add(blocks[i]);
    pane_frame.add(pane);
	pane_frame.add(pane_dice);
     
    frame.setContentPane(pane_frame);
    frame.pack();
    frame.setVisible(true);
    blocks[boardsize-position1].setBackground(Color.yellow);
    label2.setText(names[0]+"'S TURN ");
    Thread.sleep(2000);
    JOptionPane.showMessageDialog(frame,names[0]+" START THE GAME. YOUR COLOR IS YELLOW.");
     //pressing a button to generate a random no between 1 and 6
    dice.addActionListener(this);
  }

 
 public void actionPerformed(ActionEvent e)
 {
 	Random generator1 = new Random();
	int number=random_number(1,6,generator1);
 	count++;
  	if(count%6==0)
 		number=1;
  	if(count%12==0)
 	number=2;
    try{
		new_position_index(number);
	 }  
    catch (InterruptedException exc) {
        System.err.println("Caught IntrException: " + exc.getMessage());
     }

 }
 
 public void new_position_index(int number) throws InterruptedException
 {
     
     label3.setText("YOU GOT "+number);
     //label2.repaint();
     int positiontemp;int position=0,indexnew;
     int index=totalcount%numplayers;
     steps=totalcount/numplayers;
     if(index==0)
     position=position1;
     if(index==1)
     position=position2;
     if(index==2)
     position=position3;
    
    
    // JOptionPane.showMessageDialog(frame,names[index]+" YOU GOT A  "+number);
    
   // Thread.sleep(1000);
     if(boardsize-position<=6)
    	xcount++;
    	 
     if(number>(boardsize-position))
    	{
		if(xcount==1)
			JOptionPane.showMessageDialog(frame,names[index]+" TO WIN YOU NEED "+(boardsize-position));
         xcount++;totalcount++;
     	  return;
     }
       
    if(number==(boardsize-position))
    {
    	 JOptionPane.showMessageDialog(frame,"CONGRATS "+names[index]+" YOU HAVE WON IN "+steps+" DICE THROWS");
    	if(index==0)
    	blocks[0].setBackground(Color.yellow);
    	if(index==1)
    	blocks[0].setBackground(Color.green);
    	if(index==2)
    	blocks[0].setBackground(Color.blue);

    	test_and_change_color(index,position);
    	totalcount++;
     	return;
     }
   
   if(number<(boardsize-position))
    xcount=0;  
    
       test_and_change_color(index,position);
    //	blocks[boardsize-position].setBackground(Color.cyan);
 		position=position+number;
        if(index==0)
    	blocks[boardsize-position].setBackground(Color.yellow);
    	if(index==1)
    	blocks[boardsize-position].setBackground(Color.green);
    	if(index==2)
    	blocks[boardsize-position].setBackground(Color.blue);
   // blocks[boardsize-position].setBackground(Color.yellow);
 	if(contains(snakestart,position))
 	 {
 	   positiontemp=position;
 	   position=snakeend[new_position_index(snakestart,position)];
       JOptionPane.showMessageDialog(frame,"SNAKE AT "+positiontemp+" CAUGHT YOU! GO TO "+position);
       test_and_change_color(index,positiontemp);
      // blocks[boardsize-positiontemp].setBackground(Color.cyan);
      }
 
 	if(contains(ladstart,position))
 	{	positiontemp=position;
 		position=ladend[new_position_index(ladstart,position)];
 		JOptionPane.showMessageDialog(frame,"YOU ARE UP THROUGH LADDER AT "+positiontemp+" TO "+position);
 	    test_and_change_color(index,positiontemp);
 	   // blocks[boardsize-positiontemp].setBackground(Color.cyan);
 	}
 	
    if(index==0)
    	blocks[boardsize-position].setBackground(Color.yellow);
    	if(index==1)
    	blocks[boardsize-position].setBackground(Color.green);
    	if(index==2)
    	blocks[boardsize-position].setBackground(Color.blue);
   
    //blocks[boardsize-position].setBackground(Color.yellow);
   if(index==0)
   position1=position;
    if(index==1)
   position2=position;
    if(index==2)
   position3=position;
   
    totalcount++;
    index=totalcount%numplayers;
 
 label2.setText(names[index]+"'s TURN ");
   if(totalcount==1&&index==1)
   {
   	 blocks[boardsize-1].setBackground(Color.green);
     JOptionPane.showMessageDialog(frame,names[1]+" START THE GAME. YOUR COLOR IS GREEN.");
    }
     else
     if(totalcount==2&&index==2)
    {
    	blocks[boardsize-1].setBackground(Color.blue);
   		JOptionPane.showMessageDialog(frame,names[2]+" START THE GAME. YOUR COLOR IS BLUE.");
   	}
   else
  blocks[boardsize-1].setBackground(Color.white);
  
  
   
    indexnew=totalcount%numplayers;
  // JOptionPane.showMessageDialog(frame,"IT IS NOW "+names[indexnew]+"'S TURN");
 }
 

void test_and_change_color(int index1,int pos)
{
if(index1==0&&pos!=1)
    	{
    		if(pos==position2)
    			blocks[boardsize-pos].setBackground(Color.green);
    		else
    			if(pos==position3)	
     	    	    blocks[boardsize-pos].setBackground(Color.blue);
     				else
     					blocks[boardsize-pos].setBackground(Color.white);
     	}
if(index1==1&&pos!=1)
    	{
    		if(pos==position1)
    			blocks[boardsize-pos].setBackground(Color.yellow);
    		else
    			if(pos==position3)	
     	    	    blocks[boardsize-pos].setBackground(Color.blue);
     				else
     					blocks[boardsize-pos].setBackground(Color.white);

           }


if(index1==2&&pos!=1)
    	{
    		if(pos==position2)
    			blocks[boardsize-pos].setBackground(Color.green);
    		else
    			if(pos==position1)	
     	    	    blocks[boardsize-pos].setBackground(Color.yellow);
     				else
     					blocks[boardsize-pos].setBackground(Color.white);
		}
 
 }
 
 
 
 static boolean contains(int[] array,int element)
 {
 	int i=0;
 	while(i!=array.length)
 	{
 		if(array[i]==element)
 		return true;
 	i++;	
 	}
 return false;
 }
 
  
 
  int new_position_index(int[] array,int number)
  {
 	int i=0;	
  	for(i=0;i<array.length;i++)
  	if(array[i]==number)
  	return i;
  
  return 0;
  
  }
  
    void initialize(int array[])
   {
   	int i=0;
   	for(i=0;i<array.length;i++)
   	array[i]=0;
   }

static	int random_number(int aUpperLimit,int aLowerLimit,Random generator)
	{
 		int number;
 	
       long range = (long)aUpperLimit - (long)aLowerLimit + 1;
      long fraction = (long)(range * generator.nextDouble());
       number= (int)(fraction + aLowerLimit);
       return number;
       
	}


 protected static ImageIcon createImageIcon(String path,
                                           String description) {
    java.net.URL imgURL = snake_ladder.class.getResource(path);
    if (imgURL != null) {
        return new ImageIcon(imgURL, description);
    } else {
        System.err.println("Couldn't find file: " + path);
        return null;
    }
}
 
 
    public static void main(String[] args)throws IOException,InterruptedException  {
        
       final snake_ladder object1=new snake_ladder();
       
               object1.createAndShowGUI();
        
    }
}
