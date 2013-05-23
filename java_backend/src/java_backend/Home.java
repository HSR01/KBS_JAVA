package java_backend;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Jelle
 */
class Home extends JPanel {
    private JPanel homelogo, homecontent;
    private JLabel contenttext, startimg;
    public Home() {
        this.setLayout(new BorderLayout());       
        homelogo = new JPanel();
        homecontent = new JPanel();
        contenttext = new JLabel();
        startimg = new JLabel();
        
        try {
            BufferedImage start = ImageIO.read(new URL("http://www.tztpost.nl/start.png"));
            //nog even kijken naar logo bovenaan de pagina met laden afbeelding van internet.
            this.startimg.setIcon(new javax.swing.ImageIcon(start));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        homelogo.add(startimg);
        contenttext.setText("<html>Welkom bij TZT Post. <br><br>Dit is het backoffice systeem van TZT Post.<br> Maak een keuze uit een van de bovenstaande items.<br><br> Voor meer informatie ga naar <a href='www.tztpost.nl'>TZTPost.nl</a><br><br><br><br><br><br><br><br><br><br>Software v1.0<br>Door: Jelle, Laurens, Justin, Leon, Dominique en Daniel</html>");
        homecontent.add(contenttext);
        homelogo.add(startimg);
        this.add(this.homelogo, BorderLayout.WEST);
        this.add(this.homecontent, BorderLayout.CENTER);

        this.setVisible(true);
    }
    
}
