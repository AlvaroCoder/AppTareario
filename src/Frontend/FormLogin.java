package Frontend;

import Backend.Conexion;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class FormLogin extends JFrame {

    PanelLogin panel_login ;
    PanelLoading panel_loading;
    PanelBackground panelBackground;
    
    public FormLogin(){
        super("App de Tareas");
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        iniciarcomponentes();
    }
    
    private void iniciarcomponentes(){
        panelBackground = new PanelBackground();
        getContentPane().add(panelBackground);
        pack();
    }
}

class PanelBackground extends JPanel{
    PanelLogin panelLogin;
    GridBagLayout gbl;
    GridBagConstraints gbc;
    PanelBackground(){
        setBackground(Color.white);
        setPreferredSize(new Dimension(900,500));
        iniciarComponentesBackground();
    }
    public HashMap getUser(){
        return panelLogin.getUser();
    }
    public void iniciarComponentesBackground(){
        
        gbl = new GridBagLayout();
        panelLogin = new PanelLogin();
        setLayout(gbl);
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(panelLogin.InicioPanel(),gbc);
        add(panelLogin.IngresarPanel(),gbc);
        add(panelLogin.RegistrarPanel(),gbc);
    }
}

class PanelLogin extends JPanel{
    Frame frame;
    Conexion conn;
    HashMap usuario ;
    
    private JPanel ingresarPanel, registrarPanel, inicioPanel;
    private JButton ingresarBtn, registrarBtn, submmitBtn_R, submmitBtn_I;
    private JLabel titulo;
    GridBagLayout gbl_ingresar, gbl_registrar;
    GridBagConstraints gbc_ingresar, gbc_registrar;
    JTextField name_field_R, email_field_R;
    JTextField name_field_I;
   
    JPasswordField  password_field_R, password_field_I;
    
    final String rutaImgBack = "src/Imagenes/FondoAzul1.png";
    
