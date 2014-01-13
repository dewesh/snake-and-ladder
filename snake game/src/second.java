/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */




import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.Random;
class second extends JPanel implements ActionListener{
    Timer t = new Timer(5,this);
    JPanel con=new JPanel();
  FileInputStream snk=new FileInputStream("E:\\snake.txt");
            DataInputStream dsnk=new DataInputStream(snk);
            BufferedReader bsnk=new BufferedReader(new InputStreamReader(dsnk));
             FileInputStream snk1=new FileInputStream("E:\\ladder.txt");
            DataInputStream dsnk1=new DataInputStream(snk1);
            BufferedReader bsnk1=new BufferedReader(new InputStreamReader(dsnk1));
             FileInputStream tsnk=new FileInputStream("E:\\snake.txt");
            DataInputStream tdsnk=new DataInputStream(tsnk);
            BufferedReader tbsnk=new BufferedReader(new InputStreamReader(tdsnk));
             FileInputStream tsnk1=new FileInputStream("E:\\ladder.txt");
            DataInputStream tdsnk1=new DataInputStream(tsnk1);
            BufferedReader tbsnk1=new BufferedReader(new InputStreamReader(tdsnk1));
           
  JLabel pic =new JLabel();
  JButton click ;
  JTextArea test;
  int b_size,p_no;
  // time se;
  int i[]={0,0,0},destination[]={0,0,0},temp0,snakehead[]={15,23,35,45},snaketail[]={5,19,25,4},snake_no=0,ladder_no=0,ladders[]={2,13,40,52,77},laddere[]={14,24,55,79,92};
  int coll,in,q=0,p[]={0,0,0};
  String snake[][],ladder[][];
  boolean snaked=false;
  private ImageIcon image1,image2;
   Random d;
    int lev=1,sec=0,z=1,x[]={0,100,50},y[]={100,100,100},temp,temp1;
    
    public second() throws IOException{
        
        
        t.start();
 while(tbsnk.readLine() != null ){
              snake_no++;  
            }
  while(tbsnk1.readLine() != null ){
              ladder_no++;  
            }
 snake=new String[snake_no][2];
 ladder=new String[ladder_no][2];

 String as,ass;
 int i1=0,j1=0;
 for(i1=0;i1<snake_no;i1++){
     as=bsnk.readLine();
     snake[i1]=as.split(",");
 }
 for(j1=0;j1<ladder_no;j1++){
     ass=bsnk1.readLine();
     //System.out.println(ass);
     ladder[j1]=ass.split(",");
     System.out.println(ladder[j1][1]);
     
 }
 bsnk.close();
bsnk1.close();
dsnk.close();
dsnk1.close();
snk.close();
snk1.close();
tbsnk.close();
tbsnk1.close();
tdsnk.close();
tdsnk1.close();
tsnk.close();
tsnk1.close();

 
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
                click=new JButton("");
                
               // se=new time();
                click.setSize(100, 100);
                //this.setLayout(new GridLayout(0,2));
                click.setBorderPainted(false);
      click.setContentAreaFilled(false);
                click.addActionListener(this);
                click.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                 click.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                clickMouseEntered(evt);
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                clickMouseExited(evt);
            }

            private void clickMouseEntered(MouseEvent evt) {
                click.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dice.png")));
            }

