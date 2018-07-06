
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


import java.io.BufferedReader;
import java.io.InputStreamReader;


import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;


import com.google.gson.Gson;


public class placeOrder extends JFrame implements ActionListener {

    private static JButton btnPlace;
    private static JButton btnClear;
    private JTextField username;
    private JTextField mobilenumber;

    private JTextField productname;
    private final String USER_AGENT = "Mozilla/5.0";

    private String userName;
    private String mobileNumber;
    private String productName;


    public placeOrder() {

        setLocationRelativeTo(null);
        setSize(350, 260);
        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);
        setTitle("Client application");
        setVisible(true);
    }



    private void placeComponents(JPanel panel) {


        panel.setLayout(null);

        Label lblName = new Label("User name");
        lblName.setBounds(30, 20, 120, 25);
        panel.add(lblName);

        Label lblPassword = new Label("Mobile number");
        lblPassword.setBounds(30, 60, 120, 25);
        panel.add(lblPassword);

        Label lblProdut = new Label("Product name");
        lblProdut.setBounds(30, 100, 120, 25);
        panel.add(lblProdut);


        username = new JTextField(20);
        username.setBounds(170, 20, 150, 25);
        panel.add(username);

        mobilenumber = new JTextField(20);
        mobilenumber.setBounds(170, 60, 150, 25);
        panel.add(mobilenumber);

        productname = new JTextField(20);
        productname.setBounds(170, 100, 150, 25);
        panel.add(productname);


        btnPlace = new JButton("Place");
        btnPlace.setBounds(60, 180, 100, 25);
        btnPlace.addActionListener(this);
        panel.add(btnPlace);

        btnClear = new JButton("Clear");
        btnClear.setBounds(180, 180, 100, 25);
        btnClear.addActionListener(this);
        panel.add(btnClear);
    }


    private void sendOrder() throws Exception {

        data details = new data(userName,mobileNumber,productName);

        String url = "http://localhost:9090/calculator/operation";
        Gson gson = new Gson();
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);


        post.setHeader("User-Agent", USER_AGENT);

        StringEntity postingString = new StringEntity(gson.toJson(details));


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


    @Override
    public void actionPerformed(ActionEvent e){

        if (e.getSource() == btnPlace) {
            userName = username.getText();
            mobileNumber = mobilenumber.getText();
            productName = productname.getText();

            try{
                sendOrder();
            }catch (Exception ex){
                System.out.println(ex.toString());
            }
        }



    }

}
