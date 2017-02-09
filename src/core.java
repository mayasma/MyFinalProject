import javax.swing.JFrame;


 public class core 
 {

    static String str;
    public static void main(String[] args) 
        {
        JFrame j = new JFrame("Core");
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setSize(400, 400);
        SimpleHashFunc pnl = new SimpleHashFunc();
        j.add(pnl);
        j.setVisible(true);

    }

}

