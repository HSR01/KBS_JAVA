/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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

        contenttext.setText("Welkom bij TZT Postaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa.");
        homecontent.add(contenttext);
        homelogo.add(startimg);
        this.add(this.startimg, BorderLayout.WEST);
        this.add(this.homecontent, BorderLayout.CENTER);

        this.setVisible(true);
    }
    
}
