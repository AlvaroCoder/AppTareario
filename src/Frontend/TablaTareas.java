package Frontend;

import Backend.Conexion;
import Frontend.Componentes.ContenedorBarra;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.time.LocalDate;
import java.util.HashMap;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import Frontend.Componentes.Tarea;
import java.awt.Insets;

public class TablaTareas extends JPanel{
    Conexion conexion;
    private int contador;
    LocalDate fecha ;
    private final JPanel panel;
    private final GridBagConstraints gbcPanel;
    private final JScrollPane PanelScroll;
    int lastIdTarea;
    
    ContenedorBarra contenedor;
    private int  heigth ;
    Tarea tarea = null;
    int longitud;
    Insets insetGrid;
    TablaTareas(){
        setLayout(new GridBagLayout());
        setBackground(Color.white);
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.white);
        
        PanelScroll = new JScrollPane();
        PanelScroll.setPreferredSize(new Dimension(750,400));
        PanelScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        PanelScroll.setViewportView(panel);
        PanelScroll.setBorder(null);
        add(PanelScroll);
        
        fecha = LocalDate.now();
         gbcPanel = new GridBagConstraints();
        gbcPanel.fill = GridBagConstraints.HORIZONTAL;
        gbcPanel.gridx = 0;
        gbcPanel.anchor = GridBagConstraints.PAGE_END;
        insetGrid =new Insets(10,0,0,0);
        gbcPanel.insets = insetGrid;
        conexion = new Conexion(fecha.getDayOfMonth(),fecha.getMonthValue(),fecha.getYear());
 
        mostrarTareas();
    }
    
    public void setContenedorBarra(ContenedorBarra barra){
        this.contenedor = barra;
    }
    
    public void mostrarTareas(){
        contador = 0;
        heigth = 10;
        conexion.mostrarTareas(fecha.getDayOfMonth()).forEach((element)->{
            HashMap hash = (HashMap) element;
            int idTarea = Integer.parseInt((String)hash.get(("idTarea")));
            String nombreTarea = (String)hash.get("nombre");
            int estado = Integer.parseInt((String)hash.get("estado"));
            String descripcion = (String)hash.get("descripcion");
            Tarea task = new Tarea(idTarea,nombreTarea,estado,descripcion);
            task.setTablaTareas(this);
            gbcPanel.gridy = contador++;
            panel.add(task,gbcPanel);
            if ("".equals(descripcion)) {
                heigth+=80;
            }else{
                heigth+=150;
            }
            lastIdTarea = idTarea;
            });
        panel.setSize(700,heigth);
        panel.updateUI();
    }
    public void agregarTarea(){
        if (lastIdTarea > 0) {
            lastIdTarea++;
            HashMap hash = conexion.traerTarea((lastIdTarea));
            int idTarea = Integer.parseInt((String)hash.get(("idTarea")));
            String nombreTarea = (String)hash.get("nombre");
            int estado = Integer.parseInt((String)hash.get("estado"));
            String descripcion = (String)hash.get("descripcion");
            Tarea task = new Tarea(idTarea,nombreTarea,estado,descripcion);
            task.setTablaTareas(this);
            if ("".equals(descripcion)) {
                heigth+=80;
            }else{
                heigth+=150;
            }
            gbcPanel.gridy = contador++;
            panel.add(task,gbcPanel);
            panel.setSize(700,heigth);
            panel.updateUI();
        }else{
            mostrarTareas();
        }
    }
    public void actualizarBarra(){
        contenedor.actualizar();
        panel.updateUI();
        updateUI();
    }
}
