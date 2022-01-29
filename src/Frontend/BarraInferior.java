package Frontend;

import Backend.Conexion;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.time.LocalDate;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BarraInferior extends JPanel{
    Conexion conexion;
    private JLabel labelTareasNoEmp, labelTareasEnProc, labelTareasTerm;
    private int fechaDia, fechaMes, fechaAño;
    protected LocalDate fecha;
    public BarraInferior(){
        setBackground(Color.decode("#DFDFDF"));
        setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
        setPreferredSize(new Dimension(600, 35));
        conexion = new Conexion();
        fecha = LocalDate.now();
        iniciarComponentes();
    }
    private void iniciarComponentes(){
        Font labelFont = new Font("Arial",Font.PLAIN,15);
        labelTareasNoEmp = new JLabel();
        labelTareasNoEmp.setFont(labelFont);
        labelTareasNoEmp.setForeground(Color.decode("#003049"));
        labelTareasEnProc = new JLabel();
        labelTareasEnProc.setFont(labelFont);
        labelTareasEnProc.setForeground(Color.decode("#003049"));
        labelTareasTerm = new JLabel();
        labelTareasTerm.setFont(labelFont);
        labelTareasTerm.setForeground(Color.decode("#003049"));
        
        mostrarDatos();
        
        add(labelTareasNoEmp);
        add(labelTareasEnProc);
        add(labelTareasTerm);
    }
    private void mostrarDatos(){
        fechaDia = fecha.getDayOfMonth();
        fechaMes = fecha.getMonthValue();
        fechaAño = fecha.getYear();
        
        conexion.setNumTareasNoEmp(fechaDia, fechaMes, fechaAño);
        conexion.setNumTareasEnProc(fechaDia, fechaMes, fechaAño);
        conexion.setNumTareasTerm(fechaDia, fechaMes, fechaAño);
        
        labelTareasNoEmp.setText("No empezadas : "+Integer.toString(conexion.getNumTareasNoEmp()));
        labelTareasNoEmp.updateUI();
        labelTareasEnProc.setText("En proceso : "+Integer.toString(conexion.getNumTareasEnProc()));
        labelTareasEnProc.updateUI();
        labelTareasTerm.setText("Terminadas : "+Integer.toString(conexion.getNumTareasTerm()));
        labelTareasTerm.updateUI();
    }
    public void actualizar(){
        mostrarDatos();
        updateUI();
    }
}
