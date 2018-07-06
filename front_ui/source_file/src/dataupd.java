public class dataupd {
    private String username;
    private String mobilenumber;
    private String productname;
    private String status;

    public dataupd(String username, String mobilenumber, String productname, String status) {
        this.username = username;
        this.mobilenumber = mobilenumber;
        this.productname = productname;
        this.status = status;
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
