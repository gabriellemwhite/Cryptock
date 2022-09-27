package com.gabriellewhite.cryptock.ui.favorites.models;

public class CryptoFavoritesModel {

    private String symbol;

    private CryptoFavoritesModel() {}

    private CryptoFavoritesModel(String symbol){
        this.symbol = symbol;
    }

    public String getSymbol(){
        return symbol;
    }

}