            private void clickMouseExited(MouseEvent evt) {
                click.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dice_off.png")));
            }
        });
                click.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dice_off.png")));
                JPanel con1=new JPanel();
                con1.setOpaque(false);
                
                add(con1);
                add(click);
                
                click.setLocation(400,0);
        try {
            FileInputStream fstr=new FileInputStream("E:\\database1.txt");
            DataInputStream dis=new DataInputStream(fstr);
            BufferedReader br=new BufferedReader(new InputStreamReader(dis));
            
            p_no=Integer.valueOf(br.readLine());
            b_size=Integer.valueOf(br.readLine());
            //System.out.println(p_no+"\n"+b_size);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(second.class.getName()).log(Level.SEVERE, null, ex);
        }
               
                
    }
    int xco=0,yco=0;
    JButton click1=new JButton("");
    
    @Override
    public void paintComponent(Graphics g){
      super.paintComponent(g); 
      super.setBackground(Color.pink);
      this.add(con);
      con.setSize(500,150);
      con.setBackground(Color.yellow);
      con.setVisible(true);
      click1.setSize(600,150);
      click1.setBorderPainted(false);
      click1.setContentAreaFilled(false);
      
             click1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/snake_banne1r.jpg")));
              
con.setOpaque(false);
con.add(click1);

            Graphics2D g2 = (Graphics2D) g;
            g2.setFont(new Font("Serif",Font.PLAIN, 30));
     // Rectangle2D rec = new Rectangle2D.Double(0,0,50,50);
      Ellipse2D circle=new Ellipse2D.Double(x[0],y[0],50,50);
      Ellipse2D circle1=new Ellipse2D.Double(x[1],y[1],50,50);
      Ellipse2D circle2=null;
      if(p_no==3)
         circle2=new Ellipse2D.Double(x[2],y[2],50,50);
     // g2.setColor(Color.white);
     //g2.fill(rec);
      int num=1,yc=1;
     
         for(yco=b_size+2;yco>=3;yco--,yc++)
         {
             for(xco=0;xco<b_size;xco++,num++){
                 if((xco+yco-3)%2==0){
              g2.setColor(Color.black);
              
             g2.fillRect(xco*50, yco*50, 50, 50);
             g2.setColor(Color.white);
             if(yc%2==0){
                 for(temp=0;temp<snake_no;temp++){
                     if((yc*b_size-xco)==Integer.valueOf(snake[temp][0]))
                         g2.setColor(Color.red);
                 }
                 for(temp=0;temp<ladder_no;temp++){
                     if((yc*b_size-xco)==Integer.valueOf(ladder[temp][0]))
                         g2.setColor(Color.green);
                 }
                 g2.drawString(String.valueOf(yc*b_size-xco),xco*50,yco*50+50);
                 g2.setColor(Color.white);
             }
             else{
                  for(temp=0;temp<snake_no;temp++){
                     if((num)==Integer.valueOf(snake[temp][0]))
                         g2.setColor(Color.red);
                 }
                 for(temp=0;temp<ladder_no;temp++){
                     if((num)==Integer.valueOf(ladder[temp][0]))
                         g2.setColor(Color.green);
                 }
                 
                 g2.drawString(String.valueOf(num),xco*50,yco*50+50);
             g2.setColor(Color.white);
             }
                 }
         else{         
     g2.setColor(Color.white);
     g2.fillRect(xco*50, yco*50, 50, 50);
     g2.setColor(Color.black);
      if(yc%2==0){ for(temp=0;temp<snake_no;temp++){
                     if((yc*b_size-xco)==Integer.valueOf(snake[temp][0]))
                         g2.setColor(Color.red);
                 }
                 for(temp=0;temp<ladder_no;temp++){
                     if((yc*b_size-xco)==Integer.valueOf(ladder[temp][0]))
                         g2.setColor(Color.green);
                 }
                 g2.drawString(String.valueOf(yc*b_size-xco),xco*50,yco*50+50);
                   g2.setColor(Color.black);
      }
      else{
          for(temp=0;temp<snake_no;temp++){
                     if((num)==Integer.valueOf(snake[temp][0]))
                         g2.setColor(Color.red);
                 }
                 for(temp=0;temp<ladder_no;temp++){
                     if((num)==Integer.valueOf(ladder[temp][0]))
                         g2.setColor(Color.green);
                 }
      
    g2.drawString(String.valueOf(num),xco*50,yco*50+50);
      g2.setColor(Color.black);
         }
                 }
    } 
    }
         int s[][],s1[][],l1[][],l[][];
      s=new int[snake_no][2];
      l=new int[ladder_no][2];
      s1=new int[snake_no][2];
      l1=new int[ladder_no][2];
       g2.setStroke(new java.awt.BasicStroke(7));
      ///////////////////////////////////////////////////////////////////////////////////////////////////////////
      g2.setColor(Color.red);
      
      for(temp=0;temp < snake_no;temp++){
          
         temp0=Integer.valueOf(snake[temp][0])%b_size;
        coll=(int) Integer.valueOf(snake[temp][0])/b_size;
        if(temp0==0){
        	--coll;
           temp0=b_size-1;}
        else if(Integer.valueOf(snake[temp][0])>0)
            --temp0;
        
        s[temp][0]=(2+b_size)*50-(coll*50);
        if(coll%2==0){
            s[temp][1]=temp0*50;
        }
        else{
            s[temp][1]=(b_size-1)*50-temp0*50;
        }
        
            temp0=Integer.valueOf(snake[temp][1])%b_size;
        coll=(int) Integer.valueOf(snake[temp][1])/b_size;
        if(temp0==0){
        	--coll;
           temp0=b_size-1;}
        else if(Integer.valueOf(snake[temp][1])>0)
            --temp0;
        
        s1[temp][0]=(2+b_size)*50-(coll*50);
        if(coll%2==0){
            s1[temp][1]=temp0*50;
        }
        else{
            s1[temp][1]=(b_size-1)*50-temp0*50;
        }
      Composite originalComposite = g2.getComposite();
      g2.setComposite(makeComposite((float) 0.6));
    g2.setPaint(Color.red);
    g2.fillRect(s[temp][1],s[temp][0],50,50);
    
        g2.drawLine(s[temp][1]+20, s[temp][0]+20, s1[temp][1]+20, s1[temp][0]+20);
        g2.setComposite(originalComposite);
      }
          ///////////////////////////////////////////////////////////////////////////////////////////////////////////
      g2.setColor(Color.green);
      
      for(temp=0;temp < ladder_no;temp++){
          
         temp0=Integer.valueOf(ladder[temp][0])%b_size;
        coll=(int) Integer.valueOf(ladder[temp][0])/b_size;
        if(temp0==0){
        	--coll;
           temp0=b_size-1;}
        else if(Integer.valueOf(ladder[temp][0])>0)
            --temp0;
        
        l[temp][0]=(2+b_size)*50-(coll*50);
        if(coll%2==0){
            l[temp][1]=temp0*50;
        }
        else{
            l[temp][1]=(b_size-1)*50-temp0*50;
        }
        
            temp0=Integer.valueOf(ladder[temp][1])%b_size;
        coll=(int) Integer.valueOf(ladder[temp][1])/b_size;
        if(temp0==0){
        	--coll;
           temp0=b_size-1;}
        else if(Integer.valueOf(ladder[temp][1])>0)
            --temp0;
        
        l1[temp][0]=(2+b_size)*50-(coll*50);
        if(coll%2==0){
            l1[temp][1]=temp0*50;
        }
        else{
            l1[temp][1]=(b_size-1)*50-temp0*50;
        }
      float op=(float) 0.6;
        Composite originalComposite = g2.getComposite();
      g2.setComposite(makeComposite(op));
    g2.setPaint(Color.blue);
    g2.fillRect(l[temp][1],l[temp][0],50,50);
    
        g2.drawLine(l[temp][1]+20, l[temp][0]+20, l1[temp][1]+20, l1[temp][0]+20);
        g2.setComposite(originalComposite);
      }
          
     g2.setColor(Color.RED);
     g2.fill(circle);
     g2.setColor(Color.GREEN);
     g2.fill(circle1);
      if(p_no==3){
     g2.setColor(Color.blue);
     g2.fill(circle2);
      }
      g2.setFont(new Font("Serif",Font.BOLD, 38));
      g2.setColor(Color.red);
      g2.drawString(tex, 0, (b_size+4)*50-12);
     
    }
