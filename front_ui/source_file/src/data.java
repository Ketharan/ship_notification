
public class data {
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobilenumber() {
        return mobilenumber;
    }

    public void setMobilenumber(String mobilenumber) {
        this.mobilenumber = mobilenumber;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    private String username;
    private String mobilenumber;
    private String productname;


    public data(String username,String mobilenumber,String productname){
        this.username = username;
        this.mobilenumber = mobilenumber;
        this.productname = productname;
    }
}