    public Icon iconImage(String ruta, int width, int heigth){
        ImageIcon image = new ImageIcon(ruta);
        Icon icon = new ImageIcon(image.getImage().getScaledInstance(width, heigth, Image.SCALE_DEFAULT));
        return icon;
    }
    public JPanel IngresarPanel(){
        //Inicilizamos los layouts
        gbl_ingresar = new GridBagLayout();
        gbc_ingresar = new GridBagConstraints();

        Icon iconoWalpaper = iconImage(rutaImgBack,300,500);
        JLabel lab_Img = new JLabel();
        lab_Img.setIcon(iconoWalpaper);
       
        Icon user = iconImage("src/Imagenes/User.png", 40,40);
        JLabel lab_ingresar = new JLabel("Iniciar sesion");
        lab_ingresar.setIcon(user);
        lab_ingresar.setVerticalAlignment(JLabel.CENTER);
        lab_ingresar.setHorizontalAlignment(JLabel.CENTER);
        
        name_field_I = new JTextField();
        password_field_I = new JPasswordField();
        submmitBtn_I = new JButton("INICIAR");
        submmitBtn_I.addActionListener(new ActionBtn());
        submmitBtn_I.setPreferredSize(new Dimension(200,50));
        submmitBtn_I.setBackground(Color.BLUE);
        submmitBtn_I.setForeground(Color.WHITE);
        
        JPanel pane_Btn = new JPanel();
        pane_Btn.setLayout(new GridBagLayout());
        pane_Btn.setBackground(Color.WHITE);
        pane_Btn.add(submmitBtn_I);
        
        JLabel labelName = new JLabel("Nombre");
        JLabel labelPassword = new JLabel("Contraseña");
        
        JPanel panelField = new JPanel();
        panelField.setPreferredSize(new Dimension(200,300));
        panelField.setBackground(Color.WHITE);
        panelField.setLayout(new GridLayout(6,0,10,10));
        panelField.add(lab_ingresar);
        panelField.add(labelName);
        panelField.add(name_field_I);
        panelField.add(labelPassword);
        panelField.add(password_field_I);
        panelField.add(pane_Btn);
        
        JPanel panelBackField = new JPanel();
        panelBackField.setBackground(Color.WHITE);
        panelBackField.setPreferredSize(new Dimension(600,500));
        panelBackField.setLayout(new GridBagLayout());
        panelBackField.add(panelField);
        
        gbc_ingresar.fill = GridBagConstraints.VERTICAL;
        gbc_ingresar.gridx = 0;
        gbc_ingresar.gridy = 0;
        
        ingresarPanel = new JPanel();
        ingresarPanel.setVisible(false);
        ingresarPanel.setLayout(gbl_ingresar);
        ingresarPanel.add(panelBackField,gbc_ingresar);
        
        gbc_ingresar.gridx = 1;
        gbc_ingresar.weighty = 1;
        ingresarPanel.add(lab_Img,gbc_ingresar);
        
        return ingresarPanel;
        
    }
    public JPanel RegistrarPanel(){
        
        JLabel lab_registrar = new JLabel("Registrarse");
        lab_registrar.setHorizontalAlignment(JLabel.CENTER);
        
        JPanel panelBackField = new JPanel();
        panelBackField.setLayout(new GridBagLayout());
        panelBackField.setBackground(Color.WHITE);
        panelBackField.setPreferredSize(new Dimension(600,500));
        
        JPanel panelField = new JPanel();
        panelField.setPreferredSize(new Dimension(200,350));
        panelField.setBackground(Color.WHITE);
        
        JLabel labelName = new JLabel("Nombre ");
        JLabel labelPassword = new JLabel("Contraseña ");
        JLabel labelEmail = new JLabel("Email");
        
        name_field_R = new JTextField();
        password_field_R = new JPasswordField();
        email_field_R = new JTextField();
        
        submmitBtn_R = new JButton("REGISTRAR");
        submmitBtn_R.setName("Boton1");
        submmitBtn_R.setBackground(Color.BLUE);
        submmitBtn_R.setPreferredSize(new Dimension(400,40));
        submmitBtn_R.setForeground(Color.WHITE);
        submmitBtn_R.addActionListener(new ActionBtn());
        
        JPanel pane_Btn = new JPanel();
        pane_Btn.setLayout(new FlowLayout(FlowLayout.CENTER));
        pane_Btn.setBackground(Color.WHITE);
        pane_Btn.add(submmitBtn_R);
        
        panelField.setLayout(new GridLayout(8,0,10,10));
        panelField.add(lab_registrar);
        panelField.add(labelName);
        panelField.add(name_field_R);
        panelField.add(labelPassword);
        panelField.add(password_field_R);
        panelField.add(labelEmail);
        panelField.add(email_field_R);
        panelField.add(pane_Btn);
        
        panelBackField.add(panelField);
        
        Icon iconBack = iconImage(rutaImgBack,300,500);
        JLabel imgBack = new JLabel();
        imgBack.setIcon(iconBack);
        
        gbl_registrar = new GridBagLayout();
        gbc_registrar = new GridBagConstraints();
        gbc_registrar.fill = GridBagConstraints.VERTICAL;
        gbc_registrar.gridx = 0;
        gbc_registrar.gridy = 0;
        
        registrarPanel = new JPanel();
        registrarPanel.setVisible(false);
        registrarPanel.setLayout(gbl_registrar);
        registrarPanel.add(panelBackField,gbc_registrar);
        
        gbc_registrar.gridx = 1;
        registrarPanel.add(imgBack, gbc_registrar);
        return registrarPanel;
    }
    public JPanel InicioPanel(){
        Font btnFont = new Font("Arial", Font.PLAIN, 15);
        
        titulo = new JLabel("App de Tareas");
        titulo.setFont(new Font("Bernard MT Condensed",Font.BOLD,30));
        
        titulo.setHorizontalAlignment(JLabel.CENTER);
        titulo.setForeground(Color.BLUE);
        
        registrarBtn = new JButton("Registrar");
        registrarBtn.setFont(btnFont);
        registrarBtn.setBackground(Color.white);
        registrarBtn.addActionListener(new ActionBtn());
        
        ingresarBtn = new JButton("Ingresar");
        ingresarBtn.setFont(btnFont);
        ingresarBtn.setBackground(Color.BLUE);
        ingresarBtn.setForeground(Color.WHITE);
        ingresarBtn.addActionListener(new ActionBtn());
        
        inicioPanel = new JPanel();
        inicioPanel.setPreferredSize(new Dimension(300,200));
        inicioPanel.setLayout(new GridLayout(3,0,30,10));
        inicioPanel.setBackground(Color.WHITE);
        inicioPanel.add(titulo);
        inicioPanel.add(ingresarBtn);
        inicioPanel.add(registrarBtn);
        
        return inicioPanel;
    }
    public HashMap getUser(){
        return this.usuario;
    }
    public PanelLogin(){
        conn = new Conexion();
        frame = new Frame();
    }
    public boolean validateUser(String name, String password, String email){
        boolean estado;
        if ("".equals(name) || "".equals(password)) {
            estado = false;
            JOptionPane.showMessageDialog(null, "Por favor complete los espacios en blanco ","Error",JOptionPane.ERROR_MESSAGE);
        }
        else{
            estado  = true;
            String mensaje = conn.crearUsuario(name, password, email);
            usuario = conn.traerUsuario(name, password);
            JOptionPane.showMessageDialog(null, mensaje);
            
        }
        return estado;
    }
    public boolean validateUser(String name, String password){
        boolean estado;
        if ("".equals(name)|| "".equals(password)) {
            estado = false;
            JOptionPane.showMessageDialog(null, "Por favor complete los espacios en blanco ","Error",JOptionPane.ERROR_MESSAGE);
        }else{
            estado = true;
            usuario = conn.traerUsuario(name, password);
        }
        return estado;
    }
    class ActionBtn implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == ingresarBtn ) {
                inicioPanel.setVisible(false);
                ingresarPanel.setVisible(true);
            }
            
            if(e.getSource() == registrarBtn){
                inicioPanel.setVisible(false);
                registrarPanel.setVisible(true);
            }
            
            if (e.getSource() == submmitBtn_R ) {
                String name = name_field_R.getText().strip();
                String password = password_field_R.getText().strip();
                String email = email_field_R.getText();

                if (validateUser(name, password, email)) {
                    
                }
            }
            if (e.getSource() == submmitBtn_I) {
                String name = name_field_I.getText();
                String password = password_field_R.getText();
                
                if (validateUser(name, password)) {
                    
                }
                
            }
        }
    
    }
}


class PanelLoading extends JPanel{
    PanelLoading(){
        setVisible(false);
    }
}