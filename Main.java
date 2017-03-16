//Nicholas Henseleit   ID: 300248546
//Patrick Klitgaard    ID: 300252395

import java.util.Scanner;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Main extends JFrame
{

    public static void main(String[] args){
        Main theGame = new Main();
    }

    public Main(){
        /**GUI Objects and design on Application load
        =============================================================================================================================**/
        super("Black Jack");
        values gameValues = new values(10000); //The variables class. 
        JPanel bodyPanel = new JPanel(new BorderLayout());
        add(bodyPanel);
        ImageIcon img = new ImageIcon("png/gameicon.png");
        setIconImage(img.getImage());
        //======================================================================== Top Panel
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20,20));
        topPanel.setBackground(new Color(224,224,224));

        JLabel cardValue = new JLabel("Card Value: ");
        cardValue.setPreferredSize(new Dimension(400,35));
        cardValue.setFont(new Font("Roboto", Font.BOLD, 22));

        JLabel winningMoney = new JLabel("Winnings: $" + gameValues.getBetAmount());
        winningMoney.setPreferredSize(new Dimension(400,35));
        winningMoney.setFont(new Font("Roboto", Font.BOLD, 22));

        JLabel money = new JLabel("Money: $" + gameValues.getMoney());
        money.setPreferredSize(new Dimension(400,35));
        money.setFont(new Font("Roboto", Font.BOLD, 22));

        topPanel.add(cardValue);
        topPanel.add(winningMoney);
        topPanel.add(money);
        bodyPanel.add(topPanel, BorderLayout.NORTH);
        //=============================================================================================

        //========================================================================= Center piece
        JPanel cardsPanel = new JPanel(new GridLayout(2, 4)); 
        JLabel cardsPos1 = new JLabel(""); //creating 8 positions for cards to be placed in
        JLabel cardsPos2 = new JLabel("");
        JLabel cardsPos3 = new JLabel("");
        JLabel cardsPos4 = new JLabel("");
        JLabel cardsPos5 = new JLabel("");
        JLabel cardsPos6 = new JLabel("");
        JLabel cardsPos7 = new JLabel("");
        JLabel cardsPos8 = new JLabel("");
        cardsPos1.setIcon(new ImageIcon("png/back.png")); //setting those 8 positions to a blank png image
        cardsPos2.setIcon(new ImageIcon("png/back.png"));
        cardsPos3.setIcon(new ImageIcon("png/back.png"));
        cardsPos4.setIcon(new ImageIcon("png/back.png"));
        cardsPos5.setIcon(new ImageIcon("png/back.png"));
        cardsPos6.setIcon(new ImageIcon("png/back.png"));
        cardsPos7.setIcon(new ImageIcon("png/back.png"));
        cardsPos8.setIcon(new ImageIcon("png/back.png"));
        cardsPanel.add(cardsPos1); //adding those images to the cardsPanel
        cardsPanel.add(cardsPos2);
        cardsPanel.add(cardsPos3);
        cardsPanel.add(cardsPos4);
        cardsPanel.add(cardsPos5);
        cardsPanel.add(cardsPos6);
        cardsPanel.add(cardsPos7);
        cardsPanel.add(cardsPos8);
        bodyPanel.add(cardsPanel, BorderLayout.CENTER);

        //==============================================================================================
        //========================================================================= SIDE LOG PANEL
        JPanel gameLog = new JPanel(new FlowLayout(FlowLayout.CENTER, 10,10));
        JTextArea gameLogText = new JTextArea(33,39);
        gameLogText.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 19));
        gameLogText.setEditable(false);
        gameLogText.setText(" ");

        gameLog.add(gameLogText);
        bodyPanel.add(gameLog, BorderLayout.EAST);
        //==============================================================================================

        //========================================================================= Bottom controls
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10,10));
        bottomPanel.setBackground(new Color(224,224,224)); 

        JButton playAgain = new JButton("Play Again?"); //Creation of the "play again?" Button
        playAgain.setPreferredSize(new Dimension(150,50));
        playAgain.setFont(new Font("Roboto", Font.PLAIN, 22));
        playAgain.setEnabled(false);

        JButton stay = new JButton("Stay"); //Creation of the "stay" Button
        stay.setPreferredSize(new Dimension(100,50));
        stay.setFont(new Font("Roboto", Font.PLAIN, 22));
        stay.setEnabled(false);

        JButton hit = new JButton("Hit"); //Creation of the "hit" button
        hit.setPreferredSize(new Dimension(100,50));
        hit.setFont(new Font("Roboto", Font.PLAIN, 22));
        hit.setEnabled(false);

        JButton bet = new JButton("Click to submit bet"); //Creation of the "bet" button
        bet.setPreferredSize(new Dimension(250,50));
        bet.setFont(new Font("Roboto", Font.PLAIN, 22));

        JTextField betAmountTB = new JTextField(10); //Creation of the betAmount Text area
        betAmountTB.setPreferredSize(new Dimension(100,25));
        betAmountTB.setFont(new Font("Roboto", Font.PLAIN, 22));

        bottomPanel.add(playAgain);
        bottomPanel.add(stay); //adding the objects to the bottom pane;
        bottomPanel.add(hit);
        bottomPanel.add(bet);
        bottomPanel.add(betAmountTB);
        bodyPanel.add(bottomPanel, BorderLayout.SOUTH); //addings the bottom panel to the body panel 

        setSize(1700,950);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        /**Event Handling for buttons
        ================================================================================================================================**/

        bet.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){

                    try{
                        int betAmount = Integer.parseInt(betAmountTB.getText()); //parsing the betAmountTB to an Integer
                        int betSender = betAmount + (betAmount/2); //The player wins their money + half of what they bet
                        if(betAmount > gameValues.getMoney()){ //if the player bets over the amount of money they have, throw an exception
                            throw new Exception();
                        }
                        gameValues.setBetAmount(betSender);
                        gameValues.setMoney(betAmount);

                        winningMoney.setText("Winnings: $" + gameValues.getBetAmount());
                        money.setText("Money: $" + gameValues.getMoney());
                        printToGameLog(gameValues, "You bet: $" + betAmount, gameLogText);
                        betAmountTB.setText("");

                        cardsPos1.setIcon(new ImageIcon("png/" + cardGen(gameValues, 0) + ".png")); 
                        cardsPos2.setIcon(new ImageIcon("png/" + cardGen(gameValues, 0) + ".png")); 
                        cardValue.setText("Card Value: " + gameValues.getHandValue(0));

                        //After event button handling (enabled or disabled after event) 
                        hit.setEnabled(true);
                        stay.setEnabled(true);
                        bet.setEnabled(false);
                        betAmountTB.setEditable(false);
                    }catch(Exception exceptionE){
                        printToGameLog(gameValues, "Please Enter a valid amount", gameLogText);
                    }
                    if(gameValues.getHandValue(0) == 21){ //if the player hits 21 off of the first "hit" enable "stay" to compare the comps cards
                        hit.setEnabled(false);
                        stay.setEnabled(true);
                        bet.setEnabled(false);
                        betAmountTB.setEditable(false);
                        playAgain.setEnabled(false);
                        printToGameLog(gameValues, "You hit 21!", gameLogText);
                    }
                }
        });
        hit.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    int card = cardGen(gameValues, 0);
                    printToGameLog(gameValues, "You Hit!", gameLogText);
                    switch(gameValues.getHitCounter()){ //deciding which 1x9 card grid position to place the image
                        case 1:  //if getHitCounter = 1; change cardPos3
                        cardsPos3.setIcon(new ImageIcon("png/" + card + ".png")); break;
                        case 2:
                        cardsPos4.setIcon(new ImageIcon("png/" + card + ".png")); break;
                        case 3: 
                        cardsPos5.setIcon(new ImageIcon("png/" + card + ".png")); break;
                        case 4: 
                        cardsPos6.setIcon(new ImageIcon("png/" + card + ".png")); break;
                        case 5: 
                        cardsPos7.setIcon(new ImageIcon("png/" + card + ".png")); break;
                        case 6:
                        cardsPos8.setIcon(new ImageIcon("png/" + card + ".png")); 
                        hit.setEnabled(false);
                        break;
                    }
                    if(gameValues.getHandValue(0) > 21){ //if the players cards on a hit are greater than 21, they lose
                        gameEnd(gameValues, money, hit, stay, bet, playAgain, betAmountTB, gameLogText);
                    }
                    if(gameValues.getHandValue(0) == 21){ //if the players cards on a hit are equal to 21, compare to computer
                        printToGameLog(gameValues, "You hit 21!", gameLogText);
                        gameEnd(gameValues, money, hit, stay, bet, playAgain, betAmountTB, gameLogText);
                    }
                    gameValues.setHitCounterNew(1);
                    cardValue.setText("Card Value: " + gameValues.getHandValue(0));
                }
        });

        stay.addActionListener(new ActionListener(){ //Event handle for comparing the game results
                @Override
                public void actionPerformed(ActionEvent e){
                    printToGameLog(gameValues, "You Stay", gameLogText);
                    gameEnd(gameValues, money, hit, stay, bet, playAgain, betAmountTB, gameLogText);
                }
        });

        playAgain.addActionListener(new ActionListener(){ //Event handle for resetting the game board
                @Override
                public void actionPerformed(ActionEvent e){
                    try{ //resets the game board and round values back to their stock amount
                        System.out.println(gameValues.getGameLogText());
                        cardsPos1.setIcon(new ImageIcon("png/back.png")); 
                        cardsPos2.setIcon(new ImageIcon("png/back.png")); 
                        cardsPos3.setIcon(new ImageIcon("png/back.png")); 
                        cardsPos4.setIcon(new ImageIcon("png/back.png")); 
                        cardsPos5.setIcon(new ImageIcon("png/back.png")); 
                        cardsPos6.setIcon(new ImageIcon("png/back.png")); 
                        cardsPos7.setIcon(new ImageIcon("png/back.png")); 
                        cardsPos8.setIcon(new ImageIcon("png/back.png")); 

                        gameValues.resetValues(); //resets the gameValues round values
                        winningMoney.setText("Winnings: "); //next few lines reset the visible game board and disables buttons
                        cardValue.setText("Card Value: ");
                        gameLogText.setText(" ");
                        hit.setEnabled(false);
                        stay.setEnabled(false);
                        playAgain.setEnabled(false);
                        if(gameValues.getMoney() == 0){ //if the players money is at $0, show an info box and throw an exception
                            infoBox("You have ran out of Money! \nYour Money: $" + gameValues.getMoney(), "GAME OVER");
                            throw new Exception();
                        }
                        bet.setEnabled(true);
                        betAmountTB.setEditable(true);
                    } catch(Exception exceptionE){
                        bet.setEnabled(false);
                        betAmountTB.setEditable(false);
                        playAgain.setEnabled(false);
                        printToGameLog(gameValues, "GAME OVER", gameLogText);
                    }
                }
        });
            
    }

    
    public static int cardGen(values gameValues, int playerSelect){
        Boolean cardCheck = true;
        int randomGen = 0;
        while(cardCheck == true){ //Generates random numbers between 0-51 until it comes across a number it has not dealed yet      
            randomGen = (int)(Math.random() * 51);
            System.out.println(randomGen);
            cardCheck = gameValues.checkCard(randomGen); //calls on the gameValues object to check the "allCardsDrawn" array to see if it has been used
        }
        if(playerSelect == 0){ gameValues.setPlayerCardInHand(randomGen); } //depending on the playerSelect will determin whos hand to add the cards to
        if(playerSelect == 1){ gameValues.setCompCardInHand(randomGen); }
        return randomGen;
    } //Method for generating random cards(for player or computer)

    public static int ComputerCards(values gameValues){
        cardGen(gameValues, 1);
        cardGen(gameValues, 1);
        while(gameValues.getHandValue(1) < 17){ //the dealer draws cards until his hand is equal to, or greater than 17
            cardGen(gameValues, 1); 
        }
        return gameValues.getHandValue(1);
    } //method for generating the dealers cards

    public static void gameEnd(values gameValues, JLabel money, JButton hit, JButton stay, JButton bet, JButton playAgain, JTextField betAmountTB, JTextArea gameLogText){

        ComputerCards(gameValues); //generates the computers cards, if the value is below 17, the commp draws another card
        int player = gameValues.getHandValue(0); //sets the player and comp handValues to 2 variables
        int computer = gameValues.getHandValue(1);
        if( computer > 21 && player > 21){ //The following 6 comparative statements compares the players hand to the computers hand 
            gameValues.setMoneyTie();
            money.setText("Money: $" + gameValues.getMoney());
            printToGameLog(gameValues, "\n You both Busted! Game Draw!", gameLogText);
        }
        else if(computer == player){  
            gameValues.setMoneyTie();
            money.setText("Money: $" + gameValues.getMoney());
            printToGameLog(gameValues, "\n You and the computer tied. Game Draw!", gameLogText); 
        }
        else if(computer > 21 && player <= 21){ 
            gameValues.setMoneyWin();
            money.setText("Money: $" + gameValues.getMoney());
            printToGameLog(gameValues, "\n The computer Busted! You Win!", gameLogText);
        }
        else if(computer <= 21 && player > 21){ 
            money.setText("Money: $" + gameValues.getMoney());
            printToGameLog(gameValues, "\n You Busted! Sorry you Lose!", gameLogText);
        }
        else if(computer > player ){
            money.setText("Money: $" + gameValues.getMoney());
            printToGameLog(gameValues, "\n The computer beat you! Sorry you Lose", gameLogText);
        }
        else if (computer < player){
            gameValues.setMoneyWin();
            money.setText("Money: $" + gameValues.getMoney());
            printToGameLog(gameValues, "\n you beat the computer! You Win!", gameLogText);        
        }

        hit.setEnabled(false);
        stay.setEnabled(false);
        bet.setEnabled(false);
        playAgain.setEnabled(true);
        betAmountTB.setEditable(false);
        printToGameLog(gameValues, "\n The computers card values were: " + gameValues.getHandValue(1), gameLogText);
        printToGameLog(gameValues, "Your card values were: " + gameValues.getHandValue(0), gameLogText);
        
    } //this method is is used to compare the final game values (decide who won, and change JLabels)

    public static void printToGameLog(values gameValues, String addString, JTextArea gameLogText){
        gameValues.setGameLogText(" " + addString);
        gameLogText.setText("");
        gameLogText.append(gameValues.getGameLogText());
    } //this method is for adding text to 'setGameLogText' in the values class, then appending that string to the gameLog JTextArea

    public static void infoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
    } //Used for bringing up an infoBox for when the player runs out of money
}


