/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lanzaayuda;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.net.URL;
import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.JOptionPane;

/**
 *
 * @author Luis
 */
public class LanzaAyuda implements ActionListener{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //creamos y mostramos la aplicacion
        javax.swing.SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
               createAndShowGUI();
            }
            
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    //Ceacion del menuBar
    public JMenuBar createMenuBar(){
        JMenuBar menuBar;
        JMenu menu;
        JMenuItem menuItem;
        
        //Creamos el menuBar
        menuBar = new JMenuBar();
        
        //añadimos el primer menu Al menu Bar
        menu = new JMenu("Ayuda");
        menu.setMnemonic(KeyEvent.VK_A); //cuando pulsemos Alt + A se nos desplegara el menu de ayuda
        //Asignamos informacion sobre este menu
        menu.getAccessibleContext().setAccessibleDescription("Menu de ayuda donde encontraremos lo necesario para aprender sobre esta aplicación");
        
        //Añadimos el menu al menuBar
        menuBar.add(menu);
        
        //Añadimos el menuItem a nuestro menu
        menuItem = new JMenuItem("ayuda", KeyEvent.VK_F1); //Si le damos a F1 se nos abrira directamente la ayuda
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, ActionEvent.ALT_MASK));
        
        //Instanciamos la ayuda
        instanciarAyuda(menuItem);
        
        //Añadimos el menuItem al menu
        menu.add(menuItem);
        
        //Añadimos un segundo menu para salir de la aplicacion
        menu = new JMenu("Salir");
        menuBar.add(menu);
        
        
        return menuBar;
        
    }
    
    private static void createAndShowGUI(){
        
        //Creamos la ventana
        JFrame frame = new JFrame("Lanza Ayuda");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //seteamos los elementos
        LanzaAyuda ayuda = new LanzaAyuda();
        frame.setJMenuBar(ayuda.createMenuBar());
        
        
        //mostramos la ventana
        frame.setSize(450, 260);
        frame.setVisible(true);
    }
    
    //Procedimiento de incorporacion de ayuda
    public static HelpSet obtenerHelpSet(){
        try{
            ClassLoader cl = LanzaAyuda.class.getClassLoader();
            File file = new File("help/helpset.hs");
            URL url = file.toURI().toURL();
            
            //Creamos el objeto helpset
            HelpSet hs = new HelpSet(null, file.toURI().toURL());
            
            return hs;
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Fichero helpset no encontrado");
            return null;
        }
    }
    
    public static void instanciarAyuda(JMenuItem menuItem){
        //Creamos los objetos necesarios para visualizar la ayuda
        HelpSet hs = obtenerHelpSet();
        HelpBroker hb = hs.createHelpBroker();
        
        hb.enableHelpOnButton(menuItem, "principalView", hs);
        
    }
    
}
