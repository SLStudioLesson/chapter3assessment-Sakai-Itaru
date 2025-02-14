package com.recipeapp.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.recipeapp.datahandler.DataHandler;
import com.recipeapp.model.Ingredient;
import com.recipeapp.model.Recipe;

public class RecipeUI {
    private BufferedReader reader;
    private DataHandler dataHandler;

    public RecipeUI(DataHandler dataHandler) {
        reader = new BufferedReader(new InputStreamReader(System.in));
        this.dataHandler = dataHandler;
    }

    public void displayMenu() {

        System.out.println("Current mode: " + dataHandler.getMode());

        while (true) {
            try {
                System.out.println();
                System.out.println("Main Menu:");
                System.out.println("1: Display Recipes");
                System.out.println("2: Add New Recipe");
                System.out.println("3: Search Recipe");
                System.out.println("4: Exit Application");
                System.out.print("Please choose an option: ");

                String choice = reader.readLine();

                switch (choice) {
                    case "1":
                        displayRecipes();
                        break;
                    case "2":
                        addNewRecipe();
                        break;
                    case "3":
                        break;
                    case "4":
                        System.out.println("Exiting the application.");
                        return;
                    default:
                        System.out.println("Invalid choice. Please select again.");
                        break;
                }
            } catch (IOException e) {
                System.out.println("Error reading input from user: " + e.getMessage());
            }
        }
    }

    // DataHandlerから読み込んだレシピデータを整形してコンソールに表示します。
    // IOExceptionを受け取った場合はError reading file: 例外のメッセージとコンソールに表示します
    private void displayRecipes() {
        try {
            // 読み込んだレシピがあれば
            if (!dataHandler.readData().isEmpty()) {
                ArrayList<Recipe> recipes = dataHandler.readData();
                System.out.println("Recipes: ");
                System.out.println("-----------------------------------");
                // 出力
                for (Recipe recipe : recipes) {
                    System.out.print("Recipe Name: ");
                    System.out.println(recipe.getName());
                    System.out.print("Main Ingredients: ");
                    for (int i = 0; i < recipe.getIngredients().size(); i++) {
                        Ingredient ingredient = recipe.getIngredients().get(i);
                        System.out.print(ingredient.getName());
                        if (i < recipe.getIngredients().size() - 1) {
                            System.out.print(", ");
                        }
                    }
                    System.out.println();
                    System.out.println("-----------------------------------");
                }
            } else {
                System.out.println("No recipes available.");
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    // ユーザーからレシピ名と主な材料を入力させ、DataHandlerを使用してrecipes.csvに新しいレシピを追加します。
    // 材料の入力はdoneと入力するまで入力を受け付けます。
    private void addNewRecipe() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));) {
            System.out.println("Adding a new recipe. ");
            System.out.print("Enter recipe name: ");
            String name = reader.readLine();

            System.out.println("Enter ingredients (type 'done' when finished): ");
            ArrayList<Ingredient> ingredients = new ArrayList<>();
            while (true) {
                System.out.print("Ingredient: ");
                String s = reader.readLine();
                // 入力された文字がdoneならループ抜ける
                if (s.equals("done")) {
                    break;
                }
                ingredients.add(new Ingredient(s));
            }

            // 入力された情報をもとにRecipeインスタンス生成
            Recipe recipe = new Recipe(name, ingredients);
            // Recipeインスタンスを渡して書き込み
            dataHandler.writeData(recipe);

            System.out.print("Recipe added successfully.");
        } catch (IOException e) {
            System.out.println("Failed to add new recipe: " + e.getMessage());
        }

    }

    // ユーザーから検索クエリを入力させ、DataHandlerから検索結果を受け取り、受け取った結果をコンソールに表示します。
    // 一致するレシピがある場合は、そのレシピの名前と主な材料をコンソールに表示します。
    // 一致するレシピがない場合は、No matching recipes found.とコンソールに出力します。
    private void searchRecipe() {

    }
}
