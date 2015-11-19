package salao.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public  class Exemplos implements ActionListener {
       JFrame janela = new JFrame();

       JPanel painel = new JPanel();
       JLabel rotulo1 = new JLabel("Escolha um time: ");
       JLabel rotulo2 = new JLabel("Escolha um Esporte: ");
       JLabel rotuloVazio = new JLabel();

       DefaultListModel listModel = new DefaultListModel();
       JList lista = new JList(listModel);
       JComboBox combo = new JComboBox();
       JButton botao = new JButton("OK");

       public static void main (String args[]){
               new Exemplos();
       }

       private Exemplos(){

               janela.setTitle("Exemplos");
               janela.setSize(300, 200);
               janela.setLocation(50, 50);
               janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               //painel.setLayout(new GridLayout(3, 2));
               painel.add(rotulo1);
               listModel.addElement("Atlético");
               listModel.addElement("Cruzeiro");
               listModel.addElement("Flamengo");

               painel.add(lista);
               painel.add(rotulo2);
               combo.addItem("Muay Thai");
               combo.addItem("Tae Kwon do");
               combo.addItem("Futebol");

               painel.add(combo);

               painel.add(rotuloVazio);
               botao.addActionListener(this);
               painel.add(botao);

               janela.add(painel);

               janela.setVisible(true);
       }

       public void actionPerformed(ActionEvent e) {
               JOptionPane.showMessageDialog(null,combo.getSelectedItem());
               JOptionPane.showMessageDialog(null,lista.getSelectedValue());

       }
       
}