package com.example.dannykamo.shopapp.items;

public class ProdItem {
    String prodName,prodDesc;
    int userId;

    public ProdItem(int userId, String prodName, String prodDesc) {
        this.userId = userId;
        this.prodName = prodName;
        this.prodDesc = prodDesc;
    }

    public int getUserId() {
        return userId;
    }

    public String getProdName() {
        return prodName;
    }

    public String getProdDesc() {
        return prodDesc;
    }
}
