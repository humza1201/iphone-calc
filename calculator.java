import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class calculator {
    int width = 360;
    int height = 540;
    Color gry = new Color(212, 212, 210);
    Color dgry = new Color(80, 80, 80);
    Color blk = new Color(28, 28, 28);
    Color org = new Color(255, 149, 0);

    String[] buttVals = {
            "AC", "+/-", "%", "/",
            "7", "8", "9", "*",
            "4", "5", "6", "-",
            "1", "2", "3", "+",
            ".", "0", "sqrt", "="
    };

    String[] right = { "+", "*", "-", "/","=","sqrt" };
    String[] top = { "AC", "+/-", "%" };

    JFrame frame = new JFrame("my calc");
    JLabel label = new JLabel();
    JPanel panel = new JPanel();
    JPanel buttpan = new JPanel();

    String A = "0";
    String operator = null;
    String B = null;

    @SuppressWarnings("ConvertToStringSwitch")
    public calculator() {
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        label.setBackground(blk);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Ariel", Font.PLAIN, 80));
        label.setHorizontalAlignment(JLabel.RIGHT);// to set the label at right
        label.setText("0");
        label.setOpaque(true);

        panel.setLayout(new BorderLayout());
        panel.add(label);

        frame.add(panel, BorderLayout.NORTH);

        buttpan.setLayout(new GridLayout(5, 4));
        buttpan.setBackground(blk);
        frame.add(buttpan);

        //imp
        for (String buttVal : buttVals) {
            JButton but = new JButton();
            String buttvalue = buttVal;
            but.setFont(new Font("Ariel", Font.PLAIN, 30));
            but.setText(buttvalue);
            but.setFocusable(false);
            but.setBorder(new LineBorder(blk));
            if (Arrays.asList(top).contains(buttvalue)) {
                // but.setBackground(Color.decode("#2F4F7F"));
                but.setBackground(gry);
                but.setForeground(blk);
            } else if (Arrays.asList(right).contains(buttvalue)) {
                but.setBackground(org);
                but.setForeground(Color.WHITE);
            } else {
                but.setBackground(dgry);
                but.setForeground(Color.WHITE);
            }
            buttpan.add(but);

            but.addActionListener((ActionEvent e) -> {
                JButton button = (JButton) e.getSource();
                String buttonvalue = button.getText();
                if (Arrays.asList(right).contains(buttonvalue)) {
                    if(buttonvalue.equals("=")){
                        if(A!=null){
                            B = label.getText();
                            double numA = Double.parseDouble(A);
                            double numB = Double.parseDouble(B);
                            if(operator.equals("+")){
                                label.setText(whole(numA+numB));
                            }
                            else if(operator.equals("-")){
                                label.setText(whole(numA-numB));
                            }
                            else if(operator.equals("*")){
                                label.setText(whole(numA*numB));
                            }
                            else if(operator.equals("/")){
                                if(numB!=0){
                                    label.setText(whole(numA/numB));
                                }
                            }
                            else if(operator.equals("sqrt")){
                                if(numA>=0){
                                    label.setText(whole(Math.sqrt(numA)));
                                }
                            }
                            clear();
                        }
                    }
                    else if ("+-*/sqrt".contains(buttonvalue)) {//imp
                        if(operator == null){
                            A = label.getText();
                            label.setText("0");
                            B = "0";
                        }
                        operator = buttonvalue;
                    }
                    
                } else if (Arrays.asList(top).contains(buttonvalue)) {
                    if (buttonvalue.equals("AC")) {
                        clear();
                        label.setText("0");
                    } else if (buttonvalue.equals("+/-")) {
                        double num = Double.parseDouble(label.getText());
                        num *= -1;
                        label.setText(whole(num));
                        
                    } else if (buttonvalue.equals("%")) {
                        double num = Double.parseDouble(label.getText());
                        num /= 100;
                        label.setText(whole(num));
                    }
                    
                } else {
                    if (buttonvalue.equals(".")) {
                        if (!label.getText().contains(buttonvalue)) {
                            label.setText(label.getText() + buttonvalue);
                        }
                    } else if ("0123456789".contains(buttonvalue)) {
                        if ("0".equals(label.getText())) {
                            label.setText(buttonvalue);
                        } else {
                            label.setText(label.getText() + buttonvalue);
                        }
                    }
                }
            });
            frame.setVisible(true);
        }
    }

    public String whole(double num){
         if(num%1==0){
          return Integer.toString((int)num);
        }
        return Double.toString(num);
    }

    public void clear(){
        A = "0";
        operator = null;
        B = null;
    }
}