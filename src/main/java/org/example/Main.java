package org.example;

import java.awt.*;
import java.awt.event.*;
import java.util.Map;

class MyCalc extends WindowAdapter implements ActionListener{
    //Frame f;
    Frame f;
    Button convertButton;
    Label label1;
    Label label2;
    Label label3;
    Label label4;
    Label label5;
    TextField textField1;
    Choice choice1;
    Choice choice2;

    ExchangeApi api = new ExchangeApi();
    Map<String, Double> currencyList =api.getCurrencies();


    MyCalc(){
        f= new Frame("CURRENCY CONVERTER");
        f.setBackground(Color.PINK);
        f.setSize(800,500);
        f.setLayout(null);
        f.setVisible(true);

        label1=new Label("Currency Converter", Label.CENTER);
        label1.setBounds(100,50,600,50);
        label1.setBackground(Color.magenta);
        label1.setFont(new Font("Arial", Font.BOLD, 30));

        label2=new Label("Value to convert:");
        label2.setBounds(350,130,120,20);
        label2.setFont(new Font("Arial", Font.PLAIN, 15));
        label2.setBackground(Color.magenta);

        textField1=new TextField();
        textField1.setBounds(430,160,160,30);
        textField1.setFont( new Font("Arial", Font.PLAIN,20));
        textField1.setBackground(Color.lightGray);

        label3=new Label("From:");
        label3.setBounds(260,160,55,30);
        label3.setFont(new Font("Arial", Font.PLAIN, 20));

        label4=new Label("To:");
        label4.setBounds(260,240,50,30);
        label4.setFont(new Font("Arial", Font.PLAIN, 20));

        label5=new Label("");
        label5.setBounds(430,240,160,30);
        label5.setFont(new Font("Arial", Font.PLAIN, 20));
        label5.setBackground(Color.lightGray);

        choice1=new Choice();
        choice1.setBounds(330,160,85,30);
        choice1.setFont(new Font("Arial", Font.PLAIN, 20));

        choice2=new Choice();
        choice2.setBounds(330,240,85,30);
        choice2.setFont(new Font("Arial", Font.PLAIN, 20));



        convertButton=new Button("Convert");
        convertButton.setBounds(330,330,150,50);
        convertButton.setFont(new Font("Arial", Font.BOLD, 20));
        convertButton.setBackground(Color.magenta);



        f.add(convertButton);
        f.add(choice1);
        f.add(choice2);
        f.add(label1);
        f.add(label2);
        f.add(label3);
        f.add(label4);
        f.add(label5);
        f.add(textField1);

        f.addWindowListener(this);
        convertButton.addActionListener(this);


        for(String c : currencyList.keySet()){
            choice1.add(c);
            choice2.add(c);
        }

    }


    public void windowClosing(WindowEvent e) {
        f.dispose();
    }

    public void actionPerformed(ActionEvent e){
        double result;
        double value=0;

        String currencyFrom = choice1.getSelectedItem();
        String currencyTo = choice2.getSelectedItem();
        try{
            value=Double.parseDouble(textField1.getText());
            result=0;

            if(currencyTo==currencyFrom)
            {
                result=value;
            }
            else
            {
                for(Map.Entry<String, Double> entry : currencyList.entrySet())
                {
                    if(currencyFrom==entry.getKey())
                    {
                        result=value*entry.getValue();
                        if(currencyTo=="USD")
                        {
                            break;
                        }
                        else
                        {
                            for(Map.Entry<String,Double> entry1 : currencyList.entrySet())
                            {
                                if(currencyTo==entry1.getKey())
                                {
                                    result=result*entry1.getValue();
                                    break;
                                }
                            }
                        }
                    }
                }

            }

            label5.setText(String.format("%.2f",result));

        } catch(Exception err){
            System.out.println("Enter a value to convert");
        }


    }

    public static void main(String[] args){
        new MyCalc();
    }
}




