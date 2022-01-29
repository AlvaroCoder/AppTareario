package Frontend.Componentes;

import Frontend.BarraInferior;
import Frontend.BarraSuperior;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;

public class ContenedorBarra extends JPanel{
    BarraInferior barraInf;
    BarraSuperior barraSup;
    GridBagConstraints gbcBarra;
    public ContenedorBarra(){
        setLayout(new GridBagLayout());
        setBackground(Color.white);
        setMinimumSize(new Dimension(600,80));
        
        barraInf = new BarraInferior();
        barraSup = new BarraSuperior();
        
        gbcBarra = new GridBagConstraints();
        gbcBarra.fill = GridBagConstraints.HORIZONTAL;
        gbcBarra.gridx = 0;
        gbcBarra.weightx = 1;
        gbcBarra.gridy = 0;
        add(barraSup,gbcBarra);
        gbcBarra.gridy = 1;
        add(barraInf,gbcBarra);
    }
    public void actualizar(){
        barraInf.actualizar();
        barraSup.actualizar();
    }
}
