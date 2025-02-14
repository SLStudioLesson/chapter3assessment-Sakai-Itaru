package com.recipeapp.model;
import java.util.ArrayList;

public class Recipe {
    private String name; // レシピの名前
    private ArrayList<Ingredient> ingredients = new ArrayList<>(); // レシピの材料リスト

    // コンストラクタ
    public Recipe(String name, ArrayList<Ingredient> ingredients) {
        this.name = name;
        this.ingredients = ingredients;
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<Ingredient> getIngredients() {
        return this.ingredients;
    }

}
