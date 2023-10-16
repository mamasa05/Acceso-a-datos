package es.florida.Ad_ejercicios_T1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Controlador {
    private Vista vista;
    private Modelo modelo;

    public Controlador(Vista vista, Modelo modelo) {
        this.vista = vista;
        this.modelo = modelo;
        this.vista.setBuscarButtonListener(new BuscarButtonListener());
        this.vista.setFusionarButtonListener(new FusionarButtonListener());
    }

    private class BuscarButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
             
        }
    }

    private class FusionarButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
           
        }
    }
}