String tex="green roll the dice";
  private AlphaComposite makeComposite(float alpha) {
    int type = AlphaComposite.SRC_OVER;
    return(AlphaComposite.getInstance(type, alpha));
  }
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource()==click){
             d=new Random();
in=d.nextInt(6)+1;
            JOptionPane.showMessageDialog(null,"the number is"+in);
            if(p_no==2){
                if(q==0){
            	q=1;
                p[0]+=1;}
                else{
            	q=0;p[1]++;}
            }
            else
            {
               /* if(q==0){
            	q=1;p[0]++;}
                else if(q==2){
            	q=0;p[1]++;}
                else{
                    q=2;
                p[2]++;}*/
                if(p[0]==p[1] && p[1]==p[2]){
                    q=1;p[0]++;
                }
                else if(p[0]>p[1]){
                    q=0;p[1]++;
                }
                else if(p[1]>p[2]){
                    q=2;p[2]++;
                }
            }
           destination[q]=i[q]+in;
           i[q]=destination[q];
           if(i[q]>(b_size*b_size))
           {
        	   JOptionPane.showMessageDialog(null,"sorry...boundry");
        	   i[q]-=in;
           }
           int ls=i[q];
          for(temp=0;temp<snake_no;temp++){
        	  if(i[q]==Integer.valueOf(snake[temp][0])){
        		  
                          //System.out.println(snake[temp][1]);
        		  i[q]=Integer.valueOf(snake[temp][1]);
                          repaint();
                          JOptionPane.showMessageDialog(null,"snake at " + ls + " got u");
        	  }
          }
          
          for(temp=0;temp<ladder_no;temp++){
        	  if(i[q]==Integer.valueOf(ladder[temp][0])){
                           //System.out.println(Integer.valueOf(ladder[temp][1]));
        		  i[q]=Integer.valueOf(ladder[temp][1]);
                          repaint();
                          JOptionPane.showMessageDialog(null,"ladder at "+ls);
        	  }
          }
           
        temp0=i[q]%b_size;
        coll=(int) i[q]/b_size;
        if(temp0==0){
        	--coll;
           temp0=b_size-1;}
        else if(i[q]>0)
            --temp0;
        
        y[q]=(2+b_size)*50-(coll*50);
        if(coll%2==0){
            x[q]=temp0*50;
        }
        else{
            x[q]=(b_size-1)*50-temp0*50;
        }
        if(p_no==2){
                if(q==0){
            	tex="green roll the dice";
                }
                else{
            	tex="red roll the dice";
            }
        }
            else
            {
         if(p[0]==p[1] && p[1]==p[2]){
                    tex="green roll the dice";
                }
                else if(p[0]>p[1]){
                    tex="red roll the dice";
                }
                else if(p[1]>p[2]){
                    tex="blue roll the dice";
                }
            }
         
        /*if(q==0){
        	//click.setText("blue::");
            tex="blue roll the dice ";
        }
        else if(q==1)
        {//click.setText("red::");
            tex="red roll the dice";
        }
        else
        {
        	//click.setText("green::");
            tex="green roll the dice";
        }*/
         repaint();
        if(i[q]==(b_size*b_size))
        {
     	   JOptionPane.showMessageDialog(null,"player "+(q+1)+ " is the winner"+"\n"+"SCORE:"+(p[q]+1)); 
                try {FileWriter winner = null;BufferedWriter win = null;
                
               
                    FileInputStream w=new FileInputStream("E:\\winner"+b_size+".txt");
                    DataInputStream di=new DataInputStream(w);
                    BufferedReader w1=new BufferedReader(new InputStreamReader(di));
                    
                    if(p[q]+1<Integer.valueOf(w1.read()))
                    {w.close();
                    di.close();
                    w1.close();
                     winner =new FileWriter("E:\\winner"+b_size+".txt");
                    win=new BufferedWriter(winner);
                        JOptionPane.showMessageDialog(null,"congozzz u have a new high score"); 
                        
                    win.write(String.valueOf(p[q]+1));
                    }
                    else{
                        w.close();
                    di.close();
                    w1.close();
                    }
                    win.close();
                    winner.close();
                } catch (IOException ex) {
                    Logger.getLogger(second.class.getName()).log(Level.SEVERE, null, ex);
                }
     	   System.exit(1);
        }
        	
        }
       
        
       /*if((x1-x) < 30 && (x1-x) > -30 && (y1-y) < 30 && (y1-y) > -30){
            temp=velx;
            temp1=vely;
            velx=-velx1;vely=vely1;velx1=temp;vely1=temp1;
            JOptionPane.showMessageDialog(null,"CONGRATULATION\ngreen u won");
            x=0;y=500;x1=500;y1=500;
             JOptionPane.showMessageDialog(null,"green-cop\nred-theaf");
             x=0;y=500;velx=0;vely=0;x1=500;y1=500;velx1=0;vely1=0;z=1.5;lev=0;
        }*/
       /* if(destination>i){
         
        runf();
        
    }
        if(destination==i && snaked==true){
            destination=snaketail[snake_no]-1;
            snaked=false;
        }
        
        if(destination<i)
        {
            funr();
           
        }*/
    }
   
 
     
      /*  public void runf(){
             if(x<0 ||x>500){
            velx=-velx;
            y-=50;
            test.append(x+"::"+y+"()");
            coll++;
        }
      if(y<0 ||y>500){
            vely=0;velx=0;
            
        }
        if(x1<0 ||x1>500){
            velx1=-velx1;
            y1-=50;
        }
      if(y1<0 ||y1>500){
            vely1=0;
            velx1=0;
        }
      repaint();
        x+=velx;
       y+=vely;
       
        x1+=velx1;
       y1+=vely1;
        }

    private void funr() {
       
        if(x<0 ||x>500){
            velx=-velx;
            y+=50;
            //test.append(x+"::"+y+"()");
            coll--;
        }
      if(y<0 ||y>500){
            vely=0;velx=0;
            
        }
        if(x1<0 ||x1>500){
            velx1=-velx1;
            y1+=50;
        }
      if(y1<0 ||y1>500){
            vely1=0;
            velx1=0;
        }
      repaint();
        x-=velx;
       y-=vely;
       
        x1-=velx1;
       y1-=vely1;
    }*/
}