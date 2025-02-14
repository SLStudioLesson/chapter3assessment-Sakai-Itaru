package com.recipeapp.datahandler;
import java.io.IOException;
import java.util.ArrayList;

import com.recipeapp.model.Recipe;

public class JSONDataHandler implements DataHandler {
    
    // 現在のモードを返す
    @Override
    public String getMode() {
        String s = "JSON";
        return s;
    }

    // レシピデータを読み込み、Recipeオブジェクトのリストとして返します。
    @Override
    public ArrayList<Recipe> readData() throws IOException {
        return null;
    }


    // 指定されたRecipeオブジェクトを追加します。
    @Override
    public void writeData(Recipe recipe) throws IOException {

    }

    // 指定されたキーワードでレシピを検索し、一致するRecipeオブジェクトのリストを返します。
    @Override
    public ArrayList<Recipe> searchData() throws IOException {
        return null;
    }
}
