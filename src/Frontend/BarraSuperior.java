package Frontend;

import Backend.Conexion;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.time.LocalDate;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class BarraSuperior extends JPanel{
    private int numTareas;
    private JLabel labelTareas;
    private JPanel  panelTareas;
    public LocalDate fecha;
    private int fechaDia, fechaMes, fechaAño ;
    
    GridBagLayout gbl;
    GridBagConstraints gbc;
    Conexion conexion;
    
    public BarraSuperior(){
        fecha = LocalDate.now();
        gbl = new GridBagLayout();
        gbc = new GridBagConstraints();
        conexion = new Conexion();
        setBackground(Color.decode("#8E8E8E"));
        setPreferredSize(new Dimension(600,55));
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        iniciarComponentes();
    }
    
    private void iniciarComponentes(){
        labelTareas = new JLabel();
        panelTareas = new JPanel();
        panelTareas.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelTareas.setPreferredSize(new Dimension(400,50));
        panelTareas.setBackground(Color.decode("#8E8E8E"));
        
        labelTareas.setForeground(Color.WHITE);
        labelTareas.setFont(new Font("Arial",Font.BOLD,18));
        iniciarDatos();
        panelTareas.add(labelTareas);
        add(panelTareas);
    }
    private void iniciarDatos(){
        fechaDia = fecha.getDayOfMonth();
        fechaMes = fecha.getMonthValue();
        fechaAño = fecha.getYear();
        conexion.setNumTarea(fechaDia, fechaMes, fechaAño);
        numTareas = conexion.getNumTareas();
        labelTareas.setText(Integer.toString(numTareas)+" tareas pendientes. ");
    }
    
    public void actualizar(){
        iniciarDatos();
        updateUI();
    }
}
