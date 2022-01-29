package Frontend;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Frame extends JFrame{
    FormTarea form;
    PanelTarea panel;
    
    JButton btn_guardar;
    HashMap usuario;
    
    GridBagConstraints gbc;
    GridBagLayout gbl;
    public Frame(){
        
        super("APP DE TAREAS");
        ImageIcon icono = new ImageIcon("./src/Imagenes/LogoAppTareas.png");
        setIconImage(icono.getImage().getScaledInstance(26, 25, Image.SCALE_DEFAULT));
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1200,600));
        
        gbl = new GridBagLayout();
        setLayout(gbl);
        
        form = new FormTarea();
        panel = new PanelTarea();
          
        btn_guardar = new JButton("Guardar Tarea");
        btn_guardar.setPreferredSize(new Dimension(250,30));
        btn_guardar.setBackground(Color.decode("#FCA311"));
        btn_guardar.addActionListener(new Action());
        
        form.add_Btn(btn_guardar);
        
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1;
        gbc.weightx = 0.3;
        add(form,gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        add(panel, gbc);
        
        
    }
    
    
    class Action implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            boolean isEmpty = form.isTextEmpty();
            if (isEmpty) {
                JOptionPane.showMessageDialog(null, "Por favor llene los espacios en blanco", "Error", JOptionPane.ERROR_MESSAGE, null);
            }else{
                form.sendData();
                form.clearText();
                panel.actualizar();
                
            }
        }
    
    }
    
}
