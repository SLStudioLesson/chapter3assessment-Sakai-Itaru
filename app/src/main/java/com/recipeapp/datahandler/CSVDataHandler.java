package com.recipeapp.datahandler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.recipeapp.model.Ingredient;
import com.recipeapp.model.Recipe;

public class CSVDataHandler implements DataHandler {
    private String filePath; // レシピデータを格納するCSVファイルのパス。

    // コンストラクタ
    public CSVDataHandler() {
        this.filePath = "app/src/main/resources/recipes.csv";
    }

    // コンストラクタ
    public CSVDataHandler(String filePath) {
        this.filePath = filePath;
    }

    // 現在のモードを返す
    @Override
    public String getMode() {
        String s = "CSV";
        return s;
    }

    // レシピデータを読み込み、Recipeオブジェクトのリストとして返します。
    @Override
    public ArrayList<Recipe> readData() throws IOException {
        ArrayList<Recipe> recipes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(this.filePath))) {
            String line = "";
            while ((line = reader.readLine()) != null) {
                if ((line = reader.readLine()) == null) {
                    return null;
                }
                // 文字列をカンマで区切る
                String[] lines = line.split(",");
                // 一つ目の要素はレシピ名
                String name = lines[0];
                // 二つ目以降は、材料。Ingredient型のリストに入れる
                ArrayList<Ingredient> ingredients = new ArrayList<>();
                for (int i = 1; i < lines.length; i++) {
                    ingredients.add(new Ingredient(lines[i]));
                }
                // 整理した、データをもとにインスタンス生成
                Recipe recipe = new Recipe(name, ingredients);
                recipes.add(recipe);
            }
            return recipes;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    // 新しいレシピをrecipes.csvに追加します。
    // レシピ名と材料はカンマ区切りで1行としてファイルに書き込まれます。
    @Override
    public void writeData(Recipe recipe) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.filePath, true));) {
            // レシピ名書き込み
            writer.write(recipe.getName() + ", ");
            // 材料書き込み
            for (int i = 0; i < recipe.getIngredients().size(); i++) {
                Ingredient ingredient = recipe.getIngredients().get(i);
                writer.write(ingredient.getName());
                // 最後以外には", "つける
                if (i < recipe.getIngredients().size() - 1) {
                    writer.write(", ");
                }
            }
            writer.newLine(); // 書き込み後に改行する
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
        
    }

    // 検索クエリを受け取り、そのクエリに基づいてレシピを検索し、一致するレシピを返します。
    // 検索クエリはnameとingredientのキーをサポートし、&で複数の条件を組み合わせることができます。
    // （例: name=Soup&ingredient=Tomatoは、名前に"Soup"を含みかつ材料に"Tomato"を含むレシピを検索します。）
    // 検索クエリがnameのみ、またはingredientのみの時も検索を行うものとします。
    // （例： name=Soupが入力されたときは、名前に"Soup"を含むレシピを検索します。）
    @Override
    public ArrayList<Recipe> searchData() throws IOException {
        return null;
    }

}
