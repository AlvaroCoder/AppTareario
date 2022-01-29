package Frontend;

import Backend.Conexion;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class FormTarea extends JPanel{
    
    JPanel Form;
    JTextField text_nombre;
    JTextArea text_descripcion;
    JLabel lab_nombre, lab_descripcion, lab_estados, lab_space, lab_titulo;
    JComboBox box_estado;
    JButton btn_guardar;
    
    GridBagLayout gbl;
    GridBagConstraints gbc;
    
    Dimension lab_dimension;
    
    Conexion conn;
    Color font_colors = Color.white;
    public FormTarea(){
        
        Form = new JPanel();
        setBackground(Color.decode("#14213D"));
        lab_dimension = new Dimension(200,50);
        
        lab_titulo = new JLabel("Nueva Tarea");
        lab_titulo.setPreferredSize(lab_dimension);
        lab_titulo.setFont(new Font("Arial",Font.BOLD, 20));
        lab_titulo.setForeground(Color.white);
        lab_titulo.setHorizontalAlignment(JLabel.CENTER);
        
        lab_nombre = new JLabel("Nombre");
        lab_nombre.setPreferredSize(lab_dimension);
        lab_nombre.setForeground(font_colors);

        lab_descripcion = new JLabel("Descripcion");
        lab_descripcion.setPreferredSize(lab_dimension);
        lab_descripcion.setForeground(font_colors);
        
        lab_estados = new JLabel("Estado");
        lab_estados.setPreferredSize(lab_dimension);
        lab_estados.setForeground(font_colors);
        
        lab_space = new JLabel("");
        lab_space.setPreferredSize(lab_dimension);
        
        text_nombre = new JTextField();
        text_nombre.setPreferredSize(new Dimension(300, 30));
        text_nombre.setMargin(new Insets(5,5,5,5));
        
        text_descripcion = new JTextArea();
        text_descripcion.setPreferredSize(new Dimension(300,100));
        //Modificamos el salto de linea en el jtextArea
        text_descripcion.setLineWrap(true);
        text_descripcion.setWrapStyleWord(true);
        text_descripcion.setMargin(new Insets(10,10,10,10));
        
        String[] estados = {"No empezado","En proceso","Terminado"};
        box_estado = new JComboBox(estados);
        box_estado.setPreferredSize(new Dimension(300,20));
        
        
        gbl = new GridBagLayout();
        gbc = new GridBagConstraints();
        
        setLayout(gbl);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
                
        gbc.gridx = 0;
        
        gbc.gridy = 0;
        add(lab_titulo,gbc);
        gbc.gridy = 1;
        add(lab_nombre,gbc);
        
        gbc.gridy = 2;
        add(text_nombre,gbc);
        
        gbc.gridy = 3;
        add(lab_descripcion,gbc);
        
        gbc.gridy = 4;
        add(text_descripcion,gbc);
        
        gbc.gridy = 5;
        add(lab_estados,gbc);
        
        gbc.gridy = 6;
        add(box_estado,gbc);
        
        gbc.gridy = 7;
        add(lab_space,gbc);
        
    }
    public void add_Btn(JButton btn){
        gbc.gridy = 7;
        add(btn, gbc);
    }
    public boolean isTextEmpty(){
        String nombre = text_nombre.getText().strip();
        if (nombre.isEmpty() ) {
           return true;
        }else{
            return false;
        }
    }
    public void clearText(){
        text_nombre.setText("");
        text_descripcion.setText("");
    }
    public void sendData(){
        String nombre = text_nombre.getText();
        String descripcion = text_descripcion.getText();
        int estado = box_estado.getSelectedIndex();
        conn = new Conexion();
        conn.crearTarea(nombre, descripcion, estado);
    }
}
