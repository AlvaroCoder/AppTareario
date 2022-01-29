package Frontend.Componentes;

import Backend.Conexion;
import Frontend.TablaTareas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Tarea extends JPanel implements ActionListener{
    public TablaTareas tabla;
    int idTarea, estado;
    String nombreTarea,descripcionTarea;
    public Tarea apuntador;
    ContenedorBarra barra;
    
    Conexion con;
    
    Icon iconCheck, iconDelete, iconPlay, iconStop, iconReStart;
    ImageIcon imageCheck, imageDelete, imagePlay, imageStop, imageReStart;
    
    JButton btnCheck, btnEliminar, btnPlay, btnStop, btnRestart;
    JPanel etiqueta, descripcion, panelSup, panelInf, ctnBtn;
    JLabel labelTarea, labelEstado;
    JTextArea labelDescripcion;
    Dimension dimension;
    public Tarea(int idTarea, String nombreTarea, int estado, String descripcion){
        this.idTarea = idTarea;
        this.nombreTarea = nombreTarea;
        this.estado  = estado;
        this.descripcionTarea = descripcion;
        
        dimension = new Dimension(600,70);
        
        setLayout(null);
        iniciarComp();
        setPreferredSize(dimension);
    }
    public void setContenedor(ContenedorBarra barra){
        this.barra = barra;
    }
    public void setTablaTareas(TablaTareas tabla){
        this.tabla = tabla;
    }
    public void inicializarIconos(){
        int widthImage = 30, heigthImage = 30, scale = Image.SCALE_DEFAULT;
        
        imageCheck = new ImageIcon("./src/Imagenes/CheckIcon.png");
        iconCheck = new ImageIcon(imageCheck.getImage().getScaledInstance(widthImage, heigthImage, scale));
        
        imageDelete = new ImageIcon("./src/Imagenes/DeleteIcon.png");
        iconDelete = new ImageIcon(imageDelete.getImage().getScaledInstance(widthImage, heigthImage, scale));
        
        imagePlay = new ImageIcon("./src/Imagenes/PlayIcon.png");
        iconPlay = new ImageIcon(imagePlay.getImage().getScaledInstance(widthImage, heigthImage, scale));
        
        imageStop = new ImageIcon("./src/Imagenes/PauseIcon.png");
        iconStop = new ImageIcon(imageStop.getImage().getScaledInstance(widthImage, heigthImage, scale));
        
        imageReStart = new ImageIcon("./src/Imagenes/ResStartIcon.png");
        iconReStart = new ImageIcon(imageReStart.getImage().getScaledInstance(widthImage, heigthImage, scale));
        
        btnCheck.setIcon(iconCheck);
        btnCheck.setMargin(new Insets(5,5,5,5));
        btnEliminar.setIcon(iconDelete);
        btnEliminar.setMargin(new Insets(5,5,5,5));
        btnPlay.setIcon(iconPlay);
        btnStop.setIcon(iconStop);
        btnRestart.setIcon(iconReStart);
    }
    public JPanel addJPanel(JPanel panel, int X, int Y, int Width, int Heigth){
        JPanel pane = panel;
        pane.setSize(Width,Heigth);
        pane.setLocation(X,Y);
        pane.setLayout(new FlowLayout(FlowLayout.LEFT, 10,10));
        return pane;
    }
    public void iniciarComp(){
        con = new Conexion();
        etiqueta = new JPanel();
        labelTarea = new JLabel();
        labelEstado = new JLabel();
        labelDescripcion = new JTextArea();
        
        btnCheck = new JButton();
        btnEliminar = new JButton();
        btnPlay = new JButton();
        btnStop = new JButton();
        btnRestart = new JButton();
        
        descripcion = new JPanel();
        panelSup = new JPanel();
        panelInf = new JPanel();
        ctnBtn = new JPanel();
        
        Dimension btnSize = new Dimension(40,40);
        
        inicializarIconos();
        
        ctnBtn.setLayout(new GridBagLayout());
        ctnBtn.setPreferredSize(new Dimension(40,40));
        ctnBtn.add(btnPlay);
        ctnBtn.add(btnStop);
        ctnBtn.add(btnRestart);
        
        btnCheck.setPreferredSize(btnSize);
        btnCheck.setBackground(Color.decode("#8E8E8E"));
        btnCheck.addActionListener(this );
        
        btnEliminar.setPreferredSize(btnSize);
        btnEliminar.setBackground(Color.decode("#D62828"));
        btnEliminar.addActionListener(this);
        
        btnRestart.setPreferredSize(btnSize);
        btnRestart.setBackground(Color.decode("#464A5A"));
        btnRestart.addActionListener(this);
        
        btnStop.setPreferredSize(btnSize);
        btnStop.setBackground(Color.decode("#464A5A"));
        btnStop.addActionListener(this);
        
        btnPlay.setPreferredSize(btnSize);
        btnPlay.setBackground(Color.decode("#464A5A"));
        btnPlay.addActionListener(this);
        
        labelTarea.setText(this.nombreTarea);
        labelTarea.setPreferredSize(new Dimension(300,40));
        etiqueta.setPreferredSize(new Dimension(100,40));
        
        switch(estado){
            case 0:
                btnStop.setVisible(false);
                btnRestart.setVisible(false);
                btnPlay.setVisible(true);
                
                etiqueta.setBackground(Color.decode("#FCBF49"));
                 panelSup.setBackground(Color.decode("#FEF6CD"));
                 panelInf.setBackground(Color.decode("#FEF6CD"));
                 setBackground(Color.decode("#FEF6CD"));
                labelEstado.setText("No empezado");
                break;
            case 1:
                btnPlay.setVisible(false);
                btnStop.setVisible(true);
                btnRestart.setVisible(false);
                
                etiqueta.setBackground(Color.decode("#C4C4C4"));
                panelSup.setBackground(Color.decode("#FEF6CD"));
                setBackground(Color.decode("#FEF6CD"));
                panelInf.setBackground(Color.decode("#FEF6CD"));
                labelEstado.setText("En proceso");
                break;
            case 2:
                ctnBtn.setVisible(false);
                btnCheck.setEnabled(false);
                etiqueta.setBackground(Color.decode("#FFFFFF"));
                labelEstado.setText("Terminado");
                btnCheck.setBackground(Color.decode("#003049"));
                panelSup.setBackground(Color.decode("#E5E5E5"));
                panelInf.setBackground(Color.decode("#E5E5E5"));
                setBackground(Color.decode("#E5E5E5"));
                break;
        }
        if (!descripcionTarea.equals("")) {
            int widthDescripcion = 300, heigthDescripcion = 30;
            Font fontDescripcion = new Font("Arial",Font.PLAIN, 12);
            
            labelDescripcion.setText(descripcionTarea);
            labelDescripcion.setFont(fontDescripcion);
            labelDescripcion.setLineWrap(true);
            labelDescripcion.setEditable(false);
            labelDescripcion.setMargin(new Insets(5,5,5,5));
            
            labelDescripcion.setBackground(Color.decode("#FEF49D"));
            
            FontMetrics fm = labelDescripcion.getFontMetrics(fontDescripcion);
            int anchoDescripcion = fm.stringWidth(descripcionTarea);
            while(anchoDescripcion>widthDescripcion){
                heigthDescripcion+=10;
                anchoDescripcion = anchoDescripcion%widthDescripcion;
            }
            labelDescripcion.setPreferredSize(new Dimension(widthDescripcion,heigthDescripcion));
            panelInf.add(labelDescripcion);
            dimension.setSize(600, (90+heigthDescripcion));
            add(addJPanel(panelInf,40,70,500,70));
        }
        
        etiqueta.add(labelEstado);
        panelSup.add(btnCheck);
        panelSup.add(labelTarea);
        panelSup.add(etiqueta);
        panelSup.add(btnEliminar);
        panelSup.add(ctnBtn);
        add(addJPanel(panelSup,0,0,600,70));
        
    }
    public int getId(){
        return this.idTarea;
    }
    public void checkTarea(int indexTarea){
            con.checkTarea(indexTarea);
            tabla.actualizarBarra();
            
            labelEstado.setText("Terminado");
            etiqueta.setBackground(Color.white);
            btnCheck.setBackground(Color.decode("#003049"));
            btnCheck.setEnabled(false);
            setBackground(Color.decode("#E5E5E5"));
            
            panelSup.setBackground(Color.decode("#E5E5E5"));
            panelInf.setBackground(Color.decode("#E5E5E5"));
            ctnBtn.setVisible(false);
            this.updateUI();
        }
        public void eliminarTarea(int indexTarea){
            con.eliminarTarea(indexTarea);
            tabla.actualizarBarra();
            
            panelSup.setVisible(false);
            panelInf.setVisible(false);
            this.setVisible(false);
            this.updateUI();
        }
        public void empezarTarea(int indexTarea){
            con.empezarTarea(indexTarea);
            tabla.actualizarBarra();
            
            labelEstado.setText("En proceso");
            etiqueta.setBackground(Color.decode("#C4C4C4"));
            btnPlay.setVisible(false);
            btnStop.setVisible(true);
            btnRestart.setVisible(false);
            this.updateUI();
        }
        public void pararTarea(int indexTarea){
            con.pararTarea(indexTarea);
            tabla.actualizarBarra();
            
            labelEstado.setText("No empezado");
            etiqueta.setBackground(Color.decode("#FCBF49"));
            panelSup.setBackground(Color.decode("#FEF6CD"));
            panelInf.setBackground(Color.decode("#FEF6CD"));
            
            btnStop.setVisible(false);
            btnPlay.setVisible(true);
            btnRestart.setVisible(false);
            this.setBackground(Color.decode("#FCBF49"));
            this.updateUI();
        }
    @Override
    public String toString() {
        return "Tarea{" + "idTarea=" + idTarea + ", estado=" + estado + ", nombreTarea=" + nombreTarea +", tablaTareas= "+tabla+", apuntador=" + apuntador + '}';
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCheck) {
            checkTarea(idTarea);}
                else if (e.getSource() == btnEliminar) {
                    eliminarTarea(idTarea);
                }
                else if (e.getSource() == btnPlay) {
                    empezarTarea(idTarea);
                }else if(e.getSource()==btnStop){
                    pararTarea(idTarea);
                }
    }
}
