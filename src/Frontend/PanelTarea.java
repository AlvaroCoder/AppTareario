package Frontend;

import Frontend.Componentes.ContenedorBarra;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class PanelTarea extends JPanel{
    JScrollPane scroll;
    GridBagLayout gbl;
    GridBagConstraints gbc;
    
    TablaTareas tabla;
    ContenedorBarra barra;
    
    public PanelTarea(){
        gbl = new GridBagLayout();
        setLayout(gbl);
        setBackground(Color.white);
        agregarComponentes();
    }
    public void agregarComponentes(){
        barra = new ContenedorBarra();
        tabla = new TablaTareas();
        tabla.setContenedorBarra(barra);
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 0.1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        
        add(barra, gbc);
        gbc.weighty = 0.9;
        gbc.gridy = 1;
        add(tabla,gbc);
        updateUI();
    }
    public void actualizar(){
        barra.actualizar();
        barra.updateUI();
        tabla.agregarTarea();
    }
}
