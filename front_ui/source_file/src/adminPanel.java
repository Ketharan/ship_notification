import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;



public class adminPanel extends JFrame implements ActionListener {

    private JFrame frame;
    private JButton btnGetCustomers;

    private Label lblCustomer;
    private Label lblCurrentState;
    private Label lblChangeStatus;

    private JComboBox<String> customerCombo;
    private Label lblCurrentDyn;
    private JComboBox<String> stateCombo;

    private JButton btnUpdate;

    private final String USER_AGENT = "Mozilla/5.0";

    private String[] usernameArray;
    private String[] mobnumberArray;
    private String[] stateArray;

    private dataupd dataex;

    public adminPanel() {

        frame = new JFrame("Admin portal");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 380);
        frame.setLocation(430, 100);
        addComponents();
    }

    private void addComponents() {
        JPanel panel = new JPanel();

        panel.setLayout(null);

        btnGetCustomers = new JButton("Get details");
        btnGetCustomers.setBounds(40, 20, 400, 60);
        btnGetCustomers.addActionListener(this);
        panel.add(btnGetCustomers);

        lblCustomer = new Label("Customer");
        lblCustomer.setBounds(40, 100, 120, 30);
        panel.add(lblCustomer);

        lblCurrentState = new Label("Current State");
        lblCurrentState.setBounds(40, 200, 120, 30);
        panel.add(lblCurrentState);

        lblChangeStatus = new Label("Change State");
        lblChangeStatus.setBounds(40, 240, 120, 30);
        panel.add(lblChangeStatus);

        String[] choices = {"Invoicing", "Packing", "Shipping", "Delivered"};

        customerCombo = new JComboBox<String>(choices);
        customerCombo.setBounds(170, 100, 150, 30);
        customerCombo.setMaximumSize(customerCombo.getPreferredSize());
        customerCombo.setAlignmentX(Component.CENTER_ALIGNMENT);

        customerCombo.addActionListener(this);
        panel.add(customerCombo);



        lblCurrentDyn = new Label("Change State");
        lblCurrentDyn.setBounds(170, 200, 150, 30);
        panel.add(lblCurrentDyn);

        stateCombo = new JComboBox<String>(choices);
        stateCombo.setBounds(170, 240, 150, 30);
        stateCombo.setMaximumSize(stateCombo.getPreferredSize());
        stateCombo.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(stateCombo);

        btnUpdate = new JButton("Update");
        btnUpdate.setBounds(40, 275, 400, 60);
        btnUpdate.addActionListener(this);
        panel.add(btnUpdate);

        frame.add(panel);
        frame.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String gat = "";
        if (e.getSource() == btnGetCustomers) {

            try{
                gat = getDetails();
            }catch (Exception ex){
                System.out.println(ex.toString());
            }

            stringAnaly(gat);
            customerCombo.removeAllItems();


            for (int i = 0; i < usernameArray.length;i++ ){
                customerCombo.addItem(usernameArray[i]);

            }

        }else if(e.getSource() == customerCombo){

            if (customerCombo.getSelectedIndex() > -1) {
                lblCurrentDyn.setText(stateArray[customerCombo.getSelectedIndex()]);

            }
        }else if (e.getSource() == btnUpdate){
            String username = customerCombo.getSelectedItem().toString();
            int ind = customerCombo.getSelectedIndex();
            String mobilenumber = mobnumberArray[ind];
            String status = stateCombo.getSelectedItem().toString() ;
            dataex = new dataupd(username,mobilenumber,"anchor",status);
            try{
                updateDetails();
            }catch (Exception ex){
                System.out.println(ex.toString());
            }


        }

    }

    private String getDetails() throws Exception {

        requester req = new requester("yes");

        String url = "http://localhost:9090/calculator/sentvals";
        Gson gson          = new Gson();
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);


        post.setHeader("User-Agent", USER_AGENT);

        StringEntity postingString = new StringEntity(gson.toJson(req));


        post.setEntity(postingString);
        post.setHeader("Content-type", "application/json");
        HttpResponse response = client.execute(post);

        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        return  result.toString();
    }

    public void stringAnaly(String gathered){
        String[] top =  gathered.split("&");

        usernameArray = top[0].substring(0,top[0].length()-1).split("@");
        mobnumberArray = top[1].substring(0,top[1].length()-1).split("@");
        stateArray = top[2].substring(0,top[2].length()-1).split("@");
    }

    private void updateDetails() throws Exception {

        String url = "http://localhost:9090/calculator/update";
        Gson    gson          = new Gson();
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);


        post.setHeader("User-Agent", USER_AGENT);

        StringEntity postingString = new StringEntity(gson.toJson(dataex));


        post.setEntity(postingString);
        post.setHeader("Content-type", "application/json");
        HttpResponse response = client.execute(post);

        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

    }


}